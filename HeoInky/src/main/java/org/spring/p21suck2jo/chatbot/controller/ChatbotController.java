package org.spring.p21suck2jo.chatbot.controller;

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
	public String message(String message, Model model) throws Exception {
		model.addAttribute("messages", komoranService.nlpAnalyze(message));
		return "chatbot/bot-message";
	}

}
