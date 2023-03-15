package org.project.groupware.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
