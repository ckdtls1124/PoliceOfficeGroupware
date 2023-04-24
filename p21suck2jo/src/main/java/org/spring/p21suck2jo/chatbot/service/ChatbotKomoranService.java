package org.spring.p21suck2jo.chatbot.service;

import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import org.spring.p21suck2jo.chatbot.dto.AnswerDto;
import org.spring.p21suck2jo.chatbot.dto.MessageDto;
import org.spring.p21suck2jo.chatbot.dto.MovieComingSoonListDto.ComingSoonMovieAllLists;
import org.spring.p21suck2jo.chatbot.dto.MovieComingSoonListDto.ComingSoonMovieNmOpenDtContainer;
import org.spring.p21suck2jo.chatbot.dto.MovieComingSoonListDto.ComingSoonMovieResultContainer;
import org.spring.p21suck2jo.chatbot.dto.MovieDailyBoxOfficeDto.DailyBoxOfficeListContainer;
import org.spring.p21suck2jo.chatbot.dto.SecondAnswer;
import org.spring.p21suck2jo.chatbot.entity.AnswerEntity;
import org.spring.p21suck2jo.chatbot.entity.IntentionEntity;
import org.spring.p21suck2jo.chatbot.entity.OfficerEntity;
import org.spring.p21suck2jo.chatbot.repository.IntentionRepository;
import org.spring.p21suck2jo.chatbot.repository.OfficerRepository;
import org.spring.p21suck2jo.movieAPIUsage.dataReceiver.MovieAPIExplorerJava;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatbotKomoranService {

    @Autowired
    private Komoran komoran;

    public MessageDto contactNlpAnalyze(String message) {

        KomoranResult result = komoran.analyze(message);
        Set<String> nouns = result.getNouns().stream().collect(Collectors.toSet());

        nouns.forEach((noun) -> {
            System.out.println(">>>> " + noun);
        });


        System.out.println("1. 분석된 명사 : " + nouns + " -> DB에 있는지 확인");
        return contactAnalyzeToken(nouns);

    }

    private MessageDto contactAnalyzeToken(Set<String> nouns) {

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("a H:mm");


        MessageDto messageDto = MessageDto.builder().time(today.format(timeFormatter)).build();

        for (String token : nouns) {

            Optional<IntentionEntity> result = decisionTree(token, null);

            if (result.isEmpty()) continue;
            Set<String> next = nouns.stream().collect(Collectors.toSet());

            next.remove(token);

            AnswerDto answer = contactAnalyzeToken(next, result).answerDto();

            if (token.contains("안녕")) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
                messageDto.today(today.format(dateFormatter));
            } else if (token.contains("연락처")) {
                //연락처 키워드가 있으면 -> analyzeTokenIsPhone(next)
                SecondAnswer phone = contactAnalyzeTokenIsPhone(next);
                System.out.println("phone -> " + phone);
                answer.phone(phone);
            }

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
            messageDto.today(today.format(dateFormatter));

            messageDto.answer(answer);
            return messageDto;
        }

        //분석 명사들이 등록한 의도와 일치하는게 존재하지 않을경우 null "기타 혹은 null일 경우"
        AnswerDto answer = decisionTree("기타", null).get().getAnswerId().answerDto();
        messageDto.answer(answer);
        return messageDto;

    }

    // 1차
    @Autowired
    IntentionRepository intention;

    //의도가 존재하는지 DB에서 파악 -> 키워드 값이 존재 하는지 -> DB에 키워드가 있는지 확인
    private Optional<IntentionEntity> decisionTree(String token, IntentionEntity upper) {
        System.out.println("의도 파악 Token(DB에서 확인) : " + token);
        return intention.findByKeywordAndUpperNo(token, upper);
    }

    //1차 의도가 존재하면
    //하위 의도가 존재하는지 파악
    private AnswerEntity contactAnalyzeToken(Set<String> next, Optional<IntentionEntity> upper) {

        for (String token : next) {

            Optional<IntentionEntity> result = decisionTree(token, upper.get());
            if (result.isEmpty()) continue;
            return result.get().getAnswerId();
        }
        return upper.get().getAnswerId();
    }


    @Autowired
    OfficerRepository officer;

    private SecondAnswer contactAnalyzeTokenIsPhone(Set<String> next) {

        for (String name : next) {

            Optional<OfficerEntity> m = officer.findByOfficerName(name);

            if (m.isEmpty()) continue;
            //존재하면
            String deptName = m.get().getDeptId().getDeptName();
            String officerPhone = m.get().getOfficerPhone();
            String officerName = m.get().getOfficerName();

            System.out.println("deptName : " + deptName);
            System.out.println("officerPhone : " + officerPhone);
            System.out.println("officerName : " + officerName);

            return SecondAnswer.builder().deptName(deptName).officerPhone(officerPhone).officerName(officerName).build();
        }
        return null;
    }


//    Movie API ========================================================================

    public DailyBoxOfficeListContainer findDailyBoxOfficeList(String dateAndTime) throws IOException {
        String dailyListString = MovieAPIExplorerJava.getMovieDataWithSelectedDateBoxOffice(dateAndTime);
        DailyBoxOfficeListContainer dailyListInResult = MovieAPIExplorerJava.returnDailyMovieBoxOfficeList(dailyListString);

        return dailyListInResult;
    }

    public String findComingSoonMovies() throws IOException {
        String comingSoonMoviesList = MovieAPIExplorerJava.getComingSoonMovies();

        return comingSoonMoviesList;
    }

    public MessageDto showCurrentDateTime() {
        MessageDto messageDto = new MessageDto();
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        messageDto.today(today.format(dateFormatter));

        return messageDto;
    }

    public int returnWeekNumBasedOnSubmittedDate(String term) {
        LocalDateTime toLocalDateTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String todayString = toLocalDateTime.format(dateFormatter);

        LocalDate today = this.stringDateTimeLocalDateTimeFormatter(todayString);
        int result = 0;
        if (term.contains("이번")) {
            result = returnTheWeekNumberOfCertainDate(today);
        } else if (term.contains("다음")) {
//            '다음' 단어가 변수에 포함되어 있으면, '다음 주'로 인식하고, 현재 날짜에 7일을 더하여, 다음주를 잡는다.
            LocalDate nextWeek = today.plusDays(7);
            result = returnTheWeekNumberOfCertainDate(nextWeek);
        }

        return result;
    }


    public Map<String, String> returnComingSoonMoviesByCertainTerm(String term) throws IOException {
        String comingSoonMovieList = this.findComingSoonMovies();
        ComingSoonMovieResultContainer result = MovieAPIExplorerJava.returnComingSoonMovieList(comingSoonMovieList);

        int weekNumOfSubmittedDate = this.returnWeekNumBasedOnSubmittedDate(term);
        Map<String, String> weekNumOfFoundMovieOpenDt = this.listOfOpenDateOfEachMovie(result);

        Map<String, String> filteredMapOfComingSoonMovies = new HashMap<>();
        for (String key : weekNumOfFoundMovieOpenDt.keySet()) {
            if (this.returnTheWeekNumberOfCertainDate(this.stringDateTimeLocalDateTimeFormatter(key)) == weekNumOfSubmittedDate) {
                filteredMapOfComingSoonMovies.put(key, weekNumOfFoundMovieOpenDt.get(key));
            }
        }


        return filteredMapOfComingSoonMovies;
    }

    public int returnTheWeekNumberOfCertainDate(LocalDate today) {
        int weekOfYear = today.get(WeekFields.ISO.weekOfYear());
        return weekOfYear;
    }

    public LocalDate stringDateTimeLocalDateTimeFormatter(String stringLocalDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate formattedLocalDateTime = LocalDate.parse(stringLocalDateTime, formatter);
        return formattedLocalDateTime;
    }

    public Map<String, String> listOfOpenDateOfEachMovie(ComingSoonMovieResultContainer result) {
        //        출력된 값 중에서, 영화 제목과 개봉일을 Map으로 변환
        Map<String, String> mapMovieNmOpenDt = new HashMap<>();
        for (ComingSoonMovieAllLists movieDetail : result.getMovieListResult().getMovieList()) {
            String eachMovieOpenDate = movieDetail.getOpenDt();
            String eachMovieNm = movieDetail.getMovieNm();

            ComingSoonMovieNmOpenDtContainer comingSoonMovieNmOpenDtContainer = new ComingSoonMovieNmOpenDtContainer(eachMovieOpenDate, eachMovieNm);
            mapMovieNmOpenDt.put(comingSoonMovieNmOpenDtContainer.getOpenDt(), comingSoonMovieNmOpenDtContainer.getMovieNm());
        }
        return mapMovieNmOpenDt;
    }


}
