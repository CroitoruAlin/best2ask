package best2ask.repository;

import best2ask.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<ChatMessage,Integer> {


}
