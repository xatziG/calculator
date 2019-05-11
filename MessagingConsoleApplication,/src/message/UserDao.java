package message;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao {
    boolean insertUser(User user)throws SQLException;

    void updateUser(User user)throws SQLException;

    void deleteUser(User user)throws SQLException;

    ArrayList<User> getAllUser()throws SQLException;

    User getUserByEmail(String email)throws SQLException;

    boolean isUserExistByEmail(String email)throws SQLException;

    User getUserByUsername(String username) throws SQLException ;
    User getUserById(Integer id)throws SQLException;
}
