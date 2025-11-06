package zadatak2;

public class Waiter extends Employee {
    private double prekovremeniSati; // weekly overtime hours

    public Waiter(int id, String firstName, String lastName, double plataPoSatu, double ukupanBrojSati, double prekovremeniSati) {
        super(id, firstName, lastName, plataPoSatu, ukupanBrojSati);
        this.prekovremeniSati = prekovremeniSati;
    }

    public double getPrekovremeniSati() { return prekovremeniSati; }
    public void setPrekovremeniSati(double prekovremeniSati) { this.prekovremeniSati = prekovremeniSati; }

    @Override
    public double calculateMonthlySalary() {
        double regularWeekly = ukupanBrojSati - prekovremeniSati;
        if (regularWeekly < 0) regularWeekly = 0;
        double weekly = regularWeekly * plataPoSatu + prekovremeniSati * plataPoSatu * 1.2;
        return 4 * weekly;
    }

    @Override
    public String getType() {
        return "Konobar";
    }
}
