data class LastMove(val player: String, val last_moves: Array<Array<Int>>) {
    fun getLastMove(): Array<Int> {
        return last_moves[last_moves.size - 1]
    }

    fun getLastMoveFrom(): Int {
        return getLastMove()[0]
    }

    fun getLastMoveTo(): Int {
        return getLastMove()[1]
    }
}