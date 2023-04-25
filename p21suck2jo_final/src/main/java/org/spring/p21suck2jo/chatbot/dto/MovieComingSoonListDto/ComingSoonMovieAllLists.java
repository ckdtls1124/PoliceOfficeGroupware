package org.spring.p21suck2jo.chatbot.dto.MovieComingSoonListDto;

import lombok.Data;

import java.util.List;

@Data
public class ComingSoonMovieAllLists {

    private String movieCd;
    private String movieNm;
    private String movieNmEn;
    private String prdtYear;
    private String openDt;
    private String typeNm;
    private String prdtStatNm;
    private String nationAlt;
    private String genreAlt;
    private String repNationNm;
    private String repGenreNm;
    private List<ComingSoonMovieDirectorsDetail> directors;
    private List<ComingSoonMovieCompanyDetail> companys;





}
