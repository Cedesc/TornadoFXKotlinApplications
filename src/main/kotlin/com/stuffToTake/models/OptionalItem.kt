package com.stuffToTake.models

class OptionalItem(name: String, amount: String, to_take: Boolean)
    : AbstractItem(name, amount, to_take) {

    // optionally the amount can be given as an Integer instead of a String
    constructor(name: String, amount: Int, to_take: Boolean) : this(name, amount.toString(), to_take)

    override fun markAsToTake() {
        super.markAsToTake()
        TODO("Not yet implemented")
    }

    override fun unmarkAsToTake() {
        super.unmarkAsToTake()
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Optional ${super.toString()}"
    }

}
