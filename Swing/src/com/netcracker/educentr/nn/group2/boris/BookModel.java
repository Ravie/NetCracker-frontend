package com.netcracker.educentr.nn.group2.boris;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bola0814 on 14.12.2016.
 */
public class BookModel extends AbstractTableModel {

    private List<Book> books = new ArrayList<>();

    public BookModel() {
        books.add(new Book(1231412233479L,"John Carter of Mars",100,10.5,new Author("Edgar Rice Burroughs","Burroughs@gmail.com","m")));
    }

    public void addBook(Book b){
        books.add(b);
        fireTableDataChanged();
    }

    public boolean isBookInLibrary(Book b){
        return books.contains(b);
    }

    public void editBook(int rowIndex, double price, int qty){
        Book cur=books.get(rowIndex);
        cur.setPrice(price);
        cur.setQty(qty);
        fireTableDataChanged();
    }

    public void removeBook(int rowIndex){
        books.remove(rowIndex);
        fireTableDataChanged();
    }

    public void editAuthor(int rowIndex, Author author){
        Book cur=books.get(rowIndex);
        cur.setAuthor(author);
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return books.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book cur=books.get(rowIndex);
        switch (columnIndex){
            case 0:
                return cur.getIsbn();
            case 1:
                return cur.getName();
            case 2:
                return cur.getPrice();
            case 3:
                return cur.getQty();
            case 4:
                return cur.getAuthor().getName();
            case 5:
                return cur.getAuthor().getEmail();
            case 6:
                return cur.getAuthor().getGender();
        }
        return null;
    }

    public String getColumnName(int column) {
        switch (column){
            case 0:
                return "ISBN";
            case 1:
                return "Book name";
            case 2:
                return "Price";
            case 3:
                return "Count";
            case 4:
                return "Author Name";
            case 5:
                return "Author Email";
            case 6:
                return "Author Gender";
        }
        return "";
    }

    public int getAuthorFieldCount() {
        return 3;
    }

    public String getAuthorColumnName(int column) {
        switch (column){
            case 0:
                return "Name";
            case 1:
                return "Email";
            case 2:
                return "Gender";
        }
        return "";
    }

    public Object getAuthor(int rowIndex, int columnIndex) {
        Author cur=books.get(rowIndex).getAuthor();
        switch (columnIndex){
            case 0:
                return cur.getName();
            case 1:
                return cur.getEmail();
            case 2:
                return cur.getGender();
        }
        return null;
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return Long.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Integer.class;
            case 4:
                return String.class;
        }
        return Object.class;
    }
}
