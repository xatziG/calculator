package message;



import java.util.ArrayList;
import java.util.Scanner;


public class Actions {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    UserDaoImpl userDao = new UserDaoImpl();
    private Scanner scanner = new Scanner(System.in);
    private Scanner scanner1=new Scanner(System.in).useDelimiter("\n");
    MessageDaoImpl messageDao=new MessageDaoImpl();
    ContactDaoImpl contactDao=new ContactDaoImpl();
    ConnectionFactory connectionFactory = new ConnectionFactory();
    private User user;


    public void loginAction(){
        System.out.println("Δώστε username:");
        String username = scanner.next();
        try {
            this.user = userDao.getUserByUsername(username);
            if(this.user.getUsername() != null){
                System.out.println("Δώστε password:");
                String password = scanner.next();
                if(password.equals(user.getPassword())){
                    this.loggedInUserAction();
                }else {
                    System.out.println("o κωδικός ειναι λάθος!");
                }
            }else {
                System.out.println("Δεν υπάρχει user με αυτο το username");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void registerAction(){
        User newUser = new User();
        System.out.println("Δώστε Όνομα");
        newUser.setName(scanner.next());
        System.out.println("Δώστε Επίθετο");
        newUser.setSurname(scanner.next());
        System.out.println("Δώστε email");
        newUser.setEmail(scanner.next());
        System.out.println("Δώστε username");
        newUser.setUsername(scanner.next());
        System.out.println("Δώστε password");
        newUser.setPassword(scanner.next());
        System.out.println("Δωστε ρολο στον χρηστη.πατηστε 1 για Admin,2.για User,3. για Guest");
        newUser.setRole(scanner.nextInt());
        try {
            if(!this.userDao.isUserExistByEmail(newUser.getEmail())) {
                userDao.insertUser(newUser);
            }else {
                System.out.println("Υπάρχει ήδη χρήστης με αυτό το email.");
            }
            System.out.println("Ο χρήστης προστέθηκε με επιτυχία!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loggedInUserAction(){
        int option = 0;
        try {
            Integer unreadMessages = this.messageDao.getUnreadMessages(this.getUser().getId());
            if(unreadMessages > 0){
                System.out.println(ANSI_RED +"Έχετε " + String.valueOf(unreadMessages) + " αδιαβαστa μηνύματα"+ANSI_RESET);
            }else {
                System.out.println("Δεν έχετε αδιαβαστa μηνύματα");
            }
            if (this.getUser().isRoleAdmin()) {
                System.out.println("Συνδεθήκατε σαν superadmin!");
                do{
                    System.out.println("1.Νέο μήνυμα, 2.Εισερχόμενα, 3.Απεσταλμένα, 4.Διαχείρηση των επαφών μου, 5. Προβολη και διαχείρηση χρηστών εφαρμογής, 6.Διαχείρηση όλων των μηνυμάτων, 7.Αποσύνδεση");
                    option = CommonUtilities.scannerReadNumber(this.scanner, 1, 7);
                    if(option == 1){
                        this.createMessage();
                    }else if(option == 2) {
                        this.getInbox();
                    }else if(option==3) {
                        this.getSent();
                    }else if(option==4) {
                        this.manageContacts();
                    }else if (option==5){
                        this.adminManageUsers();
                    }else if (option==6)
                        this.manageAllMessages();
                }while(option !=7);
                System.exit(0);
            } else if (this.getUser().isRoleUser()) {
                //simple user actions
                System.out.println("Συνδεθήκατε σαν user!");
                do {
                    System.out.println("1.Νέο μήνυμα, 2.Εισερχόμενα, 3.Απεσταλμένα, 4.Διαχείρηση των επαφών μου, 5.Διαχείρηση όλων των μηνυμάτων, 6.Αποσθυνδεση");
                    option = CommonUtilities.scannerReadNumber(this.scanner,1,6);
                    if(option == 1){
                        this.createMessage();
                    }else if(option == 2) {
                        this.getInbox();
                    }else if(option==3){
                        this.getSent();
                    }else if(option == 4){
                        this.manageContacts();
                    }else  if(option==5){
                         this.manageAllMessages();
                    }
                }while (option != 6);
                System.exit(0);
            }else if(this.getUser().isRoleGuest()){
                System.out.println("Συνδεθήκατε σαν Guest!");
                do {
                    System.out.println("1.Νέο μήνυμα, 2.Εισερχόμενα, 3.Απεσταλμένα, 4.Διαχείρηση των επαφών μου, 5.Προβολη ολων των μηνυματων, 6.Αποσθυνδεση");
                    option = CommonUtilities.scannerReadNumber(scanner,1,6);
                    if(option == 1){
                        this.createMessage();
                    }else if(option == 2) {
                        this.getInbox();
                    }else if(option==3){
                        this.getSent();
                    }else if(option == 4){
                        this.manageContacts();
                    }else if (option==5){
                        ArrayList<Message> messageArrayList = new ArrayList<>();
                        messageArrayList = this.messageDao.getAllMessages();
                        CommonUtilities.printMessagesInTableForm(messageArrayList);
                    }


                }while (option != 6);
                System.exit(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void getInbox(){
        try {
            ArrayList<Message> messageArrayList = this.messageDao.getMessagesByReceiver(this.getUser().getId());
            CommonUtilities.printMessagesInTableForm(messageArrayList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getSent(){
        try{
            ArrayList<Message> messageArrayList= this.messageDao.getMessagesBySender(this.getUser().getId());
            CommonUtilities.printMessagesInTableForm(messageArrayList,"receiver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createMessage(){
        Message message = new Message();
        FileProcess fileProcess=new FileProcess();
        try{
            System.out.println("Δώστε email παραλήπτη");
            String receiverEmail = scanner.next();
            User receiver = this.userDao.getUserByEmail(receiverEmail);
            if(CommonUtilities.isNotEmpty(receiver.getEmail())){
                System.out.println("Πληκτρολογήστε το μήνυμα.");
                String messageBody = scanner1.next();
                receiver.setEmail(receiverEmail);
                message.setReceiver(receiver);
                message.setSender(this.getUser());
                message.setBody(messageBody);
                this.messageDao.insertMessage(message);
                fileProcess.fileWrite(message);
            System.out.println("Το μήνυμα στάλθηκε επιτυχώς!");
            }else {
                System.out.println("Δεν υπάρχει χρήστης με αυτό το email");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        this.loggedInUserAction();
    }

    private void manageContacts(){
        int option;
        ArrayList<User> myContactsArrayList = new ArrayList<>();
        try {
            myContactsArrayList = this.contactDao.getContacts(getUser().getId());
        }catch (Exception e){
            e.printStackTrace();
        }
        do {
            System.out.println("1.Προσθήκη νέας επαφής, 2.Προβολή επαφών, 3.Διαγραφή επαφής 4.Επιστροφή");
           option= CommonUtilities.scannerReadNumber(scanner,1,4);

            if(option == 1){
                this.createContact();
            }else if(option == 2){
                CommonUtilities.printUsersInTableForm(myContactsArrayList);
            }else if(option == 3){
                CommonUtilities.printUsersInTableForm(myContactsArrayList);
                System.out.println("Επιλέξτε το νούμερο(id) της επαφής");
                int userId= CommonUtilities.scannerReadNumber(this.scanner);

                try {
                    this.contactDao.deleteContact(new Contact(this.getUser().getId(), myContactsArrayList.get(userId-1).getId()));
                    myContactsArrayList = this.contactDao.getContacts(getUser().getId());
                    System.out.println("Η επαφή διαγράφηκε με επιτυχία");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }while (option != 4);
    }

    private void createContact(){
        String username;
        System.out.println("Δώστε το username της νέας επαφής");
        username = scanner.next();
        try{
            User contactUser = this.userDao.getUserByUsername(username);
            if(CommonUtilities.isNotEmpty(contactUser)){
                Contact contact = new Contact(this.getUser().getId(), contactUser.getId());
                this.contactDao.insertContact(contact);
                System.out.println("Η επαφή προστέθηκε με επιτυχία!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        manageContacts();
    }

    private void printContacts(){
        try {
            ArrayList<User> myContacts = this.contactDao.getContacts(getUser().getId());
            CommonUtilities.printUsersInTableForm(myContacts);
        }catch (Exception e){
            e.printStackTrace();
        }
        manageContacts();
    }


    private void adminManageUsers(){
        int option =0;
        try{
            ArrayList<User> userArrayList=this.userDao.getAllUser();
            CommonUtilities.printUsersInTableForm(userArrayList);
            do {
                System.out.println("1.Προσθήκη νέου χρήστη, 2. Επεξεργασία χρήστη, 3.Διαγραφή χρήστη, 4.Προβολή χρηστών,5. Eπιστροφή");
               option= CommonUtilities.scannerReadNumber(scanner);
               // option = scanner.nextInt();
                if (option==1){
                    this.registerAction();
                }else if(option==2) {
                    this.adminUpdateUser(userArrayList);
                }else if (option==3) {
                    this.deleteUser(userArrayList);
                }else if (option==4) {
                    CommonUtilities.printUsersInTableForm(userArrayList);
                }
            }while(option!=5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void deleteUser(ArrayList<User> userArrayList){
        System.out.println("επιλεξτε τον χρηστη που θελετε να διαγραψετε");
        int userArrayId = scanner.nextInt();
        try {
            CommonUtilities.printUsersInTableForm(userArrayList.get(userArrayId-1));
            userDao.deleteUser(userArrayList.get(userArrayId-1));
            userArrayList.remove(userArrayId-1);
            System.out.println("ο χρηστης διαγραφτηκε επιτυχώς!!");
        }catch (Exception e) {
            e.printStackTrace();

        }}
    private void adminUpdateUser(ArrayList<User> userArrayList) {
        int option=0;
        int userArrayId = scanner.nextInt();
        do{
            try {

                CommonUtilities.printUsersInTableForm(userArrayList.get(userArrayId-1));
                System.out.println("επιλεξτε τον χρηστη που θελετε να κανετε update");

                System.out.println("Πατήστε 1 για να αλλάξετε το όνομα πατήστε 2 για να αλλάξετε το Επίθετο πατηστε 3 για να αλλάξετε το Username πατήστε " +
                        "4. για να αλλάξετε το email 5.για να αλλαξετε το ρολο του χρηστη, 6. για να επιστρέψετε");
                option =scanner.nextInt();
                if (option==1){
                    System.out.println("Δώστε νέο όνομα");
                    String name = scanner.next();
                    userArrayList.get(userArrayId-1).setName(name);
                    userDao.updateUser(userArrayList.get(userArrayId-1));
                    System.out.println("Τα στοιχεία του χρήστη ανανεώθηκαν!!");

                } else if(option ==2){
                    System.out.println("Δωσε νεο Επιθετο");
                    String surname = scanner.next();
                    userArrayList.get(userArrayId).setSurname(surname);
                    userDao.updateUser(userArrayList.get(userArrayId-1));
                    System.out.println("Τα στοιχεία του χρήστη ανανεώθηκαν!!");
                }else if (option==3){
                    System.out.println ("Δωσε νεο username ");
                    String username =scanner.next();
                    userArrayList.get(userArrayId).setUsername(username);
                    userDao.updateUser(userArrayList.get(userArrayId-1));
                    System.out.println("Τα στοιχεία του χρήστη ανανεώθηκαν!!");
                }else if (option==4){
                    System.out.println("Δωσε νεο email");
                    String email =scanner.next();
                    userArrayList.get(userArrayId).setEmail(email);
                    userDao.updateUser(userArrayList.get(userArrayId-1));
                    System.out.println("Τα στοιχεία του χρήστη ανανεώθηκαν!!");

                }else if (option==5){
                    System.out.println("Αλλαξε τον ρολο του χρηστη");
                    Integer role =scanner.nextInt();
                    userArrayList.get(userArrayId).setRole(role);
                    userDao.updateUser(userArrayList.get(userArrayId-1));
                    System.out.println("Τα στοιχεία του χρήστη ανανεώθηκαν!!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }while (option!=6);
    }

    private void manageAllMessages(){
        int option = 0;
        int messageId;
        ArrayList<Message> messageArrayList = new ArrayList<>();
        try{
            messageArrayList = this.messageDao.getAllMessages();

            do{
                System.out.println("Επιλέξτε ενέργεια:");
                if(this.getUser().isRoleAdmin()) {
                    System.out.println("1.Προβολή όλων των μηνυμάτων, 2.Επεξεργασία μηνύματος, 3.Διαγραφή μηνύματος, 4.Επιστροφή");
                }else if(this.getUser().isRoleUser()){
                    System.out.println("1.Προβολή όλων των μηνυμάτων, 2.Επεξεργασία μηνύματος, 4.Επιστροφή");
                }
               option= CommonUtilities.scannerReadNumber(this.scanner);

                if(option == 1){
                    CommonUtilities.printMessagesInTableForm(messageArrayList);
                }else if(option == 2 || option == 3){
                    CommonUtilities.printMessagesInTableForm(messageArrayList);
                    System.out.println("Επιλέξτε το νούμερο(id) του μηνύματος");
                    messageId= CommonUtilities.scannerReadNumber(this.scanner);
                    if(option == 2) {
                        System.out.println("Γράψτε νέο κείμενο(body) για το μήνυμα");
                        scanner.nextLine();
                        String messageBody = this.scanner.nextLine();
                        this.messageDao.updateMessageBody(messageArrayList.get(messageId - 1), messageBody);
                        System.out.println("Η αλλαγή έγινε με επιτυχία");

                    }else if(this.user.isRoleAdmin()) {
                        this.messageDao.deleteMessage(messageArrayList.get(messageId-1));
                        System.out.println("Η διαγραφή έγινε με επιτυχία");
                    }
                    messageArrayList = this.messageDao.getAllMessages();
                }
            }while (option != 4);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
