package com.stuffToTake.saves

import com.stuffToTake.models.*
import java.io.File
import java.io.FileWriter
import java.io.IOException


/**
 * Returns a list of the saved items.
 */
fun txtToCode(filepath: String = "src/main/kotlin/com/stuffToTake/saves/items.txt"): MutableList<AbstractItem> {

    val result: MutableList<AbstractItem> = mutableListOf()

    val text = File(filepath).readText()

    // Creates a three element long list in which each element is the full string of the essential items, the optional
    // items and the one time items, respectively.
    val allItems: List<String> = text.split("ESSENTIAL ITEMS", "OPTIONAL ITEMS", "ONE TIME ITEMS")
        .drop(1)


    // For each of the three strings with the respective element type, adds the items to the result.
    // The index is also needed here, because with the singleItem the type must be different depending on the list.
    for ((index: Int, itemsString: String) in allItems.withIndex()) {

        // Splits the respective list in a list of strings of a single item.
        val itemsList: List<String> = itemsString.split("ITEM").drop(1)

        // For each string of an item...
        itemsList.forEach { item ->

            // ... splits the string into lines and delete empty / redundant lines, ...
            val singleItemString: List<String> = item.lines().filter { line ->
                line.contains("NAME:") || line.contains("AMOUNT:") ||
                        line.contains("CATEGORIES:") || line.contains("TAKE:")
            }

            // ... extracts the respective attributes of the item, ...
            val attributes: ItemAttributes =
                extractAttributesFromString(singleItemString[0], singleItemString[1],
                    singleItemString[2], singleItemString[3])

            // ... creates an item of the respective type, depending on the current list, ...
            val singleItem: AbstractItem = when(index) {
                0 -> EssentialItem(attributes.name, attributes.amount, attributes.toTake)
                1 -> OptionalItem(attributes.name, attributes.amount, attributes.toTake)
                2 -> OneTimeItem(attributes.name, attributes.amount, attributes.toTake)
                else -> throw Exception("Too many lists of items, there is no 4th category.")
            }
            // ... adds categories to that item and ...
            attributes.categories.forEach {
                if (! singleItem.addCategory(it))
                    println("Warning! In the process of parsing the categories from string to code, the program " +
                            "tried to add a same category a second time!")
            }

            // ... adds the item to the result.
            result.add(singleItem)

        }

    }

    return result
}

/**
 * Returns the attributes in the string as a triple.
 */
fun extractAttributesFromString(nameLine: String, amountLine: String, categoriesLine: String, toTakeLine: String):
        ItemAttributes {

    // extract name
    val name: String = removeBlanks(startsWithAndBeginAfterThat(nameLine, "    NAME:"))

    // extract amount
    val amount: String = removeBlanks(startsWithAndBeginAfterThat(amountLine, "    AMOUNT:"))

    // extract categories
    val categories: MutableList<Category> =
        categoriesStringToEnum(removeBlanks(startsWithAndBeginAfterThat(categoriesLine, "    CATEGORIES:")))

    // extract toTake
    val toTake: Boolean = when(removeBlanks(startsWithAndBeginAfterThat(toTakeLine, "    TAKE:"))) {
        "O" -> true
        "X" -> false
        else -> {
            throw Exception("\"TAKE\" in the text file is neither \"O\" nor \"X\"")
        }
    }


    return ItemAttributes(name, amount, categories, toTake)
}

/**
 * If the string starts with the input, the following part of the string is returned.
 */
fun startsWithAndBeginAfterThat(completeString: String, subString: String): String {
    if (!completeString.startsWith(subString))
        throw Exception("The input string doesn't start with the given substring")
    else
        return completeString.drop(subString.length)
}

/**
 * Converts a string of multiple categories to a mutable list of these categories.
 */
fun categoriesStringToEnum(stringCategories: String): MutableList<Category> {

    // Caution!
    // 1. If a String isn't the representation of a category, it will be ignored.
    // 2. Theoretically it's possible that multiple Categories are added because of one String.

    val result: MutableList<Category> = mutableListOf()

    val stringCategoriesList: List<String> = stringCategories.split(" ; ")

    stringCategoriesList.forEach { stringCategory ->
        Category.values().forEach { category ->
            if (stringCategory == category.toString())
                result.add(category)
        }
    }

    return result
}

/**
 * Returns the input string without blanks at the start or the end.
 */
fun removeBlanks(string: String): String {

    var result: String = string

    // drop the first char while the first char is a blank space
    result = result.dropWhile {
        it == ' '
    }

    // drop the last char while the last char is a blank space
    result = result.dropLastWhile {
        it == ' '
    }

    return result
}



/**
 * Saves the input items in a txt file.
 */
fun codeToTxt(essentialItems: MutableList<EssentialItem>, optionalItems: MutableList<OptionalItem>,
              oneTimeItem: MutableList<OneTimeItem>,
              filepath: String = "src/main/kotlin/com/stuffToTake/saves/items.txt"): Boolean {

    val fileWriter: FileWriter = FileWriter(filepath)


    return false
}

/**
 * Creates a txt file at the given path.
 */
fun createFile(filepath: String): Boolean {
    try {
        val file: File = File(filepath)
        return file.createNewFile()
    } catch (e: IOException) {
        throw Exception("An error occurred while creating the txt file.")
    }
}

/**
 * Deletes a txt file at the given path.
 */
fun deleteFile(filepath: String): Boolean {
    try {
        val file: File = File(filepath)
        return file.delete()
    } catch (e: IOException) {
        throw Exception("An error occurred while deleting the txt file.")
    }
}

/**
 * Checks if the txt file at the given path exists.
 */
fun checkFileExists(filepath: String): Boolean {
    try {
        val file: File = File(filepath)
        return file.exists()
    } catch (e: IOException) {
        throw Exception("An error occurred while deleting the txt file.")
    }
}
