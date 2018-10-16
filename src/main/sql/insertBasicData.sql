/*
drop table dbo.user_role;
drop table dbo.app_role;
drop table dbo.app_user;
drop table dbo.country;
drop table dbo.persistent_logins;
*/
delete from dbo.Country;
delete from dbo.App_User;
delete from dbo.app_role;
delete from dbo.user_role;

-------------------------------------
insert into dbo.country (country, descr) values ('CO', 'Colombia' );
insert into dbo.country (country, descr) values ('CA', 'Canada' );
insert into dbo.country (country, descr) values ('US', 'United States' );
---
insert into dbo.App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED, EMAIL, FIRST_NAME, LAST_NAME, CELLPHONE, COUNTRY, BIRTHDATE)
values (1, 'driverco', 'BE0A32338660901A75CB090AE4BAC39D88759FFB535B14B79E3DD525D85A6300', 1, 'driverco@gmail.com', 'Wilmer', 'Rodriguez', '3003246000', 'CO', '1984-02-28');

insert into dbo.App_User (USER_ID, USER_NAME, ENCRYTED_PASSWORD, ENABLED, EMAIL, FIRST_NAME, LAST_NAME, CELLPHONE, COUNTRY, BIRTHDATE)
values (2, 'user', 'BE0A32338660901A75CB090AE4BAC39D88759FFB535B14B79E3DD525D85A6300', 1, 'driverco@gmail.com', 'Wilmer', 'Rodriguez', '3003246000', 'CO', '1984-02-28');
---
insert into dbo.app_role (ROLE_ID, ROLE_NAME) values (1, 'ROLE_ADMIN');
insert into dbo.app_role (ROLE_ID, ROLE_NAME) values (2, 'ROLE_USER');
---
insert into dbo.user_role (ID, USER_ID, ROLE_ID) values (1, 1, 1);
insert into dbo.user_role (ID, USER_ID, ROLE_ID) values (2, 1, 2);
insert into dbo.user_role (ID, USER_ID, ROLE_ID) values (3, 2, 2);
---
Commit;