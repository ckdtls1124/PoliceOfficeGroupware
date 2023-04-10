package org.spring.p21suck2jo.chatbot.repository;

import org.spring.p21suck2jo.chatbot.entity.IntentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IntentionRepository extends JpaRepository<IntentionEntity, Long> {

	Optional<IntentionEntity> findByKeywordAndUpperNo(String token, IntentionEntity upper);

}
