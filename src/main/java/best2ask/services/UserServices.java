package best2ask.services;


import best2ask.model.User;
import best2ask.model.UserCredentials;
import best2ask.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.*;

@Service
@Transactional
public class UserServices implements UserDetailsService {
    private UserRepository userRepository;


    public UserServices(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public void  saveUser(User user)
    {
        String passwordForSave=Criptare.hashPassword(user.getPassword());
       user.setPassword(passwordForSave);

        userRepository.save(user);
    }
    public User cautaUserCredentials(UserCredentials u)
    {
        User r=userRepository.findByUsername(u.getUsername());
        if(r!=null && Criptare.checkPassword(u.getPassword(),r.getPassword()))
        {
            return r;
        }
        else
            return null;
    }
    public User cautaUser(User u)
    {
        User result=userRepository.findByUsername(u.getUsername());
       return  result;

    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User r=userRepository.findByUsername(s);

        return new UserCredentials(r);
    }

    public User getUser(String email)
    {
        User r=userRepository.findByEmail(email);
        return r;
    }

    public User getUserByUsername(String username)
    {
        User r=userRepository.findByUsername(username);
        return  r;
    }
    public User getUserById(Integer id)
    {
        Connection con=null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/best2ask", "root", "root1234");
            PreparedStatement ps = con.prepareStatement("select * from users where user_id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                return new User(rs.getBoolean(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void setUserActive(User user,Boolean enabled)
    {
        user.setEnabled(enabled);
        userRepository.save(user);
    }

}
