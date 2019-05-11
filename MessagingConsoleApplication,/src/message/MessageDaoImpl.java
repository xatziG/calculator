package message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessageDaoImpl implements MessageDao {

    ConnectionFactory connectionFactory = new ConnectionFactory();



    public ArrayList<Message> getAllMessages()throws SQLException{
        ArrayList<Message> messageArrayList=new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = this.connectionFactory.executeQuery("select * from messages ");
            return CreateModel.messages(resultSet);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return  messageArrayList;
    }
    public void deleteMessage (Message message) throws SQLException{
        ResultSet resultSet;
        try{
            resultSet=this.connectionFactory.delete(String.format("delete from messages where id='%s'",message.getId()));
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Integer getUnreadMessages(Integer userId) throws SQLException {
        ResultSet resultSet;
        try {
            resultSet = this.connectionFactory.executeQuery("select count(*) from messages where unread = 1 and receiver = " + String.valueOf(userId));
            resultSet.next();
            return resultSet.getInt("count(*)");
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<Message> getMessagesByReceiver(Integer userId)throws SQLException{
        ArrayList<Message> messageArrayList = new ArrayList<>();
        try {
            ResultSet resultSet;
            resultSet = this.connectionFactory.executeQuery("Select * from messages where receiver = " + String.valueOf(userId));
            messageArrayList = CreateModel.messages(resultSet);
            this.updateMessageUnread(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return messageArrayList;
    }


    public ArrayList<Message> getMessagesBySender(Integer userId)throws SQLException{
        ArrayList<Message> messageArrayList = new ArrayList<>();
        try {
            ResultSet resultSet;
            resultSet = this.connectionFactory.executeQuery("Select * from messages where sender = " + String.valueOf(userId));
            messageArrayList = CreateModel.messages(resultSet);
        }catch (Exception e){
            e.printStackTrace();
        }
        return messageArrayList;
    }

    public void insertMessage(Message message)throws SQLException{
        try{
            this.connectionFactory.insert(String.format("INSERT INTO MESSAGES (SENDER, RECEIVER, DATE_OF_SUBMISSION, BODY, UNREAD) VALUES ('%s', '%s', '%s', '%s', '%s')", message.getSender().getId(), message.getReceiver().getId(), LocalDateTime.now(), message.getBody(), 1));
        }catch (Exception e){
            e.printStackTrace();

        }
    }
    public void updateMessageBody(Message message, String body)throws SQLException{
        try {
            this.connectionFactory.update(String.format("update messages set body = '%s' where id=%d", body, message.getId()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateMessageUnread(int id)throws SQLException{
        try {
            this.connectionFactory.update(String.format("update messages set unread = 0 where receiver = %d;", id));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
