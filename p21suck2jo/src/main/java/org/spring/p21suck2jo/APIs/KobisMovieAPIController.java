package org.spring.p21suck2jo.APIs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/movie")
public class KobisMovieAPIController {

//    http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20230411

    @GetMapping("/KobisMovieAPI")
    public String tdMovieBoxoffice(){
        return "/APIs/KobisMovieAPI";
    }

}
