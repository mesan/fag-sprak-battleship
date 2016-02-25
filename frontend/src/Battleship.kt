
import kotlin.browser.document

fun main(args: Array<String>) {
    val e = document.getElementById("main")!!
    e.textContent = "Frontend"

    get<String>("test.txt") {
        text -> e.textContent = text
    }

}