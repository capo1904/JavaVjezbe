package projekat2;

public class Main {
    public static void main(String[] args) {

        Player p = new Player("lUKa caRApic", 10, 5, 32, 32, 85);
        Game game = new Game(p);
        Enemy e1 = new Enemy("Orc", 12, 5, 16, 16, 20);
        game.addEnemy(e1);
        String line = "Goblin; 12,5;16x16;20";
        String[] parts = line.split(";");

        String type = parts[0].trim(); // Goblin
        int x = Integer.parseInt(parts[1].split(",")[0].trim()); // 12
        int width = Integer.parseInt(parts[2].split("x")[0].trim()); // 16
        int height = Integer.parseInt(parts[2].split("x")[1].trim()); // 16
        int damage = Integer.parseInt(parts[3].trim()); // 20

        Enemy e2 = new Enemy(type, x, 5, width, height, damage);
        game.addEnemy(e2);

        System.out.println("Lista neprijatelja");
        game.printEnemies();

        System.out.println("Pretraga po tipu (gob)");
        for (Enemy e : game.findByType("gob")) {
            System.out.println(e);
        }

        System.out.println("Stanje igrača prije kolizije");
        System.out.println(p);

        game.resolveCollisions();

        System.out.println("Stanje igrača poslije kolizije");
        System.out.println(p);

        System.out.println();
        game.showEventLog();
    }
}
