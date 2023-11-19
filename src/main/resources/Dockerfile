FROM openjdk:8
# 创建者
MAINTAINER bolo.bienao.life
WORKDIR /home
# 拷贝jar包
COPY springboot-init-0.0.1-SNAPSHOT.jar .
COPY application-pro.yml /home/config/
COPY data.db /home/db/
CMD ["java","-jar","-Duser.timezone=GMT+08","/home/springboot-init-0.0.1-SNAPSHOT.jar","--spring.config.location=/home/config/application-pro.yml"]
