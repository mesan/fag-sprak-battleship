package no.mesan.battleship

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
open class Application {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplicationBuilder(Application::class.java)
                    .properties(mapOf(Pair("server.port", 83746)))
                    .build()
                    .run(*args)
        }
    }
}