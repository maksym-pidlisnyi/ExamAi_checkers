fun main(args: Array<String>) {
//    val bot = CheckersBot("Поработить человечество")
//    bot.startBattle()
    /*
    val cells = arrayOf(
        Cell(color="NONE", row=0, column=0, king=false, position=1),
        Cell(color="NONE", row=0, column=1, king=false, position=2),
        Cell(color="NONE", row=0, column=2, king=false, position=3),
        Cell(color="NONE", row=0, column=3, king=false, position=4),
        Cell(color="NONE", row=1, column=0, king=false, position=5),
        Cell(color="NONE", row=1, column=1, king=false, position=6),
        Cell(color="BLACK", row=1, column=2, king=false, position=7),
        Cell(color="RED", row=1, column=3, king=false, position=8),
        Cell(color="RED", row=2, column=0, king=false, position=9),
        Cell(color="RED", row=2, column=1, king=false, position=10),
        Cell(color="RED", row=2, column=2, king=true, position=11),
        Cell(color="NONE", row=2, column=3, king=false, position=12),

        Cell(color="NONE", row=3, column=0, king=false, position=13),
        Cell(color="BLACK", row=3, column=1, king=false, position=14),
        Cell(color="BLACK", row=3, column=2, king=false, position=15),
        Cell(color="NONE", row=3, column=3, king=false, position=16),
        Cell(color="NONE", row=4, column=0, king=false, position=17),
        Cell(color="NONE", row=4, column=1, king=false, position=18),
        Cell(color="NONE", row=4, column=2, king=false, position=19),
        Cell(color="NONE", row=4, column=3, king=false, position=20),

        Cell(color="BLACK", row=5, column=0, king=false, position=21),
        Cell(color="NONE", row=5, column=1, king=false, position=22),
        Cell(color="NONE", row=5, column=2, king=false, position=23),
        Cell(color="BLACK", row=5, column=3, king=false, position=24),
        Cell(color="BLACK", row=6, column=0, king=false, position=25),
        Cell(color="BLACK", row=6, column=1, king=false, position=26),
        Cell(color="BLACK", row=6, column=2, king=false, position=27),
        Cell(color="BLACK", row=6, column=3, king=false, position=28),
        Cell(color="BLACK", row=7, column=0, king=false, position=29),
        Cell(color="BLACK", row=7, column=1, king=false, position=30),
        Cell(color="BLACK", row=7, column=2, king=false, position=31),
        Cell(color="BLACK", row=7, column=3, king=false, position=32),
    )

    val board = Board(cells)
    val moves = board.generateAllMoves("RED")
    for (i in moves.indices) {
        println(moves[i])
    }
*/

    val firstBot = Thread(CheckersBot("Player1"))
    val secondBot = Thread(CheckersBot("Player2"))
    firstBot.start()
    secondBot.start()
}