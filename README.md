<div align="center">

# 🧩 Code Club

**A DDD-styled Spring Cloud microservice platform for programmers — an online question bank with categories, full-text search, likes & a contributor leaderboard, plus RBAC auth and a WeChat-code login flow behind a gateway.**

**English** | [简体中文](README.zh-CN.md)

![Java](https://img.shields.io/badge/Java-8-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.4.2-6DB33F?logo=springboot&logoColor=white)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2020.0.6-6DB33F?logo=spring&logoColor=white)
![Spring Cloud Alibaba](https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2021.1-FF6A00)
![Sa-Token](https://img.shields.io/badge/Auth-Sa--Token%201.37.0-blue)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-sessions%20%26%20counters-DC382D?logo=redis&logoColor=white)
![Elasticsearch](https://img.shields.io/badge/Elasticsearch-7.5.2-06B8D4?logo=elasticsearch&logoColor=white)
![RocketMQ](https://img.shields.io/badge/RocketMQ-2.1.1-D42428?logo=apacherocketmq&logoColor=white)
![XXL-Job](https://img.shields.io/badge/XXL--Job-2.3.1-2BA744)
![MinIO](https://img.shields.io/badge/MinIO-8.2.0-green?logo=minio&logoColor=white)
![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)
![GitHub stars](https://img.shields.io/github/stars/Andrewlv/code-club?style=social)

</div>

---

## 📖 About

Code Club (程序员 Coding 社区) is a multi-service "coding community" whose core business is a **question bank**: admins categorize questions (radio / multiple / judge / short-answer) with labels, users browse, search, like, and practice them. Around that core, the repo demonstrates how to cut a small platform into DDD-style microservices:

- **code-club-auth** — users, roles, permissions (RBAC) and Sa-Token login
- **code-club-subject** — the question bank domain (the largest service)
- **code-club-practice** — practice sets built on top of questions
- **code-club-gateway** — Spring Cloud Gateway with route-level auth
- **code-club-oss** — file upload to MinIO
- **code-club-wx** — WeChat Official Account callback (scan-to-login)

Services register themselves with **Nacos** (config + discovery), talk over **OpenFeign**, cache and count with **Redis**, search with **Elasticsearch**, stream events over **RocketMQ**, and run scheduled jobs on **XXL-Job** (the standalone `wx` service is Redis-only).

## ✨ Features

### Core business

- ❓ **Question bank** — create questions of 4 types (radio / multiple / judge / short answer). A `SubjectTypeHandlerFactory` + strategy pattern dispatches each type to its own handler and table (`subject_radio` / `subject_multiple` / `subject_judge` / `subject_brief`)
- 🗂️ **Categories & labels** — full CRUD for question categories and labels, linked to questions via a many-to-many `subject_mapping` table
- 🔎 **Elasticsearch full-text search** — every new question is mirrored into the `subject_index` ES index by a hand-rolled multi-cluster `EsRestClient`; `/subject/getSubjectPageBySearch` serves keyword queries
- 👍 **Likes** — recorded in Redis (hash + per-question counter), then flushed to MySQL by the `syncLikedJobHandler` XXL-Job
- 🏆 **Contributor leaderboard** — each new question bumps a Redis ZSet; `/subject/getContributeList` returns the top 5 with nicknames fetched over Feign from the auth service
- 🧑‍💻 **Auth (RBAC)** — user / role / permission / role-permission management, register / update / enable / disable / delete, all cached to Redis for the gateway to consume
- 🚪 **Gateway auth** — Sa-Token reactor filter checks login on `/oss/**` and `/subject/**`, requires the `subject:add` permission for question creation, and injects the `loginId` header downstream
- 📝 **Practice sets** — `/practice/set/getSpecialPracticeContent` assembles special-practice content grouped by category and label
- 💬 **WeChat-code login** — send “验证码” to the bound Official Account, and the `wx` service stores `code → openId` in Redis for 5 minutes; `POST /auth/user/doLogin?validCode=N` exchanges it for a Sa-Token

### Engineering

- 🏗️ **DDD layering** — every domain service is split into `application` (controllers / DTO converters) · `domain` (services / BOs / strategy handlers) · `infra` (MyBatis-Plus mappers / Redis / ES / RPC) · `common` · `starter`
- 🐘 **Druid pool** with slow-SQL logging and a monitoring console (`/druid/*`)
- 📜 **Log4j2** replaces the default logback in all services
- 🧬 **Code generator** — `easy-gen-code-spring-boot-starter` (Velocity templates) + `code-club-gen` scaffold a whole DDD module (controller / service / BO / DAO / XML) from a table config; the likes module was generated this way
- 🧵 **dynamic-thread-pool** starter module (work in progress)

## 🧱 Tech Stack

| Category | Technology |
| --- | --- |
| Framework | Spring Boot 2.4.2 · Spring MVC · Spring AOP |
| Microservices | Spring Cloud 2020.0.6 (Gateway, OpenFeign, LoadBalancer) · Spring Cloud Alibaba 2021.1 (Nacos config + discovery) |
| Auth | Sa-Token 1.37.0 (reactor integration in the gateway, Redis + Jackson session store) |
| Data Access | MyBatis-Plus 3.4.0 · Druid 1.1.21 connection pool (password encrypted via ConfigFilter) |
| Database | MySQL 8 |
| Cache | Redis (Lettuce pool) — likes, counters, role/permission cache, login codes |
| Search | Elasticsearch 7.5.2 (custom multi-cluster `EsRestClient`) |
| Messaging | RocketMQ 2.1.1 (test topic producer + consumer in the subject service) |
| Scheduling | XXL-Job 2.3.1 (executor `code-club-subject`) |
| Object Storage | MinIO 8.2.0 (oss service) |
| Mapping | MapStruct 1.4.2.Final · fastjson |
| Utilities | Lombok · Guava `Preconditions` · commons-lang3 |

## 📂 Project Structure

```
code-club
├── doc/code-club-init.sql                 # 17 tables: auth_*, subject_*, practice_*
├── code-club-auth                         # Auth domain (DDD)
│   ├── code-club-auth-api                 # Feign interface (UserFeignService) + shared DTOs
│   ├── code-club-auth-application         # controller (user/role/permission) · mq · job
│   ├── code-club-auth-domain              # domain services, Sa-Token login
│   ├── code-club-auth-infra               # mappers, Redis, Druid
│   ├── code-club-auth-common               # Result, enums, utils
│   └── code-club-auth-starter             # boot app (port 3011)
├── code-club-subject                      # Question-bank domain (DDD, the core service)
│   ├── code-club-subject-application      # controllers (subject/category/label/liked) · mq
│   ├── code-club-subject-domain           # strategy handlers, ES sync, likes, SyncLikedJob
│   ├── code-club-subject-infra            # mappers, EsRestClient, UserRpc (Feign)
│   ├── code-club-subject-common           # Result, PageResult, LoginUtil
│   └── code-club-subject-starter          # boot app (port 3010)
├── code-club-practice                     # Practice domain
│   ├── code-club-practice-api             # shared entities/VOs
│   └── code-club-practice-server          # boot app (port 3013)
├── code-club-gateway                      # Spring Cloud Gateway + Sa-Token (port 5000)
├── code-club-oss                          # MinIO file upload (port 4000)
├── code-club-wx                           # WeChat OA callback + login codes (port 3012)
├── code-club-common-starter
│   └── easy-gen-code-spring-boot-starter  # Velocity-based DDD code generator
├── code-club-gen                          # generator templates & configs (uses the starter)
└── dynamic-thread-pool                    # custom thread-pool starter (WIP)
```

> Service ports are env-overridable — e.g. `PORT_WX` (default **3012**) and `PORT_PRACTICE` (default **3013**) — so nothing conflicts out of the box.

## 🚀 Quick Start

There is **no root POM**: every top-level directory is an independent Maven project, so you build and run each service yourself.

### Prerequisites

| Requirement | Version | Required? | Notes |
| --- | --- | --- | --- |
| JDK | 8 | ✅ | All modules target Java 8 |
| Maven | 3.6+ | ✅ | No wrapper included |
| MySQL | 8 | ✅ | Database `code-club` |
| Nacos | 2.x | ✅ | Config + service discovery for all service apps |
| Redis | 6+ | ✅ | Sa-Token sessions, likes, counters, login codes |
| Elasticsearch | 7.5.2 | ✅ | Question search (`subject_index`) |
| RocketMQ | 4.x | ⬜ | Only for the MQ producer/consumer demo |
| XXL-Job Admin | 2.3.1 | ⬜ | Only for the likes-sync scheduled job |
| MinIO | any | ⬜ | Only for the oss file-upload service |
| WeChat OA credentials | — | ⬜ | Only for the wx login-callback service |

### 1. Create the database

The init script creates **18 tables** (`auth_user`, `auth_role`, `auth_permission`, `auth_role_permission`, `auth_user_role`, `subject_info`, `subject_category`, `subject_label`, `subject_liked`, `subject_mapping`, `subject_radio`, `subject_multiple`, `subject_judge`, `subject_brief`, `practice_set`, `practice_info`, `practice_detail`, `practice_set_detail`) but not the database itself:

```bash
mysql -u root -p -e "CREATE DATABASE \`code-club\` DEFAULT CHARACTER SET utf8mb4;"
mysql -u root -p code-club < doc/code-club-init.sql
```

### 2. Point the services at your middleware

All `bootstrap.yml` / `application.yml` files default to `localhost` and read overrides from environment variables:

| Middleware | Environment variables |
| --- | --- |
| MySQL | `MYSQL_HOST`, `MYSQL_PORT`, `MYSQL_PASSWORD` |
| Redis | `REDIS_HOST`, `REDIS_PASSWORD` |
| Nacos | `NACOS_HOST` |
| Elasticsearch | `ES_HOST` |
| RocketMQ | `ROCKETMQ_HOST` |
| XXL-Job | `XXL_JOB_HOST` |
| MinIO | `MINIO_HOST`, `MINIO_ACCESS_KEY`, `MINIO_SECRET_KEY` |
| Druid console | `DRUID_CONSOLE_USER`, `DRUID_CONSOLE_PASSWORD` |
| Service ports | `PORT_WX`, `PORT_PRACTICE` |

> ⚠️ **Security note:** this repository previously committed real credentials for a public server. They have been scrubbed from the configs, but if you cloned before that fix, **rotate your passwords and treat the old ones as compromised** — they remain readable in old git history.

### 3. Build in dependency order

```bash
# 1) install the auth API jar (the subject service Feign-calls it)
cd code-club-auth && mvn clean install

# 2) build the remaining services
cd ../code-club-subject && mvn clean package
cd ../code-club-practice && mvn clean package
cd ../code-club-gateway   && mvn clean package
cd ../code-club-oss       && mvn clean package
cd ../code-club-wx        && mvn clean package

# optional: install the code-generator starter
cd ../code-club-common-starter && mvn clean install
```

### 4. Start the services

Each `*-starter` / server module is a plain Spring Boot app (`mvn spring-boot:run` or `java -jar target/*.jar`):

| Service | Port | Start command (from its starter dir) |
| --- | --- | --- |
| Auth | 3011 | `mvn spring-boot:run -pl code-club-auth-starter` |
| Subject | 3010 | `mvn spring-boot:run -pl code-club-subject-starter` |
| Practice | 3013 | `mvn spring-boot:run -pl code-club-practice-server` |
| OSS | 4000 | `mvn spring-boot:run` (in `code-club-oss`) |
| WX | 3012 | `mvn spring-boot:run` (in `code-club-wx`) |
| Gateway | 5000 | `mvn spring-boot:run` (in `code-club-gateway`) |

Services discover each other through Nacos (`auth`, `subject`, `practice`, `oss` and the gateway register themselves; the `wx` service is standalone, Redis-only), so start Nacos first and the gateway last.

### 5. Try it

Through the gateway (`http://localhost:5000`):

| Method | Path | Description |
| --- | --- | --- |
| POST | `/auth/user/doLogin?validCode=N` | Exchange a WeChat login code for a Sa-Token |
| POST | `/auth/user/register` | Register a user (call directly on port 3011 — the gateway's `LoginFilter` blocks anonymous traffic) |
| POST | `/subject/subject/add` | Create a question — requires the `subject:add` permission |
| POST | `/subject/subject/getSubjectPage` | Page questions by category / label |
| POST | `/subject/subject/getSubjectPageBySearch` | Elasticsearch keyword search |
| POST | `/subject/subject/querySubjectInfo` | Question detail (with like state, prev/next ids) |
| POST | `/subject/subject/getContributeList` | Top-5 contributor leaderboard |
| POST | `/subject/subjectLiked/add` | Like / unlike a question (Redis) |
| POST | `/oss/upload` | Upload a file to MinIO |
| GET | `/practice/set/getSpecialPracticeContent` | Practice-set content (direct, port 3013) |

The auth service also exposes Druid monitoring at `http://localhost:3011/druid/` (and likewise on the other DB services) — set `DRUID_CONSOLE_USER` / `DRUID_CONSOLE_PASSWORD` before exposing anything.

## 🧩 Optional Modules

<details>
<summary><b>RocketMQ — messaging demo</b></summary>

The subject starter registers a `rocketmq-spring-boot-starter` producer; `POST /subject/subject/pushMessage?id=1` sends a test message to the `first-topic`, consumed by `TestConsumer` in the `code-club-application-mq` module. Set `rocketmq.name-server` to your broker to enable it.

</details>

<details>
<summary><b>XXL-Job — scheduled likes sync</b></summary>

Likes are written to Redis first; the `syncLikedJobHandler` XXL-Job (`SyncLikedJob` in the subject domain) periodically drains the `subject.liked` hash into the `subject_liked` MySQL table. Configure `xxl.job.admin.addresses` to point at your XXL-Job Admin and register the `code-club-subject` executor.

</details>

<details>
<summary><b>WeChat — Official Account login</b></summary>

Point your OA's callback URL at the `wx` service's `/callback` (GET verifies the signature with the token configured in `CallBackController`, POST routes messages). Texting “验证码” to the account replies with a 5-minute code; the web app then calls `/auth/user/doLogin` with it. Replace the hard-coded `token` in `CallBackController` with your own.

</details>

<details>
<summary><b>MinIO — file upload</b></summary>

The `oss` service uploads to MinIO via `/upload` (bucket + object name parameters). The `storage.service.type` value shown by `/testNacos` is meant to be supplied from a Nacos `code-club-oss` YAML config — create it in your Nacos console.

</details>

<details>
<summary><b>Code generator</b></summary>

`code-club-gen` drives the `easy-gen-code-spring-boot-starter`: describe a table in `src/main/resources/genCode/*.yml`, and its Velocity templates emit a full DDD slice (controller, DTO + converter, BO + converter, domain service, DAO, MyBatis XML). Run `GenCodeMain` after installing the starter (`code-club-common-starter`).

</details>

## 🐳 Docker

No Dockerfile is included — each service is a standard executable Spring Boot jar, so containerize with any base image you like (e.g. `openjdk:8-jdk` + the `target/*.jar` from step 3).

## 🧪 Tests

```bash
cd dynamic-thread-pool/dynamic-thread-pool-spring-boot-starter && mvn test
```

This is the only test module in the repo (`ApiTest`) — the services themselves currently ship without unit tests.

## 🤝 Acknowledgments

This project is a personal rebuild of the **jc-club（鸡翅 Club）** community project from [程序员鸡翅](https://space.bilibili.com)'s Bilibili course *《Java 微服务从 0 到 1 带你做社区项目实战》* — the original course design (DDD question-bank microservices, the WeChat login flow, and several config defaults) is theirs. The `dynamic-thread-pool` starter module is scaffolded from 小傅哥's open-source framework archetype. Many thanks to both for sharing their work! 🙏

## ⭐ Support

If this project helped you understand microservice architecture or DDD, a star is appreciated — it helps other developers find it.
