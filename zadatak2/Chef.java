package zadatak2;

public class Chef extends Employee {
    private static final double FIXED = 1500.0;

    public Chef(int id, String firstName, String lastName, double plataPoSatu, double ukupanBrojSati) {
        super(id, firstName, lastName, plataPoSatu, ukupanBrojSati);
    }

    @Override
    public double calculateMonthlySalary() {
        return FIXED + 4 * ukupanBrojSati * plataPoSatu;
    }

    @Override
    public String getType() {
        return "Kuvar";
    }
}