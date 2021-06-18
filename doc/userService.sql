use jblog;

select * from user;
select * from blog;
select * from category;

-- join --
-- inserts
insert into user values('mong', '배유진', '1111', now());
insert into blog values('mong', concat('mong',"'s blog"), 'assets/images/blog/default_profile.jpg');
insert into category values(null, 'basic','basic', now(), 'mong');

update blog set logo = 'assets/images/blog/default_profile.jpg' where id='toms';