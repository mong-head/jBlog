use jblog;

select * from blog;
select * from category;
select * from post;
select p.no, title,contents, p.reg_date as regDate, category_no as categoryNo  from post p join category c on p.category_no = c.no where blog_id = 'mong' order by c.no desc limit 1; 

-- get blog contents
select * from blog where id = 'mong';
select no,name,c.desc as description , reg_date as regDate , blog_id as blogId from category c where blog_id = 'mong';
select no,title,contents,reg_date as regDate, category_no as categoryNo from post where no = 1 and category_no = 1;
select no,title,contents,reg_date as regDate, category_no as categoryNo from post where no = 1;
select no,title,contents,reg_date as regDate, category_no as categoryNo from post where category_no = 1 order by no desc;

-- update blog
update blog set logo = 'assets/images/blog/default_profile.jpg' , title = 'mongs' where id='mong';

-- insert post
insert into post values(null,'test입니당','테스트내용입니다.\n안녕',now(),3);
insert into post values(null,'test입니당2','테스트내용입니다.2\n안녕',now(),1);

-- find recent post
select * from post order by no desc limit 1;

-- category list

select c.name, c.desc, if(isnull(p.count),0,p.count) as count
from category c
left join (select category_no, count(*) as count from post group by category_no) p
on c.no = p.category_no
where c.blog_id = 'mong'
;

select  c.name, c.desc, count(p.category_no) as count
from category c
left join post p
on c.no = p.category_no
where c.blog_id = 'mong'
group by c.no
order by 3 desc
;

-- insert category
insert into category values( null, '하이2', '하이2', now(), 'mong');

-- delete category
delete from post where category_no = 3;
delete from category where no = 3;
