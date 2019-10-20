package classes;

import java.text.SimpleDateFormat;
import java.util.Date;
public class Recorde {
private String userId;
private String bookISBN;
private Date borrowedAt;
public Recorde(String id,String ISBN) {
    this.userId = id;
    this.bookISBN = ISBN;
    this.borrowedAt = new Date();
}
public boolean is (User user,String ISBN){
return  userId.equals(user.getId())&&bookISBN.equals(ISBN);
}

    public String getUserId() {
        return userId;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public String getBrrowredAt() {
        SimpleDateFormat fmt = new SimpleDateFormat("YYY-MM-dd HH:mm:ss");
        return fmt.format(borrowedAt);
    }
}