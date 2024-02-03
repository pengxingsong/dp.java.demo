show TBaseDB;

CREATE TABLE TUser (
                       Id INT AUTO_INCREMENT PRIMARY KEY,
                       UserName VARCHAR(255),
                       UserPwd VARCHAR(255),
                       NickName VARCHAR(255),
                       CreateUserId INT,
                       CreateTime TIMESTAMP,
                       UpdateUserId INT,
                       UpdateTime TIMESTAMP,
                       DeleteFlag INT
);
