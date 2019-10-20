package classes;
import Aaction.Action;
import exceptions.NoSuchBookException;
import exceptions.BorrowedOutException;
import exceptions.YetBorrowedException;
import exceptions.NotBorrowedException;
import java.util.Scanner;

public class Students extends User {
    public Students(String id, String name) {
        super(id, name);
    }

    @Override
    public void menu() {
        System.out.println("== 同学,请选择==");
        System.out.println("== 0,退出 ");
        System.out.println("== 1,查阅图书==");
        System.out.println("== 2,借阅图书==");
        System.out.println("== 3,归还图书==");
    }

    @Override
    public boolean input() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int select = scanner.nextInt();
        scanner.nextLine();
        switch (select) {
            case 0: return  true;
            case 1:
                queryBooks();
                break;
            case 2:
                borrowBook();
                break;
            case 3:
                returnBook();
                break;
            default:
                System.out.println("选项错误");
        }
        return false;
    }
private void borrowBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要借阅书籍的ISBN");
        String ISBN = scanner.nextLine();
        User user = User.getCurrentUser();
        try{
            Book book = Action.borrowBook(user,ISBN );
            System.out.printf("<<%s>>借阅成功! %n",book.getTitle());
        }catch (NoSuchBookException e){
            System.out.println("错误！没有这本书");
        } catch (BorrowedOutException e) {
            System.out.println("错误！这本书已经全被借完了");
        } catch (YetBorrowedException e) {
            System.out.println("错误！这本书已经你已经借过了");
        }
}
private void returnBook() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要归还的图书的ISBN:");
        String ISBN = scanner.nextLine();
        User user = User.getCurrentUser();
        try {
            Book book = Action.returnBook(user,ISBN);
            System.out.printf("<%s> 归还成功", book.getTitle());
        }catch (NoSuchBookException e){
            System.out.println("没有此图书");
        }catch (NotBorrowedException e){
            System.out.println("没有借此书");
        }
}
}