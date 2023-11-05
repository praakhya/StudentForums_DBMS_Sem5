## create table command
CREATE TABLE sections (
  id binary(16) NOT NULL,
  forum_id binary(16),
  class_repid binary(16) DEFAULT NULL,
  class_teacherid binary(16) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  constraint pk_sections PRIMARY KEY (id),
  constraint fk_sections foreign key(forum_id) references forums(id)
);

drop trigger if exists createSectionForum;
delimiter $$
create trigger createSectionForum
after insert on sections
for each row
begin
	declare uuid binary(16);
    select UUID_TO_BIN(UUID()) into uuid;
	insert into forums values(uuid, new.name, new.class_teacher_id, new.id);
    insert into users_forums values(new.class_teacher_id, uuid);
    insert into users_forums values(new.class_rep_id, uuid);
end;$$
delimiter ;

drop trigger if exists onDeleteSection;
delimiter $$
create trigger onDeleteSection
after delete on sections
for each row
begin
    update users set section_id = null where users.id in (old.class_rep_id, old.class_teacher_id);
end;$$
delimiter ;


drop trigger if exists createForumIdInSection;
delimiter $$
create trigger createForumIdInSection
after insert on forums
for each row
begin
	if new.section_id is not null then
		update sections set sections.forum_id = new.id where sections.id = new.section_id;
	end if;
end;$$
delimiter ;
#declare username varchar(255);
select * from sections where sections.id in (select forums.section_id from forums where forums.id in (select users_forums.forum_id from users_forums where users_forums.user_id));

