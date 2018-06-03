package best2ask.model;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="chatmessage")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "content")
    private String content;
   @Column(name="author_user")
    private int userAuthor;
    @Column(name="receiver_user")
    private int userReceiver;
   @Column(name="time_sent")
    private Date time_sent;
   @Column (name = "name_author")
   private String name;



    public ChatMessage() {
        this.time_sent=new Date();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserAuthor() {
        return userAuthor;
    }

    public void setUserAuthor(int userAuthor) {
        this.userAuthor = userAuthor;
    }

    public int getUserReceiver() {
        return userReceiver;
    }

    public void setUserReceiver(int userReceiver) {
        this.userReceiver = userReceiver;
    }

    public Date getTime_sent() {
        return time_sent;
    }

    public void setTime_sent(Date time_sent) {
        this.time_sent = time_sent;
    }
}
