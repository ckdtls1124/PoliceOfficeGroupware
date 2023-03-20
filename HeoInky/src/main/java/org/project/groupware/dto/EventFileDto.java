package org.project.groupware.dto;

import lombok.*;
import org.project.groupware.entity.EventEntity;
import org.project.groupware.entity.EventFileEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventFileDto {

	public Long eventFile_id;

	private String eventFileName;

	private EventEntity fileJoinEvent;

	private LocalDateTime createTime;

	private LocalDateTime updateTime;

}
