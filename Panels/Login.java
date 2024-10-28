package Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

import static java.lang.System.exit;

public class Login extends JPanel {
    public Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
    public JButton create;
    public JButton login;
    public Color bg = Color.BLUE;
    public JTextField namefield;
    public JTextField passwordfield;
    public JLabel namelabel, passwordlabel;
    public AppInterface parent;

    public Login(AppInterface parent) {
        super(null);
        this.parent = parent;
        setSize(900, 500);
        setBackground(bg);
        setLayout(null);
        JLabel head = new JLabel("LOGIN", 0);
        head.setFont(new Font("Times New Roman", 1, 50));
        head.setBounds(250, 10, 400, 100);
        head.setForeground(Color.BLACK);
        add(head);
        create = new JButton("Create Account");
        login = new JButton("Login");
        create.setBounds(250, 330, 200, 50);
        create.setBorder(solidBorder);
        create.setBackground(Color.YELLOW);
        create.setFont(new Font("Times New Roman", 1, 18));
        login.setBounds(500, 330, 200, 50);
        login.setBorder(solidBorder);
        login.setBackground(Color.YELLOW);
        login.setFont(new Font("Times New Roman", 1, 18));
        add(create);
        add(login);
        namelabel = new JLabel("Enter Username", 0);
        passwordlabel = new JLabel("Enter Password", 0);
        namefield = new JTextField();
        passwordfield = new JTextField();

        namelabel.setFont(new Font("Times New Roman", 1, 25));
        namefield.setFont(new Font("Times New Roman", 1, 25));
        passwordfield.setFont(new Font("Times New Roman", 1, 25));
        passwordlabel.setFont(new Font("Times New Roman", 1, 25));

        namelabel.setBackground(Color.YELLOW);
        passwordlabel.setBackground(Color.YELLOW);
        namelabel.setForeground(Color.BLACK);
        passwordlabel.setForeground(Color.BLACK);
        namelabel.setOpaque(true);
        passwordlabel.setOpaque(true);

        namelabel.setBounds(150, 150, 300, 50);
        passwordlabel.setBounds(150, 230, 300, 50);
        namefield.setBounds(500, 150, 300, 50);
        passwordfield.setBounds(500, 230, 300, 50);
        add(namefield);
        add(passwordfield);
        add(passwordlabel);
        add(namelabel);
        login.addActionListener(e -> {
            // JButton clickedButton = (JButton) e.getSource();
            final String username = namefield.getText();
            final String password = passwordfield.getText();
            // System.out.println("Username: " + username);
            // System.out.println("Password: " + password);
            checkUser(username, password);

        });
        // CHANGES : ADD CREATE NEW ACCOUNT LINK BUTTON LOGIC HERE!
        create.addActionListener(e -> {
            // JButton clickedButton = (JButton) e.getSource();
            // Component n = parent.getComponentAt(-1000, -1000);
            parent.showCreatePage();

        });
    }

    public boolean checkUser(String username, String password) {
        try {
            String u = "", p = "";
            int flag = 0;
            File userfile = new File("assets/users.txt");
            Scanner in = new Scanner(userfile);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                int i = 0, beg = 0;
                while (line.charAt(i) != '\n') {
                    if (line.charAt(i) == ',') {
                        u = line.substring(beg, i);
                        // System.out.println("UNAME: " + u);
                        p = line.substring(i + 1);
                        // System.out.println("PASS : " + p);
                        break;
                    }
                    i++;
                }
                if (u.equals(username) && p.equals(password)) {
                    flag = 1;
                    break;
                } else if (u.equals(username)) {
                    flag = 2;
                    break;
                } else {
                    flag = 0;
                }
            }
            if (flag == 1) {
                parent.displayMenu(username);
                return true;
            } else if (flag == 2) {
                JOptionPane.showMessageDialog(this, "WRONG PASSWORD!", "COULDN'T LOGIN",
                        JOptionPane.WARNING_MESSAGE);
                // System.out.println("Wrong Password");
                return false;
            } else {
                JOptionPane.showMessageDialog(this, "USERNAME DOESN'T EXIST", "COULDN'T LOGIN",
                        JOptionPane.WARNING_MESSAGE);
                // System.out.println("USERNAME DOESNT EXIST!");
                return false;
            }

        } catch (FileNotFoundException f) {
            System.out.println("File Not Found");
            exit(0);
            return false;
        }
    }
}
