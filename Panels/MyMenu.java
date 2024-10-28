package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenu extends JPanel {
    private AppInterface parent;
    private String user;

    public MyMenu(AppInterface p, String username) {
        super(null);
        this.parent = p;
        this.user = username;
        setSize(900, 500);
        setBackground(Color.ORANGE);

        JLabel head = new JLabel("MENU", 0);
        head.setFont(new Font("Times New Roman", 1, 50));
        head.setBounds(250, 10, 400, 100);
        head.setForeground(Color.BLACK);
        add(head);

        JLabel tail = new JLabel("Logged as: " + user, 0);
        tail.setFont(new Font("Times New Roman", 1, 20));
        tail.setBounds(60, 5, 200, 40);
        tail.setForeground(Color.BLACK);
        add(tail);

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

        JButton x4 = new JButton("4x4 PUZZLE");
        JButton x3 = new JButton("3x3 PUZZLE");
        JButton co = new JButton("MULTIPLAYER");
        JButton ex = new JButton("EXIT");

        x4.setBounds(300, 110, 300, 50);
        x3.setBounds(300, 180, 300, 50);
        co.setBounds(300, 250, 300, 50);
        ex.setBounds(300, 320, 300, 50);
        x4.setFont(new Font("Times New Roman", 1, 18));
        x3.setFont(new Font("Times New Roman", 1, 18));
        co.setFont(new Font("Times New Roman", 1, 18));
        ex.setFont(new Font("Times New Roman", 1, 18));
        add(x4);
        add(x3);
        add(co);
        add(ex);
        ex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.destroy();
            }
        });
        x4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.call4x4();
            }
        });
        x3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.call3x3();
            }
        });
        co.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.callComp();
            }
        });
    }
}
