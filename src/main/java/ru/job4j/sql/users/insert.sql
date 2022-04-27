insert into roles(name) values('Administrator');
insert into roles(name) values('Worker');

insert into users(name, roles_id) values('Admin', 1);
insert into users(name, roles_id) values('Bob', 2);

insert into rules(name) values('Create');
insert into rules(name) values('Read');
insert into rules(name) values('Update');
insert into rules(name) values('Delete');

insert into roles_rules(roles_id, rules_id) values(1, 1);
insert into roles_rules(roles_id, rules_id) values(1, 2);
insert into roles_rules(roles_id, rules_id) values(1, 3);
insert into roles_rules(roles_id, rules_id) values(1, 4);
insert into roles_rules(roles_id, rules_id) values(2, 2);
insert into roles_rules(roles_id, rules_id) values(2, 3);

insert into category(name) values('Incoming');
insert into category(name) values('Outgoing');

insert into state(name) values('In work');
insert into state(name) values('Completed');

insert into item(name, users_id, category_id, state_id) values('Database', 1, 1, 2);
insert into item(name, users_id, category_id, state_id) values('Profile', 2, 2, 1);

insert into comments(name, item_id) values('Create new database for projectX', 1);
insert into comments(name, item_id) values('Update profiles and email', 2);

insert into attachs(name, item_id) values('List of profiles', 2);

