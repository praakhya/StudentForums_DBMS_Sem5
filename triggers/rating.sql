delimiter $$
create function getPostCount(userid varchar(255))
returns int
begin
	declare count int;
    select count(*) into count from posts where posts.poster_id = UUID_TO_BIN(userid);
	return count;
end;$$
delimiter ;

delimiter $$
create function getValidatedCount(userid varchar(255))
returns int
begin
	declare count int;
    select count(*) into count from resources,users where 
		resources.owner_name = users.username and
        users.id = userid and
        resources.validated = ;
	return count;
end;$$
delimiter ;


