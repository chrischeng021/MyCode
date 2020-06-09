# 编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。
select Email from
(
    select Email, count(Email) as num
    from Person
    group by Email
) as t
where num > 1;

# Another Solution:
select Email from Person
group by Email
having count(Email) > 1;