package com.netcracker.educentr.nn.group2.boris;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Swing extends JFrame {
    private JTable table;

    public Swing() {
        super("Library");
        setSize(1280, 720);
        setLocation(150, 100);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        BookModel m=new BookModel();
        table=new JTable(m);
        JScrollPane jScrollPane=new JScrollPane(table);
        add(jScrollPane);
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0) );

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                int column = table.columnAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1 && ((BookModel)table.getModel()).getColumnName(column).equals("Author")) {
                    SwingUtilities.invokeLater(() -> new AuthorEditor(table, row));
                }
            }
        });

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener( new BtnAddClick());
        grid.add(btnAdd);

        JButton btnEdit = new JButton("Edit");
        btnEdit.addActionListener( new BtnEditClick());
        grid.add(btnEdit);

        JButton btnRemove = new JButton("Remove");
        btnRemove.addActionListener( new BtnRemoveClick());
        grid.add(btnRemove);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT ));
        flow.add(grid);
        add(flow, BorderLayout.SOUTH );
        setVisible(true);
    }

    private class BtnRemoveClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i : table.getSelectedRows())
            {
                ((BookModel)table.getModel()).removeBook(i);
            }
        }
    }

    private class BtnAddClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(() -> new BookAdd(table));
        }
    }

    private class BtnEditClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (table.getSelectedRow() != -1 && ((BookModel)table.getModel()).getColumnName(table.getSelectedColumn()).equals("Author")) {
                SwingUtilities.invokeLater(() -> new AuthorEditor(table, table.getSelectedRow()));
            }
            else if(table.getSelectedRow() > -1 && table.getSelectedRowCount() == 1) {
                SwingUtilities.invokeLater(() -> new BookEditor(table));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Swing());
    }
}


