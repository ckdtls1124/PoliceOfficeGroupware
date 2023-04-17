package org.spring.openapi1.openapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class BusController {

    @GetMapping("/busList")
    public Map<String,String> busList(@RequestParam(required = false)
                                          String strSrch) throws IOException{
        String busList=ApiExplorer.getBusList(strSrch);
        System.out.println(busList+"busList");

        Map<String,String> map=new HashMap<>();
        map.put("busList",busList);
        return map;
    }
    @GetMapping("/busId1")
    public Map<String,String> busId(@RequestParam(required = false)
                                      String busRouteId) throws IOException{
        String busId2=ApiExplorer.getbusRouteId(busRouteId);


        System.out.println(busId2+"busId2");

        Map<String,String> map=new HashMap<>();

        map.put("busId3",busId2);

        return map;
    }
}
