##  DB Insert Query


### Dept
insert into dept(dept_location,dept_name) values("서울특별시 노원구","생활안전팀");

insert into dept(dept_location,dept_name) values("서울특별시 노원구","신고대응팀");

insert into dept(dept_location,dept_name) values("서울특별시 노원구","민원해결팀");


### Police_officer
insert into police_officer(email,password,police_name,police_number,police_phone,ranks,create_time,dept_id,role) 
 values("admin","1111","admin1","19980213","010-4566-1147","경감",now(),1,"ADMIN");
 
insert into police_officer(email,password,police_address,detail_address,zip_code,police_name,police_number,police_phone,ranks,create_time,dept_id,role) 
 values("rudrka@gmail.com","1111","서울 노원구 공릉로 95","OO빌라 1동 101호","01849","김경감","19960213","010-4566-1147","경감",now(),1,"MEMBER");
 
insert into police_officer(email,password,police_address,detail_address,zip_code,police_name,police_number,police_phone,ranks,create_time,dept_id,role) 
 values("rudrka@gmail.com","1111","서울 노원구 노원로 15","OO아파트 101동 102호","01364","김경위","20001127","010-8372-2971","경위",now(),2,"MEMBER");
 
insert into police_officer(email,password,police_address,detail_address,zip_code,police_name,police_number,police_phone,ranks,create_time,dept_id,role) 
 values("rudrka@gmail.com","1111","서울 노원구 마들로 46","OO빌라 2동 103호","01752","김순경","20220325","010-2256-9852","순경",now(),3,"MEMBER");
 
insert into police_officer(email,password,police_address,detail_address,zip_code,police_name,police_number,police_phone,ranks,create_time,dept_id,role) 
 values("rudrka@gmail.com","1111","서울 노원구 동일로 91","OO아파트 101동 201호","01358","이순경","20200711","010-5584-1892","순경",now(),1,"MEMBER");


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

insert into chat_intention(keyword, answer_id) values('안녕', 1);

insert into chat_intention(keyword, answer_id) values('연락처', 2);

insert into chat_intention(keyword, answer_id) values('날씨', 3);

insert into chat_intention(keyword, answer_id) values('교통', 4);

insert into chat_intention(keyword, answer_id) values('기타', 5);

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

영화 NNP

오늘 NNP

박스 NNP

오피스 NNP

개봉 NNP

이번 NNP

주 NNP

배우 NNP
