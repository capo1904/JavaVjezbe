package zadatak2;

public abstract class Employee {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected double plataPoSatu; 
    protected double ukupanBrojSati; 

    public Employee(int id, String firstName, String lastName, double plataPoSatu, double ukupanBrojSati) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.plataPoSatu = plataPoSatu;
        this.ukupanBrojSati = ukupanBrojSati;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getPlataPoSatu() { return plataPoSatu; }
    public double getUkupanBrojSati() { return ukupanBrojSati; }

    public void setPlataPoSatu(double plataPoSatu) { this.plataPoSatu = plataPoSatu; }
    public void setUkupanBrojSati(double ukupanBrojSati) { this.ukupanBrojSati = ukupanBrojSati; }

    public String getFullName() { return firstName + " " + lastName; }

    public abstract double calculateMonthlySalary();
    public abstract String getType();
}
