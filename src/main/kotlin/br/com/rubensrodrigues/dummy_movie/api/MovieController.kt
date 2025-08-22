package br.com.rubensrodrigues.dummy_movie.api

import br.com.rubensrodrigues.dummy_movie.dto.MovieRequest
import br.com.rubensrodrigues.dummy_movie.dto.MovieResponse
import br.com.rubensrodrigues.dummy_movie.service.MovieService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movies")
class MovieController(private val service: MovieService) {

    @GetMapping
    fun list(
        @RequestParam(required = false) title: String?,
        @RequestParam(required = false) genre: String?,
        @RequestParam(required = false) yearFrom: Int?,
        @RequestParam(required = false) yearTo: Int?,
        @PageableDefault(size = 20, sort = ["title"]) pageable: Pageable
    ): Page<MovieResponse> =
        service.list(title, genre, yearFrom, yearTo, pageable)

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): MovieResponse = service.get(id)

    @PostMapping
    fun create(@Valid @RequestBody req: MovieRequest): MovieResponse = service.create(req)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody req: MovieRequest): MovieResponse =
        service.update(id, req)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}