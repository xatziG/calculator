package message;

import java.sql.Timestamp;


public class Message {


private Integer id;
private User sender;
private boolean unread;
private String body;
private User receiver;
private Timestamp dateOfSubmision ;




    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getSender() {
        return sender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public Timestamp getDateOfSubmision() {
        return dateOfSubmision;
    }

    public void setDateOfSubmision(Timestamp dateOfSubmision) {
        this.dateOfSubmision = dateOfSubmision;
    }
}

