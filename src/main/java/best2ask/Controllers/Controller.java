package best2ask.Controllers;



import best2ask.model.Comments;
import best2ask.model.Question;
import best2ask.model.User;
import best2ask.model.UserCredentials;
import best2ask.services.CommentsServices;
import best2ask.services.MessageServices;
import best2ask.services.QuestionServices;
import best2ask.services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
@SessionAttributes("user")
public class Controller {


    @Autowired
    private QuestionServices questionServices;

    @Autowired
    private UserServices userServices;
    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;
    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    private CommentsServices commentsServices;
    @Autowired
    private MessageServices messageServices;

    @RequestMapping(value="/",method= RequestMethod.GET)
    public String home()
    {

        return "redirect:/login";
    }

    @RequestMapping(value="/register",method=RequestMethod.GET)

    public String save(Model model)
    {

        model.addAttribute("user",new User());
        return "register";
    }
    @RequestMapping(value="/register",method=RequestMethod.POST)

    public String postRegister(@Valid @ModelAttribute User u, BindingResult bindingResult, WebRequest request) {
        User result = userServices.cautaUser(u);
        if (result == null && !bindingResult.hasErrors()) {
            u.setEnabled(true);
            userServices.saveUser(u);
             return "redirect:/login";}
        else
            return "register";

    }

    @RequestMapping (value = "/login",method = RequestMethod.GET)
    public String login(Model model, SessionStatus status, HttpServletRequest request)
    {

        status.setComplete();
        model.addAttribute("userCredentials",new UserCredentials());
        return "login";
    }


    /*@RequestMapping(value="/doLogin",method = RequestMethod.POST)
    public String doLogin(@ModelAttribute("userCredentials") UserCredentials u, Model model )
    {
        User r=userServices.cautaUserCredentials(u);
        if(r!=null) {
            model.addAttribute("user",r);
            return "redirect:/afterLogin";

        }
         else
            return "redirect:/login";
    }*/
    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(SessionStatus status)
    {


        status.setComplete();
        return "redirect:/login";
    }
    @RequestMapping(value="/afterLogin",method = RequestMethod.POST)
    public String afterLogin(Model model)
    {
        org.springframework.security.core.Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        User r= (User) auth.getPrincipal();
        model.addAttribute("userLogged",r);
         return "afterLogin";

    }
    @RequestMapping(value="/afterLogin",method = RequestMethod.GET)
    public String afterLoginGet(Model model)
    {
        org.springframework.security.core.Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        User r= (User) auth.getPrincipal();
        model.addAttribute("userLogged",r);
        return "afterLogin";

    }
    @RequestMapping(value="/chat",method = RequestMethod.GET)
    public  String getChat(Model model, Authentication auth)
    {
        User currentUser= (User) auth.getPrincipal();
        List<Object> principals=sessionRegistry.getAllPrincipals();
        List<String> usernames=new ArrayList<>();
        model.addAttribute("currentUser",currentUser.getUsername());

        for(Object p:principals)
            if(p instanceof User)
                if(((User) p).getUsername()!= currentUser.getUsername())
                    usernames.add(((User) p).getUsername());
        model.addAttribute("usersList",usernames);
        return "chat";
    }
    @RequestMapping(value = "/chat/messaging/{username}",method = RequestMethod.GET)
    public String chatInstance(@PathVariable(name="username") String username,Model model,Authentication auth)
    {
        User currentUser= (User) auth.getPrincipal();
        User otherUser= userServices.getUserByUsername(username);
        List<Integer> la=new ArrayList<>();
        List<String> lm=new ArrayList<>();
        User r=userServices.getUserByUsername(currentUser.getUsername());
        messageServices.getMessages(r.getUser_id(),otherUser.getUser_id(),la,lm);
        List<String> la1=new ArrayList<>();

        la.forEach(id->{
            if(id.equals(r.getUser_id()))
                la1.add(r.getUsername());
            else
                la1.add(username);
        });

        System.out.println(la);
        model.addAttribute("otherUser",username);
        model.addAttribute("currentUser",currentUser.getUsername());
        model.addAttribute("listAuthors",la1);
        model.addAttribute("Messages",lm);

        return "messaging";
    }

    @RequestMapping(value = "/questions",method = RequestMethod.GET)
    public String showQuestions(Model model)
    {
        Question q=new Question();
        model.addAttribute("question",q);
        model.addAttribute("questions",questionServices.getAllQuestions());
        return "questions";
    }
    @RequestMapping(value = "/questions",method = RequestMethod.POST)
    public String addQuestions(@ModelAttribute("question") Question q,Model model,HttpServletRequest request)
    {


        User u=userServices.getUserByUsername(request.getUserPrincipal().getName());
        System.out.println(u.getUser_id());
        q.setUser_id(u.getUser_id());
        questionServices.saveQuestion(q);
        model.addAttribute("questions",questionServices.getAllQuestions());
        return "redirect:/questions";
    }

    @RequestMapping(value = "/questions/{id}",method = RequestMethod.GET)
    public String questionAnswer(@PathVariable int id,Model model)
    {
        List<Comments> l=commentsServices.getAnswers(id);
        List<User> lu=new ArrayList<>();
        for(Comments c:l)
            lu.add(userServices.getUserById(c.getIdu()));
        Question q=questionServices.getQuestion(id);
        Comments a= new Comments();
        model.addAttribute("listAnswers",l);
        model.addAttribute("question",q);
        model.addAttribute("answer",a);
        int user_question=q.getUser_id();
        User author=userServices.getUserById(user_question);

        model.addAttribute("authorUser",author);
        model.addAttribute("listUsers",lu);
        return "question";
    }
    @RequestMapping(value = "/questions/{id}",method = RequestMethod.POST)
    public String questionPostAnswer(@ModelAttribute("answer") Comments a,@PathVariable int id,HttpServletRequest request)
    {

        User u=userServices.getUserByUsername(request.getUserPrincipal().getName());
        a.setIdu(u.getUser_id());
        System.out.println(u);
        a.setIdq(id);
        commentsServices.saveComment(a);
        return "redirect:/questions/"+id;
    }

}
