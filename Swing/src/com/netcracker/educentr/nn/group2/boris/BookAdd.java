package com.netcracker.educentr.nn.group2.boris;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class BookAdd extends JFrame {
    private final String TITLE_INPUT_ERROR = "Ошибка ввода";
    public BookAdd(JTable table) {
        super("Add book to library");
        setSize(640, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation( HIDE_ON_CLOSE );
        /*int curCol = 0;
        Box[] row = new Box[((BookModel)table.getModel()).getColumnCount()];
        JLabel[] columnName = new JLabel[((BookModel)table.getModel()).getColumnCount()];
        JTextField[] columnValue = new JTextField[((BookModel)table.getModel()).getColumnCount()];
        while(curCol < columnName.length) {
            row[curCol] = Box.createHorizontalBox();
            columnName[curCol] = new JLabel(((BookModel)table.getModel()).getColumnName(curCol)+":");
            columnValue[curCol] = new JTextField(20);
            row[curCol].add(columnName[curCol]);
            row[curCol].add(Box.createHorizontalStrut(20));
            row[curCol].add(columnValue[curCol]);
            curCol++;
        }
        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        curCol = 0;
        while(curCol < columnName.length) {
            mainBox.add(row[curCol]);
            mainBox.add(Box.createVerticalStrut(12));
            curCol++;
        }
        setContentPane(mainBox);*/
        Container container = getContentPane();
        container.setLayout (new BorderLayout ());

        JPanel jPanelBook = new JPanel(new GridLayout(((BookModel)table.getModel()).getColumnCount() - ((BookModel)table.getModel()).getAuthorFieldCount(), 2, 5, 12));
        jPanelBook.setBorder(BorderFactory.createTitledBorder("Book"));
        JLabel[] columnName = new JLabel[((BookModel)table.getModel()).getColumnCount()];
        JTextField[] columnValue = new JTextField[((BookModel)table.getModel()).getColumnCount()];
        for (int curCol=0; curCol < ((BookModel)table.getModel()).getColumnCount() - ((BookModel)table.getModel()).getAuthorFieldCount(); curCol++) {
            columnName[curCol] = new JLabel(((BookModel)table.getModel()).getColumnName(curCol)+":");
            if(table.getSelectedRow() > -1)
                columnValue[curCol] = new JTextField(((BookModel)table.getModel()).getValueAt(table.getSelectedRow(), curCol).toString());
            else
                columnValue[curCol] = new JTextField(20);
            jPanelBook.add(columnName[curCol]);
            jPanelBook.add(columnValue[curCol]);
        }
        container.add(jPanelBook, BorderLayout.NORTH);

        JPanel jPanelAuthor = new JPanel(new GridLayout(((BookModel)table.getModel()).getAuthorFieldCount(), 2, 5, 12));
        jPanelAuthor.setBorder(BorderFactory.createTitledBorder("Author"));
        JLabel[] authorColName = new JLabel[((BookModel)table.getModel()).getAuthorFieldCount()];
        JTextField[] authorColValue = new JTextField[((BookModel)table.getModel()).getAuthorFieldCount()];
        for (int curCol=0; curCol < ((BookModel)table.getModel()).getAuthorFieldCount(); curCol++) {
            authorColName[curCol] = new JLabel(((BookModel) table.getModel()).getAuthorColumnName(curCol) + ":");
            if(table.getSelectedRow() > -1)
                authorColValue[curCol] = new JTextField((String) ((BookModel) table.getModel()).getAuthor(table.getSelectedRow(), curCol));
            else
                authorColValue[curCol] = new JTextField(20);
            jPanelAuthor.add(authorColName[curCol]);
            jPanelAuthor.add(authorColValue[curCol]);
        }
        container.add(jPanelAuthor, BorderLayout.CENTER);

        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0) );
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener( new BookAdd.OkayClick(table, columnValue, authorColValue));
        grid.add(btnOk);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT ));
        flow.add(grid);
        container.add(flow, BorderLayout.SOUTH );

        pack();
        setVisible(true);
    }

    private class OkayClick implements ActionListener {
        private JTable table;
        private JTextField[] columnValue;
        private JTextField[] authorColValue;

        OkayClick(JTable table, JTextField[] columnValue, JTextField[] authorColValue)
        {
            this.table = table;
            this.columnValue = columnValue;
            this.authorColValue = authorColValue;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (Long.parseLong(columnValue[0].getText()) < 0)
                    JOptionPane.showMessageDialog(BookAdd.this,
                            new String[]{"Отрицательное значение:", "ISBN = " + Integer.parseInt(columnValue[3].getText())},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                if (Integer.parseInt(columnValue[3].getText()) < 0)
                    JOptionPane.showMessageDialog(BookAdd.this,
                            new String[]{"Отрицательное значение:", "Count = " + Integer.parseInt(columnValue[3].getText())},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                if (Double.parseDouble(columnValue[2].getText()) < 0)
                    JOptionPane.showMessageDialog(BookAdd.this,
                            new String[]{"Отрицательное значение:", "Price = " + Double.parseDouble(columnValue[2].getText())},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                if(!(authorColValue[1].getText().contains("@")) && !(authorColValue[1].getText().contains(".")))
                    JOptionPane.showMessageDialog(BookAdd.this,
                            new String[]{"Данный Email недопустим"},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                if (!(authorColValue[2].getText().equals("male")) && !(authorColValue[2].getText().equals("female")) && !(authorColValue[2].getText().equals("f")) && !(authorColValue[2].getText().equals("m")))
                    JOptionPane.showMessageDialog(BookAdd.this,
                            new String[]{"Введите верный пол: male/female (m/f)"},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                else {
                    ((BookModel) table.getModel()).addBook(new Book(
                            Long.parseLong(columnValue[0].getText()),
                            columnValue[1].getText(),
                            Integer.parseInt(columnValue[3].getText()),
                            Double.parseDouble(columnValue[2].getText()),
                            new Author(authorColValue[0].getText(),
                                    authorColValue[1].getText(),
                                    authorColValue[2].getText())));
                    dispose();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(BookAdd.this,
                        new String[]{"NumberFormatException:", "Проверьте правильность введенных данных"},
                        TITLE_INPUT_ERROR,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
