package best2ask.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;
    @NotEmpty
    @Column(name="username")
    private String username;
    @NotEmpty
    @Size(min=6)
    @Column(name="password")
    private String password;
    @NotEmpty
    @Email
    @Column(name="email")
    private String email;
    @Column(name="enabled")
    private boolean enabled;
    @Column(name="role")
    private String role;


    public User(boolean enabled, String email, String password, String role, String username) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.role = role;
    }



    public User(User user)
    {
        this.username=user.username;
        this.password=user.password;
        this.email=user.email;
        this.enabled=user.enabled;
        this.role="user";
    }
    public User()
    {

    }

    public User(@NotEmpty @Size(min = 6, max = 30) String username, @NotEmpty @Size(min = 6, max = 30) String password) {
        this.username=username;
        this.password=password;
        this.email="";
        this.enabled=true;
        this.role="user";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }



    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
