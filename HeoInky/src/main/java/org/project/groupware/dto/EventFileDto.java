package org.project.groupware.dto;

import lombok.*;
import org.project.groupware.entity.EventEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventFileDto {

	public Long eventFileId;

	private String eventFileName;

	private EventEntity fileJoinEvent;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

}
