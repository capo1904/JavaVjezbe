package projekat5.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.nio.file.Paths;
import java.util.List;

public class GameGUI {
    private JFrame frame;
    private JTextField nameField;
    private JTextField healthField;
    private JTextField xField;
    private JTextField yField;
    private JComboBox<String> shapeBox;
    private JTextArea outputArea;
    private Game game = new Game();

    public GameGUI() {
        frame = new JFrame("Projekat5 - Simple Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        JPanel input = new JPanel(new GridLayout(6,2));
        input.add(new JLabel("Name:"));
        nameField = new JTextField();
        input.add(nameField);

        input.add(new JLabel("Health (0-100):"));
        healthField = new JTextField("100");
        input.add(healthField);

        input.add(new JLabel("X:"));
        xField = new JTextField("50");
        input.add(xField);

        input.add(new JLabel("Y:"));
        yField = new JTextField("50");
        input.add(yField);

        input.add(new JLabel("Collider:"));
        shapeBox = new JComboBox<>(new String[]{"Rectangle", "Circle"});
        input.add(shapeBox);

        JButton runBtn = new JButton("Pokreni igru");
        runBtn.addActionListener(this::onRun);
        input.add(runBtn);

        JButton chooseBtn = new JButton("Izaberi CSV... (optional)");
        chooseBtn.addActionListener(e -> onChooseCSV());
        input.add(chooseBtn);

        frame.add(input, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private String chosenCSV = null;

    private void onChooseCSV() {
        JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(frame);
        if (res == JFileChooser.APPROVE_OPTION) {
            chosenCSV = fc.getSelectedFile().getAbsolutePath();
            JOptionPane.showMessageDialog(frame, "Selected: " + chosenCSV);
        }
    }

    private void onRun(ActionEvent ev) {
        try {
            String name = nameField.getText();
            int health = Integer.parseInt(healthField.getText().trim());
            int x = Integer.parseInt(xField.getText().trim());
            int y = Integer.parseInt(yField.getText().trim());
            Collidable collider;
            if ("Rectangle".equals(shapeBox.getSelectedItem())) {
                collider = new RectangleCollider(x, y, 32, 32);
            } else {
                collider = new CircleCollider(x, y, 16);
            }
            Player p = new Player(name, health, x, y, collider);
            game = new Game();
            game.setPlayer(p);

            String csvPath = chosenCSV;
            if (csvPath == null) {
                csvPath = Paths.get("src", "projekat5", "game", "enemies.csv").toString();
            }

            // Use tolerant loader (clamp out-of-range values) so GUI doesn't crash on the provided CSV
            List<Enemy> loaded = Game.loadEnemiesFromCSV(csvPath, true);
            for (Enemy e : loaded) {
                game.addEnemy(e);
            }

            game.resolveCollisions();

            // build output
            StringBuilder sb = new StringBuilder();
            sb.append("Player status:\n");
            sb.append(game.getPlayer().toString()).append("\n\n");
            sb.append("Enemies:\n");
            for (Enemy e : game.getEnemies()) sb.append(e.toString()).append("\n");
            sb.append("\nColliding with player:\n");
            for (Enemy e : game.collidingWithPlayer()) sb.append(e.toString()).append("\n");
            sb.append("\nLog:\n");
            for (String l : game.getLog()) sb.append(l).append("\n");

            outputArea.setText(sb.toString());

            if (game.getPlayer().getHealth() <= 0) {
                JOptionPane.showMessageDialog(frame, "Player defeated!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else if (game.collidingWithPlayer().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Player survived and no collisions remain - you win!", "Victory", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameGUI::new);
    }
}