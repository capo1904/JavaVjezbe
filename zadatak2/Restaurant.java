package zadatak2;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private String pib;
    private List<Employee> employees;

    public Restaurant(String name, String address, String pib) {
        this.name = name;
        this.address = address;
        this.pib = pib;
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public boolean removeEmployeeById(int id) {
        Employee e = findEmployeeById(id);
        if (e != null) {
            employees.remove(e);
            return true;
        }
        return false;
    }

    public Employee findEmployeeById(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    public List<PayrollRecord> generateMonthlyPayroll(int month, int year) {
        List<PayrollRecord> records = new ArrayList<>();
        for (Employee e : employees) {
            double amount = e.calculateMonthlySalary();
            String notes = "";
            if (e instanceof Waiter) {
                Waiter w = (Waiter) e;
                if (w.getPrekovremeniSati() > 0) notes = "ura?unat prekovremeni rad";
            } else if (e instanceof Manager) {
                Manager m = (Manager) e;
                if (m.getBonus() > 0) notes = "bonus: " + String.format("%.2f EUR", m.getBonus());
            }
            records.add(new PayrollRecord(month, year, e, amount, notes));
        }
        return records;
    }

    public double getTotalMonthlyCost(int month, int year) {
        double total = 0.0;
        for (PayrollRecord r : generateMonthlyPayroll(month, year)) {
            total += r.getAmount();
        }
        return total;
    }

    public void printPayroll(int month, int year) {
        List<PayrollRecord> records = generateMonthlyPayroll(month, year);
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%4s | %-15s | %-10s | %-6s | %-12s | %10s\n", "ID", "Ime i prezime", "Tip", "Sati", "Prek/Bonus", "Plata(EUR)");
        System.out.println("--------------------------------------------------------------------------");
        for (PayrollRecord r : records) {
            Employee e = r.getEmployee();
            String type = e.getType();
            String extra = r.getNotes();
            if (extra == null) extra = "";
            double hours = e.getUkupanBrojSati();
            // include overtime hours for waiters
            if (e instanceof Waiter) {
                Waiter w = (Waiter) e;
                extra = String.format("OT: %.1f", w.getPrekovremeniSati());
            } else if (e instanceof Manager) {
                Manager m = (Manager) e;
                extra = String.format("BON: %.2f", m.getBonus());
            }
            System.out.printf("%4d | %-15s | %-10s | %6.1f | %-12s | %10.2f\n",
                    e.getId(), e.getFullName(), type, hours, extra, r.getAmount());
        }
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("Ukupni trosak plata za %d/%d: %.2f EUR\n", month, year, getTotalMonthlyCost(month, year));
    }
}
