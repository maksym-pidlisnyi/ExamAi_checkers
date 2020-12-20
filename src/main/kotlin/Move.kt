data class Move(val from: Cell, val to: Cell) {

    constructor(from: Cell, to: Cell, cellToAttack: Cell) : this(from, to)

    var cellToAttack: Cell? = null

}
