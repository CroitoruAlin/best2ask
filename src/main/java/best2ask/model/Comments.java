package best2ask.model;


import javax.naming.Name;
import javax.persistence.*;


@Table
@Entity(name="comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idcomments;
    @Column(name="content")
    private String content;
    @Column(name="id_question")
    private int idq;
    @Column(name="iduser")
    private int idu;

    public Comments() {
    }

    public Comments(String content, int idq, int idu) {

        this.content = content;
        this.idq = idq;
        this.idu = idu;
    }

    public int getIdcomments() {
        return idcomments;
    }

    public void setIdcomments(int idcomments) {
        this.idcomments = idcomments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdq() {
        return idq;
    }

    public void setIdq(int idq) {
        this.idq = idq;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }
}
