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
