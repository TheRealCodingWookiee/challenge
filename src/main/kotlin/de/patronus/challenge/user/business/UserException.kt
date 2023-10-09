package de.patronus.challenge.user.business

import de.patronus.challenge.common.ProblemException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND

abstract class UserException(code: String, status: HttpStatus):
    ProblemException(
        title = "User Exception: $code",
        status = status,
        error = code,
    )

class UserNotFound : UserException("USER_NOT_FOUND", NOT_FOUND)