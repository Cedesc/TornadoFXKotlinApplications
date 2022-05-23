package com.stuffToTake.models

// The string representations must not have any spaces at the beginning or at the end!
enum class Category {
    ON_PC {override fun toString(): String = "Upload on PC"},
    NINTENDO_SWITCH {override fun toString(): String = "Nintendo Switch Stuff"},
    CLOTHING {override fun toString(): String = "Clothing"}
}
