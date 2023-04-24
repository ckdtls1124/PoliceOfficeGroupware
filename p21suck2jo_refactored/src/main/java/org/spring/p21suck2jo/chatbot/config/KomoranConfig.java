package org.spring.p21suck2jo.chatbot.config;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import org.spring.p21suck2jo.chatbot.repository.ChatDeptRepository;
import org.spring.p21suck2jo.chatbot.repository.OfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class KomoranConfig {

	private String USER_DIC = "user.dic";

	@Autowired
	OfficerRepository officer;

	@Autowired
	ChatDeptRepository dept;

	@Bean
	Komoran komoran() {
		userDic();
		Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
		komoran.setUserDic(USER_DIC);
		return komoran;
	}

	private void userDic() {
		Set<String> keys = new HashSet<>();
		try {
			File file = new File(USER_DIC);
			if(file.exists()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String data = null;
				while ((data = br.readLine()) != null) {
					if (data.startsWith("#"))
						continue;
					String[] str = data.split("\\t");
					keys.add(str[0]);
				}
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//부서명을 set에 저장
		dept.findAll().forEach(e -> {
			keys.add(e.getDeptName());
		});

		//경관명을 set에 저장
		officer.findAll().forEach(e -> {
			keys.add(e.getOfficerName());
		});

		//저장된 명단을 고유명사로 파일에 등록
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(USER_DIC));
			keys.forEach(key -> {
				try {
					bw.write(key + "\tNNP\n");
					System.out.println(key);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
