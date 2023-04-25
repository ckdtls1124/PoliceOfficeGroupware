package org.spring.groupware.chatbot.repository;

import org.spring.groupware.chatbot.entity.IntentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IntentionRepository extends JpaRepository<IntentionEntity, Long> {

	Optional<IntentionEntity> findByKeywordAndUpperNo(String token, IntentionEntity upper);

}
