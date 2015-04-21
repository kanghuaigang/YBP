drop table bdtask;
drop table bduser;

create table bdtask (
	id number(19,0),
	title varchar2(128) not null,
	description varchar2(255),
	user_id bigint not null,
    primary key (id)
);

create table bduser (
	id number(19,0),
	login_name varchar2(64) not null unique,
	name varchar2(64) not null,
	password varchar2(255) not null,
	salt varchar2(64) not null,
	roles varchar2(255) not null,
	regiser_date date not null,
	primary key (id)
);


create sequence bdseq_task start with 100 increment by 20;
create sequence bdseq_user start with 100 increment by 20;



alter table BD_MESSAGE
   add constraint FK_UR_REF_USER foreign key (USER_ID)
      references ASC_USER_INFO (ID);
      
      
      
      
      
           
/*==============================================================*/
/* Table: BD_MESSAGE                                   */
/*==============================================================*/
      
ALTER TABLE BD_MESSAGE ADD USER_JOB VARCHAR(100) 
ALTER TABLE BD_MESSAGE ADD USER_NAME VARCHAR(100) 
ALTER TABLE BD_MESSAGE ADD return_actot_id VARCHAR(100) 
alter table BD_MESSAGE rename column user_id to return_actotr_id;

alter  table bd_message drop column user_name