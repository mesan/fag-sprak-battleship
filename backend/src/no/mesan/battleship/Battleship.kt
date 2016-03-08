package no.mesan.battleship

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class Battleship {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Battleship::class.java, *args)
        }
    }
}