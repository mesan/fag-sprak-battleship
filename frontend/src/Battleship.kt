
import net.yested.Component
import net.yested.div
import net.yested.el

fun main(args: Array<String>) {

//    get<String>("test.txt") {
//        text -> e.textContent = text
//    }

    val rows = 10
    val cols = 10

    val test = div {
        for (row in 0 until rows) {
            div {
                for (col in 0 until cols) {
                    span {
                        onmousedown = {
                            println("Click [$row,$col]")
                        }
                        +"Cell"
                    }
                }
            }
        }
    }

    setContent(test)
}

fun setContent(n: Component) {
    el("app")?.innerHTML = ""
    el("app")?.appendChild(n.element)
}