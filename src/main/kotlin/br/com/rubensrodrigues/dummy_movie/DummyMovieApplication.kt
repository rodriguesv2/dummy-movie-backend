package br.com.rubensrodrigues.dummy_movie

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DummyMovieApplication

fun main(args: Array<String>) {
	runApplication<DummyMovieApplication>(*args)
}
