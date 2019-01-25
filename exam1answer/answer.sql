-- create table students(
--   id int not null auto_increment primary key,
--   name varchar(55),
--   surname varchar(55),
--   course enum ('I', 'II', 'III', 'IV') not null,
--   lecture varchar(50));

select course, max(students_count) as max_students from (
  select course, count(course) as students_count from students group by course order by course desc
) results;

select course, max(students_lecture) as max_students
from (
  select course, count(lecture) as students_lecture
  from students where lecture = 'Java' group by course order by students_lecture desc
) result;

select course, max(students_lecture) as max_students
from (
  select course, count(lecture) as students_lecture
  from students where lecture = 'PHP' group by course order by students_lecture desc
) result;

select * from (
  select * from students order by lecture desc
) result order by course desc;