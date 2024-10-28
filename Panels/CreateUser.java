package Panels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Path;
import static java.lang.System.exit;

public class CreateUser extends JPanel {
    public Border solidBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
    public JButton create;
    public Color bg = Color.BLUE;
    public JTextField namefield;
    public JTextField passwordfield;
    public JTextField retype;
    public JLabel nameLabel, passLabel, retypeLabel;
    public Path filename = Path.of("assets/users.txt");
    public boolean flag = false;
    public AppInterface parent;

    public CreateUser(AppInterface p) {
        super(null);
        parent = p;
        setSize(900, 500);
        setBackground(bg);
        setLayout(null);

        JLabel head = new JLabel("CREATE ACCOUNT", 0);
        head.setFont(new Font("Times New Roman", 1, 50));
        head.setBounds(150, 10, 600, 100);
        head.setForeground(Color.BLACK);
        add(head);

        JButton backButton = new JButton("BACK");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.BLACK);
        backButton.setFont(new Font("Times New Roman", 1, 20));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p.showLoginPage();
            }
        });

        backButton.setBounds(650, 370, 200, 50);
        add(backButton);

        create = new JButton("Create Account");
        create.setBounds(350, 350, 200, 50);
        create.setBorder(solidBorder);
        create.setBackground(Color.YELLOW);
        create.setFont(new Font("Times New Roman", 1, 18));
        add(create);

        namefield = new JTextField();
        passwordfield = new JTextField();
        retype = new JTextField();

        nameLabel = new JLabel("Enter Username", 0);
        passLabel = new JLabel("Enter Password", 0);
        retypeLabel = new JLabel("Enter Retype", 0);

        nameLabel.setFont(new Font("Times New Roman", 1, 25));
        namefield.setFont(new Font("Times New Roman", 1, 25));
        passwordfield.setFont(new Font("Times New Roman", 1, 25));
        passLabel.setFont(new Font("Times New Roman", 1, 25));
        retype.setFont(new Font("Times New Roman", 1, 25));
        retypeLabel.setFont(new Font("Times New Roman", 1, 25));

        nameLabel.setBackground(Color.YELLOW);
        passLabel.setBackground(Color.YELLOW);
        retypeLabel.setBackground(Color.YELLOW);
        nameLabel.setForeground(Color.BLACK);
        passLabel.setForeground(Color.BLACK);
        retypeLabel.setForeground(Color.BLACK);
        nameLabel.setOpaque(true);
        passLabel.setOpaque(true);
        retypeLabel.setOpaque(true);

        nameLabel.setBounds(150, 100, 300, 50);
        passLabel.setBounds(150, 180, 300, 50);
        retypeLabel.setBounds(150, 260, 300, 50);

        namefield.setBounds(500, 100, 300, 50);
        passwordfield.setBounds(500, 180, 300, 50);
        retype.setBounds(500, 260, 300, 50);

        add(namefield);
        add(passwordfield);
        add(retype);
        add(nameLabel);
        add(passLabel);
        add(retypeLabel);

        create.addActionListener(e -> {
            // JButton clickedButton = (JButton) e.getSource();
            flag = storeUser();
        });
    }

    public boolean existinguser(String username) {
        try {
            String u = "", p = "";
            int flag = 0;
            File userfile = new File("assets/users.txt");
            Scanner in = new Scanner(userfile);
            while (in.hasNextLine()) {
                String line = in.nextLine();
                // System.out.println(line);
                int i = 0, beg = 0;
                while (line.charAt(i) != '\0') {
                    if (line.charAt(i) == ',') {
                        u = line.substring(beg, i);
                        // System.out.println(u);
                        p = line.substring(i + 1);
                        // System.out.println(p);
                        break;
                    }
                    i++;
                }
                if (u.equals(username)) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                JOptionPane.showMessageDialog(this, "USER ALREADY EXISTS!", "COULDN'T CREATE ACCOUNT",
                        JOptionPane.WARNING_MESSAGE);
                // System.out.println("User Already exists");
                return true;
            } else {
                JOptionPane.showMessageDialog(this, "ACCOUNT CREATED SUCCESSFULLY!", "SUCCESS!",
                        JOptionPane.INFORMATION_MESSAGE);
                // System.out.println("Proceed");
                return false;
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "FILE NOT FOUND (users.txt)", "COULDN'T CREATE ACCOUNT",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("FILE NOT FOUND");
            exit(0);
            throw new RuntimeException(e);
        }
    }

    public boolean storeUser() {
        final String username = namefield.getText();
        final String password = passwordfield.getText();
        final String retpass = retype.getText();
        boolean ch = false;
        if (existinguser(username))
            return false;
        if (!password.equals(retpass)) {
            JOptionPane.showMessageDialog(this, "PASSWORD DOESN'T MATCH!", "COULDN'T CREATE ACCOUNT",
                    JOptionPane.WARNING_MESSAGE);
            // System.out.println("Incorrect input");
            ch = false;
        } else {
            String info = username + "," + password;
            File filename = new File("assets/users.txt");
            try {
                if (filename.exists() == false) {
                    System.out.println("We had to make a new file.");
                    filename.createNewFile();
                }
                PrintWriter out = new PrintWriter(new FileWriter(filename, true));
                out.append(info + "\n");
                out.close();
                parent.displayMenu(username);
                ch = true;
            } catch (IOException i) {
                System.out.println("error");
                exit(0);
            }
        }
        return ch;
    }
}
