package org.spring.p21suck2jo.dto;

import lombok.*;
import org.spring.p21suck2jo.entity.EventEntity;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

	private Long personId;

	private String personName;

	private EventEntity eventList;

}
