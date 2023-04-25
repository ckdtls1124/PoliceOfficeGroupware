package org.spring.groupware.chatbot.repository;

import org.spring.groupware.chatbot.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
}
