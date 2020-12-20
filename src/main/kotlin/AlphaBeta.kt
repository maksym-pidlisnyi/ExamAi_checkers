import java.lang.Float.NEGATIVE_INFINITY
import java.lang.Float.POSITIVE_INFINITY

//
//fun getAIMoveRed(
//    board: Board,
//    depth: Int,
//    color: String
//): Move? //picking and returning a move from all possible moves based on the current state of the board
//{
//    var moves: List<Move> = board.generateAllMoves(color)
//    require(moves.isNotEmpty())
//
//    if(board.attackMoves.isNotEmpty()) moves = board.attackMoves
//
//    val scores = FloatArray(moves.size)
//    var maxScoreIndex = 0
//    var i = 0
//    var alpha: Float = NEGATIVE_INFINITY //alpha keeps track of max score
//    val beta: Float = POSITIVE_INFINITY //beta keeps track of mini score
//
//    for (move in moves) {
//
//        var prevCellArr : MutableList<Cell> = mutableListOf()
//
//        for(cell in board.cells){
//            prevCellArr.add(Cell(cell.color, cell.row, cell.column, cell.king, cell.position))
//        }
//
//
//        if (move.cellToAttack != null) {
//            for (cell in prevCellArr) {
//                if (cell.position == move.cellToAttack!!.position) {
//                    cell.color = "NONE"
//                }
//                if (cell.position == move.from.position) {
//                    cell.position = move.to.position
//                    cell.row = move.to.row
//                    cell.column = move.to.column
//                }
//            }
//        } else {
//            for (cell in prevCellArr) {
//                if (cell.position == move.from.position) {
//                    cell.position = move.to.position
//                    cell.row = move.to.row
//                    cell.column = move.to.column
//                }
//            }
//        }
//
//        val moved = Board(prevCellArr.toTypedArray(), board.color) //getting a copy of the board and making the current move in the copy
//
//        scores[i] = getScoreRed(moved, depth, true, alpha, beta, color)  //false -- isKingMove
//
//        if (scores[i] > scores[maxScoreIndex]) maxScoreIndex = i //keeping track of the best move
//        alpha = Math.max(alpha, scores[i])
//        i++
//    }
//    return moves[maxScoreIndex]
//}
//
//
//fun getScoreRed(
//    board: Board,
//    depth: Int,
//    maxing: Boolean,
//    alpha: Float,
//    beta: Float,
//    color: String
//): Float {
//    //var depth = depth
//    var alpha = alpha
//    var beta = beta
//    val moves: List<Move> = board.generateAllMoves(color)
//
//    if (depth == 0) return if (maxing) getPlayerScore(board, "RED") else getPlayerScore(board, "BLACK")
//
//    //if (color == "RED") depth++
//
//    if (moves.size == 0) //if no moves i.e. no pieces or blocked, then current player loses
//        return if (maxing) NEGATIVE_INFINITY else POSITIVE_INFINITY
//
//    return if (maxing) //maximising
//    {
//        var best: Float = NEGATIVE_INFINITY
//        for (move in moves) {
//
//            //////Here you make move
//            var prevCellArr : MutableList<Cell> = mutableListOf()
//
//            for(cell in board.cells){
//                prevCellArr.add(Cell(cell.color, cell.row, cell.column, cell.king, cell.position))
//            }
//            if (move.cellToAttack != null) {
//                for (cell in prevCellArr) {
//                    if (cell.position == move.cellToAttack!!.position) {
//                        cell.color = "NONE"
//                    }
//                    if (cell.position == move.from.position) {
//                        cell.position = move.to.position
//                        cell.row = move.to.row
//                        cell.column = move.to.column
//                    }
//                }
//            } else {
//                for (cell in prevCellArr) {
//                    if (cell.position == move.from.position) {
//                        cell.position = move.to.position
//                        cell.row = move.to.row
//                        cell.column = move.to.column
//                    }
//                }
//            }
//            ////*************
//
//            val score = getScoreRed(Board(prevCellArr.toTypedArray(), board.color), depth - 1, false, alpha, beta, "BLACK")     //isKingMode
//            best = Math.max(best, score)
//            alpha = Math.max(alpha, score) //update alpha with maximum value
//            if (beta <= alpha) break
//        }
//        best
//    }
//            else  //minimising
//    {
//        var best: Float = POSITIVE_INFINITY
//        for (move in moves) {
//
//
//            //////Here
//            var prevCellArr : MutableList<Cell> = mutableListOf()
//
//            for(cell in board.cells){
//                prevCellArr.add(Cell(cell.color, cell.row, cell.column, cell.king, cell.position))
//            }
//            if (move.cellToAttack != null) {
//                for (cell in prevCellArr) {
//                    if (cell.position == move.cellToAttack!!.position) {
//                        cell.color = "NONE"
//                    }
//                    if (cell.position == move.from.position) {
//                        cell.position = move.to.position
//                        cell.row = move.to.row
//                        cell.column = move.to.column
//                    }
//                }
//            } else {
//                for (cell in prevCellArr) {
//                    if (cell.position == move.from.position) {
//                        cell.position = move.to.position
//                        cell.row = move.to.row
//                        cell.column = move.to.column
//                    }
//                }
//            }
//            ////*************
//
//            val score = getScoreRed(Board(prevCellArr.toTypedArray(), board.color), depth - 1, true, alpha, beta, "RED")
//            best = Math.min(best, score)
//            beta = Math.min(beta, score) //update beta with the minimum value
//            if (beta <= alpha) break
//        }
//        best
//    }
//}


fun getPlayerScore(board: Board, color: String): Float {
    return if (color == "RED") {
        board.getRed().toFloat() + board.getRedKings() * 2 - board.getBlack().toFloat() + board.getBlackKings() * 2
    } else {
        board.getBlack().toFloat() + board.getBlackKings() * 2 - board.getRed().toFloat() + board.getRedKings() * 2
    }
}

//
//fun getAIMoveBlack(
//    board: Board,
//    depth: Int,
//    color: String
//): Move? //picking and returning a move from all possible moves based on the current state of the board
//{
//    var moves: List<Move> = board.generateAllMoves(color)
//    require(moves.isNotEmpty())
//
//    if(board.attackMoves.isNotEmpty()) moves = board.attackMoves
//
//    val scores = FloatArray(moves.size)
//    var maxScoreIndex = 0
//    var i = 0
//    var alpha: Float = NEGATIVE_INFINITY //alpha keeps track of max score
//    val beta: Float = POSITIVE_INFINITY //beta keeps track of mini score
//
//    for (move in moves) {
//
//        var prevCellArr : MutableList<Cell> = mutableListOf()
//
//        for(cell in board.cells){
//            prevCellArr.add(Cell(cell.color, cell.row, cell.column, cell.king, cell.position))
//        }
//
//
//        if (move.cellToAttack != null) {
//            for (cell in prevCellArr) {
//                if (cell.position == move.cellToAttack!!.position) {
//                    cell.color = "NONE"
//                }
//                if (cell.position == move.from.position) {
//                    cell.position = move.to.position
//                    cell.row = move.to.row
//                    cell.column = move.to.column
//                }
//            }
//        } else {
//            for (cell in prevCellArr) {
//                if (cell.position == move.from.position) {
//                    cell.position = move.to.position
//                    cell.row = move.to.row
//                    cell.column = move.to.column
//                }
//            }
//        }
//
//        val moved = Board(prevCellArr.toTypedArray(), board.color) //getting a copy of the board and making the current move in the copy
//
//        scores[i] = getScoreBlack(moved, depth, true, alpha, beta, color)  //false -- isKingMove
//
//        if (scores[i] > scores[maxScoreIndex]) maxScoreIndex = i //keeping track of the best move
//        alpha = Math.max(alpha, scores[i])
//        i++
//    }
//    return moves[maxScoreIndex]
//}
//
//
//
//
//fun getScoreBlack(
//    board: Board,
//    depth: Int,
//    maxing: Boolean,
//    alpha: Float,
//    beta: Float,
//    color: String
//): Float {
//    //var depth = depth
//    var alpha = alpha
//    var beta = beta
//    val moves: List<Move> = board.generateAllMoves(color)
//
//    if (depth == 0) return if (maxing) getPlayerScore(board, "BLACK") else getPlayerScore(board, "RED")
//
//    //if (color == "RED") depth++
//
//    if (moves.size == 0) //if no moves i.e. no pieces or blocked, then current player loses
//        return if (maxing) NEGATIVE_INFINITY else POSITIVE_INFINITY
//
//    return if (maxing) //maximising
//    {
//        var best: Float = NEGATIVE_INFINITY
//        for (move in moves) {
//
//            //////Here you make move
//            var prevCellArr : MutableList<Cell> = mutableListOf()
//
//            for(cell in board.cells){
//                prevCellArr.add(Cell(cell.color, cell.row, cell.column, cell.king, cell.position))
//            }
//            if (move.cellToAttack != null) {
//                for (cell in prevCellArr) {
//                    if (cell.position == move.cellToAttack!!.position) {
//                        cell.color = "NONE"
//                    }
//                    if (cell.position == move.from.position) {
//                        cell.position = move.to.position
//                        cell.row = move.to.row
//                        cell.column = move.to.column
//                    }
//                }
//            } else {
//                for (cell in prevCellArr) {
//                    if (cell.position == move.from.position) {
//                        cell.position = move.to.position
//                        cell.row = move.to.row
//                        cell.column = move.to.column
//                    }
//                }
//            }
//            ////*************
//
//            val score = getScoreBlack(Board(prevCellArr.toTypedArray(), board.color), depth - 1, false, alpha, beta, "RED")     //isKingMode
//            best = Math.max(best, score)
//            alpha = Math.max(alpha, score) //update alpha with maximum value
//            if (beta <= alpha) break
//        }
//        best
//    }
//    else  //minimising
//    {
//        var best: Float = POSITIVE_INFINITY
//        for (move in moves) {
//
//
//            //////Here
//            var prevCellArr : MutableList<Cell> = mutableListOf()
//
//            for(cell in board.cells){
//                prevCellArr.add(Cell(cell.color, cell.row, cell.column, cell.king, cell.position))
//            }
//            if (move.cellToAttack != null) {
//                for (cell in prevCellArr) {
//                    if (cell.position == move.cellToAttack!!.position) {
//                        cell.color = "NONE"
//                    }
//                    if (cell.position == move.from.position) {
//                        cell.position = move.to.position
//                        cell.row = move.to.row
//                        cell.column = move.to.column
//                    }
//                }
//            } else {
//                for (cell in prevCellArr) {
//                    if (cell.position == move.from.position) {
//                        cell.position = move.to.position
//                        cell.row = move.to.row
//                        cell.column = move.to.column
//                    }
//                }
//            }
//            ////*************
//
//            val score = getScoreBlack(Board(prevCellArr.toTypedArray(), board.color), depth - 1, true, alpha, beta, "BLACK")
//            best = Math.min(best, score)
//            beta = Math.min(beta, score) //update beta with the minimum value
//            if (beta <= alpha) break
//        }
//        best
//    }
//}

/////////////////////////////////////////////

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

    val scores = FloatArray(moves.size)
    var maxScoreIndex = 0
    var i = 0
    var alpha: Float = NEGATIVE_INFINITY //alpha keeps track of max score
    val beta: Float = POSITIVE_INFINITY //beta keeps track of mini score

    for (move in moves) {

        val newBoard = performMove(move, board, board.color)  //getting a copy of the board and making the current move in the copy

        scores[i] = getMinimaxScore(newBoard, depth, true, alpha, beta)

        if (scores[i] > scores[maxScoreIndex]) maxScoreIndex = i //keeping track of the best move
        alpha = Math.max(alpha, scores[i])
        i++
    }
    return moves[maxScoreIndex]
}


fun getMinimaxScore(
    board: Board,
    depth: Int,
    maxing: Boolean,
    alpha: Float,
    beta: Float
): Float {

    var alpha = alpha
    var beta = beta
    val moves: List<Move> = board.generateAllMoves(board.color)

    val playerColor = board.color
    val enemyColor = if (playerColor == "RED") "BLACK" else "RED"

    if (depth == 0) return if (maxing) getPlayerScore(board, board.color) else getPlayerScore(board, board.color)


    if (moves.size == 0) //if no moves i.e. no pieces or blocked, then current player loses
        return if (maxing) NEGATIVE_INFINITY else POSITIVE_INFINITY

    return if (maxing) //maximising
    {
        var best: Float = NEGATIVE_INFINITY
        for (move in moves) {

            val newBoard = performMove(move, board, enemyColor)

            val score = getMinimaxScore(newBoard,depth - 1,false, alpha, beta)
            best = Math.max(best, score)
            alpha = Math.max(alpha, score)
            if (beta <= alpha) break
        }
        best
    } else  //minimising
    {
        var best: Float = POSITIVE_INFINITY
        for (move in moves) {

            val newBoard = performMove(move, board, enemyColor)

            val score = getMinimaxScore(newBoard, depth - 1, true, alpha, beta)
            best = Math.min(best, score)
            beta = Math.min(beta, score) //update beta with the minimum value
            if (beta <= alpha) break
        }
        best
    }
}


private fun performMove(move: Move, board: Board, color: String): Board {

    var prevCellArr: MutableList<Cell> = mutableListOf()
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
