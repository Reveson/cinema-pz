package com.example.cinema.cinemapz.resource;

import java.util.List;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.cinema.cinemapz.dto.ProjectionIdWithEpoch;
import com.example.cinema.cinemapz.dto.SimpleMovie;
import com.example.cinema.cinemapz.model.Movie;

public interface MovieResource extends JpaRepository<Movie, Integer> {

	@Query(value = "select m.id, mt.name, m.image_url as image "
			+ "from movie m join movie_translation mt on m.id = mt.movie_id "
			+ "where mt.translation_id = (select id from translation_language where name = ?)", nativeQuery = true) //TODO
	List<SimpleMovieProjection> findAllSimple(String lang);

	@Query(value = "select m.id, mt.name, m.image_url as image "
			+ "from movie m join movie_translation mt on m.id = mt.movie_id "
			+ "where mt.translation_id = (select id from translation_language where name = ?2) "
			+ "and m.category_id = ?1 ", nativeQuery = true)
	List<SimpleMovieProjection> findByCategorySimple(int categoryId, String lang);

	@Query("select new com.example.cinema.cinemapz.dto.ProjectionIdWithEpoch(p.id, p.movieStartTimestamp) "
			+ "from Projection p where p.movieEvent.movie.id = ?1")
	List<ProjectionIdWithEpoch> getProjectionDates(int movieId);

	@Query(value = "select m.id, mt.name, m.age, mt.description, m.duration, mct.category_name, m.image_url as image "
			+ "from movie m join movie_translation mt on m.id = mt.movie_id "
			+ "join movie_category_translation mct on mct.category_id = m.category_id "
			+ "where m.id = ?1 "
			+ "and mct.translation_id = (select id from translation_language where name = ?2) "
			+ "and mt.translation_id = (select id from translation_language where name = ?2) ", nativeQuery = true)
	Optional<MovieProjection> findByIdProjection(int movieId, String lang);


	interface SimpleMovieProjection {
		int getId();
		String getName();
		String getImage();
	}

	interface MovieProjection {
		int getId();
		String getName();
		String getAge();
		String getDescription();
		String getDuration();
		String getCategory();
		String getImage();
	}
}
