data class Board(val cells: Array<Cell>) {
    var board : Array<Cell> = Array<Cell>(4 * 8) { Cell("NONE", 0, 0, false, 0) }

    init {
        for (cell in cells) {
            board[cell.position - 1] = cell
        }
        var pos = 0;
        for (r in 0 until 8) {
            for (c in 0 until 4) {
                pos = getPosition(r, c)
                if (board[pos - 1].color == "NONE")
                    board[pos - 1] = Cell("NONE", r, c, false, pos)  // for now this way, mb later will change
                println(board[pos - 1])
            }
        }
    }

    private fun getPosition(row: Int, col: Int): Int {
        return row * 4 + col + 1
    }
}