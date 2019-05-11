package message;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileProcess {




        public void fileWrite(Message message) throws IOException {

            FileWriter fw = new FileWriter("message.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(String.format(" %30s %30s %30s %30s ", message.getSender().getEmail(), message.getReceiver().getEmail(), String.format(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), message.getDateOfSubmision()), message.getBody()));
            pw.close();
        }
}