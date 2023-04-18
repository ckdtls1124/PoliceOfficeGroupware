package org.spring.p21suck2jo.chatbotOriginal.controller;

import org.spring.p21suck2jo.chatbotOriginal.dto.DailyBoxOfficeListResult;
import org.spring.p21suck2jo.chatbotOriginal.dto.DailyBoxOfficeListItem;
import org.spring.p21suck2jo.chatbotOriginal.dto.MessageDto;
import org.spring.p21suck2jo.chatbotOriginal.service.ChatbotKomoranService;
import org.spring.p21suck2jo.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ChatbotController {

	@Autowired
	ChatbotKomoranService chatbotKomoranService;
	@Autowired
	private ReplyRepository replyRepository;


	@PostMapping("/goChatbot")
	public String message(String message, Model model) throws NullPointerException {

		MessageDto messages = chatbotKomoranService.contactNlpAnalyze(message);
		System.out.println(messages+" <<< ");

		model.addAttribute("messages",messages);
		model.addAttribute("dailyBoxOfficeList", new DailyBoxOfficeListItem());

		System.out.println("Answer : " + messages.getAnswer());
		System.out.println("키워드 : " + messages.getAnswer().getKeyword());
		System.out.println("답변 : " + messages.getAnswer().getContent());
		System.out.println("2차 답변 : " + messages.getAnswer().getSecondAnswer() + "\n");

		return "chatbot/bot-message";
	}

	@PostMapping("/goChatbotForMovie")
	public String messageForMovie(String dateAndTime, Model model) throws IOException {

		DailyBoxOfficeListResult dailyBoxOfficeListResult = chatbotKomoranService.findDailyBoxOfficeList(dateAndTime);
		model.addAttribute("dailyBoxOfficeData",dailyBoxOfficeListResult);

		return "chatbot/bot-message";
	}

}
