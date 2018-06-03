package best2ask.services;

import best2ask.model.Comments;
import best2ask.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentsServices  {

    @Autowired
    private CommentsRepository commentsRepository;

    public List<Comments> getAnswers(int id)
    {
        return commentsRepository.findByIdq(id);
    }
    public void saveComment(Comments c)
    {
        commentsRepository.save(c);
    }
}
