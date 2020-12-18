import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit

class CheckersBot (val teamName: String) {

    private val TEAM_NAME = this.teamName
    private lateinit var game: Game
    private lateinit var player: Player
    private val client = OkHttpClient.Builder()
        .connectTimeout(15000, TimeUnit.MILLISECONDS)          // TODO maybe change timeout
        .writeTimeout(60000, TimeUnit.MILLISECONDS)            // TODO or set to default
        .readTimeout(60000, TimeUnit.MILLISECONDS)
        .build()
    private val gson = Gson()


    // TODO add try catch for safety

    // gets game info
    private fun getinfo() : Game {
        val request = Request.Builder()
            .url("http://localhost:8081/game")
            .build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            var jsonS = response.body!!.string()
            println(jsonS)
            jsonS = jsonS.replace("{\"status\": \"success\", \"data\": ", "").replace("}}", "}")
            game = gson.fromJson(jsonS, Game::class.java)
            println(game)
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
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            var jsonS = response.body!!.string()
            println(jsonS)
            jsonS = jsonS.replace("{\"status\": \"success\", \"data\": ", "").replace("}}", "}")
            player = gson.fromJson(jsonS, Player::class.java)
            println(player)
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
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            for ((name, value) in response.headers) {
                println("$name: $value")
            }

            val jsonS = response.body!!.string()
            println(jsonS)
        }
    }

    fun startBattle() {
        connect()
        getinfo()
        var board = Board(game.board)

        while (game.is_started && !game.is_finished) {
            getinfo()
            board = Board(game.board)
            if (game.whose_turn == player.color) {
                // TODO() minimax move

                ///////
                var from = 0
                var to = 0
                if (player.color == "RED") {
                    from = 9
                    to = 13
                    move(9, 13)
                    println("Moved from 9 to 13")                   // Just for testing purposes

                } else {
                    Thread.sleep(5000)
                    from = 24
                    to = 28
                    move(24, 28)
                    println("Moved from 24 to 28")
                }
                ///////


            } else
                continue
        }
    }

}