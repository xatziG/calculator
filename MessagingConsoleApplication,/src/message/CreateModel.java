package message;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CreateModel {



    public static User user(ResultSet resultSet){
        User user = new User();
        try {
            while (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(resultSet.getInt("role"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static ArrayList<User> users(ResultSet resultSet){
        ArrayList<User> users = new ArrayList<User>();
        try {
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setRole(resultSet.getInt("role"));
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public static ArrayList<Message> messages(ResultSet resultSet){
        ArrayList<Message> messagesArrayList = new ArrayList<Message>();
        UserDaoImpl userDao=new UserDaoImpl();
        try {
            while (resultSet.next()){
                Message message = new Message();
                message.setBody(resultSet.getString("body"));
                message.setDateOfSubmision(resultSet.getTimestamp( "date_of_submission"));
                message.setId(resultSet.getInt("id"));
                message.setReceiver(userDao.getUserById(resultSet.getInt("receiver")));
                message.setSender(userDao.getUserById(resultSet.getInt("sender")));
                messagesArrayList.add(message);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return messagesArrayList;
    }

}
