import java.lang.Double.NEGATIVE_INFINITY
import java.lang.Double.POSITIVE_INFINITY
import kotlin.math.max
import kotlin.math.min


fun getMinimaxMove(
    board: Board,
    depth: Int,
    singleCell: Cell?
): Move? //picking and returning a move from all possible moves based on the current state of the board
{
    var moves: List<Move> = if(singleCell != null) board.generateMoves(singleCell) else board.generateAllMoves(board.color)
    if (moves.isEmpty())
        return null

    if (board.attackMoves.isNotEmpty()) moves = board.attackMoves

    val scores = DoubleArray(moves.size)
    var maxScoreIndex = 0
    var alpha: Double = NEGATIVE_INFINITY //alpha keeps track of max score
    val beta: Double = POSITIVE_INFINITY //beta keeps track of mini score

    for ((i, move) in moves.withIndex()) {

        val newBoard =
            performMove(move, board, board.color)  //getting a copy of the board and making the current move in the copy

        scores[i] = getMinimaxScore(newBoard, depth, true, alpha, beta)

        if (scores[i] > scores[maxScoreIndex]) maxScoreIndex = i //keeping track of the best move
        alpha = max(alpha, scores[i])
    }
    return moves[maxScoreIndex]
}

fun getMinimaxScore(
    board: Board,
    depth: Int,
    maxing: Boolean,
    alpha1: Double,
    beta1: Double
): Double {
    var alpha = alpha1
    var beta = beta1
    val moves: List<Move> = board.generateAllMoves(board.color)

    val playerColor = board.color
    val enemyColor = if (playerColor == "RED") "BLACK" else "RED"

    if (depth == 0) return if (maxing) getPlayerScore(board, board.color) else getPlayerScore(board, board.color)


    if (moves.isEmpty())
        return if (maxing) NEGATIVE_INFINITY else POSITIVE_INFINITY

    return if (maxing) //maximising
    {
        var best: Double = NEGATIVE_INFINITY
        for (move in moves) {

            val newBoard = performMove(move, board, enemyColor)

            val score = getMinimaxScore(newBoard, depth - 1, false, alpha, beta)
            best = max(best, score)
            alpha = max(alpha, score)
            if (beta <= alpha) break
        }
        best
    } else  //minimising
    {
        var best: Double = POSITIVE_INFINITY
        for (move in moves) {
            val newBoard = performMove(move, board, enemyColor)
            val score = getMinimaxScore(newBoard, depth - 1, true, alpha, beta)
            best = min(best, score)
            beta = min(beta, score) //update beta with the minimum value
            if (beta <= alpha) break
        }
        best
    }
}

private fun performMove(move: Move, board: Board, color: String): Board {
    val prevCellArr: MutableList<Cell> = mutableListOf()
    for (cell in board.cells) {
        prevCellArr.add(Cell(cell.color, cell.row, cell.column, cell.king, cell.position))
    }

    if (move.cellToAttack != null) {
        for (cell in prevCellArr) {
            if (cell.position == move.cellToAttack!!.position) {
                cell.color = "NONE"
            }
        }
    }

    for (cell in prevCellArr) {
        if (cell.position == move.from.position) {
            cell.position = move.to.position
            cell.row = move.to.row
            cell.column = move.to.column

            if (move.to.row == 0 || move.to.row == 7) {
                cell.king = true
            }
        }
    }
    return Board(prevCellArr.toTypedArray(), color)
}

fun getPlayerScore(board: Board, color: String): Double {
    val enemyColor = if (color == "RED") "BLACK" else "RED"
    return getPlayerScoreWeighted(board, color) - getPlayerScoreWeighted(board, enemyColor)
}

fun getPlayerScoreWeighted(board: Board, color: String): Double {
    val weights = doubleArrayOf(4.0, 8.0, 1.0, 2.0, 1.5, -3.0)
    val enemyColor = if (color == "RED") "BLACK" else "RED"

    val scores = mutableListOf(0, 0, 0, 0, 0, 0)
    scores[0] = board.getNonKings(color)
    scores[1] = board.getKings(color)

    for(cell in board.cells) {
        if(cell.color == color) {
            val row = cell.row
            val col = cell.column

            if (row == 0 || row == 7) {
                scores[2]++
            } else {
                if (row == 3 || row == 4) {
                    if (col == 1 || col == 2) {
                        scores[3]++
                    } else {
                        scores[4]++
                    }
                }

                if (row < 7) {
                    val lUp: Int = board.getPosition(row - 1, board.getUpDown(row, col, "Up")) - 1
                    val rUp: Int = board.getPosition(row + 1, board.getUpDown(row, col, "Up")) - 1
                    val lDown: Int = board.getPosition(row - 1, board.getUpDown(row, col, "Down")) - 1
                    val rDown: Int = board.getPosition(row + 1, board.getUpDown(row, col, "Down")) - 1

                    if (col in 1..2) {
                        if (board.board[rUp].color == enemyColor && board.board[lDown].color == "NONE") {
                            scores[5]++
                        } else if (board.board[rDown].color == enemyColor && board.board[lUp].color == "NONE") {
                            scores[5]++
                        } else if (board.board[lUp].color == enemyColor && board.board[lUp].king && board.board[rDown].color == "NONE") {
                            scores[5]++
                        } else if (board.board[lDown].color == enemyColor && board.board[lDown].king && board.board[rUp].color == "NONE") {
                            scores[5]++
                        }
                    }
                }
            }
        }
    }
    var result = 0.0
    for((i,sc) in scores.withIndex()){
        result += sc * weights[i]
    }
    return result
}