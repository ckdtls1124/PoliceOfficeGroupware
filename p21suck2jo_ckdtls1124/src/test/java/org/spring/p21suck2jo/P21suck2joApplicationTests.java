package org.spring.p21suck2jo;

import org.junit.jupiter.api.Test;
import org.spring.p21suck2jo.chatbotMovie.dataReceiver.MovieAPIExplorerJava;
import org.spring.p21suck2jo.controller.PoliceController;
import org.spring.p21suck2jo.repository.PoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class P21suck2joApplicationTests {

	@Autowired
	private PoliceRepository policeRepository;
	private PoliceController policeController;


//	Kobis Movie API Java 코드 작동 테스트
//	@Test
//	void testMovieAPIExplorerJava() throws IOException {
//		String result = MovieAPIExplorerJava.getMovieList();
//	}

//	Kobis MovieAPI Java 코드 일간 박스오피스 조회 테스트
//	@Test
//	void testMovieAPIsearchTodayBoxoffice() throws IOException {
//		MovieAPIExplorerJava.getMovieDataWithTodayBoxOffice("20230415");
//	}

}
