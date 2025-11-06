package zadatak2;

public class Manager extends Employee {
    private double bonus;
    private static final double BASE = 1300.0;

    public Manager(int id, String firstName, String lastName, double plataPoSatu, double ukupanBrojSati, double bonus) {
        super(id, firstName, lastName, plataPoSatu, ukupanBrojSati);
        this.bonus = bonus;
    }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    public double calculateMonthlySalary() {
        return BASE + 4 * ukupanBrojSati * plataPoSatu + bonus;
    }

    public String getType() {
        return "Menadzer";
    }
}
