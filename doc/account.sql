CREATE DATABASE if not exists account DEFAULT CHARSET=utf8 ;

USE account;

DROP TABLE IF EXISTS member;
CREATE TABLE member (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(50) DEFAULT NULL COMMENT '用户名',
  phone varchar(20) DEFAULT NULL COMMENT '手机,唯一',
  email varchar(30) DEFAULT NULL COMMENT '邮箱,唯一',
  create_time datetime DEFAULT NULL COMMENT '添加时间',
  modify_time datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (id),
  UNIQUE KEY phone (phone),
  UNIQUE KEY email (email)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员表';


DROP TABLE IF EXISTS member_account;
CREATE TABLE member_account (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  mid bigint(20) NOT NULL COMMENT '会员ID',
  account varchar(50) NOT NULL COMMENT '登录账号,可以为用户名,手机,邮箱',
  password varchar(255) DEFAULT NULL,
  create_time datetime DEFAULT NULL COMMENT '添加时间',
  modify_time datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (id),
  UNIQUE KEY account (account)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员账户表';


DROP TABLE IF EXISTS member_info_log;
CREATE TABLE member_info_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  mid bigint(20) NOT NULL COMMENT '会员id',
  password varchar(50) DEFAULT NULL COMMENT '密码',
  phone varchar(20) DEFAULT NULL COMMENT '手机',
  email varchar(30) DEFAULT NULL COMMENT '邮箱',
  modify_time datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员信息日志表';


DROP TABLE IF EXISTS member_login_log;
CREATE TABLE member_login_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  mid bigint(20) NOT NULL COMMENT '会员id',
  account varchar(50) DEFAULT NULL COMMENT '登录账户',
  status int(11) DEFAULT NULL COMMENT '登录状态,1-成功,0-失败',
  login_ip varchar(20) DEFAULT NULL COMMENT '登录ip',
  login_zone varchar(30) DEFAULT NULL COMMENT '登录区域',
  login_time datetime DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='会员登录日志表';