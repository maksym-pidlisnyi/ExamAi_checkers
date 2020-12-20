import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.util.concurrent.TimeUnit

class CheckersBot(private val teamName: String) {

    private val TEAM_NAME = this.teamName
    private lateinit var game: Game
    private lateinit var player: Player
    private val client = OkHttpClient.Builder()
        .connectTimeout(15000, TimeUnit.MILLISECONDS)
        .writeTimeout(60000, TimeUnit.MILLISECONDS)
        .readTimeout(60000, TimeUnit.MILLISECONDS)
        .build()
    private val gson = Gson()

    // gets game info
    private fun getinfo() : Game {
        val request = Request.Builder()
            .url("http://localhost:8081/game")
            .build()
        client.newCall(request).execute().use { response ->
//            if (!response.isSuccessful) throw IOException("Unexpected code $response")
//            for ((name, value) in response.headers) {
//                println("$name: $value")
//            }

            var jsonS = response.body!!.string()
//            println(jsonS)
            jsonS = jsonS.replace("{\"status\": \"success\", \"data\": ", "").replace("}}", "}")
            game = gson.fromJson(jsonS, Game::class.java)
//            println(game)
//            println("Last move from ${game.last_move?.getLastMoveFrom()} to ${game.last_move?.getLastMoveTo()}")
            return game
        }
    }

    // connect to the game
    private fun connect() : Player {
        val body = "test".toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url("http://localhost:8081/game?team_name=$TEAM_NAME")
            .header("Accept", "application/json")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
//            if (!response.isSuccessful) throw IOException("Unexpected code $response")
//            for ((name, value) in response.headers) {
//                println("$name: $value")
//            }

            var jsonS = response.body!!.string()
//            println(jsonS)
            jsonS = jsonS.replace("{\"status\": \"success\", \"data\": ", "").replace("}}", "}")
            player = gson.fromJson(jsonS, Player::class.java)
//            println(player)
            println("Connected to the game - $teamName - ${player.color}")
            return player
        }
    }

    // makes move
    private fun move(from: Int, to: Int) {
        val body = "{\n    \"move\": [$from, $to]\n}".toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        val request = Request.Builder()
            .url("http://localhost:8081/move")
            .header("Accept", "application/json")
            .addHeader("Authorization", "Token ${player.token}")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
//            if (!response.isSuccessful) throw IOException("Unexpected code $response")
//            for ((name, value) in response.headers) {
//                println("$name: $value")
//            }

            val jsonS = response.body!!.string()
            println("\nMaking move from $from to $to")
            println(jsonS)
            println()
        }
    }

    fun startBattle() {
        connect()
        getinfo()
        var board : Board

        while (game.is_started && !game.is_finished) {
            getinfo()
            if (game.whose_turn == player.color) {
                board = Board(game.board, player.color)
                val move : Move?
                if (game.last_move != null && game.last_move?.player == player.color) {
                    var cellToFind: Cell? = null
                    for (cell in board.cells) {
                        if (cell.position == game.last_move?.getLastMoveTo()) {
                            cellToFind = cell
                            break
                        }
                    }
                    move = getMinimaxMove(board, 6, cellToFind)
                } else {
                    move = getMinimaxMove(board, 6, null)
                }
                if (move != null) {
                    move(move.from.position, move.to.position)
                }
            } else
                continue
        }
    }

}