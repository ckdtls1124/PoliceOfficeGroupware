package org.spring.chatbot.repository;

import org.spring.chatbot.entity.IntentionEntity;

import java.util.Optional;

public interface IntentionRepository {

	Optional<IntentionEntity> findByNameAndUpper(String token, IntentionEntity upper);

}
