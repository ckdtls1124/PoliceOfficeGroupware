package org.spring.groupware.dto;

import lombok.*;
import org.spring.groupware.entity.EventEntity;

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
