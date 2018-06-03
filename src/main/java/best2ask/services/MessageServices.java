package best2ask.services;

import best2ask.model.ChatMessage;
import best2ask.model.User;
import best2ask.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageServices {

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    UserServices userServices;

    public void saveMessage(ChatMessage chatMessage)
    {
        chatRepository.save(chatMessage);
    }
    public void getMessages(int id1, int id2, List<Integer> la, List<String> lm)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/best2ask","root","root1234");
            PreparedStatement ps=con.prepareStatement("select content,author_user from (select content,author_user from (select content,time_sent,author_user from chatmessage where author_user=? and receiver_user=?"+
                    " union select content,time_sent,author_user from chatmessage where author_user=? and receiver_user=? ) test1 order by time_sent desc) test2  limit 5;");
            ps.setInt(1,id1);
            ps.setInt(2,id2);
            ps.setInt(3,id2);
            ps.setInt(4,id1);

            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                la.add(rs.getInt(2));
                lm.add(rs.getString(1));
            }
            ;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
