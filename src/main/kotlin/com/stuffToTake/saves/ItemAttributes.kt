package com.stuffToTake.saves

import com.stuffToTake.models.Category

data class ItemAttributes(val name: String, val amount: String,
                          val categories: MutableList<Category>, val toTake: Boolean)
