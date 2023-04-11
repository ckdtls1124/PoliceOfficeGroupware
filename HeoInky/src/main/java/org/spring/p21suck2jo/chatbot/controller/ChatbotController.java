package org.spring.p21suck2jo.chatbot.controller;

import org.spring.p21suck2jo.chatbot.dto.MessageDto;
import org.spring.p21suck2jo.chatbot.service.KomoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChatbotController {

	@Autowired
	KomoranService komoranService;

	@PostMapping("/goChatbot")
	public String message(String message, Model model) throws NullPointerException {

		MessageDto messages = komoranService.nlpAnalyze(message);
		System.out.println(messages+" <<< ");

		model.addAttribute("messages",messages);

		System.out.println("Answer : " + messages.getAnswer());
		System.out.println("키워드 : " + messages.getAnswer().getKeyword());
		System.out.println("답변 : " + messages.getAnswer().getContent());
		System.out.println("2차 답변 : " + messages.getAnswer().getSecondAnswer() + "\n");

		return "chatbot/bot-message";
	}

}
