INSERT INTO brand
SELECT * FROM ttbkk_old.brand_brand;

INSERT INTO place
SELECT * FROM ttbkk_old.place_place;

INSERT INTO hashtag
SELECT * FROM ttbkk_old.hashtag_hashtag;

INSERT INTO place_hashtags
SELECT * FROM ttbkk_old.place_place_hashtags;