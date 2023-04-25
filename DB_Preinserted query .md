## Caution 
For preventing Korean language distortion

Help -> Edit Custom VM Option

Add
```
-Dfile.encoding=UTF-8
-Dconsole.encoding=UTF-8
```


##  DB Insert Query


### Dept
insert into dept(dept_location,dept_name) values("서울특별시 노원구","생활안전팀");

insert into dept(dept_location,dept_name) values("서울특별시 노원구","신고대응팀");

insert into dept(dept_location,dept_name) values("서울특별시 노원구","민원해결팀");


### Person
insert into person(create_time,person_address,person_email,person_name,person_phone) values(now(),"도봉구 쌍문동","kki@naver.com","김경현","01094556651");

insert into person(create_time,person_address,person_email,person_name,person_phone) values(now(),"도봉구 창동","jeo2@gmail.com","김태수","01066581123");

insert into person(create_time,person_address,person_email,person_name,person_phone) values(now(),"도봉구 창동","auum@nate.com","황도연","01077103328");

insert into person(create_time,person_address,person_email,person_name,person_phone) values(now(),"도봉구 쌍문동","cumart@gmail.com","황수정","01068259358");


### Event Group
insert into event_group(event_group_name) values('112신고');

insert into event_group(event_group_name) values('인근소란');

insert into event_group(event_group_name) values('불안감조성');

insert into event_group(event_group_name) values('신호위반');

insert into event_group(event_group_name) values('공무집행방해');

insert into event_group(event_group_name) values('음주운전');

insert into event_group(event_group_name) values('폭행');

insert into event_group(event_group_name) values('기타');


### Chat-Bot
insert into chat_answer(content, keyword) values('안녕하세요 일석이조 지구대입니다^^ 무엇을 도와드릴까요?', '안녕');

insert into chat_answer(content, keyword) values('문의하신 경관의 연락처입니다.', '연락처');

insert into chat_answer(content, keyword) values('오늘의 날씨 정보입니다.', '날씨');

insert into chat_answer(content, keyword) values('오늘의 교통 정보입니다.', '교통');

insert into chat_answer(content, keyword) values('죄송합니다. 답변이 준비되지 않았어요', '기타');

insert into chat_answer(content, keyword) values('조회하실 박스오피스 일자를 입력해주세요 (yyyyMMdd 형식)', '박스');

insert into chat_answer(content, keyword) values('어떤 영화 보고 싶으세요?', '영화');

insert into chat_answer(content, keyword) values('검색하실 시점을 알려주세요(이번 주, 다음 주)', '개봉');

insert into chat_intention(keyword, answer_id) values('안녕', 1);

insert into chat_intention(keyword, answer_id) values('연락처', 2);

insert into chat_intention(keyword, answer_id) values('날씨', 3);

insert into chat_intention(keyword, answer_id) values('교통', 4);

insert into chat_intention(keyword, answer_id) values('기타', 5);

insert into chat_intention(keyword, answer_id) values('박스', 6);

insert into chat_intention(keyword, answer_id) values('영화', 7);

insert into chat_intention(keyword, answer_id) values('개봉', 8);

insert into chat_dept(dept_name) values('생활안전팀');

insert into chat_dept(dept_name) values('신고대응팀');

insert into chat_dept(dept_name) values('민원해결팀');

insert into chat_officer(officer_name, officer_phone, dept_id) values('강창신', '010-1234-5678', 3);

insert into chat_officer(officer_name, officer_phone, dept_id) values('이지창', '010-2345-6789', 2);

insert into chat_officer(officer_name, officer_phone, dept_id) values('장효선', '010-9876-5432', 3);

insert into chat_officer(officer_name, officer_phone, dept_id) values('허인경', '010-8765-4321', 1);

insert into chat_officer(officer_name, officer_phone, dept_id) values('김득주', '010-3458-1628', 2);


### 사용자사전
민원해결반	NNP

생활안전반	NNP

신고대응반	NNP

이지창	NNP

장효선	NNP

허인경	NNP

강창신	NNP

김득주	NNP

