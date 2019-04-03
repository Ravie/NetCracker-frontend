package com.netcracker.educentr.nn.group2.boris;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Swing extends JFrame {
    private JTable table;
    private final String TITLE_confirm = "Окно подтверждения";

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
            int result = JOptionPane.showConfirmDialog(Swing.this,
                            "Подтвердите удаление "+table.getSelectedRows().length+" строк(и)",
                            TITLE_confirm,
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                int[] selection = table.getSelectedRows();
                for (int i = selection.length - 1; i >= 0; i--) {
                    ((BookModel)table.getModel()).removeBook(selection[i]);
                }
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
            if (table.getSelectedRow() != -1 &&
                            (((BookModel)table.getModel()).getColumnName(table.getSelectedColumn()).equals("Author Name") ||
                            ((BookModel)table.getModel()).getColumnName(table.getSelectedColumn()).equals("Author Email") ||
                            ((BookModel)table.getModel()).getColumnName(table.getSelectedColumn()).equals("Author Gender"))) {
                SwingUtilities.invokeLater(() -> new AuthorEditor(table, table.getSelectedRow()));
            }
            else if(table.getSelectedRow() > -1 && table.getSelectedRowCount() == 1 &&
                            (((BookModel)table.getModel()).getColumnName(table.getSelectedColumn()).equals("Price") ||
                            ((BookModel)table.getModel()).getColumnName(table.getSelectedColumn()).equals("Count"))) {
                SwingUtilities.invokeLater(() -> new BookEditor(table));
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Swing());
    }
}


