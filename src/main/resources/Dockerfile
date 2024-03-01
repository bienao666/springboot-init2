FROM adoptopenjdk/openjdk8-openj9:ubi
# 设置创建者(可选配)
MAINTAINER bolo.bienao.life
# 设置工作目录
WORKDIR /home
# 拷贝jar包
COPY springboot-init2-0.0.1-SNAPSHOT.jar .
# 拷贝配置文件
COPY application-pro.yml /home/config/
# 拷贝数据库文件(sqlite数据是一个文件，如果对接的是其他如mysql数据库，则没有该配置)
COPY data.db /home/db/
# 配置启动参数
CMD ["java","-jar","-Duser.timezone=GMT+08","/home/springboot-init2-0.0.1-SNAPSHOT.jar","--spring.config.location=/home/config/application-pro.yml"]
