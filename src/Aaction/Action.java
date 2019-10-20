
package Aaction;
import classes.Book;
import classes.User;
import databases.BookShelf;
import databases.RecordShlf;
import databases.Where;
import classes.Recorde;
import exceptions.BorrowedOutException;
import exceptions.NoSuchBookException;
import exceptions.NotBorrowedException;
import exceptions.YetBorrowedException;

import java.util.Comparator;
import java.util.List;

public class Action {
    public static Book putBook(String ISBN,String  title, String author, double price, int count ){
        BookShelf bookShelf = BookShelf.getInstance();
        try {
            Book book = bookShelf.search(ISBN);
            book.increaseCount(count);
            return book;
        }catch (NoSuchBookException exc){
            Book book = new Book(ISBN,title ,author ,price ,count );
            bookShelf.putBook(book);
            return book;
        }
        }
        public static List<Book> queryBooks(Comparator<Book> orderBy){
        BookShelf bookShelf = BookShelf.getInstance();
        return bookShelf.queryBooks(null,orderBy);
        }

        public static List<Book> querBookByWhere(Where<Book> where){
        BookShelf bookShelf = BookShelf.getInstance();
        return  bookShelf.queryBooks(where,null);
        }
        public static List<Recorde> queryRecords(){
        RecordShlf recordShlf = RecordShlf.getInstance();
        return  recordShlf.queryRecords(null);
        }

        public static Book borrowBook(User user,String ISBN) throws NoSuchBookException, BorrowedOutException, YetBorrowedException {
        BookShelf bookShelf = BookShelf.getInstance();
        RecordShlf recordShlf = RecordShlf.getInstance();
        Book book = bookShelf.search(ISBN);
        if(book.getCurrentCount()<=0){
            throw  new BorrowedOutException();
        }
        if(recordShlf.contains(user,ISBN)){
            throw new YetBorrowedException();
        }
        book.borrowBook();
        recordShlf.putRecord(user,ISBN);
        return book;
        }
    public static Book returnBook(User user,String ISBN)throws NoSuchBookException, NotBorrowedException{
        BookShelf bookShelf = BookShelf.getInstance();
        Book book = bookShelf.search(ISBN);
        RecordShlf recordShlf = RecordShlf.getInstance();
        recordShlf.remove(user,ISBN);
        book.returnBook();
        return book;
    }
}
