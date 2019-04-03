package com.netcracker.educentr.nn.group2.boris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthorEditor extends JFrame {
    private JTextField[] columnValue;
    private final String TITLE_INPUT_ERROR = "Ошибка ввода";

    public AuthorEditor(JTable table, int row) {
        super("Author Editor");
        setSize(640, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation( HIDE_ON_CLOSE );

        JPanel grid1 = new JPanel(new GridLayout(((BookModel)table.getModel()).getAuthorFieldCount(), 2, 5, 12));
        grid1.setBorder(BorderFactory.createTitledBorder("Author"));
        JLabel[] columnName = new JLabel[((BookModel)table.getModel()).getAuthorFieldCount()];
        columnValue = new JTextField[((BookModel)table.getModel()).getAuthorFieldCount()];
        for (int curCol=0; curCol < ((BookModel)table.getModel()).getAuthorFieldCount(); curCol++) {
            columnName[curCol] = new JLabel(((BookModel) table.getModel()).getAuthorColumnName(curCol) + ":");
            if(row == -1)
                columnValue[curCol] = new JTextField(20);
            else
                columnValue[curCol] = new JTextField((String) ((BookModel) table.getModel()).getAuthor(row, curCol));
            grid1.add(columnName[curCol]);
            grid1.add(columnValue[curCol]);
        }
        add(grid1);

        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0) );
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener( new AuthorEditor.OkayClick(table));
        grid.add(btnOk);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT ));
        flow.add(grid);
        add(flow, BorderLayout.SOUTH );

        setVisible(true);
    }

    private class OkayClick implements ActionListener {
        private JTable table;

        OkayClick(JTable table)
        {
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        if(!(columnValue[1].getText().contains("@")) && !(columnValue[1].getText().contains("."))){
            JOptionPane.showMessageDialog(AuthorEditor.this,
                    new String[]{"Данный Email недопустим"},
                    TITLE_INPUT_ERROR,
                    JOptionPane.ERROR_MESSAGE);
        }
        if(!(columnValue[2].getText().equals("male")) && !(columnValue[2].getText().equals("female")) && !(columnValue[2].getText().equals("f")) && !(columnValue[2].getText().equals("m"))){
            JOptionPane.showMessageDialog(AuthorEditor.this,
                    new String[]{"Введите верный пол: male/female (m/f)"},
                    TITLE_INPUT_ERROR,
                    JOptionPane.ERROR_MESSAGE);
        }else{
                if (table.getSelectedRow() > -1)
                    ((BookModel) table.getModel()).editAuthor(table.getSelectedRow(), new Author(columnValue[0].getText(), columnValue[1].getText(), columnValue[2].getText()));
                dispose();
            }
        }
    }
}
