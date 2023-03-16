package org.project.groupware.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity {

	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createTime;

	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updateTime;

}
