package br.com.rubensrodrigues.dummy_movie.dto

import br.com.rubensrodrigues.dummy_movie.domain.entity.Movie
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class MovieRequest(
    @field:NotBlank val title: String,
    @field:Min(1888) @field:Max(2100) val year: Int,
    val genres: String? = null,
    val synopsis: String? = null,
    @field:Positive(message = "durationMin must be > 0")
    val durationMin: Int? = null
)

data class MovieResponse(
    val id: Long,
    val title: String,
    val year: Int,
    val genres: String?,
    val synopsis: String?,
    val durationMin: Int?
) {
    companion object {
        fun from(entity: Movie) = MovieResponse(
            id = entity.id!!,
            title = entity.title,
            year = entity.year,
            genres = entity.genres,
            synopsis = entity.synopsis,
            durationMin = entity.durationMin
        )
    }
}

fun MovieRequest.toEntity() = Movie(
    title = title,
    year = year,
    genres = genres,
    synopsis = synopsis,
    durationMin = durationMin
)