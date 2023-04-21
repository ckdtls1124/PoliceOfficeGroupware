package org.spring.p21suck2jo.chatbot.dto.MovieDailyBoxOfficeDto;

import lombok.Data;

import java.util.List;

@Data
public class DailyBoxOfficeListItemContainer {

    private String boxofficeType; // 박스오피스 종류

    private String showRange; // 박스오피스 조회 일자

    private List<DailyBoxOfficeListItem> dailyBoxOfficeList;

}
