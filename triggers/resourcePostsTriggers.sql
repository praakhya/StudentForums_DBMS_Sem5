drop trigger detachResourceFromPost if 
delimiter $$
create trigger detachResourceFromPost
before delete on resources
for each row
begin
	delete from posts_resources where posts_resources.resource_id = old.id;
end; $$
delimiter ;


CREATE TABLE `posts_resources` (
  `resource_id` binary(16) NOT NULL,
  `post_id` binary(16) NOT NULL,
  KEY `FKp44ka1ole6vq6npx6p0bh1a5y` (`post_id`),
  KEY `FKr1ghju1rlw056v2ka136hw3b7` (`resource_id`),
  CONSTRAINT `FKp44ka1ole6vq6npx6p0bh1a5y` FOREIGN KEY (`post_id`) REFERENCES `resources` (`id`),
  CONSTRAINT `FKr1ghju1rlw056v2ka136hw3b7` FOREIGN KEY (`resource_id`) REFERENCES `posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci 