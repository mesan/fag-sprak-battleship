import kotlin.browser.document

fun main(args: Array<String>) {
    val e = document.getElementById("main")!!
    e.textContent = "Frontend"
}