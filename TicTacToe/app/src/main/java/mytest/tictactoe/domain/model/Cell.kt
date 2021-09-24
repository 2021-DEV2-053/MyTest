package mytest.tictactoe.domain.model

data class Cell(
    var x: Int? = null,
    var y: Int? = null,
    var value: Char? = null
) {
    override fun toString(): String {
        return "$x$y"
    }
}