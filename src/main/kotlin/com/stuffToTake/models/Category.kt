package com.stuffToTake.models

enum class Category {
    ON_PC {override fun toString(): String = "Am PC hochladen"},
    NINTENDO_SWITCH {override fun toString(): String = "Sachen für Nintendo Switch"}
}
