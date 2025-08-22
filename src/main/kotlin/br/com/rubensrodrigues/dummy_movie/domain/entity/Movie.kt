package br.com.rubensrodrigues.dummy_movie.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.time.Instant

@Entity
@Table(name = "movies")
class Movie(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank
    @Column(nullable = false, length = 200)
    var title: String,

    @field:Min(1888) @field:Max(2100)
    @Column(nullable = false)
    var year: Int,

    @Column(length = 200)
    var genres: String? = null,

    @Column(columnDefinition = "text")
    var synopsis: String? = null,

    var durationMin: Int? = null,

    @Column(name = "created_at", updatable = false, insertable = false)
    var createdAt: Instant? = null
)