import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class CheckersBot {

    private lateinit var game: Game
    private val client = OkHttpClient()
    private val gson = Gson()

    // gets game info
    fun getinfo() : Game {
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

    // connect to the game    val json = gson.fromJson(response.body.toString(), JsonObject::class.java).get("data").getAsJsonObject()
    fun connect() {

    }

    // makes move
    fun move(from: Int, to: Int) {

    }
}