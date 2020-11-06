DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (
    id INT auto_increment NOT NULL,
    username VARCHAR(64) NOT NULL,
    email VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    salt VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS role;
CREATE TABLE IF NOT EXISTS role(
    id INT auto_increment primary key NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS permission;
CREATE TABLE IF NOT EXISTS permission(
    id INT auto_increment primary key NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS organization;
CREATE TABLE IF NOT EXISTS organization(
    id INT auto_increment primary key NOT NULL,
    name VARCHAR(64) NOT NULL ,
    number VARCHAR(64) NOT NULL ,
    parent INT NOT NULL,
    type INT NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS user_role;
CREATE TABLE IF NOT EXISTS user_role(
    id INT auto_increment primary key NOT NULL,
    user_id INT NOT NULL ,
    role_id INT NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 组织与用户关联表
--
DROP TABLE IF EXISTS organization_user;
CREATE TABLE IF NOT EXISTS organization_user(
    id INT auto_increment primary key NOT NULL,
    user_id INT NOT NULL,
    organization_id INT NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;