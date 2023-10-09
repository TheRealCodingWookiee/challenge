package de.patronus.challenge.common

import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.web.ErrorResponseException

open class ProblemException(
    title: String,
    status: HttpStatusCode,
    override val cause: Exception? = null,
    val error: Any? = null,
) : ErrorResponseException(status, ProblemDetail.forStatusAndDetail(status, title), cause) {

    init {
        body.setProperty("error", error)
        body.title = title
    }
}