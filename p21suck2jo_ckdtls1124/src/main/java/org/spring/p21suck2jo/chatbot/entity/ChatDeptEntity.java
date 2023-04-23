package org.spring.p21suck2jo.chatbot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Table(name = "chat_dept")
@AllArgsConstructor
@NoArgsConstructor
public class ChatDeptEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    @Column(nullable = false)
    private String deptName;

}
