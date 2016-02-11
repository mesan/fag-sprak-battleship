package no.mesan.battleship.domain

import com.fasterxml.jackson.annotation.JsonValue

data class GameId(val value: Int) {
    // Finnes det en bedre måte å gjøre dette på?
    // Gjør at GameId objektet ikke er synlig i json.
    // F.eks "id" = 3 i stedet for "id" = { value = "3" }
    @JsonValue
    fun id() = value
}
data class Username(val value: String) {
    @JsonValue
    fun name() = value
}