package com.stuffToTake.models

// The string representations must not have any spaces at the beginning or at the end!
enum class Category {
    ON_PC {override fun toString(): String = "Upload on PC"},
    GAME_CONSOLES_STUFF {override fun toString(): String = "Game Consoles Stuff"},
    CLOTHING {override fun toString(): String = "Clothing"},
    // PS4 stuff (?) or console stuff (PS4 and Switch together)
    // stuff i have to take in hands (like suitcase and bag)
    // food (?) and beverages (?)
    // hygiene products (?) / bathroom stuff (?) / care products (?)
    // technical stuff
    // uni stuff (?)
}
