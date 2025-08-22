package br.com.rubensrodrigues.dummy_movie.service

import br.com.rubensrodrigues.dummy_movie.dto.MovieRequest
import br.com.rubensrodrigues.dummy_movie.dto.MovieResponse
import br.com.rubensrodrigues.dummy_movie.dto.toEntity
import br.com.rubensrodrigues.dummy_movie.repository.MovieRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MovieService(private val repo: MovieRepository) {

    @Transactional(readOnly = true)
    fun list(title: String?, genre: String?, yearFrom: Int?, yearTo: Int?, pageable: Pageable): Page<MovieResponse> {
        return repo.searchNative(title?.ifBlank { null }, genre?.ifBlank { null }, yearFrom, yearTo, pageable)
            .map(MovieResponse::from)
    }

    @Transactional
    fun create(req: MovieRequest): MovieResponse {
        val saved = repo.save(req.toEntity())
        return MovieResponse.from(saved)
    }

    @Transactional(readOnly = true)
    fun get(id: Long): MovieResponse =
        MovieResponse.from(repo.findById(id).orElseThrow { NoSuchElementException("Movie $id not found") })

    @Transactional
    fun update(id: Long, req: MovieRequest): MovieResponse {
        val m = repo.findById(id).orElseThrow { NoSuchElementException("Movie $id not found") }
        m.title = req.title
        m.year = req.year
        m.genres = req.genres
        m.synopsis = req.synopsis
        m.durationMin = req.durationMin
        return MovieResponse.from(repo.save(m))
    }

    @Transactional
    fun delete(id: Long) {
        if (!repo.existsById(id)) throw NoSuchElementException("Movie $id not found")
        repo.deleteById(id)
    }
}