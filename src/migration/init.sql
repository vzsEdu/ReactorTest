
CREATE TABLE `reactortest`.`reactor_test_table` (
  reactorTestTableId bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  name varchar(200) NOT NULL COMMENT 'name',
  comment varchar(200) COMMENT 'comments',
  createdAt datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'create at',
  PRIMARY KEY (reactorTestTableId)
) ENGINE=InnoDB AUTO_INCREMENT=1;

