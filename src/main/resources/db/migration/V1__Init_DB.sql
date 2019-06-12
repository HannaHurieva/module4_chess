create table usr
(
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(255) not null,
  password varchar(255) not null,
  active   boolean     not null
)
ENGINE = InnoDB;

create table user_role
(
  user_id BIGINT NOT NULL,
  roles varchar(13)
);

alter table user_role
  add constraint user_role_user_fk
  foreign key (user_id) references usr(id);