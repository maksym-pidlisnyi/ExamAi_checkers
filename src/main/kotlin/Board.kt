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

    private fun generateAllMoves(color: String): ArrayList<Move> {
        val resultList = arrayListOf<Move>()
        for (cell in board) {
            if (cell.color == color) {
                resultList.addAll(generateMoves(color, cell))
            }
        }
        return resultList
    }

    private fun getUp(i: Int, j: Int): Int {
        return if (i % 2 == 0) j else j - 1
    }

    private fun getDown(i: Int, j: Int): Int {
        return if (i % 2 == 0) j + 1 else j
    }

    private fun isEven(cell: Cell): Boolean {
        return cell.row % 2 == 0
    }

    private fun generateMoves(playerColor: String, cell: Cell): ArrayList<Move> {
        val result = ArrayList<Move>()
        //find valid not ling moves
        val rowChange = if (playerColor == "RED") 1 else -1
        val enemyColor = if (rowChange == 1) "BLACK" else "RED"
        if (!cell.king) {
            var row = cell.row + rowChange
            if (row in 0..7) {

                //↙↘           daun
                var col = getDown(cell.row, cell.column)
                var pos = getPosition(row, col)
                if (col < 4) {
                    var nCell = board[pos - 1]
                    if (nCell.color == enemyColor) {
                        //↙↘           daun one more
                        val killPos = pos
                        col = getDown(row, col)
                        row += rowChange
                        pos = getPosition(row, col)
                        if (row in 0..7 && col >= 0 && col < 4) {
                            nCell = board[pos - 1]

                            //if empty make move
                            if (nCell.color == "NONE") {
                                result.add(Move(cell.position, pos))
                            }
                        }
                    } else if (nCell.color == "NONE") {
                        result.add(Move(cell.position, pos))
                    }
                }

                //↖↗           BBePx
                row = cell.row + rowChange
                col = getUp(cell.row, cell.column)
                pos = getPosition(row, col)
                if (col >= 0) {
                    var nCell = board[pos - 1]
                    if (nCell.color == enemyColor) {
                        //↖↗           BBePx one more
                        val killPos = pos
                        col = getUp(row, col)
                        row += rowChange
                        pos = getPosition(row, col)
                        if (row in 0..7 && col >= 0 && col < 4) {
                            nCell = board[pos - 1]

                            //if empty make move
                            if (nCell.color == "NONE") {
                                result.add(Move(cell.position, pos))
                            }
                        }
                    } else if (nCell.color == "NONE") {
                        result.add(Move(cell.position, pos))
                    }
                }
            }
        }
        //else {
//            find valid king moves
        //↗           BBePx
//            result.addAll(findKingMoves(cell.position, cell.row, cell.column, enemyColor, Direction.UpRight))
//            //↘           daun
//            result.addAll(findKingMoves(cell.position, cell.row, cell.column, enemyColor, Direction.DownRight))
//            //↖           BBePx
//            result.addAll(findKingMoves(cell.position, cell.row, cell.column, enemyColor, Direction.UpLeft))
//            //↙           daun
//            result.addAll(findKingMoves(cell.position, cell.row, cell.column, enemyColor, Direction.DownLeft))
        // }
        return result
    }


}