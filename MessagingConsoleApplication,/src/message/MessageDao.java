package message;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MessageDao {
    ArrayList<Message> getAllMessages()throws SQLException;
    void deleteMessage (Message message)throws SQLException;
    Integer getUnreadMessages(Integer userId)throws SQLException;
    ArrayList<Message> getMessagesByReceiver(Integer userId)throws SQLException;
    ArrayList<Message> getMessagesBySender(Integer userId)throws SQLException;
    void updateMessageBody(Message message, String body)throws SQLException;
    void insertMessage(Message message) throws SQLException;
    void updateMessageUnread(int id)throws SQLException;


}