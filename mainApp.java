import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowEvent;

import Panels.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class ScoreChecker {
    private static final String score_file = "assets/scores.txt";

    public static boolean store_compare_highscore(String type, String name, int score) {
        boolean scoreUpdated = false;
        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(score_file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(type)) {
                    int score_in_file = Integer.parseInt(parts[2]);
                    if (score_in_file > score) {
                        updatedContent.append(parts[0]).append(",").append(name).append(",").append(score).append("\n");
                        scoreUpdated = true;
                    } else {
                        updatedContent.append(line).append("\n");
                    }
                } else {
                    updatedContent.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (scoreUpdated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(score_file))) {
                writer.write(updatedContent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return scoreUpdated;
    }

    // public static void main(String[] args) {
    // store_compare_highscore("3x3_multiplayer", "Keshav", 23);
    // store_compare_highscore("4x4_multiplayer", "Karthik", 90);
    // }

}

public class mainApp extends JFrame implements AppInterface {
    public JPanel currentPanel;
    private MyMenu menuPage;
    private String uname;

    public mainApp() {
        super();
        this.setLayout(null);
        this.setBounds(100, 100, 900, 500);
        this.getContentPane().setBackground(Color.BLACK);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void destroy() {
        this.dispose();
    }

    public void checkHighScore(String mode, int moves) {
        if (ScoreChecker.store_compare_highscore(mode, uname, moves)) {
            JOptionPane.showMessageDialog(this, "NEW HIGHSCORE SET!", "NEW HIGHSCORE!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void displayMenu(String user) {
        this.remove(currentPanel);
        menuPage = new MyMenu(this, user);
        currentPanel = menuPage;
        uname = user;
        menuPage.setBounds(0, 0, 900, 500);
        this.add(menuPage);
        revalidate();
        repaint();
    }

    public void call4x4() {
        App4_4 obj = new App4_4(this);
        currentPanel.setVisible(false);
        obj.setBounds(0, 0, 900, 500);
        currentPanel = obj;
        this.add(obj);
        revalidate();
        repaint();
    }

    public void call3x3() {
        App3_3 obj = new App3_3(this);
        currentPanel.setVisible(false);
        obj.setBounds(0, 0, 900, 500);
        currentPanel = obj;
        this.add(obj);
        revalidate();
        repaint();
    }

    public void callComp() {
        Competitive obj = new Competitive(this);
        currentPanel.setVisible(false);
        obj.setBounds(0, 0, 900, 500);
        currentPanel = obj;
        this.add(obj);
        revalidate();
        repaint();
    }

    public void removeChild() {
        this.displayMenu(uname);
    }

    public void showCreatePage() {
        CreateUser obj = new CreateUser(this);
        this.remove(currentPanel);
        obj.setBounds(0, 0, 900, 500);
        currentPanel = obj;
        add(obj);
        revalidate();
        repaint();
    }

    public void showLoginPage() {
        Login obj = new Login(this);
        this.remove(currentPanel);
        obj.setBounds(0, 0, 900, 500);
        currentPanel = obj;
        add(obj);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        mainApp Window = new mainApp();
        Login loginPage = new Login(Window);
        // Window.uname = "DEV BUILD";
        // MyMenu menu = new MyMenu(Window);
        loginPage.setBounds(0, 0, 900, 500);
        Window.currentPanel = loginPage;
        Window.add(loginPage);
        Window.revalidate();
        Window.repaint();
    }

}
