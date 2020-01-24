package com.example.cinema.cinemapz.resource;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cinema.cinemapz.model.MovieCategory;
import org.springframework.data.jpa.repository.Query;

public interface MovieCategoryResource extends JpaRepository<MovieCategory, Integer> {

    @Query(value = "select mc.id, mct.category_name as name "
            + "from movie_category mc join movie_category_translation mct on mc.id = mct.category_id "
            + "where mct.translation_id = (select id from translation_language where name = ?) ", nativeQuery = true)
    List<MovieCategoryProjection> findAllWithTranslation(String lang);

    interface MovieCategoryProjection {
        int getId();
        String getName();
    }
}
