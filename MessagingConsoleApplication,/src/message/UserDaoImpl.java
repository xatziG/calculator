package message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public  class UserDaoImpl implements UserDao {
    ConnectionFactory connectionFactory = new ConnectionFactory();

    public UserDaoImpl() {

    }

    public boolean insertUser(User user) throws SQLException{
        try {
            this.connectionFactory.insert(String.format("insert into user (name, surname, email, username, password, role) values ('%s', '%s', '%s', '%s', '%s','%d')",
                    user.getName(), user.getSurname(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRole()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public void updateUser(User user) throws SQLException{
        try {

            this.connectionFactory.update(String.format("update user set name ='%s', surname='%s', email='%s', password='%s', username='%s', role='%d' where id= %d;",
                    user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getUsername(), user.getRole(), user.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void deleteUser(User user) throws SQLException{
        ResultSet resultSet;
        try {
            resultSet = this.connectionFactory.delete(String.format("delete from user where id='%s'", user.getId()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> getAllUser()throws SQLException {
        ArrayList<User> userArrayList = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = this.connectionFactory.executeQuery("select * from user ");
            userArrayList = CreateModel.users(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userArrayList;
    }


    @Override
    public User getUserByEmail(String email)throws SQLException  {
        User user = new User();
        try {
//            ResultSet resultSet = this.appConnector.executeQuery("select * from user where username = '"+ username +"'");
            ResultSet resultSet = this.connectionFactory.executeQuery(String.format("select * from user where email = '%s'", email));
            user = CreateModel.user(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean isUserExistByEmail(String email)throws SQLException  {
        ResultSet resultSet;
        int count = 0;
        try {
            resultSet = this.connectionFactory.executeQuery("select count(*) from user where email = '" + email + "'");
            resultSet.next();
            count = resultSet.getInt("count(*)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count != 0 ? true : false;
    }

    public User getUserById(Integer id)throws SQLException  {
        User user = new User();

        ResultSet resultSet;
        try {
            resultSet = this.connectionFactory.executeQuery("select * from user where id =" + id);
            user = CreateModel.user(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public User getUserByUsername(String username) throws SQLException {
        User user = new User();
        try {
            ResultSet resultSet = this.connectionFactory.executeQuery(String.format("select * from user where username = '%s'", username));
            user = CreateModel.user(resultSet);

        }catch (Exception e){
            e.printStackTrace();
        }
        return user;

    }


}