package mytest.tictactoe.domain.model

enum class GameStatus {
    STARTING {
        override fun value() = "STARTING"
    };
    abstract fun value (): String
}