package best2ask.repository;

import best2ask.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Integer>
{

    List<Comments> findByIdq(Integer idq);
}
