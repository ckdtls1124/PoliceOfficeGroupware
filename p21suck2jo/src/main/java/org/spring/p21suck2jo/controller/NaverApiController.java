package org.spring.p21suck2jo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.spring.p21suck2jo.dto.NaverTokenDTO;
import org.spring.p21suck2jo.dto.OrgResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class NaverApiController {
    @Value("${naver.api.client-id}")
    String CLIENT_ID;
    @Value("${naver.api.client-secret}")
    String CLIENT_SECRET;

    public NaverApiController() {
    }

    @GetMapping({"/api/naverAPIIndex"})
    public String naverAPIPage() {
        return "naver/NaverAPIIndex";
    }

    @GetMapping({"/naver/auth2"})
    public String naver(String code, String state, Model model) throws Exception {
        String apiURL = "https://auth.worksmobile.com/oauth2/v2.0/token";
        apiURL = apiURL + "?code=" + code;
        apiURL = apiURL + "&client_id=" + this.CLIENT_ID;
        apiURL = apiURL + "&client_secret=" + this.CLIENT_SECRET;
        apiURL = apiURL + "&grant_type=authorization_code";
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        int responseCode = con.getResponseCode();
        String responseJSONData = null;
        if (responseCode == 200) {
            responseJSONData = this.readBody(con.getInputStream());
            System.out.println("정상");
        } else {
            responseJSONData = this.readBody(con.getErrorStream());
            System.out.println("에러");
        }

        con.disconnect();
        ObjectMapper mapper = new ObjectMapper();
        NaverTokenDTO dto = (NaverTokenDTO)mapper.readValue(responseJSONData, NaverTokenDTO.class);
        System.out.println("" + dto + "  <<<");
        OrgResponse orgResponse = this.getOrgUnit(dto);
        System.out.println("====================");
        System.out.println(orgResponse);
        model.addAttribute("list", orgResponse.getOrgUnits());
        return "naver/naver-auth2";
    }

    private OrgResponse getOrgUnit(NaverTokenDTO dto) throws IOException {
        String apiURL = "https://www.worksapis.com/v1.0/orgunits";
        apiURL = apiURL + "?domainId=300075390";
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", "Bearer " + dto.getAccess_token());
        int responseCode = con.getResponseCode();
        String responseJSONData = null;
        if (responseCode == 200) {
            responseJSONData = this.readBody(con.getInputStream());
            System.out.println("OK");
        } else {
            responseJSONData = this.readBody(con.getErrorStream());
            System.out.println("Fail");
        }

        con.disconnect();
        ObjectMapper mapper = new ObjectMapper();
        OrgResponse reponse = (OrgResponse)mapper.readValue(responseJSONData, OrgResponse.class);
        return reponse;
    }

    private String readBody(InputStream inputStream) throws IOException {
        InputStreamReader streamReader = new InputStreamReader(inputStream);
        BufferedReader lineReader = new BufferedReader(streamReader);
        StringBuilder responseBody = new StringBuilder();

        String data;
        while((data = lineReader.readLine()) != null) {
            responseBody.append(data);
        }

        lineReader.close();
        streamReader.close();
        return responseBody.toString();
    }
}
