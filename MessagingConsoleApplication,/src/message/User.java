package message;

public class User {



    private Integer  id;
    private String name;
    private String surname;
    private String email;
    private String username ;
    private String password;
    private Integer role;


    public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRoleGuest(){
        return this.role.equals(3);
    }

    public boolean isRoleAdmin(){
        return this.role.equals(1);
    }

    public boolean isRoleUser(){
        return this.role.equals(2);
    }

    @Override
    public String toString() {
        return String.format("User   %d   %s   %s   %s   %s   %s   %s   ", id, name, surname, email, role, username, password);
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}


