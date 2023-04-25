package org.spring.groupware.dto;

import lombok.*;
import org.spring.groupware.entity.EventEntity;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventGroupDto {

	private Long eventGroupId;

	private String eventGroupName;

	private EventEntity eventList;

}
