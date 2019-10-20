package classes;

import Aaction.Action;
import databases.Where;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public abstract class User {
    private String id;
    private String name;
    public User(String id, String name){
        this.id = id;
        this.name = name;
    }
    private static User currentUser = null;
    public abstract void menu();
    public static User login() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入id");
        String id = scanner.nextLine();
        System.out.println("请输入姓名");
        String name = scanner.nextLine();
        System.out.println("请输入身份");
        String jueSe = scanner.nextLine();
        if(jueSe.equals("老师")){
            currentUser = new Teacher(id,name);
        }else if(jueSe.equals("学生")){
            currentUser = new Students(id, name );
        }else{
            throw new Exception("错误的身份");
        }
        return currentUser;
    }
    public abstract  boolean input() throws Exception;
    public static User getCurrentUser(){
        return currentUser;
    }
    private static class Current implements Where<Book>{

        @Override
        public boolean test(Book book) {
            return book.getCurrentCount()>0;
        }
    }

    private static class BookTital implements Where<Book>{
        private String title;
        BookTital(String title){
            this.title = title;
        }

        @Override
        public boolean test(Book book) {
            return book.getTitle().equals(title);
        }
    }
    private enum OrderBy{
        OTHER,TITLE,PRICE_ASC,PRICE_DESC,AUTHOR
    }

    private static final HashMap<OrderBy, Comparator<Book>> comparators = new HashMap<>();
    static{
        comparators.put(OrderBy.TITLE,new TitleComparator());
        comparators.put(OrderBy.PRICE_ASC,new PriceComparator(true));
        comparators.put(OrderBy.PRICE_DESC,new PriceComparator(false));
        comparators.put(OrderBy.AUTHOR,new AuthorComparator());
    }

    private static  Comparator<Book> getComparator(int selected){
        return comparators.get(selected);
    }
    protected void queryBooks(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入查询方式");
        System.out.println("1,全查询");
        System.out.println("2,余量查询");
        System.out.println("3,其他查询");
        int select = scanner.nextInt();
        scanner.nextLine();
        List<Book>bookList;
        switch (select){
            case 1:
                System.out.println("请选择排序顺序:");
                System.out.println(OrderBy.TITLE.ordinal()+",按标题排序!");
                System.out.println("2,按价格排序(从低到高)");
                System.out.println("3,按价格排序(从高到低)");
                System.out.println("4,按作者排序");
                System.out.println("其他,默认排序");
                Scanner scanner1  = new Scanner(System.in);
                int select1 = scanner1.nextInt();
                scanner1.nextLine();
                bookList = Action.queryBooks(getComparator(select1));
                break;
            case 2:
                bookList = Action.querBookByWhere(new Current());
                break;
                default:
                    System.out.println("请输入书名:");
                    String title = scanner.nextLine();
                    bookList = Action.querBookByWhere(new BookTital(title));
                    break;
        }
        for(Book book:bookList){
            System.out.printf("《%s》by %s  ISBN: %s 价格: %.2f 存量: %d 借阅次数: %d%n",book.getTitle(), book.getAuthor(),book.getISBN(), book.getPrice(),
                    book.getCurrentCount(), book.getBorrowedCount());
        }
        System.out.println("共查询到"+bookList.size()+"本书");
    }
    protected void queryRecords(){
        List<Recorde>recordeList = Action.queryRecords();
        for(Recorde recorde:recordeList){
            System.out.printf("%s %s %s%n",recorde.getUserId(),recorde.getBookISBN(),recorde.getBrrowredAt());
        }
        System.out.println("共查询到"+recordeList.size()+"条借阅记录");
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}
