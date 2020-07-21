-- 编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。
select Email from
(
    select Email, count(Email) as num
    from Person
    group by Email
) as t
where num > 1;

-- Another Solution:
select Email from Person
group by Email
having count(Email) > 1;

-- 某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。
-- 链接：https://leetcode-cn.com/problems/customers-who-never-order/
select c.Name as Customers
from Customers c 
left join Orders o 
on o.CustomerId = c.Id 
where o.Id is null;
