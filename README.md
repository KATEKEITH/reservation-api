# reservation-api

- 예약서비스 및 사용자 추적 대시보드 만들기 프로젝트 백엔드
- 라이선스 : MIT
- 프론트엔드 저장소 : <https://github.com/KATEKEITH/reservation-ui>

## 개발환경

- Vanilla JS
- Spring
- Apache Kafka
- H2 + Apache Cassandra

## Usage

**Zookeeper 실행**

```
bin/zookeeper-server-start.sh config/zookeeper.properties
```

**Kafka 실행**

```
bin/kafka-server-start.sh config/server.properties
```


## User Data collection Architecture

![image](https://user-images.githubusercontent.com/46472768/110494914-61449100-8137-11eb-9132-4ba2a2578611.png)


## Used Open Source

- [spring-framework](https://github.com/spring-projects/spring-framework)
- [Apache Kafka](https://kafka.apache.org/)

- [Apache Cassandra](http://cassandra.apache.org/)
- [H2](http://www.h2database.com/html/license.html)

- [Spring Security](https://spring.io/projects/spring-security)
