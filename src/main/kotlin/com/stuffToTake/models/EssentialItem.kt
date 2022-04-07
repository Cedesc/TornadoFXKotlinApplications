package com.stuffToTake.models

class EssentialItem(name: String, amount: String, to_take: Boolean)
    : AbstractItem(name, amount, to_take) {

    override fun markAsToTake() {
        super.markAsToTake()
        TODO("Not yet implemented")
    }

    override fun unmarkAsToTake() {
        super.unmarkAsToTake()
        TODO("Not yet implemented")
    }

}
