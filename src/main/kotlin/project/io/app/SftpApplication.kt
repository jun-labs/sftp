package project.io.app

import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SftpApplication

fun main(args: Array<String>) {
    runApplication<SftpApplication>(*args)
}

inline fun <reified T> T.Logger() = LoggerFactory.getLogger(T::class.java)!!
