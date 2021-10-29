delete from payments;
delete from users;
insert into users(id,name,phone_number) values (1,'user1','+79161234567');
insert into users(id,name,phone_number) values (2,'user2','+79171234567');
insert into users(id,name,phone_number) values (3,'user3','+79181234567');
insert into payments(id,description,payment_time,value,user_id) values (4,null,'2021-10-10 10:00:00.00000',12000.0,1);
insert into payments(id,description,payment_time,value,user_id) values (5,null,'2021-10-10 10:00:00.00000',120000.0,2);
insert into payments(id,description,payment_time,value,user_id) values (6,null,'2021-09-10 10:00:00.00000',12000.0,1);
insert into payments(id,description,payment_time,value,user_id) values (7,null,'2021-09-10 10:00:00.00000',120000.0,2);