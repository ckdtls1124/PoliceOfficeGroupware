package org.project.groupware.dto;

import lombok.*;
import org.project.groupware.entity.EventEntity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventGroupDto {

	private Long eventGroup_id;

	private String evenGroupName;

	private EventEntity eventList;

}
