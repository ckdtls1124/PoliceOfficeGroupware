package org.spring.p21suck2jo.chatbot.controller;

import org.spring.p21suck2jo.chatbot.dto.MessageDto;
import org.spring.p21suck2jo.chatbot.dto.MovieDailyBoxOfficeDto.DailyBoxOfficeListContainer;
import org.spring.p21suck2jo.chatbot.dto.MovieDailyBoxOfficeDto.DailyBoxOfficeListItemContainer;
import org.spring.p21suck2jo.chatbot.service.ChatbotKomoranService;
import org.spring.p21suck2jo.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.Map;

@Controller
public class ChatbotController {

    @Autowired
    ChatbotKomoranService chatbotKomoranService;
    @Autowired
    private ReplyRepository replyRepository;


    @PostMapping("/goChatbot")
    public String message(String message, Model model) throws NullPointerException {

        MessageDto messages = chatbotKomoranService.contactNlpAnalyze(message);
        System.out.println(messages + " <<< ");

        model.addAttribute("messages", messages);
        model.addAttribute("dailyBoxOfficeList", new DailyBoxOfficeListItemContainer());

        System.out.println("Answer : " + messages.getAnswer());
        System.out.println("키워드 : " + messages.getAnswer().getKeyword());
        System.out.println("답변 : " + messages.getAnswer().getContent());
        System.out.println("2차 답변 : " + messages.getAnswer().getSecondAnswer() + "\n");

        return "chatbot/bot-message";
    }

    @PostMapping("/findMovieDailyBoxOffice")
    public String movieDailyBoxOffice(String dateAndTime, Model model) throws IOException {

        MessageDto messageDto = chatbotKomoranService.showCurrentDateTime();
        DailyBoxOfficeListContainer dailyBoxOfficeListResult = chatbotKomoranService.findDailyBoxOfficeList(dateAndTime);
        model.addAttribute("messages", messageDto);
        model.addAttribute("dailyBoxOfficeData", dailyBoxOfficeListResult);

        return "chatbot/bot-message";
    }

    @PostMapping("/findMovieComingSoon")
    public String movieComingSoon(String term, Model model) throws IOException {
        MessageDto messageDto = chatbotKomoranService.showCurrentDateTime();

        int selectFindWeek = chatbotKomoranService.returnWeekNumBasedOnSubmittedDate(term);
        Map<String, String> filteredMapOfComingSoonMovies =  chatbotKomoranService.returnComingSoonMoviesByCertainTerm(term);

        model.addAttribute("messages", messageDto);
        model.addAttribute("thisWeekNum", selectFindWeek);
        model.addAttribute("comingSoonMovieList", filteredMapOfComingSoonMovies);

        return "chatbot/bot-message";
    }


}
