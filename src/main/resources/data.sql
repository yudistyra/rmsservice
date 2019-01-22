INSERT INTO user(username,password,email,name,active) VALUES ('admin', '$2a$10$6TajU85/gVrGUm5fv5Z8beVF37rlENohyLk3BEpZJFi6Av9JNkw9O', 'admin@springinventory.com','administrator',1);
INSERT INTO user(username,password,email,name,active) VALUES ('user', '$2a$10$6TajU85/gVrGUm5fv5Z8beVF37rlENohyLk3BEpZJFi6Av9JNkw9O', 'user@springinventory.com','user',1);
INSERT INTO `role` VALUES (1,'ROLE_ADMIN');
INSERT INTO `role` VALUES (2,'ROLE_ADMIN_PRODUCT');
INSERT INTO `role` VALUES (3,'ROLE_ADMIN_WAREHOUSE');
INSERT INTO `role` VALUES (4,'ROLE_USER');
UPDATE user SET role_id = 1 where username = 'admin';