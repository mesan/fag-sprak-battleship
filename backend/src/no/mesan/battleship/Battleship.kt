package no.mesan.battleship

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Application {
    companion object {
        @JvmStatic public fun main(args: Array<String>) {
            SpringApplication.run(no.mesan.battleshipApplication::class.java, *args)
        }
    }
}