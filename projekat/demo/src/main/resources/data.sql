
-- -----------------
-- ADDING TEST DATA
-- -----------------

-- ADDING USERS

INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(1, 'sysmanager@gmail.com', 'system', 'manager', '12345', 'pictures/user.png', 'system_manager', 'no', 'no', null, null, 0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(2, 'resmanager1@gmail.com', 'res1', 'manager1', '12345', 'pictures/user.png', 'manager', 'no', 'no', null, null, 0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(3, 'resmanager2@gmail.com', 'res2', 'manager2', '12345', 'pictures/user.png', 'manager', 'no', 'no', null, null,0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(4, 'waiter@gmail.com', 'waiter', 'waiter', '12345', 'pictures/user.png', 'waiter', 'no', 'no', null, null, 0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(5, 'cook@gmail.com', 'cook', 'cook', '12345', 'pictures/user.png', 'cook', 'no', 'no', null, null, 0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(6, 'bartender@gmail.com', 'bartender', 'bartender', '12345', 'pictures/user.png', 'bartender', 'no', 'no', null, null, 0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(7, 'guest1@gmail.com', 'guest1', 'guest1', '12345', 'https://s-media-cache-ak0.pinimg.com/736x/cb/e3/8c/cbe38cfd74bd94003988c9593076de2c.jpg', 'guest', 'yes', 'no', null, 'Z3Vlc3QxQGdtYWlsLmNvbToxMjM0', 0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(8, 'guest2@gmail.com', 'guest2', 'guest2', '12345', 'https://s-media-cache-ak0.pinimg.com/736x/20/3a/80/203a8006d562a97c88967ca64f74ea6b.jpg', 'guest', 'yes', 'no', null, null, 0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(9, 'guest3@gmail.com', 'guest3', 'guest3', '12345', 'https://s-media-cache-ak0.pinimg.com/originals/fa/cc/4f/facc4f266353726780420a6c5222e864.jpg', 'guest', 'yes', 'no', null, null, 0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(10, 'guest33@gmail.com', 'guest33', 'guest33', '12345', 'http://cdn04.cdn.justjaredjr.com/wp-content/uploads/headlines/2017/03/arrow-olicity-future-talked-about.jpg', 'guest', 'yes', 'no', null, null, 0);
INSERT INTO app_users (id, email, first_name, last_name, password, image, role, verified, login, message, token, broj_poseta) VALUES(11, 'guest34@gmail.com', 'guest34', 'guest34', '12345', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSyYfHRCIJYIe5J0OViaefPf3jP4-7lAvdW715kWBfWBYgJzW9Nkg', 'guest', 'yes', 'no', null, null, 0);

-- ADDING SYSTEM MANAGER

INSERT INTO sys_managers (sm_id) VALUES(1);

-- ADDING RESTAURANTS

INSERT INTO restaurants (rid, address, end_time, image, info, name, start_time, type, sm_id) VALUES (1, 'Safarikova, Novi Sad, Srbija', 20, 'pictures/user.png', 'lep', 'venecia', 8, 'italian', 1);
INSERT INTO restaurants (rid, address, end_time, image, info, name, start_time, type, sm_id) VALUES (2, 'Zmaj Jovina, Novi Sad, Srbija', 22, 'pictures/user.png', 'lep', 'atina', 10, 'greek', 1);

-- ADDING RESTAURANT MANAGERS

INSERT INTO managers (m_id, rid) VALUES(2, 1);
INSERT INTO managers (m_id, rid) VALUES(3, 2);

-- ADDING REGIONS

INSERT INTO regions (regid, color, name, region_no, rid) VALUES (1, 'e846df', 'pusacki deo', 1, 1);
INSERT INTO regions (regid, color, name, region_no, rid) VALUES (2, '7ee9f1', 'nepusacki deo', 2, 1);
INSERT INTO regions (regid, color, name, region_no, rid) VALUES (3, '50f737', 'basta', 3, 1);

-- ADDING EMPLOYEES

INSERT INTO employees (birthday, dress_size, e_id, first_time, shoe_size, regid, rid) VALUES ('1990-04-10', 'S', 4, 'yes', 37, 1, 1);
INSERT INTO employees (birthday, dress_size, e_id, first_time, shoe_size, regid, rid) VALUES ('1992-03-15', 'L', 5, 'yes', 43, null, 1);
INSERT INTO employees (birthday, dress_size, e_id, first_time, shoe_size, regid, rid) VALUES ('1988-09-21', 'M', 6, 'yes', 39, null, 1);

-- ADDING WAITER

INSERT INTO waiters (w_id) VALUES (4);

-- ADDING COOK

INSERT INTO cooks (c_id) VALUES (5);

-- ADDING BARTENDER

INSERT INTO bartenders (b_id) VALUES (6);

-- ADDING GUESTS

INSERT INTO guests (g_id) VALUES (7);
INSERT INTO guests (g_id) VALUES (8);
INSERT INTO guests (g_id) VALUES (9);
INSERT INTO guests (g_id) VALUES (10);
INSERT INTO guests (g_id) VALUES (11);

-- ADDING FRIENSHIPS

INSERT INTO FRIENDSHIPS(FID, STATUS, SECOND, FIRST) values (1,'accept',7,8);
INSERT INTO FRIENDSHIPS(FID, STATUS, SECOND, FIRST) values (2,'pending',9,8);
INSERT INTO FRIENDSHIPS(FID, STATUS, SECOND, FIRST) values (3,'accept',7,9);
INSERT INTO FRIENDSHIPS(FID, STATUS, SECOND, FIRST) values (4,'pending',7,11);



-- ADDING TABLES

INSERT INTO restaurant_tables (tid, deleted, height, num_of_chairs, positions, rt_table_in_restaurant_no, width, x, y, regid, br_rezervacija, version) VALUES (1, 'no', 50, 4, NULL, 1, 50, 400, 400, 1, 0, 0);
INSERT INTO restaurant_tables (tid, deleted, height, num_of_chairs, positions, rt_table_in_restaurant_no, width, x, y, regid, br_rezervacija, version) VALUES (2, 'no', 50, 4, NULL, 2, 50, 488, 393, 1, 0, 0);
INSERT INTO restaurant_tables (tid, deleted, height, num_of_chairs, positions, rt_table_in_restaurant_no, width, x, y, regid, br_rezervacija, version) VALUES (3, 'no', 50, 4, NULL, 3, 50, 564, 365, 1, 0, 0);
INSERT INTO restaurant_tables (tid, deleted, height, num_of_chairs, positions, rt_table_in_restaurant_no, width, x, y, regid, br_rezervacija, version) VALUES (4, 'no', 50, 4, NULL, 4, 50, 75, 143, 3, 0, 0);
INSERT INTO restaurant_tables (tid, deleted, height, num_of_chairs, positions, rt_table_in_restaurant_no, width, x, y, regid, br_rezervacija, version) VALUES (5, 'no', 50, 4, NULL, 5, 50, 80, 242, 3, 0, 0);
INSERT INTO restaurant_tables (tid, deleted, height, num_of_chairs, positions, rt_table_in_restaurant_no, width, x, y, regid, br_rezervacija, version) VALUES (6, 'no', 50, 4, NULL, 6, 50, 404, 131, 2, 0, 0);
INSERT INTO restaurant_tables (tid, deleted, height, num_of_chairs, positions, rt_table_in_restaurant_no, width, x, y, regid, br_rezervacija, version) VALUES (7, 'no', 50, 4, NULL, 7, 50, 492, 122, 2, 0, 0);

-- ADDING MENU ITEMS

INSERT INTO menu_items (miid, deleted, image, info, name, price, spec_type, type, rid) VALUES (1, 0, 'https://s3-media4.fl.yelpcdn.com/bphoto/Lfvt9bBBR9CMW2p8gm8BnQ/ls.jpg', 'lep', 'pizza', 150, 'fried', 'dish', 1);
INSERT INTO menu_items (miid, deleted, image, info, name, price, spec_type, type, rid) VALUES (2, 0, 'http://www.firmesrbije.com/wp-content/uploads/2012/11/118.jpg', 'lep', 'giros', 200, 'fried', 'dish', 1);
INSERT INTO menu_items (miid, deleted, image, info, name, price, spec_type, type, rid) VALUES (3, 0, 'http://cdn3.foodviva.com/static-content/food-images/cocktail-recipes/blue-lagoon-cocktail-recipe/blue-lagoon-cocktail-recipe.jpg', 'lep', 'blue lagoon', 300, 'coctail', 'drink', 1);
INSERT INTO menu_items (miid, deleted, image, info, name, price, spec_type, type, rid) VALUES (4, 0, 'https://s-media-cache-ak0.pinimg.com/originals/2c/43/55/2c43559e407a29dbf06aafbb235d552b.jpg', 'lep', 'wine', 250, 'alcohol', 'drink', 1);

-- ADDING RESERVATIONS

insert into reservations(rsid,date_of,length,name_rest,status,time_of,rid,tid,uid) values (1,'02.07.2017','16:00','venecia','reserved','12:00',1,4,7);
insert into reservations(rsid,date_of,length,name_rest,status,time_of,rid,tid,uid) values (2,'02.07.2017','16:00','venecia','reserved','12:00',1,5,7);

insert into reservations(rsid,date_of,length,name_rest,status,time_of,rid,tid,uid) values (3,'03.07.2017','17:00','venecia','reserved','14:00',1,2,7);
insert into reservations(rsid,date_of,length,name_rest,status,time_of,rid,tid,uid) values (4,'04.07.2017','13:00','venecia','reserved','11:00',1,1,7);
insert into reservations(rsid,date_of,length,name_rest,status,time_of,rid,tid,uid) values (5,'05.07.2017','12:00','venecia','finished','11:00',1,1,7);

-- ADDING FRIENDINVITATIONS

insert into friendinvitations(fid,status,second,resid,first) values (1,'pending',8,1,7);
insert into friendinvitations(fid,status,second,resid,first) values (2,'accept',9,1,7);
insert into friendinvitations(fid,status,second,resid,first) values (3,'pending',9,2,7);

-- ADDING CLIENT_ORDERS

insert into client_orders(oid, client_id, date_of, deadline, order_number, status, total_price, version, eid, rsid, rid, table_id) values (1,7,'2017-06-22 21:46:55.682', null, 1, 'created',0.0,0,null,2,1,null);
insert into client_orders(oid, client_id, date_of, deadline, order_number, status, total_price, version, eid, rsid, rid, table_id) values (2,7,'2017-06-22 21:47:55.682', null, 2, 'created',0.0,0,null,3,1,null);
insert into client_orders(oid, client_id, date_of, deadline, order_number, status, total_price, version, eid, rsid, rid, table_id) values (3,7,'2017-06-22 21:47:55.682', null, 3, 'created',0.0,0,null,5,1,null);

-- ADDING ORDER_ITEMS

insert into order_items(oiid,amount,item_number,price,restaurant_id,state,oi_table_id,version,miid,oid) values(1,1,1,300.0,1,'created',null,0,3,1);
insert into order_items(oiid,amount,item_number,price,restaurant_id,state,oi_table_id,version,miid,oid) values(2,1,1,300.0,1,'created',null,0,3,2);
insert into order_items(oiid,amount,item_number,price,restaurant_id,state,oi_table_id,version,miid,oid) values(3,1,1,300.0,1,'created',null,0,3,3);