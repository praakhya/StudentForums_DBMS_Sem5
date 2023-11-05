	create table if not exists user_notifications(user_id binary(16), notification varchar(255), ts datetime);


delimiter $$
create trigger notfiyAddToForum
after insert on users_forums
for each row
begin
	declare forum_name varchar(255);
    select name into forum_name from forums where id = new.forum_id;
    insert into user_notifications values(new.user_id, CONCAT("You were added to the ", forum_name, " forum"), now());
end; $$
delimiter ;

delimiter $$
create trigger notfiyDelFromForum
after delete on users_forums
for each row
begin
	declare forum_name varchar(255);
    select name into forum_name from forums where id = old.forum_id;
    insert into user_notifications values(old.user_id, CONCAT("You were delete from to the ", forum_name, " forum"), now());
end; $$
delimiter ;

drop procedure if exists notifyUserAddToForum;
delimiter $$
create procedure notifyUserAddToForum(in username varchar(255), in forum_id varchar(255))
begin
	declare user_id binary(16);
    declare forum_name varchar(255);
    select users.id into user_id from users where users.username = username;
    select forums.name into forum_name from forums where forums.id = UUID_TO_BIN(forum_id);
    insert into user_notifications(id, user_id, notification,ts) values(UUID_TO_BIN(UUID()), user_id, CONCAT("You were added to the ", forum_name, " forum"), now());
end; $$
delimiter ;

drop procedure if exists notifyUserDeleteFromForum;
delimiter $$
create procedure notifyUserDeleteFromForum(in user_id varchar(255), in forum_id varchar(255))
begin
    declare forum_name varchar(255);
    select forums.name into forum_name from forums where forums.id = UUID_TO_BIN(forum_id);
    insert into user_notifications(id, user_id, notification,ts) values(UUID_TO_BIN(UUID()),UUID_TO_BIN(user_id), CONCAT("You were deleted from the ", forum_name, " forum"), now());
end; $$
delimiter ;
