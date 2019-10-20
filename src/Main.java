import classes.User;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {
        while (true) {
            User currentUser = null;
            try {
                currentUser = User.login();
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean isQuit;
            do {
                currentUser.menu();
                isQuit = currentUser.input();
            } while (!isQuit);
            Scanner scanner = new Scanner(System.in);
            System.out.println("1,退出!");
            System.out.println("2,切换账号");
            int select = scanner.nextInt();
            scanner.nextLine();
            if(select==1){
            System.out.println("欢迎下次光临!");    
                break;
            }
        }
    }
}
