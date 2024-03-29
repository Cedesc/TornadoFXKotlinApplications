package com.stuffToTake.saves

import com.stuffToTake.models.*
import javafx.collections.ObservableList
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date

class ItemParser(val filepath: String = "src/main/kotlin/com/stuffToTake/saves/items.txt") {

    /**
     * Returns a list of the saved items.
     */
    fun txtToCode(): MutableList<AbstractItem> {

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
                    extractAttributesFromString(
                        singleItemString[0], singleItemString[1],
                        singleItemString[2], singleItemString[3]
                    )

                // ... creates an item of the respective type, depending on the current list, ...
                val singleItem: AbstractItem = when (index) {
                    0 -> EssentialItem(attributes.name, attributes.amount, attributes.toTake)
                    1 -> OptionalItem(attributes.name, attributes.amount, attributes.toTake)
                    2 -> OneTimeItem(attributes.name, attributes.amount, attributes.toTake)
                    else -> throw Exception("Too many lists of items, there is no 4th category.")
                }
                // ... adds categories to that item and ...
                attributes.categories.forEach {
                    if (!singleItem.addCategory(it))
                        println(
                            "Warning! In the process of parsing the categories from string to code, the program " +
                                    "tried to add a same category a second time!"
                        )
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
        val toTake: Boolean = when (removeBlanks(startsWithAndBeginAfterThat(toTakeLine, "    TAKE:"))) {
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
    fun removeBlanks(inputString: String): String {

        var result: String = inputString

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
    fun codeToTxt(
        essentialItems: MutableList<EssentialItem>, optionalItems: MutableList<OptionalItem>,
        oneTimeItems: MutableList<OneTimeItem>, chosenFilepath: String = filepath
    ) {

        if (!checkFileExists())
            throw Exception("File doesn't exist.")

        val fileWriter = FileWriter(chosenFilepath)

        fileWriter.write(
            "ESSENTIAL ITEMS\n" +
                    "\n" +
                    convertItemsToString(essentialItems) +
                    "\n" +
                    "\n" +
                    "OPTIONAL ITEMS\n" +
                    "\n" +
                    convertItemsToString(optionalItems) +
                    "\n" +
                    "\n" +
                    "ONE TIME ITEMS\n" +
                    "\n" +
                    convertItemsToString(oneTimeItems)
        )

        fileWriter.flush()
        fileWriter.close()

    }

    /**
     * Converts a mutable list of multiple categories to a string of these categories.
     */
    fun categoriesEnumToString(enumCategories: MutableSet<Category>): String {

        var result = ""
        enumCategories.forEach { category ->
            result += "$category ; "
        }
        // return the string without the last 3 characters, because they are " ; "
        return result.dropLast(3)

    }

    /**
     * Converts a full list of items to a String including all these items.
     */
    fun convertItemsToString(items: List<AbstractItem>): String {

        var result = ""

        items.forEach { item ->
            result +=
                "ITEM\n" +
                        "    NAME: ${item.name}\n" +
                        "    AMOUNT: ${item.amount}\n" +
                        "    CATEGORIES: ${categoriesEnumToString(item.categories)}\n" +
                        "    TAKE: ${if (item.toTake) "O" else "X"}\n"
        }

        return result
    }

    /**
     * Creates a txt file at the given path.
     */
    fun createFile(): Boolean {
        try {
            val file = File(filepath)
            return file.createNewFile()
        } catch (e: IOException) {
            throw Exception("An error occurred while creating the txt file.")
        }
    }

    /**
     * Deletes a txt file at the given path.
     */
    fun deleteFile(): Boolean {
        try {
            val file = File(filepath)
            return file.delete()
        } catch (e: IOException) {
            throw Exception("An error occurred while deleting the txt file.")
        }
    }

    /**
     * Checks if the txt file at the given path exists.
     */
    fun checkFileExists(): Boolean {
        try {
            val file = File(filepath)
            return file.exists()
        } catch (e: IOException) {
            throw Exception("An error occurred while deleting the txt file.")
        }
    }

    /**
     * Returns the actual date and time with the year-month-day hour-minutes.
     * In this order the sort order is temporal correct.
     */
    private fun getActualDateAndTime(): String {
        return SimpleDateFormat("yyyy-MM-dd HH-mm").format(Date())
    }

    /**
     * Creates a backup of the txt file with a similar name.
     */
    fun createBackup(inSingleFile: Boolean = false): Boolean {
        if (filepath.endsWith(".txt")) {
            try {
                val currentDate: String = getActualDateAndTime()
                val file = File(filepath)
                // Remove the ".txt" part and add "_Backup.txt" or "Backups/-DateAndTime-.txt"
                val newFilePath: String = when {
                    inSingleFile -> filepath.dropLast(4) + "_Backup.txt"
                    else -> filepath.dropLast(4) + "Backups/" + currentDate + ".txt"
                }
                // Overwrite possibly already existing file.
                file.copyTo(File(newFilePath), overwrite = true)
                return true
            } catch (e: IOException) {
                throw Exception("An error occurred while copying the txt file.")
            }
        }
        return false
    }

    /**
     * Creates history entry.
     */
    fun createHistoryEntry(items: ObservableList<ShowItem>): Boolean {

        // get three copied lists of the item types and change the toTake to true
        val allItems = getItemTypes(items)
        val essItems: MutableList<EssentialItem> = allItems.first
        essItems.forEach { it.toTake = true }
        val optItems: MutableList<OptionalItem> = allItems.second
        optItems.forEach { it.toTake = true }
        val oneItems: MutableList<OneTimeItem> = allItems.third
        oneItems.forEach { it.toTake = true }

        if (filepath.endsWith(".txt")) {
            try {
                val currentDate: String = getActualDateAndTime()
//                val file = File(filepath)
                // Remove the ".txt" part and add "Backups/-DateAndTime-.txt"
                val newFilePath: String = filepath.dropLast(4) + "History/" + currentDate + ".txt"
                // Overwrite possibly already existing file.
//                file.copyTo(File(newFilePath), overwrite = true)
                codeToTxt(essItems, optItems, oneItems, chosenFilepath = newFilePath)
                return true
            } catch (e: IOException) {
                throw Exception("An error occurred while copying the txt file.")
            }
        }
        return false
    }

    /**
     * Get a list of EssentialItems, a list of OptionalItems and a list of OneTimeItems out of a list of ShowItems.
     * The included items aren't the original but a copy.
     */
    private fun getItemTypes(items: ObservableList<ShowItem>): Triple<MutableList<EssentialItem>,
            MutableList<OptionalItem>, MutableList<OneTimeItem>> {

        val essItems = mutableListOf<EssentialItem>()
        val optItems = mutableListOf<OptionalItem>()
        val oneItems = mutableListOf<OneTimeItem>()

        items.forEach { item ->
            when (item.originalItem) {
                is EssentialItem -> essItems.add(item.originalItem.copy())
                is OptionalItem -> optItems.add(item.originalItem.copy())
                is OneTimeItem -> oneItems.add(item.originalItem.copy())
                else -> throw Exception("Not a valid item type.")
            }
        }

        return Triple(essItems, optItems, oneItems)
    }

}
