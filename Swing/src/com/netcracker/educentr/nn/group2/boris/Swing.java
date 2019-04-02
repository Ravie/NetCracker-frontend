package com.netcracker.educentr.nn.group2.boris;

import javax.swing.*;
import java.awt.*;
public class Swing extends JFrame {
    public Swing() {
        super("CommandButtons");
        setSize(350, 250);
        setLocation(150, 100);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        BookModel m=new BookModel();
        JTable table=new JTable(m);
        JScrollPane jScrollPane=new JScrollPane(table);
        add(jScrollPane);
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Swing();
            }
        });
    }
}


