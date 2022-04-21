package com.stuffToTake.tests.savesTests

import com.stuffToTake.models.*
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

import com.stuffToTake.saves.*
import java.io.File

class ItemParserKtTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun mainTestTxtToCodeAndCodeToText() {

        val path = "src/main/kotlin/com/stuffToTake/tests/savesTests/itemsWriteTest.txt"
        val items: Triple<MutableList<EssentialItem>, MutableList<OptionalItem>, MutableList<OneTimeItem>> =
            generateItems()

        codeToTxt(items.first, items.second, items.third, path)
        val actual: MutableList<AbstractItem> = txtToCode(path)

        val itemsInOneList: List<AbstractItem> = items.first + items.second + items.third

        // for every item check if the original item is the same as the item which was converted to txt and back
        for (itemIndex: Int in actual.indices) {
            assertEquals(itemsInOneList[itemIndex].toString(), actual[itemIndex].toString())
        }

    }

    @Test
    fun txtToCode() {
        val filepath = "src/main/kotlin/com/stuffToTake/tests/savesTests/itemsReadTest.txt"
        val expected: MutableList<String> = mutableListOf("Item: EssItem1\n" +
                "    Amount: -\n" +
                "    Categories: [Am PC hochladen, Sachen für Nintendo Switch]\n" +
                "    To Take: true",
                "Item: EssItem2\n" +
                "    Amount: 3\n" +
                "    Categories: []\n" +
                "    To Take: false",
                "Item: EssItem3\n" +
                "    Amount: ne Menge\n" +
                "    Categories: [Am PC hochladen]\n" +
                "    To Take: true",
                "Item: OptItem1\n" +
                "    Amount: -\n" +
                "    Categories: []\n" +
                "    To Take: false",
                "Item: OneItem1\n" +
                "    Amount: 2 dutzend\n" +
                "    Categories: [Sachen für Nintendo Switch, Am PC hochladen]\n" +
                "    To Take: false",
                "Item: OneItem2\n" +
                "    Amount: -\n" +
                "    Categories: [Am PC hochladen]\n" +
                "    To Take: true")
        val actual: MutableList<AbstractItem> = txtToCode(filepath)
        assertEquals(expected.toString(), actual.toString())
    }

    @Test
    fun extractAttributesFromString() {
        var expected =
            ItemAttributes("EssItem1", "",
                           mutableListOf(Category.ON_PC, Category.NINTENDO_SWITCH), true)
        var actual: ItemAttributes =
            extractAttributesFromString(
                "    NAME: EssItem1",
                "    AMOUNT:",
                "    CATEGORIES: Am PC hochladen ; Sachen für Nintendo Switch",
                "    TAKE: O")
        assertEquals(expected, actual)

        expected =
            ItemAttributes("OneItem1", "2 dutzend",
                           mutableListOf(Category.NINTENDO_SWITCH, Category.ON_PC), false)
        actual =
            extractAttributesFromString(
                "    NAME: OneItem1",
                "    AMOUNT: 2 dutzend",
                "    CATEGORIES: Sachen für Nintendo Switch ; Am PC hochladen",
                "    TAKE: X")
        assertEquals(expected, actual)
    }

    @Test
    fun startsWithAndBeginAfterThat() {
        assertEquals("World", startsWithAndBeginAfterThat("HelloWorld", "Hello"))
        assertEquals("yHey", startsWithAndBeginAfterThat("HeyHeyHey", "HeyHe"))
        assertThrows(Exception::class.java) {
            startsWithAndBeginAfterThat("not a", "substring")
        }
    }

    @Test
    fun categoriesStringToEnum() {
        assertEquals(mutableListOf<Category>(), categoriesStringToEnum(""))
        assertEquals(mutableListOf(Category.ON_PC),
            categoriesStringToEnum("Am PC hochladen"))
        assertEquals(mutableListOf(Category.ON_PC, Category.NINTENDO_SWITCH),
            categoriesStringToEnum("Am PC hochladen ; Sachen für Nintendo Switch"))
        assertEquals(mutableListOf<Category>(),
            categoriesStringToEnum("something ; that fits in ; no category"))
    }

    @Test
    fun removeBlanks() {
        assertEquals("test", removeBlanks("test"))
        assertEquals("test", removeBlanks("test "))
        assertEquals("test", removeBlanks(" test"))
        assertEquals("test", removeBlanks(" test "))
        assertEquals("test", removeBlanks("   test   "))
        assertEquals("Hello   World", removeBlanks(" Hello   World    "))
        assertEquals("", removeBlanks("     "))
    }

    @Test
    fun codeToTxt() {

        assertThrows(Exception::class.java) {
            // check if it throws an error if the file doesn't exist
            codeToTxt(mutableListOf(), mutableListOf(), mutableListOf(),
                "src/main/kotlin/com/stuffToTake/tests/savesTests/itemsErrorTest.txt")
        }


        val expected: String =
            "ESSENTIAL ITEMS\n" +
            "\n" +
            "ITEM\n" +
            "    NAME: EssItem1\n" +
            "    AMOUNT: \n" +
            "    CATEGORIES: Am PC hochladen ; Sachen für Nintendo Switch\n" +
            "    TAKE: O\n" +
            "ITEM\n" +
            "    NAME: EssItem2\n" +
            "    AMOUNT: 3\n" +
            "    CATEGORIES: \n" +
            "    TAKE: X\n" +
            "ITEM\n" +
            "    NAME: EssItem3\n" +
            "    AMOUNT: ne Menge\n" +
            "    CATEGORIES: Am PC hochladen\n" +
            "    TAKE: O\n" +
            "\n" +
            "\n" +
            "OPTIONAL ITEMS\n" +
            "\n" +
            "ITEM\n" +
            "    NAME: OptItem1\n" +
            "    AMOUNT: \n" +
            "    CATEGORIES: \n" +
            "    TAKE: X\n" +
            "\n" +
            "\n" +
            "ONE TIME ITEMS\n" +
            "\n" +
            "ITEM\n" +
            "    NAME: OneItem1\n" +
            "    AMOUNT: 2 dutzend\n" +
            "    CATEGORIES: Sachen für Nintendo Switch ; Am PC hochladen\n" +
            "    TAKE: X\n" +
            "ITEM\n" +
            "    NAME: OneItem2\n" +
            "    AMOUNT: \n" +
            "    CATEGORIES: Am PC hochladen\n" +
            "    TAKE: O\n"

        val path = "src/main/kotlin/com/stuffToTake/tests/savesTests/itemsWriteTest.txt"
        val items: Triple<MutableList<EssentialItem>, MutableList<OptionalItem>, MutableList<OneTimeItem>> =
            generateItems()
        codeToTxt(items.first, items.second, items.third, path)
        val actual = File(path).readText()

        assertEquals(expected, actual)

    }

    @Test
    fun categoriesEnumToString() {
        assertEquals("Am PC hochladen ; Sachen für Nintendo Switch",
            categoriesEnumToString(mutableSetOf(Category.ON_PC, Category.NINTENDO_SWITCH)))
        assertEquals("Sachen für Nintendo Switch ; Am PC hochladen",
            categoriesEnumToString(mutableSetOf(Category.NINTENDO_SWITCH, Category.ON_PC)))
        assertEquals("Am PC hochladen",
            categoriesEnumToString(mutableSetOf(Category.ON_PC)))
        assertEquals("Sachen für Nintendo Switch",
            categoriesEnumToString(mutableSetOf(Category.NINTENDO_SWITCH)))
    }

    @Test
    fun convertItemsToString() {

        val items: Triple<MutableList<EssentialItem>, MutableList<OptionalItem>, MutableList<OneTimeItem>> =
            generateItems()


        var expected = ""
        var actual: String = convertItemsToString(listOf())

        assertEquals(expected, actual)


        expected =
            "ITEM\n" +
            "    NAME: EssItem1\n" +
            "    AMOUNT: \n" +
            "    CATEGORIES: Am PC hochladen ; Sachen für Nintendo Switch\n" +
            "    TAKE: O\n" +
            "ITEM\n" +
            "    NAME: EssItem2\n" +
            "    AMOUNT: 3\n" +
            "    CATEGORIES: \n" +
            "    TAKE: X\n" +
            "ITEM\n" +
            "    NAME: EssItem3\n" +
            "    AMOUNT: ne Menge\n" +
            "    CATEGORIES: Am PC hochladen\n" +
            "    TAKE: O\n"
        actual =
            convertItemsToString(items.first)

        assertEquals(expected, actual)


        expected = "ITEM\n" +
                "    NAME: OptItem1\n" +
                "    AMOUNT: \n" +
                "    CATEGORIES: \n" +
                "    TAKE: X\n"
        actual =
            convertItemsToString(items.second)

        assertEquals(expected, actual)


        expected =
            "ITEM\n" +
            "    NAME: OneItem1\n" +
            "    AMOUNT: 2 dutzend\n" +
            "    CATEGORIES: Sachen für Nintendo Switch ; Am PC hochladen\n" +
            "    TAKE: X\n" +
            "ITEM\n" +
            "    NAME: OneItem2\n" +
            "    AMOUNT: \n" +
            "    CATEGORIES: Am PC hochladen\n" +
            "    TAKE: O\n"
        actual =
            convertItemsToString(items.third)

        assertEquals(expected, actual)
    }

    @Test
    fun fileOperations() {
        // tests createFile(), deleteFile() and checkFileExists()

        val path = "src/main/kotlin/com/stuffToTake/tests/savesTests/itemsCreateTest.txt"

        assertEquals(false,
            checkFileExists(path))

        assertEquals(true,
            createFile(path))

        assertEquals(true,
            checkFileExists(path))

        assertEquals(true,
            deleteFile(path))
    }


    private fun generateItems(): Triple<MutableList<EssentialItem>, MutableList<OptionalItem>, MutableList<OneTimeItem>> {

        val essItem1 = EssentialItem("EssItem1", "", true)
        essItem1.addCategory(Category.ON_PC)
        essItem1.addCategory(Category.NINTENDO_SWITCH)
        val essItem2 = EssentialItem("EssItem2", 3, false)
        val essItem3 = EssentialItem("EssItem3", "ne Menge", true)
        essItem3.addCategory(Category.ON_PC)

        val optItem1 = OptionalItem("OptItem1", "", false)

        val oneItem1 = OneTimeItem("OneItem1", "2 dutzend", false)
        oneItem1.addCategory(Category.NINTENDO_SWITCH)
        oneItem1.addCategory(Category.ON_PC)
        val oneItem2 = OneTimeItem("OneItem2", "", true)
        oneItem2.addCategory(Category.ON_PC)

        return Triple(
            mutableListOf(essItem1, essItem2, essItem3),
            mutableListOf(optItem1),
            mutableListOf(oneItem1, oneItem2)
        )
    }
}
