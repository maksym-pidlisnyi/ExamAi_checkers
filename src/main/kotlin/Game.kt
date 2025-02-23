data class Game(
    var status: String,
    val whose_turn: String,
    val winner: String,
    val board: Array<Cell>,
    val available_time: Double,
    val last_move: LastMove?,
    var is_started: Boolean,
    val is_finished: Boolean
)
//{"status": "success",
//    "data": {"status": "Not yet started",
//    "whose_turn": "RED",
//    "winner": null,
//    "board": [{"color": "RED", "row": 0, "column": 0, "king": false, "position": 1}, {"color": "RED", "row": 0, "column": 1, "king": false, "position": 2}, {"color": "RED", "row": 0, "column": 2, "king": false, "position": 3}, {"color": "RED", "row": 0, "column": 3, "king": false, "position": 4}, {"color": "RED", "row": 1, "column": 0, "king": false, "position": 5}, {"color": "RED", "row": 1, "column": 1, "king": false, "position": 6}, {"color": "RED", "row": 1, "column": 2, "king": false, "position": 7}, {"color": "RED", "row": 1, "column": 3, "king": false, "position": 8}, {"color": "RED", "row": 2, "column": 0, "king": false, "position": 9}, {"color": "RED", "row": 2, "column": 1, "king": false, "position": 10}, {"color": "RED", "row": 2, "column": 2, "king": false, "position": 11}, {"color": "RED", "row": 2, "column": 3, "king": false, "position": 12}, {"color": "BLACK", "row": 5, "column": 0, "king": false, "position": 21}, {"color": "BLACK", "row": 5, "column": 1, "king": false, "position": 22}, {"color": "BLACK", "row": 5, "column": 2, "king": false, "position": 23}, {"color": "BLACK", "row": 5, "column": 3, "king": false, "position": 24}, {"color": "BLACK", "row": 6, "column": 0, "king": false, "position": 25}, {"color": "BLACK", "row": 6, "column": 1, "king": false, "position": 26}, {"color": "BLACK", "row": 6, "column": 2, "king": false, "position": 27}, {"color": "BLACK", "row": 6, "column": 3, "king": false, "position": 28}, {"color": "BLACK", "row": 7, "column": 0, "king": false, "position": 29}, {"color": "BLACK", "row": 7, "column": 1, "king": false, "position": 30}, {"color": "BLACK", "row": 7, "column": 2, "king": false, "position": 31}, {"color": "BLACK", "row": 7, "column": 3, "king": false, "position": 32}],
//    "available_time": 10.2,
//    "last_move": null,
//    "is_started": false,
//    "is_finished": false}}