package com.netcracker.educentr.nn.group2.boris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookEditor extends JFrame {
    private final String TITLE_INPUT_ERROR = "Ошибка ввода";

    public BookEditor(JTable table, boolean isAddition) {
        super("Add book to library");
        setSize(640, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
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
        container.setLayout(new BorderLayout());

        JPanel jPanelBook = new JPanel(new GridLayout(((BookModel) table.getModel()).getColumnCount() - ((BookModel) table.getModel()).getAuthorFieldCount(), 2, 5, 12));
        jPanelBook.setBorder(BorderFactory.createTitledBorder("Book"));
        JLabel[] columnName = new JLabel[((BookModel) table.getModel()).getColumnCount()];
        JTextField[] columnValue = new JTextField[((BookModel) table.getModel()).getColumnCount()];
        for (int curCol = 0; curCol < ((BookModel) table.getModel()).getColumnCount() - ((BookModel) table.getModel()).getAuthorFieldCount(); curCol++) {
            columnName[curCol] = new JLabel(((BookModel) table.getModel()).getColumnName(curCol) + ":");
            if (!isAddition)
                columnValue[curCol] = new JTextField(((BookModel) table.getModel()).getValueAt(table.getSelectedRow(), curCol).toString());
            else
                columnValue[curCol] = new JTextField(20);
            jPanelBook.add(columnName[curCol]);
            jPanelBook.add(columnValue[curCol]);
        }
        container.add(jPanelBook, BorderLayout.NORTH);

        JPanel jPanelAuthor = new JPanel(new GridLayout(((BookModel) table.getModel()).getAuthorFieldCount(), 2, 5, 12));
        jPanelAuthor.setBorder(BorderFactory.createTitledBorder("Author"));
        JLabel[] authorColName = new JLabel[((BookModel) table.getModel()).getAuthorFieldCount()];
        JTextField[] authorColValue = new JTextField[((BookModel) table.getModel()).getAuthorFieldCount()];
        for (int curCol = 0; curCol < ((BookModel) table.getModel()).getAuthorFieldCount(); curCol++) {
            authorColName[curCol] = new JLabel(((BookModel) table.getModel()).getAuthorColumnName(curCol) + ":");
            if (!isAddition)
                authorColValue[curCol] = new JTextField((String) ((BookModel) table.getModel()).getAuthor(table.getSelectedRow(), curCol));
            else
                authorColValue[curCol] = new JTextField(20);
            jPanelAuthor.add(authorColName[curCol]);
            jPanelAuthor.add(authorColValue[curCol]);
        }
        container.add(jPanelAuthor, BorderLayout.CENTER);

        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(new BookEditor.OkayClick(table, isAddition, columnValue, authorColValue));
        grid.add(btnOk);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(grid);
        container.add(flow, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private class OkayClick implements ActionListener {
        private JTable table;
        private JTextField[] columnValue;
        private JTextField[] authorColValue;
        private boolean isAddition;

        OkayClick(JTable table, boolean isAddition, JTextField[] columnValue, JTextField[] authorColValue) {
            this.table = table;
            this.columnValue = columnValue;
            this.authorColValue = authorColValue;
            this.isAddition = isAddition;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean isException = false;
            try {
                Long.parseLong(columnValue[0].getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(BookEditor.this,
                        new String[]{"ISBN не является числом типа long"},
                        TITLE_INPUT_ERROR,
                        JOptionPane.ERROR_MESSAGE);
                isException = true;
            }
            try {
                Integer.parseInt(columnValue[3].getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(BookEditor.this,
                        new String[]{"Count не является числом типа int"},
                        TITLE_INPUT_ERROR,
                        JOptionPane.ERROR_MESSAGE);
                isException = true;
            }
            try {
                Double.parseDouble(columnValue[2].getText());
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(BookEditor.this,
                        new String[]{"Price не является числом типа double"},
                        TITLE_INPUT_ERROR,
                        JOptionPane.ERROR_MESSAGE);
                isException = true;
            }
            if (!isException) {
                boolean isError = false;
                if (Long.parseLong(columnValue[0].getText()) < 0) {
                    JOptionPane.showMessageDialog(BookEditor.this,
                            new String[]{"Отрицательное значение:", "ISBN = " + Integer.parseInt(columnValue[3].getText())},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                    isError = true;
                }
                if (Integer.parseInt(columnValue[3].getText()) < 0) {
                    JOptionPane.showMessageDialog(BookEditor.this,
                            new String[]{"Отрицательное значение:", "Count = " + Integer.parseInt(columnValue[3].getText())},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                    isError = true;
                }
                if (Double.parseDouble(columnValue[2].getText()) < 0) {
                    JOptionPane.showMessageDialog(BookEditor.this,
                            new String[]{"Отрицательное значение:", "Price = " + Double.parseDouble(columnValue[2].getText())},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                    isError = true;
                }
                if (!(authorColValue[1].getText().contains("@") && authorColValue[1].getText().contains("."))) {
                    JOptionPane.showMessageDialog(BookEditor.this,
                            new String[]{"Данный Email недопустим"},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                    isError = true;
                }
                if (!(authorColValue[2].getText().equalsIgnoreCase("male")) && !(authorColValue[2].getText().equalsIgnoreCase("female")) && !(authorColValue[2].getText().equalsIgnoreCase("f")) && !(authorColValue[2].getText().equalsIgnoreCase("m"))) {
                    JOptionPane.showMessageDialog(BookEditor.this,
                            new String[]{"Введите верный пол: male/female (m/f)"},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                    isError = true;
                }
                if (!isError) {
                    Book b = new Book(
                            Long.parseLong(columnValue[0].getText()),
                            columnValue[1].getText(),
                            Integer.parseInt(columnValue[3].getText()),
                            Double.parseDouble(columnValue[2].getText()),
                            new Author(authorColValue[0].getText(),
                                    authorColValue[1].getText(),
                                    authorColValue[2].getText()));
                    if (isAddition) {
                        if (((BookModel) table.getModel()).isBookInLibrary(b)) {
                            JOptionPane.showMessageDialog(BookEditor.this,
                                    new String[]{"Книга с такими ISBN, именем и автором уже существует в базе!"},
                                    TITLE_INPUT_ERROR,
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            ((BookModel) table.getModel()).addBook(b);
                        }
                    } else {
                        ((BookModel) table.getModel()).editBook(table.getSelectedRow(), b);
                    }
                    dispose();
                }
            }
        }
    }
}
