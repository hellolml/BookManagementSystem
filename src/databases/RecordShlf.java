package databases;

import classes.Recorde;
import classes.User;
import exceptions.NotBorrowedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class RecordShlf {
    private List<Recorde> recordList = new ArrayList<>();
    private static RecordShlf instance = new RecordShlf();

    public static RecordShlf getInstance() {
        return instance;
    }

    public boolean contains(User user, String ISBN) {
        for (Recorde recorde : recordList) {
            if (recorde.is(user, ISBN)) {
                return true;
            }
        }
        return false;
    }

    public void putRecord(User user, String ISBN) {
        Recorde record = new Recorde(user.getId(), ISBN);
        recordList.add(record);

    }

    public void remove(User user, String ISBN) throws NotBorrowedException {
        Iterator<Recorde> iterator = recordList.iterator();
        while (iterator.hasNext()) {
            Recorde recorde = iterator.next();
            if (recorde.is(user, ISBN)) {
                iterator.remove();
                return;
            }
        }
        throw new NotBorrowedException();
    }
    public List<Recorde> queryRecords(Where<Recorde> where){
        List<Recorde>ret = new ArrayList<>();
        for(Recorde recorde:recordList){
            if(where.test(recorde)){
                ret.add(recorde);
            }
        }
        return ret;
    }
}

