CREATE TABLE bookmark_color (              -- **즐겨찾기 색상 테이블
    id char(32) NOT NULL,                  -- 즐겨찾기 색상 아이디
    code char(7) NOT NULL,                 -- 즐겨찾기 색상 코드 (ex: #0000ff)
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE bookmark_group (              -- **즐겨찾기 그룹 테이블
    id char(32) NOT NULL,                  -- 즐겨찾기 그룹 아이디
    user_id char(32) NOT NULL,             -- 즐겨찾기 그룹 소유자 아이디 (FK)
    name varchar(150) NOT NULL,            -- 즐겨찾기 그룹 이름
    remark varchar(500),                   -- 즐겨찾기 장소 비고
    bookmark_color_id char(32),            -- 즐겨찾기 그룹 색상 아이디 (FK)
    created_at datetime(6) NOT NULL,       -- 등록일시
    updated_at datetime(6) NOT NULL,       -- 수정일시
    PRIMARY KEY (id),
    KEY fk__bookmark_group__user_id__user__id (user_id),
    KEY fk__bookmark_group__bookmark_color_id__bookmark_color__id (bookmark_color_id),
    CONSTRAINT fk__bookmark_group__user_id__user__id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk__bookmark_group__bookmark_color_id__bookmark_color__id FOREIGN KEY (bookmark_color_id) REFERENCES bookmark_color (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE bookmark (                    -- **즐겨찾기 테이블
    id char(32) NOT NULL,                  -- 즐겨찾기 아이디
    bookmark_group_id char(32) NOT NULL,   -- 즐겨찾기 그룹 아이디 (FK)
    user_id char(32) NOT NULL,             -- 즐겨찾기 소유자 아이디 (FK)
    place_id char(32) NOT NULL,            -- 즐겨찾기 장소 아이디 (FK)
    name varchar(150),                     -- 즐겨찾기 이름
    remark varchar(500),                   -- 즐겨찾기 비고
    bookmark_color_id char(32),            -- 즐겨찾기 색상 아이디 (FK)
    created_at datetime(6) NOT NULL,       -- 등록일시
    updated_at datetime(6) NOT NULL,       -- 수정일시
    PRIMARY KEY (id),
    KEY fk__bookmark__group_id__bookmark_group__id (bookmark_group_id),
    KEY fk__bookmark__user_id__user__id (user_id),
    KEY fk__bookmark__place_id__place__id (place_id),
    KEY fk__bookmark__bookmark_color_id__bookmark_color__id (bookmark_color_id),
    CONSTRAINT fk__bookmark__group_id__bookmark_group__id FOREIGN KEY (bookmark_group_id) REFERENCES bookmark_group (id),
    CONSTRAINT fk__bookmark__user_id__user__id FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk__bookmark__place_id__place__id FOREIGN KEY (place_id) REFERENCES place (id),
    CONSTRAINT fk__bookmark__bookmark_color_id__bookmark_color__id FOREIGN KEY (bookmark_color_id) REFERENCES bookmark_color (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE bookmark_group_pointer (      -- **즐겨찾기->그룹 가리키는 테이블(?) (하나의 장소 복수의 즐겨찾기그룹 가능하기 위해!)
    bookmark_id char(32) NOT NULL,         -- 즐겨찾기 아이디
    bookmark_group_id char(32) NOT NULL,   -- 즐겨찾기 그룹 아이디
    PRIMARY KEY (bookmark_id, bookmark_group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;