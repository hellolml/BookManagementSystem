package classes;
import Aaction.Action;
import java.util.Scanner;


public class Teacher extends User {
    public Teacher(String id, String name) {
        super(id, name);
    }

    @Override
    public void menu() {
        System.out.println("== 老师,请选择==");
        System.out.println("== 0,退出 ");
        System.out.println("== 1,上架图书==");
        System.out.println("== 2,查阅图书==");
        System.out.println("== 3,查阅记录==");
    }

    @Override
    public boolean input() {
        Scanner scanner = new Scanner(System.in);
        int select = scanner.nextInt();
        scanner.nextLine();
        switch (select) {
            case 0: return  true;
            case 1:
                System.out.println("上架图书");
                putBook();
                break;
            case 2:
                System.out.println("查阅图书");
                queryBooks();
                break;
            case 3:
                queryRecords();
                break;
            default:
                System.out.println("选项错误");break;
        }
        return false;
    }
    private void putBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入ISBN");
        String ISBN = scanner.nextLine();
        System.out.println("请输入书名");
        String title = scanner.nextLine();
        System.out.println("请输入作者");
        String author = scanner.nextLine();
        System.out.println("请输入价格");
        double price = scanner.nextDouble();
        System.out.println("请输入数目");
        int count = scanner.nextInt();
        Book book = Action.putBook(ISBN, title , author ,price ,count );
        System.out.printf("<<%s>> 上架成功! %n",book.getTitle());
    }
}


