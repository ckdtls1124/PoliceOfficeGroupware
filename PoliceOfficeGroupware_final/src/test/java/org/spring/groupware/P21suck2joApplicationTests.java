package org.spring.groupware;

import org.spring.groupware.controller.PoliceController;
import org.spring.groupware.repository.PoliceRepository;
import org.spring.groupware.service.MemorandumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class P21suck2joApplicationTests {

	@Autowired
	private PoliceRepository policeRepository;
	private PoliceController policeController;
	private MemorandumService memorandumService;


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

//	@Test
//	void showApprovingMemberList(){
//		List<ApprovingMemberAllDto> approvingMemberAllDtoList = memorandumService.showAllApprovingMemberNameDeptSelectedMemo();
//		Assert.isNull(approvingMemberAllDtoList, "THis is empty");
////		Assert.notNull(approvingMemberAllDtoList, "This is not empty");
//	}

}
