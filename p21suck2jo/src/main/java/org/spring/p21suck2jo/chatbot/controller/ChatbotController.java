package org.spring.chatbot.controller;

import org.spring.chatbot.service.KomoranService;
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
		model.addAttribute("message", komoranService.nlpAnalyze(message));
		return "chatbot/botMessage";
	}

}
