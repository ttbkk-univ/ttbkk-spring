-- 마이그레이션 방법
-- 기존 데이터의 스키마명은 'ttbkk_old'로 설정되어있습니다.

-- 1. ./docker-compose/mysql/data 폴더에 ttbkk_new.sql 파일 복사
-- 2. 터미널에서 docker exec -it ttbkk-mysql bash 로 접속
-- 3. 명령어 실행 : cd /var/lib/mysql
-- 4. mysql -u root -p 로 mysql 접속
-- 5. 명령어 실행 : create database ttbkk;
-- 5-1. 스키마 이름이 'ttbkk_old'가 아닌 경우
-- 		명령어 실행 : drop database <현재 스키마 이름>;
-- 		명령어 실행 : create database ttbkk_old;
-- 		명령어 실행 : use ttbkk_old;
-- 		명령어 실행 : source ttbkk_20.sql;
-- 6. 명령어 실행 : use ttbkk;
-- 7. 명령어 실행 : source ttbkk_new.sql;

CREATE TABLE auth_group (
                            id int NOT NULL AUTO_INCREMENT,
                            name varchar(150) NOT NULL,
                            PRIMARY KEY (id),
                            UNIQUE KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE content_type (
                              id int NOT NULL AUTO_INCREMENT,
                              app_label varchar(100) NOT NULL,
                              model varchar(100) NOT NULL,
                              PRIMARY KEY (id),
                              CONSTRAINT UK_content_type UNIQUE (app_label, model)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO content_type
SELECT * FROM ttbkk_old.django_content_type;

CREATE TABLE hashtag (
                         name varchar(150) NOT NULL,
                         created_at datetime(6) NOT NULL,
                         PRIMARY KEY (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO hashtag
SELECT * FROM ttbkk_old.hashtag_hashtag;

CREATE TABLE migrations (
                            id bigint NOT NULL AUTO_INCREMENT,
                            app varchar(255) NOT NULL,
                            name varchar(255) NOT NULL,
                            applied datetime(6) NOT NULL,
                            PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO migrations
SELECT * FROM ttbkk_old.django_migrations;

CREATE TABLE auth_user (
                           id int NOT NULL AUTO_INCREMENT,
                           password varchar(128) NOT NULL,
                           last_login datetime(6) DEFAULT NULL,
                           is_superuser tinyint NOT NULL,
                           username varchar(150) NOT NULL,
                           first_name varchar(150) NOT NULL,
                           last_name varchar(150) NOT NULL,
                           email varchar(254) NOT NULL,
                           is_staff tinyint NOT NULL,
                           is_active tinyint NOT NULL,
                           date_joined datetime(6) NOT NULL,
                           PRIMARY KEY (id),
                           UNIQUE KEY (username)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO auth_user
SELECT * FROM ttbkk_old.auth_user;

CREATE TABLE session (
                         session_key varchar(40) NOT NULL,
                         session_data longtext NOT NULL,
                         expire_date datetime(6) NOT NULL,
                         PRIMARY KEY (session_key),
                         KEY session_expire_date (expire_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO session
SELECT * FROM ttbkk_old.django_session;

CREATE TABLE user (
                      id char(32) NOT NULL,
                      nickname varchar(50) NOT NULL,
                      social_id varchar(50) NOT NULL,
                      social_type varchar(20) NOT NULL,
                      created_at datetime(6) NOT NULL,
                      updated_at datetime(6) NOT NULL,
                      PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE brand (
                       id char(32) NOT NULL,
                       name varchar(150) NOT NULL,
                       description longtext,
                       created_at datetime(6) DEFAULT NULL,
                       updated_at datetime(6) DEFAULT NULL,
                       created_by_id char(32) DEFAULT NULL,
                       updated_by_id char(32) DEFAULT NULL,
                       PRIMARY KEY (id),
                       KEY brand_created_by_id_fk_user_id (created_by_id),
                       KEY brand_updated_by_id_fk_user_id (updated_by_id),
                       CONSTRAINT brand_created_by_id_fk_user_id FOREIGN KEY (created_by_id) REFERENCES user (id),
                       CONSTRAINT brand_updated_by_id_fk_user_id FOREIGN KEY (updated_by_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO brand
SELECT * FROM ttbkk_old.brand_brand;

CREATE TABLE place (
                       id char(32) NOT NULL,
                       name varchar(150) NOT NULL,
                       latitude decimal(15,13) NOT NULL,
                       longitude decimal(15,12) NOT NULL,
                       is_deleted tinyint NOT NULL,
                       description longtext,
                       created_at datetime(6) NOT NULL,
                       updated_at datetime(6) NOT NULL,
                       brand_id char(32) DEFAULT NULL,
                       created_by_id char(32) DEFAULT NULL,
                       updated_by_id char(32) DEFAULT NULL,
                       telephone varchar(100) DEFAULT NULL,
                       address varchar(100) DEFAULT NULL,
                       PRIMARY KEY (id),
                       KEY place_brand_id_fk_brand_id (brand_id),
                       KEY place_created_by_id_fk_user_id (created_by_id),
                       KEY place_updated_by_id_fk_user_id (updated_by_id),
                       CONSTRAINT place_brand_id_fk_brand_id FOREIGN KEY (brand_id) REFERENCES brand (id),
                       CONSTRAINT place_created_by_id_fk_user_id FOREIGN KEY (created_by_id) REFERENCES user (id),
                       CONSTRAINT place_updated_by_id_fk_user_id FOREIGN KEY (updated_by_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO place
SELECT * FROM ttbkk_old.place_place;

CREATE TABLE place_hashtags (
                                id bigint NOT NULL AUTO_INCREMENT,
                                place_id char(32) NOT NULL,
                                hashtag_id varchar(150) NOT NULL,
                                PRIMARY KEY (id),
                                UNIQUE KEY UK_place_hashtags (place_id, hashtag_id),
                                KEY place_hashtags_hashtag_id_fk_hashtag_name (hashtag_id),
                                CONSTRAINT place_hashtags_hashtag_id_fk_hashtag_name FOREIGN KEY (hashtag_id) REFERENCES hashtag (name),
                                CONSTRAINT place_hashtags_place_id_fk_place_id FOREIGN KEY (place_id) REFERENCES place (id)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO place_hashtags
SELECT * FROM ttbkk_old.place_place_hashtags;

CREATE TABLE brand_hashtags (
                                id bigint NOT NULL AUTO_INCREMENT,
                                brand_id char(32) NOT NULL,
                                hashtag_id varchar(150) NOT NULL,
                                PRIMARY KEY (id),
                                UNIQUE KEY UK_brand_hashtags (brand_id, hashtag_id),
                                KEY brand_hashtags_hashtag_id_fk_hashtag_name (hashtag_id),
                                CONSTRAINT brand_hashtags_brand_id_fk_brand_id FOREIGN KEY (brand_id) REFERENCES brand (id),
                                CONSTRAINT brand_hashtags_hashtag_id_fk_hashtag_name FOREIGN KEY (hashtag_id) REFERENCES hashtag (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE auth_permission (
                                 id int NOT NULL AUTO_INCREMENT,
                                 name varchar(255) NOT NULL,
                                 content_type_id int NOT NULL,
                                 codename varchar(100) NOT NULL,
                                 PRIMARY KEY (id),
                                 UNIQUE KEY UK_auth_permission (content_type_id, codename),
                                 CONSTRAINT auth_permission_content_type_id_fk_content_type_id FOREIGN KEY (content_type_id) REFERENCES content_type (id)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO auth_permission
SELECT * FROM ttbkk_old.auth_permission;

CREATE TABLE auth_group_permissions (
                                        id bigint NOT NULL AUTO_INCREMENT,
                                        group_id int NOT NULL,
                                        permission_id int NOT NULL,
                                        PRIMARY KEY (id),
                                        UNIQUE KEY UK_auth_group_permissions (group_id, permission_id),
                                        KEY auth_group_permissions_permission_id_fk_auth_perm (permission_id),
                                        CONSTRAINT auth_group_permissions_permission_id_fk_auth_perm FOREIGN KEY (permission_id) REFERENCES auth_permission (id),
                                        CONSTRAINT auth_group_permissions_group_id_fk_auth_group_id FOREIGN KEY (group_id) REFERENCES auth_group (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE auth_user_permissions (
                                       id bigint NOT NULL AUTO_INCREMENT,
                                       user_id int NOT NULL,
                                       permission_id int NOT NULL,
                                       PRIMARY KEY (id),
                                       UNIQUE KEY UK_auth_user_permissions (user_id, permission_id),
                                       KEY auth_user_permissions_permission_id_fk_auth_perm (permission_id),
                                       CONSTRAINT auth_user_permissions_permission_id_fk_auth_perm FOREIGN KEY (permission_id) REFERENCES auth_permission (id),
                                       CONSTRAINT auth_user_permissions_user_id_fk_auth_user_id FOREIGN KEY (user_id) REFERENCES auth_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE auth_user_groups (
                                  id bigint NOT NULL AUTO_INCREMENT,
                                  user_id int NOT NULL,
                                  group_id int NOT NULL,
                                  PRIMARY KEY (id),
                                  UNIQUE KEY UK_auth_user_groups (user_id, group_id),
                                  KEY auth_user_groups_group_id_fk_auth_group_id (group_id),
                                  CONSTRAINT auth_user_groups_group_id_fk_auth_group_id FOREIGN KEY (group_id) REFERENCES auth_group (id),
                                  CONSTRAINT auth_user_groups_user_id_fk_auth_user_id FOREIGN KEY (user_id) REFERENCES auth_user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE admin_log (
                           id int NOT NULL AUTO_INCREMENT,
                           action_time datetime(6) NOT NULL,
                           object_id longtext,
                           object_repr varchar(200) NOT NULL,
                           action_flag smallint unsigned NOT NULL,
                           change_message longtext NOT NULL,
                           content_type_id int DEFAULT NULL,
                           user_id int NOT NULL,
                           PRIMARY KEY (id),
                           KEY admin_log_content_type_id_fk_content_type_id (content_type_id),
                           KEY admin_log_user_id_fk_auth_user_id (user_id),
                           CONSTRAINT admin_log_content_type_id_fk_content_type_id FOREIGN KEY (content_type_id) REFERENCES content_type (id),
                           CONSTRAINT admin_log_user_id_fk_auth_user_id FOREIGN KEY (user_id) REFERENCES auth_user (id),
                           CONSTRAINT admin_log_chk_1 CHECK ((action_flag >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO admin_log
SELECT * FROM ttbkk_old.django_admin_log;