/*2018-2-05*/
ALTER TABLE `productinfo` ADD product_desc TEXT AFTER product_path;
/*2018-02-07*/
ALTER TABLE `user` ADD login_name VARCHAR(32) NOT NULL AFTER username;
/*2018-02-07 �޸��û���Ĭ��ֵ����Ϊ��*/
ALTER TABLE `user` MODIFY username VARCHAR(32) DEFAULT NULL;