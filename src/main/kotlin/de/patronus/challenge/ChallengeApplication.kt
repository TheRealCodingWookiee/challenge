package de.patronus.challenge

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChallengeApplication {
	companion object {
		const val SERVER_URL_PATH_API = "/api"
	}
}

fun main(args: Array<String>) {
	runApplication<ChallengeApplication>(*args)
}
