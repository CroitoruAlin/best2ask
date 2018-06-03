package best2ask.services;

import best2ask.model.Question;
import best2ask.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionServices {
    @Autowired
    private QuestionRepository questionRepository;


    public List<Question> getAllQuestions()
    {
        return questionRepository.findAll();
    }
    public void saveQuestion(Question q){questionRepository.save(q);}

    public Question getQuestion(int id)
    {
        Optional<Question> o=questionRepository.findById(id);
        if(o.isPresent())
            return o.get();
        else
            return null;
    }
}
