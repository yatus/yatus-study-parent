# 主机登录
mysql -uroot -P3306 -h127.0.0.1 -p

# 需要执行操作
GRANT REPLICATION SLAVE ON *.* TO 'root'@'127.0.0.1:3307' IDENTIFIED BY 'yatus';

flush privileges;
Subtopic

# 从机登录
mysql -uroot -P3307 -h127.0.0.1 -p



