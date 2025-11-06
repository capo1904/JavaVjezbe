package zadatak2;

public class PayrollRecord {
    private int month;
    private int year;
    private Employee employee;
    private double amount;
    private String notes;

    public PayrollRecord(int month, int year, Employee employee, double amount, String notes) {
        this.month = month;
        this.year = year;
        this.employee = employee;
        this.amount = amount;
        this.notes = notes;
    }

    public int getMonth() { return month; }
    public int getYear() { return year; }
    public Employee getEmployee() { return employee; }
    public double getAmount() { return amount; }
    public String getNotes() { return notes; }

    @Override
    public String toString() {
        return String.format("%d/%d - %s %s: %.2f EUR (%s)", month, year, employee.getFirstName(), employee.getLastName(), amount, notes == null ? "" : notes);
    }
}
