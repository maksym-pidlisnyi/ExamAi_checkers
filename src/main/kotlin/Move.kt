data class Move(val from: Cell, val to: Cell) {


    var cellToAttack: Cell? = null

    constructor(from: Cell, to: Cell, cellToAttack: Cell) : this(from, to) {
        this.cellToAttack = cellToAttack
    }


}
