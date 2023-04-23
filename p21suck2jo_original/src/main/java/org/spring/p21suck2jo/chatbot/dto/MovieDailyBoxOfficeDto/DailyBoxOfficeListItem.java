package org.spring.p21suck2jo.chatbot.dto.MovieDailyBoxOfficeDto;

import lombok.Data;

@Data
public class DailyBoxOfficeListItem {

    private String rnum; // 순번

    private String rank; // 해당일자의 박스오피스 순위

    private String rankInten; // 전일대비 순위의 증감분

    private String rankOldAndNew; // 랭킹에 신규진입여부“OLD” : 기존 , “NEW” : 신규

    private String movieCd; // 영화의 대표코드

    private String movieNm; // 영화명(국문)

    private String openDt; // 영화의 개봉일

    private String salesAmt; // 해당일의 매출액

    private String salesShare; // 해당일자 상영작의 매출총액 대비 해당 영화의 매출비율

    private String salesInten; // 전일 대비 매출액 증감분

    private String salesChange; // 전일 대비 매출액 증감 비율

    private String salesAcc; // 누적매출액

    private String audiCnt; // 해당일의 관객수

    private String audiInten; // 전일 대비 관객수 증감분

    private String audiChange; // 전일 대비 관객수 증감 비율

    private String audiAcc; // 누적관객수

    private String scrnCnt; // 해당일자에 상영한 스크린수

    private String showCnt; // 해당일자에 상영된 횟수
}
