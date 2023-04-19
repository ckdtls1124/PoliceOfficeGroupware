package org.spring.p21suck2jo.APIs;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bus")
public class BusAPIRestController {

//    Java 코드를 이용
    @GetMapping("/busList/{strSrch}")
    public Map<String, String> busList(@PathVariable("strSrch") String strSrch) throws IOException {


        System.out.println("This is the bus number that you are looking for :"+strSrch);

        String result = BusApiExplorerJava.getBustList(strSrch);

        Map<String, String> resultMap = new HashMap<>();

        resultMap.put("result", result);

        return resultMap;
    }

}
