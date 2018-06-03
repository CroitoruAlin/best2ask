package best2ask.Controllers;

import best2ask.model.ChatMessage;
import best2ask.model.User;
import best2ask.services.MessageServices;
import best2ask.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private MessageServices messageServices;
    @Autowired
     private  UserServices userServices;

    @MessageMapping("/queue/{author}/{username}")
    public void chatPrivate(@Payload ChatMessage message, @DestinationVariable String username, @DestinationVariable String author)  {

        User autor=userServices.getUserByUsername(author);
        User receiver=userServices.getUserByUsername(username);
        message.setUserAuthor(autor.getUser_id());
        message.setUserReceiver(receiver.getUser_id());
        message.setName(author);
        messageServices.saveMessage(message);
        simpMessagingTemplate.convertAndSendToUser(username,"/queue/chat.message",message);

    }
}
