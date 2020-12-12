fun main(args: Array<String>) {
    val bot = CheckersBot()
    bot.getinfo()
    println("Hello World!")
    bot.connect()
    println("Hello World2!")
    bot.move(9,13)
    println("Hello World3!")
}