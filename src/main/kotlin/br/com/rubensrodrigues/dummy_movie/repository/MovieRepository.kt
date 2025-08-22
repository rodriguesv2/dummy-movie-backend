package br.com.rubensrodrigues.dummy_movie.repository

import br.com.rubensrodrigues.dummy_movie.domain.entity.Movie
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MovieRepository : JpaRepository<Movie, Long> {

    @Query(
        """
        select m from Movie m
        where (:title is null or lower(m.title) like lower(concat('%', :title, '%')))
          and (:genre is null or m.genres is not null and lower(m.genres) like lower(concat('%', :genre, '%')))
          and (:yearFrom is null or m.year >= :yearFrom)
          and (:yearTo   is null or m.year <= :yearTo)
        """
    )
    fun search(
        @Param("title") title: String?,
        @Param("genre") genre: String?,
        @Param("yearFrom") yearFrom: Int?,
        @Param("yearTo") yearTo: Int?,
        pageable: Pageable
    ): Page<Movie>
}