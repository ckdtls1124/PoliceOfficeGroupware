package org.spring.p21suck2jo.chatbot.dto.MovieComingSoonListDto;

import lombok.Data;

import java.util.List;

@Data
public class ComingSoonMovieResultList {

    private String totCnt;

    private String source;

    private List<ComingSoonMovieAllLists> movieList;

}
