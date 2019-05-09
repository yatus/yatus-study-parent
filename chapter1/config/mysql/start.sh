#!/bin/bash

function build_mysql_container()
{
    cur_dir=`pwd`

    type=$1
    export_port=$2
    container_name="$1"-mysql
    link_name="$3"-mysql

    docker stop ${container_name}
    docker rm ${container_name}
    docker run --name ${container_name} \
    --link $link_name:service \
    -v ${cur_dir}/$type/conf:/etc/mysql/conf.d \
    -v ${cur_dir}/$type/data:/var/lib/mysql \
    -p ${export_port}:3306 \
    -e MYSQL_ROOT_PASSWORD=yatus \
    -d mysql:5.6
}
# 写库
build_mysql_container master 3306 slave
echo "======执行写库容器完成============"

# 读库
build_mysql_container slave 3307 master
echo "======执行读库容器完成============"
