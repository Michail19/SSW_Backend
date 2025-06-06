package com.ms.ssw.backend.service;

import com.ms.ssw.backend.model.*;
import com.ms.ssw.backend.repository.EmployeeRepository;
import com.ms.ssw.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    private EmployeeRepository employeeDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    public SchedulePageResponseDTO getFullSchedulePage() {
        List<Employee> detailsList = employeeDetailsRepository.findAll();

        List<EmployeeDetailsResponseDTO> employeeDTOs = detailsList.stream()
                .map(this::convertToDTO)
                .toList();

        String currentWeek = formatCurrentWeek(LocalDate.now());

        return new SchedulePageResponseDTO(currentWeek, employeeDTOs);
    }

    private String formatCurrentWeek(LocalDate today) {
        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate sunday = today.with(DayOfWeek.SUNDAY);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
        String startDay = monday.format(formatter);
        String endDay = sunday.format(formatter);

        String month = monday.getMonth().getDisplayName(TextStyle.FULL, new Locale("en")).toLowerCase();
        int year = monday.getYear();

        return String.format("%s-%s %s %d", startDay, endDay, month, year);
    }

    private EmployeeDetailsResponseDTO convertToDTO(Employee details) {
        String projects = details.getProjects().stream()
                .map(Project::getName)
                .collect(Collectors.joining(" "));

        // Получаем расписание для текущей недели
        LocalDate currentWeekStart = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        WeekSchedule schedule = details.getWeekSchedules().stream()
                .filter(ws -> ws.getStartOfWeek().equals(currentWeekStart))
                .findFirst()
                .orElse(null);

        WeekScheduleDTO scheduleDTO = toDto(schedule);

        return new EmployeeDetailsResponseDTO(
                details.getId(),
                details.getFio(),
                projects,
                scheduleDTO
        );
    }

    private WeekScheduleDTO toDto(WeekSchedule entity) {
        if (entity == null) {
            return createEmptySchedule(); // Возвращаем пустое расписание
        }

        WeekScheduleDTO dto = new WeekScheduleDTO();

        // Для каждого дня проверяем наличие DaySchedule
        dto.setMonday(createDayScheduleDTO(entity.getMonday()));
        dto.setTuesday(createDayScheduleDTO(entity.getTuesday()));
        dto.setWednesday(createDayScheduleDTO(entity.getWednesday()));
        dto.setThursday(createDayScheduleDTO(entity.getThursday()));
        dto.setFriday(createDayScheduleDTO(entity.getFriday()));
        dto.setSaturday(createDayScheduleDTO(entity.getSaturday()));
        dto.setSunday(createDayScheduleDTO(entity.getSunday()));

        return dto;
    }

    private DayScheduleDTO createDayScheduleDTO(DaySchedule daySchedule) {
        if (daySchedule == null) {
            return new DayScheduleDTO(null, null); // Или установите значения по умолчанию
        }
        return new DayScheduleDTO(daySchedule.getStart(), daySchedule.getEnd());
    }

    private WeekScheduleDTO createEmptySchedule() {
        WeekScheduleDTO dto = new WeekScheduleDTO();
        dto.setMonday(new DayScheduleDTO(null, null));
        dto.setTuesday(new DayScheduleDTO(null, null));
        dto.setWednesday(new DayScheduleDTO(null, null));
        dto.setThursday(new DayScheduleDTO(null, null));
        dto.setFriday(new DayScheduleDTO(null, null));
        dto.setSaturday(new DayScheduleDTO(null, null));
        dto.setSunday(new DayScheduleDTO(null, null));
        return dto;
    }

    @Transactional
    public void updateSchedules(List<ScheduleUpdateRequest> requestList) {
        for (ScheduleUpdateRequest request : requestList) {
            Employee employee = employeeDetailsRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));

            // Считываем новый запрос расписания
            WeekScheduleDTO dto = request.getSchedule();

            // Проверяем, если у сотрудника нет расписания, создаем новое
            WeekSchedule schedule = employee.getWeekSchedules().stream()
                    .filter(ws -> ws.getStartOfWeek().equals(request.getWeekStart()))
                    .findFirst()
                    .orElseGet(() -> {
                        WeekSchedule newSchedule = new WeekSchedule();
                        newSchedule.setEmployee(employee);
                        newSchedule.setStartOfWeek(request.getWeekStart());
                        employee.getWeekSchedules().add(newSchedule);
                        return newSchedule;
                    });

            LocalDate startOfWeek = request.getWeekStart().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

            // Устанавливаем дату начала недели
            schedule.setStartOfWeek(startOfWeek);

            // Обновляем дни недели
            if (dto.getMonday() != null) schedule.setMonday(convertToEntity(dto.getMonday()));
            if (dto.getTuesday() != null) schedule.setTuesday(convertToEntity(dto.getTuesday()));
            if (dto.getWednesday() != null) schedule.setWednesday(convertToEntity(dto.getWednesday()));
            if (dto.getThursday() != null) schedule.setThursday(convertToEntity(dto.getThursday()));
            if (dto.getFriday() != null) schedule.setFriday(convertToEntity(dto.getFriday()));
            if (dto.getSaturday() != null) schedule.setSaturday(convertToEntity(dto.getSaturday()));
            if (dto.getSunday() != null) schedule.setSunday(convertToEntity(dto.getSunday()));

            // Сохраняем сотрудника с обновленным расписанием
            employeeDetailsRepository.save(employee);
        }
    }


    private DaySchedule convertToEntity(DayScheduleDTO range) {
//        if (range == null) return new DaySchedule("-1", "-1");
        if (range.getStart() == null || range.getEnd() == null)
            return null;

        DaySchedule ds = new DaySchedule();
        ds.setStart(range.getStart());
        ds.setEnd(range.getEnd());
        return ds;
    }

    @Transactional
    public Long addNewEmployee(EmployeeDTO employee) {
        if (userRepository.findByUsername(employee.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Пользователь с таким username уже существует");
        }

        Employee toEmployee = new Employee();
        User user = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

        // Создаем расписание для текущей недели
        WeekSchedule weekSchedule = new WeekSchedule();
        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        weekSchedule.setStartOfWeek(startOfWeek);
        weekSchedule.setEmployee(toEmployee);

        // Устанавливаем дни
        weekSchedule.setMonday(convertToEntity(employee.getWeekSchedule().getMonday()));
        weekSchedule.setMonday(convertToEntity(employee.getWeekSchedule().getMonday()));
        weekSchedule.setTuesday(convertToEntity(employee.getWeekSchedule().getTuesday()));
        weekSchedule.setWednesday(convertToEntity(employee.getWeekSchedule().getWednesday()));
        weekSchedule.setThursday(convertToEntity(employee.getWeekSchedule().getThursday()));
        weekSchedule.setFriday(convertToEntity(employee.getWeekSchedule().getFriday()));
        weekSchedule.setSaturday(convertToEntity(employee.getWeekSchedule().getSaturday()));
        weekSchedule.setSunday(convertToEntity(employee.getWeekSchedule().getSunday()));

        toEmployee.getWeekSchedules().add(weekSchedule);
        toEmployee.setFio(employee.getFio());

        // Остальная логика создания пользователя
        user.setUsername(employee.getUsername());
        user.setPassword(passwordEncoder.encode(employee.getPassword()));
        user.setLevel(AccessLevel.valueOf(employee.getLevel().toUpperCase()));

        toEmployee.setUser(user);
        user.setEmployee(toEmployee);

        employeeDetailsRepository.save(toEmployee);
        userRepository.save(user);

        return toEmployee.getId(); // <<< Возвращаем сгенерированный id
    }

//    @Transactional
//    public void deleteEmployeeById(Long employeeId) {
//        Employee employee = employeeDetailsRepository.getReferenceById(employeeId);
//
//        userRepository.findByEmployeeId(employeeId).ifPresent(user -> {
//            userRepository.delete((User) user);
//        });
//
//        employee.getWeekSchedule().setMonday(null);
//        employee.getWeekSchedule().setTuesday(null);
//        employee.getWeekSchedule().setWednesday(null);
//        employee.getWeekSchedule().setThursday(null);
//        employee.getWeekSchedule().setFriday(null);
//        employee.getWeekSchedule().setSaturday(null);
//        employee.getWeekSchedule().setSunday(null);
//        employee.setWeekSchedule(null);
//
//        employeeDetailsRepository.deleteById(employeeId);
//    }

    @Transactional
    public void deleteEmployeeById(Long employeeId) {
        Employee employee = employeeDetailsRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Сотрудник не найден"));

        // Удалить связанные расписания
        employee.setWeekSchedules(null);
//        for (WeekSchedule schedule : employee.getWeekSchedules()) {
//            schedule.setMonday(null);
//            schedule.setTuesday(null);
//            schedule.setWednesday(null);
//            schedule.setThursday(null);
//            schedule.setFriday(null);
//            schedule.setSaturday(null);
//            schedule.setSunday(null);
//        }

        // Удалить User
        if (employee.getUser() != null) {
            userRepository.delete(employee.getUser());
        }

        employeeDetailsRepository.delete(employee);
    }

    @Transactional
    public SchedulePageResponseDTO getFullSchedulePageForWeek(LocalDate anyDayOfWeek, Long requesterUserId) {
        LocalDate monday = anyDayOfWeek.with(DayOfWeek.MONDAY);
        List<Employee> allEmployees = employeeDetailsRepository.findAll();

        Optional<Employee> requesterEmployeeOpt = getCurrentEmployee(requesterUserId);
        List<Employee> sortedEmployees = new ArrayList<>();
        requesterEmployeeOpt.ifPresent(sortedEmployees::add);
        sortedEmployees.addAll(allEmployees.stream()
                .filter(emp -> !emp.equals(requesterEmployeeOpt.orElse(null)))
                .toList());

        List<EmployeeDetailsResponseDTO> employeeDTOs = sortedEmployees.stream()
                .map(employee -> {
                    // Ищем или создаем новое расписание для запрошенной недели
                    WeekSchedule schedule = employee.getWeekSchedules().stream()
                            .filter(ws -> ws.getStartOfWeek().equals(monday))
                            .findFirst()
                            .orElseGet(() -> createNewWeekSchedule(employee, monday));

                    return convertToDTO(employee, schedule); // Модифицированный метод, принимающий schedule
                })
                .toList();

        return new SchedulePageResponseDTO(formatWeekRange(monday), employeeDTOs);
    }

    private WeekSchedule createNewWeekSchedule(Employee employee, LocalDate monday) {
        WeekSchedule newSchedule = new WeekSchedule();
        newSchedule.setStartOfWeek(monday);
        newSchedule.setEmployee(employee);

        // Можно скопировать расписание из предыдущей недели или оставить пустым
        employee.getWeekSchedules().add(newSchedule);
        employeeDetailsRepository.save(employee); // Сохраняем изменения

        return newSchedule;
    }

    // Модифицированный метод convertToDTO, принимающий schedule
    private EmployeeDetailsResponseDTO convertToDTO(Employee employee, WeekSchedule schedule) {
        WeekScheduleDTO scheduleDTO = toDto(schedule);

        return new EmployeeDetailsResponseDTO(
                employee.getId(),
                employee.getFio(),
                employee.getProjects().stream()
                        .map(Project::getName)
                        .collect(Collectors.joining(" ")),
                scheduleDTO
        );
    }

    public Optional<Employee> getCurrentEmployee(Long userId) {
        return userRepository.findById(userId).map(User::getEmployee);
    }

    private boolean isSameWeek(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) return false;
        return date1.get(WeekFields.ISO.weekOfYear()) == date2.get(WeekFields.ISO.weekOfYear())
                && date1.getYear() == date2.getYear();
    }

    private EmployeeDetailsResponseDTO convertToDTOWithEmptySchedule(Employee employee) {
        return new EmployeeDetailsResponseDTO(
                employee.getId(),
                employee.getFio(),
                employee.getProjects().stream()
                        .map(Project::getName)
                        .collect(Collectors.joining(" ")),
                createEmptySchedule()
        );
    }

    private String formatWeekRange(LocalDate monday) {
        LocalDate sunday = monday.plusDays(6);
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
        String month = monday.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toLowerCase();
        int year = monday.getYear();

        return String.format("%s-%s %s %d",
                monday.format(dayFormatter),
                sunday.format(dayFormatter),
                month,
                year);
    }
}
