import java.util.*


data class Board(val cells: Array<Cell>) {
    private var board: Array<Cell> = Array<Cell>(4 * 8) { Cell("NONE", 0, 0, false, 0) }

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
                //println(board[pos - 1])
            }
        }
        val moves = generateAllMoves("RED")
        println(moves.size)
    }

    private fun getPosition(row: Int, col: Int): Int {
        return row * 4 + col + 1
    }

    fun generateAllMoves(color: String): ArrayList<Move> {
        val resultList = arrayListOf<Move>()
        for (cell in board) {
            if (cell.color == color) {
                resultList.addAll(generateMoves(cell))
            }
        }
        return resultList
    }


    private fun getUpDown(i: Int, j: Int, direction: String): Int {
        if (direction.startsWith("Up")) {
            return if (i % 2 == 0) j else j - 1
        } else {
            return if (i % 2 == 0) j + 1 else j
        }
    }

    private fun isEven(cell: Cell): Boolean {
        return cell.row % 2 == 0
    }

    private fun getRed(): Int {
        var counter = 0;
        for (cell in board) {
            if (cell.color == "RED") {
                counter ++;
            }
        }
        return counter;
    }

    private fun getBlack(): Int {
        var counter = 0;
        for (cell in board) {
            if (cell.color == "BLACK") {
                counter++;
            }
        }
        return counter;
    }

    fun singleMove(cell: Cell, direction: String, enemyColor: String): Move? {
        var pos: Int
        var killPos: Int
        var delta = if (direction == "UpLeft" || direction == "DownLeft") -1 else 1
        var i: Int = cell.row + delta
        var j: Int = getUpDown(cell.row, cell.column, direction)
        if (i in 0..7 && j >= 0 && j < 4) {
            pos = getPosition(i, j)
            if (board[pos - 1].color == enemyColor) {
                //Move one more
                j = getUpDown(i, j, direction)
                i += delta
                if (i in 0..7 && j >= 0 && j < 4) {
                    killPos = pos
                    pos = getPosition(i, j)

                    //if empty make move
                    if (board[pos - 1].color == "NONE") {
                        return Move(cell.position, pos)
                    }
                }
            } else if (board[pos - 1].color == "NONE") {
                return Move(cell.position, pos)
            }
        }

        return null

    }


    private fun generateMoves(cell: Cell): ArrayList<Move> {
        val result = ArrayList<Move>()
        val enemyColor = if (cell.color == "RED") "BLACK" else "RED"
        if (cell.color == "RED") {
            var move = singleMove(cell, "UpRight", enemyColor);
            if (move != null)
                result.add(move);
            move = singleMove(cell, "DownRight", enemyColor);
            if (move != null)
                result.add(move);
            if (cell.king) {
                move = singleMove(cell, "UpLeft", enemyColor);
                if (move != null)
                    result.add(move)
                move = singleMove(cell, "DownLeft", enemyColor);
                if (move != null)
                    result.add(move)
            }
        } else if (cell.color == "BLACK") {
            var move = singleMove(cell, "UpLeft", enemyColor);
            if (move != null)
                result.add(move);
            move = singleMove(cell, "DownLeft", enemyColor);
            if (move != null)
                result.add(move);
            if (cell.king) {
                move = singleMove(cell, "UpRight", enemyColor);
                if (move != null)
                    result.add(move)
                move = singleMove(cell, "DownRight", enemyColor);
                if (move != null)
                    result.add(move)
            }
        }

        return result
    }


}