package zadatak2;

public class DrugiZad {
    public static void main(String[] args) {
        Restaurant r = new Restaurant("MojRestoran", "Bulevar 1", "123456789");

        
        Waiter w1 = new Waiter(1, "Petar", "Petrovic", 8.0, 40.0, 5.0); 
        Waiter w2 = new Waiter(2, "Ana", "Markovic", 7.5, 38.0, 0.0);
        Chef c1 = new Chef(3, "Ivan", "Ivic", 12.0, 40.0);
        Manager m1 = new Manager(4, "Elena", "Kankaras", 40.0, 15.0, 300.0); 
    
        Chef c2 = new Chef(5, "Milos", "Milosevic", 11.0, 36.0);

        r.addEmployee(w1);
        r.addEmployee(w2);
        r.addEmployee(c1);
        r.addEmployee(m1);
        r.addEmployee(c2);
        r.printPayroll(11, 2025);
    }
}