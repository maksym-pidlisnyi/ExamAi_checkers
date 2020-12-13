fun main(args: Array<String>) {
//    val bot = CheckersBot("Поработить человечество")
//    bot.getinfo()
//    println("Hello World!")
//    bot.connect()
//    println("Hello World2!")
//    bot.move(9,13)
//    println("Hello World3!")

    val first =  Thread(CheckersBot("Player1"))
    first.start()
    val second =  Thread(CheckersBot("Player2"))
    second.start()
}