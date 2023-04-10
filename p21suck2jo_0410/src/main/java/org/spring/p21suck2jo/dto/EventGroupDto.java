package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.EventEntity;

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
