
SHOW TProductDB;

CREATE TABLE TProductCategory (
                                  Id INT AUTO_INCREMENT PRIMARY KEY,
                                  Name VARCHAR(255),
                                  Remark VARCHAR(255),
                                  CreateUserId INT,
                                  CreateTime TIMESTAMP,
                                  UpdateUserId INT,
                                  UpdateTime TIMESTAMP,
                                  DeleteFlag INT
);
CREATE TABLE TProductSPU (
                             Id INT AUTO_INCREMENT PRIMARY KEY,
                             Name VARCHAR(255),
                             Remark VARCHAR(255),
                             Category INT,
                             CreateUserId INT,
                             CreateTime TIMESTAMP,
                             UpdateUserId INT,
                             UpdateTime TIMESTAMP,
                             DeleteFlag INT
);