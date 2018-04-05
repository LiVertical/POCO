/* 我的收藏 2018-03-01 */
CREATE TABLE myCollect(
  productId VARCHAR(32) NOT NULL,
  userId VARCHAR(32) NOT NULL,
  createTime DATETIME NULL,
  curStatus VARCHAR(3) NULL COMMENT '当前收藏内容的状态，1：收藏 ，0：取消收藏',
  primary key (productId)
)
/*系统通知 2018-03-01*/
CREATE TABLE notifiaction(
  notifiactionId VARCHAR(32) NOT NULL COMMENT '消息id',
  notifiactionInfo TEXT NOT NULL COMMENT '消息内容',
  userId VARCHAR(32) NULL,
  createTime DATETIME NULL,
  usefulLife INT(32) NULL COMMENT '有效使用时长',
  curStatus VARCHAR(3) NULL COMMENT '当前通知的状态，0：删除 1：使用中',
  primary key (notifiactionId)
)
/* 用户 2018-03-05*/
CREATE TABLE users(
  userId INT PRIMARY KEY AUTO_INCREMENT,
  userName VARCHAR(32) NULL,
  loginName VARCHAR(32) NOT NULL,
  loginPass VARCHAR(32)NOT NULL,
  age INT(6) NULL,
  email VARCHAR(32) NULL,
  sex INT(2) NULL COMMENT '0：男，1：女',
  faceImg TEXT NULL,
  role INT(2) NOT NULL DEFAULT '1' COMMENT '0：普通用户，1：系统用户',  
  createTime DATETIME NULL,
  curStatus VARCHAR(3) DEFAULT '1' COMMENT '0:注销状态，1：正常状态'
)
/* 点赞实现*/
CREATE TABLE likes(
  id VARCHAR(32) NOT NULL,
  userId VARCHAR(32) NOT NULL,
  product_id VARCHAR(32) NOT NULL,
  notifiactionId VARCHAR(32) DEFAULT NULL
)
/* 我的收藏*/
CREATE TABLE collects(
  id VARCHAR(32) NOT NULL,
  userId VARCHAR(32) NOT NULL,
  productId VARCHAR(32) NOT NULL
)
/* 评论*/
CREATE TABLE comments(
  commentId VARCHAR(32) NOT NULL,
  commentUser VARCHAR(32) NOT NULL,
  productId VARCHAR(32) NOT NULL,
  createTime DATE NOT NULL,
  commentDesc TEXT NOT NULL,
  commentStatus INT(2) NOT NULL COMMENT '1：正常状态，0：非正常状态'
)
