package message;

public class Contact {
    private Integer userId;
    private Integer contactId;

    public Contact(Integer userId, Integer contactId){
        setUserId(userId);
        setContactId(contactId);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
}
