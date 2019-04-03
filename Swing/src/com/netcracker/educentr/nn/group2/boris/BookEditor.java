package com.netcracker.educentr.nn.group2.boris;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class BookEditor extends JFrame {
    private final String TITLE_INPUT_ERROR = "Ошибка ввода";
    public BookEditor(JTable table) {
        super("Book Editor");
        setSize(640, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation( HIDE_ON_CLOSE );

        JPanel grid1 = new JPanel();
        GridLayout gl = new GridLayout(2, 2, 5, 12);
        grid1.setLayout(gl);
        JLabel lPrice = new JLabel(((BookModel)table.getModel()).getColumnName(2)+":");
        JTextField vPrice = new JTextField(((BookModel)table.getModel()).getValueAt(table.getSelectedRow(), 2).toString());
        grid1.add(lPrice);
        grid1.add(vPrice);
        JLabel lQty = new JLabel(((BookModel)table.getModel()).getColumnName(3)+":");
        JTextField vQty = new JTextField(((BookModel)table.getModel()).getValueAt(table.getSelectedRow(), 3).toString());
        grid1.add(lQty);
        grid1.add(vQty);
        grid1.setBorder(new EmptyBorder(12,12,12,12));
        add(grid1);

        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0) );
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener( new BookEditor.OkayClick(table, vPrice, vQty));
        grid.add(btnOk);
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT ));
        flow.add(grid);
        add(flow, BorderLayout.SOUTH );

        setVisible(true);
    }

    private class OkayClick implements ActionListener {
        private JTable table;
        private JTextField vPrice;
        private JTextField vQty;

        OkayClick(JTable table, JTextField vPrice, JTextField vQty)
        {
            this.table = table;
            this.vPrice = vPrice;
            this.vQty = vQty;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (Double.parseDouble(vPrice.getText()) < 0)
                    JOptionPane.showMessageDialog(BookEditor.this,
                            new String[]{"Отрицательное значение:", "Price = " + Double.parseDouble(vPrice.getText())},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                if (Integer.parseInt(vQty.getText()) < 0)
                    JOptionPane.showMessageDialog(BookEditor.this,
                            new String[]{"Отрицательное значение:", "Count = " + Integer.parseInt(vQty.getText())},
                            TITLE_INPUT_ERROR,
                            JOptionPane.ERROR_MESSAGE);
                else {
                    ((BookModel) table.getModel()).editBook(table.getSelectedRow(), Double.parseDouble(vPrice.getText()), Integer.parseInt(vQty.getText()));
                    dispose();
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(BookEditor.this,
                        new String[]{"NumberFormatException:", "Проверьте правильность введенных данных"},
                        TITLE_INPUT_ERROR,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
