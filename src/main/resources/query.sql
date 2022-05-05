delete from books;

select * from books;

select * from users;
insert into books(id, author, category, title, year, owner_id)
values(1, 'Author - 1 ', 'Category - 1', 'Title - 1', 2000, 1),
(2, 'Author - 2', 'Category - 3', 'Title - 2', 2007, 1),
(3, 'Author - 3 ', 'Category - 4', 'Title - 4', 2004, 2),
(4,'Student-1','Name- 1','University- 1',2003, 2),
(5,'Student-2','Name- 2','University- 2',2005, 3),
(6,'Student-3','Name- 3','University- 3',2001, 3)