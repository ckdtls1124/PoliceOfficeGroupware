package org.spring.p21suck2jo.chatbotMovie.dataReceiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.spring.p21suck2jo.chatbotOriginal.dto.DailyBoxOfficeListContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MovieAPIExplorerJava {

//    Movie API로 출력된 dailyBoxOffice의 String을 Java 객체로 변환
    public static DailyBoxOfficeListContainer returnMovieResponse(String movieAPIResult) throws JsonProcessingException {
        ObjectMapper movieObjectMapper = new ObjectMapper();
        DailyBoxOfficeListContainer movieResponse = movieObjectMapper.readValue(movieAPIResult, DailyBoxOfficeListContainer.class);

        return movieResponse;
    }



//    searchDailyBoxOfficeList 일간 박스오피스 출력
    public static String getMovieDataWithTodayBoxOffice(String targetDateTime) throws IOException {
        String myKeyNum = "d5ed5c2a608d3ca08ee86c8fae2ce639";

        StringBuilder urlBuilder = new StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") +"="+ myKeyNum); /*Service Key*/
        urlBuilder.append("&"+URLEncoder.encode("targetDt", "UTF-8")+"="+targetDateTime);

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();



        return sb.toString();
    }

    //    searchCodeList 영화코드로 이름 출력
//    public static String getMovieNameByMovieCode(String movieCode) throws IOException {
//        String myKeyNum = "d5ed5c2a608d3ca08ee86c8fae2ce639";
//
//        StringBuilder urlBuilder = new StringBuilder("http://kobis.or.kr/kobisopenapi/webservice/rest/movie/"+movieCode+".json"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("key","UTF-8") +"="+ myKeyNum); /*Service Key*/
//
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        System.out.println(sb.toString());
//
//        return sb.toString();
//    }

}
