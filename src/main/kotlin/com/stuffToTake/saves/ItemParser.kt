package com.stuffToTake.saves

import com.stuffToTake.models.*
import java.io.File


fun printSomething() {
    println("Hallo")
}

/**
 * Returns a list of the saved items.
 */
fun txtToCode(filename: String = "src/main/kotlin/com/stuffToTake/saves/items.txt"): MutableList<AbstractItem> {

    var result: MutableList<AbstractItem> = mutableListOf()

    val text = File(filename).readText()

    val allItems: List<String> = text.split("ESSENTIAL ITEMS", "OPTIONAL ITEMS", "ONE TIME ITEMS")
        .drop(1)


    // print
    var counter: Int = 0
    allItems.forEach {
        println("$counter: $it")
        counter++
    }


    println("\n\n###########################\n\n")


    var essentialItems: List<String> = allItems[0].split("ITEM").drop(1)

    // print
    counter = 0
    essentialItems.forEach {
        println("$counter: $it")
        counter++
    }


    println("\n\n###########################\n\n")



//    // print
//    counter = 0
//    essItem.forEach {
//        println("$counter: $it")
//        counter++
//    }

    essentialItems.forEach { item ->

        val essItemString: List<String> = item.lines().filter { line ->
            line.contains("NAME:") || line.contains("AMOUNT:") || line.contains("CATEGORIES:")
        }

        val attributes: Triple<String, String, MutableList<Category>> =
            extractAttributesFromString(essItemString[0], essItemString[1], essItemString[2])

        val essItem: EssentialItem = EssentialItem(attributes.first, attributes.second, false)
        attributes.third.forEach {
            if (! essItem.addCategory(it))
                println("Warning! In the process of parsing the categories from string to code, the program tried to " +
                        "add a same category a second time!")
        }

        result.add(essItem)

    }

    println("-------------------------------------")

    result.forEach {
        println(it)
    }

    return result
}

/**
 * Returns the attributes in the string as a triple.
 */
fun extractAttributesFromString(nameLine: String, amountLine: String, categoriesLine: String):
        Triple<String, String, MutableList<Category>> {

    // extract name
    val name: String = removeBlanks(startsWithAndBeginAfterThat(nameLine, "    NAME:"))

    // extract amount
    val amount: String = removeBlanks(startsWithAndBeginAfterThat(amountLine, "    AMOUNT:"))

    // extract categories
    val categories: MutableList<Category> =
        categoriesStringToEnum(removeBlanks(startsWithAndBeginAfterThat(categoriesLine, "    CATEGORIES:")))

    return Triple(name, amount, categories)
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
              oneTimeItem: MutableList<OneTimeItem>) {
    TODO("Not yet implemented")
}
