package com.ttbkk.api.place.place_hashtags;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceHashtagsRepository extends JpaRepository<Long, PlaceHashtags> {

}
