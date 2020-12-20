import java.lang.Float.NEGATIVE_INFINITY
import java.lang.Float.POSITIVE_INFINITY


fun getAIMove(
    board: Board,
    depth: Int,
    color: String
): Move? //picking and returning a move from all possible moves based on the current state of the board
{
    val moves: List<Move> = board.generateAllMoves(color)
    require(moves.isNotEmpty())
    val scores = FloatArray(moves.size)
    var maxScoreIndex = 0
    var i = 0
    var alpha: Float = NEGATIVE_INFINITY //alpha keeps track of max score
    val beta: Float = POSITIVE_INFINITY //beta keeps track of mini score

    for (move in moves) {

        var prevCellArr = board.cells
        if (move.cellToAttack != null) {
            for (cell in prevCellArr) {
                if (cell.position == move.cellToAttack!!.position) {
                    cell.color = "NONE"
                }
                if (cell.position == move.from.position) {
                    cell.position = move.to.position
                    cell.row = move.to.row
                    cell.column = move.to.column
                }
            }
        } else {
            for (cell in prevCellArr) {
                if (cell.position == move.from.position) {
                    cell.position = move.to.position
                    cell.row = move.to.row
                    cell.column = move.to.column
                }
            }
        }

        val moved = Board(prevCellArr) //getting a copy of the board and making the current move in the copy

        scores[i] = getScore(moved, depth, true, alpha, beta, color)  //false -- isKingMove

        if (scores[i] > scores[maxScoreIndex]) maxScoreIndex = i //keeping track of the best move
        alpha = Math.max(alpha, scores[i])
        i++
    }
    return moves[maxScoreIndex]
}


fun getScore(
    board: Board,
    depth: Int,
    maxing: Boolean,
    alpha: Float,
    beta: Float,
    color: String
): Float {
    //var depth = depth
    var alpha = alpha
    var beta = beta
    val moves: List<Move> = board.generateAllMoves(color)

    if (depth == 0) return if (maxing) getPlayerScore(board, "RED") else -getPlayerScore(board, "BLACK")

    //if (color == "RED") depth++

    if (moves.size == 0) //if no moves i.e. no pieces or blocked, then current player loses
        return if (maxing) NEGATIVE_INFINITY else POSITIVE_INFINITY

    return if (maxing) //maximising
    {
        var best: Float = NEGATIVE_INFINITY
        for (move in moves) {

            //////Here you make move
            var prevCellArr = board.cells
            if (move.cellToAttack != null) {
                for (cell in prevCellArr) {
                    if (cell.position == move.cellToAttack!!.position) {
                        cell.color = "NONE"
                    }
                    if (cell.position == move.from.position) {
                        cell.position = move.to.position
                        cell.row = move.to.row
                        cell.column = move.to.column
                    }
                }
            } else {
                for (cell in prevCellArr) {
                    if (cell.position == move.from.position) {
                        cell.position = move.to.position
                        cell.row = move.to.row
                        cell.column = move.to.column
                    }
                }
            }
            ////*************

            val score = getScore(Board(prevCellArr), depth - 1, false, alpha, beta, "BLACK")     //isKingMode
            best = Math.max(best, score)
            alpha = Math.max(alpha, score) //update alpha with maximum value
            if (beta <= alpha) break
        }
        best
    }
            else  //minimising
    {
        var best: Float = POSITIVE_INFINITY
        for (move in moves) {


            //////Here
            var prevCellArr = board.cells
            if (move.cellToAttack != null) {
                for (cell in prevCellArr) {
                    if (cell.position == move.cellToAttack!!.position) {
                        cell.color = "NONE"
                    }
                    if (cell.position == move.from.position) {
                        cell.position = move.to.position
                        cell.row = move.to.row
                        cell.column = move.to.column
                    }
                }
            } else {
                for (cell in prevCellArr) {
                    if (cell.position == move.from.position) {
                        cell.position = move.to.position
                        cell.row = move.to.row
                        cell.column = move.to.column
                    }
                }
            }
            ////*************

            val score = getScore(Board(prevCellArr), depth - 1, true, alpha, beta, "RED")
            best = Math.min(best, score)
            beta = Math.min(beta, score) //update beta with the minimum value
            if (beta <= alpha) break
        }
        best
    }
}


fun getPlayerScore(board: Board, color: String): Float {
    if (color == "RED") {
        return board.getRed().toFloat() / (board.getRed() + board.getBlack()).toFloat()
    } else {
        return board.getBlack().toFloat() / (board.getRed() + board.getBlack()).toFloat()
    }
}
