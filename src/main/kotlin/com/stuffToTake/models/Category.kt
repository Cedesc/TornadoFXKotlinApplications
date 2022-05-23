package com.stuffToTake.models

// The string representations must not have any spaces at the beginning or at the end!
enum class Category {
    TO_CARRY {override fun toString(): String = "To Carry"},
    ON_PC {override fun toString(): String = "Upload on PC"},
    GAME_CONSOLES_STUFF {override fun toString(): String = "Game Consoles Stuff"},
    CLOTHING {override fun toString(): String = "Clothing"},
    FOOD_AND_BEVERAGES {override fun toString(): String = "Food and Beverages"},
    CARE_PRODUCTS {override fun toString(): String = "Care Products"},
    TECHNICAL_STUFF {override fun toString(): String = "Technical Stuff"}
}
