package best2ask.repository;

import best2ask.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    @Override
    List<Question> findAll();
    Optional<Question> findById(Integer id);
}
