package classes;
public class Book{
    private String ISBN;
    private String title;
    private String author;
    private double price;
    private int currentCount;  //存量
    private int totalCount;    //总量
    private int borrowedCount; //借阅量

    public Book(String ISBN, String title, String author, double price, int count) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.price = price;
        this.currentCount = count;
        this.totalCount = count;
        this.borrowedCount = 0;
    }
    public boolean is(String ISBN){
        return this.ISBN.equals(ISBN);
    }
    public void increaseCount(int count){
        currentCount +=count;
        totalCount+=count;
    }
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public double getPrice() {
        return price;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }
    public void borrowBook(){
        currentCount--;
        borrowedCount++;
    }
    public void returnBook(){
        currentCount++;
    }
}