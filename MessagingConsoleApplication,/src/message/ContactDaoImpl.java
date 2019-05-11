package message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContactDaoImpl implements ContactDao {

    ConnectionFactory connectionFactory = new ConnectionFactory();

    public ContactDaoImpl() {

    }

    public void deleteContact(Contact contact) throws SQLException{
        ResultSet resultSet;
        try {
            resultSet = this.connectionFactory.delete(String.format("delete from user_contacts where user_id='%s' and contact_id = '%s'", contact.getUserId(), contact.getContactId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertContact(Contact contact)  throws SQLException{
        try {
            this.connectionFactory.insert(String.format("INSERT INTO USER_CONTACTS (USER_ID, CONTACT_ID) VALUES (%o, %o)", contact.getUserId(), contact.getContactId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getContacts(Integer userId)  throws SQLException{
        ArrayList<User> users = new ArrayList<User>();
        try {
            ResultSet resultSet = this.connectionFactory.executeQuery("SELECT * FROM USER WHERE ID IN (SELECT CONTACT_ID FROM USER_CONTACTS WHERE USER_ID = " + String.valueOf(userId) + ")");
            users = CreateModel.users(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
}