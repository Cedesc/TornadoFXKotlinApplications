package com.stuffToTake.models

// The string representations must not have any spaces at the beginning or at the end!
enum class Category {
    ON_PC {override fun toString(): String = "Am PC hochladen"},
    NINTENDO_SWITCH {override fun toString(): String = "Sachen für Nintendo Switch"}
}
