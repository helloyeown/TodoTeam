create table tbl_reply (
	rno int auto_increment primary key,
	tno int not null,
	reply varchar(1000) not null,
	replyer varchar(100) not null,
	replyDate timestamp default now()
)
;

## 임시테이블 만들 때 사용 하는 방식
create table tbl_reply2 (select * from tbl_reply where rno = 10)
;

alter table tbl_reply2 add column (gno int default 0);
;

drop table tbl_reply2;

## 댓글 연습 테이블 생성
create table tbl_reply2 (
	rno int auto_increment primary key,
	tno int not null,
	reply varchar(1000) not null,
	replyer varchar(100) not null,
	replyDate timestamp default now(),
	gno int default 0
)
;

delete from tbl_reply2;

select * from tbl_reply2
;

## 순수한 댓글 - tno
insert into tbl_reply2 (tno, reply, replyer)
values (100, 'R1', 'r1')
;

## gno 업데이트
update tbl_reply2 set gno = (select last_insert_id()) where rno = 5
;

## 1번의 대댓글
insert into tbl_reply2 (tno, reply, replyer, gno)
values (100, 'R1', 'r1', 1)
;

## 조회
select
if(rno = gno, 0, 1) as step, rno, tno, gno, reply, replyer, replydate
from tbl_reply2 
where tno = 100 order by gno asc, rno asc
limit 0, 20
;

select * from tbl_reply2 order by gno asc;

insert into tbl_reply2 (tno, reply, replyer, gno)
values (100, 'R2-1', 'r1', 1)
;

## 100 글에 대해 순수한 댓글
insert into tbl_reply2 (tno, reply, replyer)
values (100, '2번째 댓글', 'r1')
;

insert into tbl_reply2 (tno, reply, replyer, gno)
values (100, 'R6-2', 'r1', 5)
;




create table tbl_reply3 (
	rno int auto_increment primary key,
	tno int not null,
	reply varchar(1000) not null,
	replyer varchar(100) not null,
	replyDate timestamp default now(),
	gno int default 0
)
;

select * from tbl_reply3 order by gno;

insert into tbl_reply3 (tno, reply, replyer)
values (100, 'test', 'tester');

update tbl_reply3 set gno = (select last_insert_id()) where rno = 8;

insert into tbl_reply3 (tno, reply, replyer, gno)
values (100, 'reply1-3', 'replyer', 1);

insert into tbl_reply3 (tno, reply, replyer)
values (100, 'test3', 'tester3');

insert into tbl_reply3 (tno, reply, replyer, gno)
values (100, 'reply8-1', 'replyer', 8);

select
if(rno = gno, 'R ', '  RR') as step, rno, tno, reply, replyer, replydate, gno
from tbl_reply3 
where tno = 100 order by gno asc
;




select * from tbl_reply
;

select count(*) from tbl_reply
;

insert into tbl_reply (tno, reply, replyer)
(
select 
		tno, 
		concat('Reply...', tno) reply,
		concat('user', mod(tno, 10)) replyer 
from tbl_todo order by tno desc limit 50, 100
)
;


insert into tbl_reply (tno, reply, replyer)
(select tno, reply, replyer from tbl_reply)
;

select * from tbl_reply 
where tno > 0 and rno  > 0
order by tno desc, rno
;

create index idx_reply1 on tbl_reply (tno desc, rno asc)
;

##1376222
select count(rno) from tbl_reply 
where tno = 1376222
order by rno asc
;

/* JOIN은 곱하기에 가까움 / 성능이 떨어질 수밖에 없음 -> 조인이 기본이지만, 반정규화를 고민해봐야 함 -> 연산이나 조인을 할 때 시간이 오래 걸려서 반정규화 작업 진행
 * 반정규화 장점 : 빠름 / 단점 : 해당 데이터의 수정, 삭제와 같은 작업을 해줘야 함 
 * Group by row를 만든다, 기준점을 만들어 준다
 * 그룹 함수 max, min, count => 결과가 하나로 떨어짐 
 */
/*
데이터 베이스에서 컴마가 나오면 테이블로 쪼개버림 
*/

select tt.tno, tt.title, tt.writer, count(re.rno)
from
	tbl_todo tt left outer join tbl_reply re
on re.tno = tt.tno
group by tt.tno
order by tt.tno desc
limit 0, 10
;

alter table tbl_todo add column (replycnt int default 0);

select * from tbl_todo order by tno desc;

update tbl_todo set replycnt = 
(select count(rno) from tbl_reply re where re.tno = tbl_todo.tno)
where tno>0;
-- transaction은 All or Nothing -> 여러개의 작업이 한 단위를 이룸