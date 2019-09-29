data class Fragment(val starting: Position, val following: Position) {

    override fun toString(): String = "$starting - $following"
}