package zadatak2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Shift {
    public enum ShiftType { JUTARNJA, POPODNEVNA, NOCNA }

    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private ShiftType type;
    private List<Employee> employees;

    public Shift(LocalDate date, LocalTime start, LocalTime end, ShiftType type) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.type = type;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee e) { employees.add(e); }
    public void removeEmployee(Employee e) { employees.remove(e); }
    public List<Employee> getEmployees() { return employees; }

    public double getDurationHours() {
        Duration d = Duration.between(start, end);
        long minutes = d.toMinutes();
        return minutes / 60.0;
    }
    @Override
    public String toString() {
        return String.format("%s %s-%s %s employees=%d", date, start, end, type, employees.size());
    }
}