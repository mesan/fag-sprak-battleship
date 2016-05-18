package no.mesan.battleship.gui

import net.yested.Component
import net.yested.el

fun display(n: Component) {
    el("app")?.innerHTML = ""
    el("app")?.appendChild(n.element)
}