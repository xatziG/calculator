package message;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ContactDao {
    void deleteContact(Contact contact)throws SQLException ;
    void insertContact(Contact contact)throws SQLException;
    ArrayList<User> getContacts(Integer userId)throws SQLException;
}
