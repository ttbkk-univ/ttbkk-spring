CREATE TABLE review (
    id char(32) NOT NULL,
    star tinyint NOT NULL,
    comment text,
    created_at datetime(6) DEFAULT NULL,
    updated_at datetime(6) DEFAULT NULL,
    place_id char(32) NOT NULL,
    user_id char(32) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT review_place_id_fk_place_id FOREIGN KEY (place_id) REFERENCES place (id),
    CONSTRAINT review_user_id_fk_user_id FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;