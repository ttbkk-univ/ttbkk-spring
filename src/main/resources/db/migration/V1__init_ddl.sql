CREATE TABLE `user` (
                             `id` char(32) NOT NULL,
                             `nickname` varchar(50) NOT NULL,
                             `social_id` varchar(50) NOT NULL,
                             `social_type` varchar(20) NOT NULL,
                             `created_at` datetime(6) NOT NULL,
                             `updated_at` datetime(6) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `brand` (
                               `id` char(32) NOT NULL,
                               `name` varchar(150) NOT NULL,
                               `description` longtext,
                               `created_at` datetime(6) DEFAULT NULL,
                               `updated_at` datetime(6) DEFAULT NULL,
                               `created_by_id` char(32) DEFAULT NULL,
                               `updated_by_id` char(32) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `brand_created_by_id_83aa3ab4_fk_user_id` (`created_by_id`),
                               KEY `brand_updated_by_id_f255c88b_fk_user_id` (`updated_by_id`),
                               CONSTRAINT `brand_created_by_id_83aa3ab4_fk_user_id` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
                               CONSTRAINT `brand_updated_by_id_f255c88b_fk_user_id` FOREIGN KEY (`updated_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `place` (
                               `id` char(32) NOT NULL,
                               `name` varchar(150) NOT NULL,
                               `latitude` decimal(15,13) NOT NULL,
                               `longitude` decimal(15,12) NOT NULL,
                               `is_deleted` tinyint(1) NOT NULL,
                               `description` longtext,
                               `created_at` datetime(6) NOT NULL,
                               `updated_at` datetime(6) NOT NULL,
                               `brand_id` char(32) DEFAULT NULL,
                               `created_by_id` char(32) DEFAULT NULL,
                               `updated_by_id` char(32) DEFAULT NULL,
                               `telephone` varchar(100) DEFAULT NULL,
                               `address` varchar(100) DEFAULT NULL,
                               PRIMARY KEY (`id`),
                               KEY `place_brand_id_4fad8cc6_fk_brand_id` (`brand_id`),
                               KEY `place_created_by_id_358e6d83_fk_user_id` (`created_by_id`),
                               KEY `place_updated_by_id_6ce6d554_fk_user_id` (`updated_by_id`),
                               CONSTRAINT `place_brand_id_4fad8cc6_fk_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
                               CONSTRAINT `place_created_by_id_358e6d83_fk_user_id` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`),
                               CONSTRAINT `place_updated_by_id_6ce6d554_fk_user_id` FOREIGN KEY (`updated_by_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `hashtag` (
                                   `name` varchar(150) NOT NULL,
                                   `created_at` datetime(6) NOT NULL,
                                   PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `brand_hashtags` (
                                        `id` bigint NOT NULL AUTO_INCREMENT,
                                        `brand_id` char(32) NOT NULL,
                                        `hashtag_id` varchar(150) NOT NULL,
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `brand_hashtags_brand_id_hashtag_id_696de634_uniq` (`brand_id`,`hashtag_id`),
                                        KEY `brand_hashtags_hashtag_id_be8eaaf4_fk_hashtag_name` (`hashtag_id`),
                                        CONSTRAINT `brand_hashtags_brand_id_c26b071f_fk_brand_id` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
                                        CONSTRAINT `brand_hashtags_hashtag_id_be8eaaf4_fk_hashtag_name` FOREIGN KEY (`hashtag_id`) REFERENCES `hashtag` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `place_hashtags` (
                                        `id` bigint NOT NULL AUTO_INCREMENT,
                                        `place_id` char(32) NOT NULL,
                                        `hashtag_id` varchar(150) NOT NULL,
                                        PRIMARY KEY (`id`),
                                        UNIQUE KEY `place_hashtags_place_id_hashtag_id_3e5ba0f0_uniq` (`place_id`,`hashtag_id`),
                                        KEY `place_hashtags_hashtag_id_7dcbc83a_fk_hashtag_name` (`hashtag_id`),
                                        CONSTRAINT `place_hashtags_hashtag_id_7dcbc83a_fk_hashtag_name` FOREIGN KEY (`hashtag_id`) REFERENCES `hashtag` (`name`),
                                        CONSTRAINT `place_hashtags_place_id_a54344ea_fk_place_id` FOREIGN KEY (`place_id`) REFERENCES `place` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
