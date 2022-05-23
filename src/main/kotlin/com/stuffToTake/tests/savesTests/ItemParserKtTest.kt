package com.stuffToTake.tests.savesTests

import com.stuffToTake.models.*
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

import com.stuffToTake.saves.*
import com.stuffToTake.tests.TestUtilities
import java.io.File

class ItemParserKtTest {

    private val testUtils = TestUtilities()

    private lateinit var itemParserEmpty: ItemParser
    private lateinit var itemParserWrite: ItemParser
    private lateinit var itemParserRead: ItemParser
    private lateinit var itemParserError: ItemParser
    private lateinit var itemParserCreate: ItemParser
    private lateinit var itemParserBackup: ItemParser

    @Before
    fun setUp() {
        itemParserEmpty = ItemParser("")
        itemParserWrite = ItemParser("src/main/kotlin/com/stuffToTake/tests/savesTests/itemsWriteTest.txt")
        itemParserRead = ItemParser("src/main/kotlin/com/stuffToTake/tests/savesTests/itemsReadTest.txt")
        itemParserError = ItemParser("src/main/kotlin/com/stuffToTake/tests/savesTests/itemsErrorTest.txt")
        itemParserCreate = ItemParser("src/main/kotlin/com/stuffToTake/tests/savesTests/itemsCreateTest.txt")
        itemParserBackup = ItemParser("src/main/kotlin/com/stuffToTake/tests/savesTests/itemsBackupTest.txt")
    }

    @After
    fun tearDown() {
    }

    @Test
    fun mainTestTxtToCodeAndCodeToText() {

        val items: Triple<MutableList<EssentialItem>, MutableList<OptionalItem>, MutableList<OneTimeItem>> =
            testUtils.generateItems()

        itemParserWrite.codeToTxt(items.first, items.second, items.third)
        val actual: MutableList<AbstractItem> = itemParserWrite.txtToCode()

        val itemsInOneList: List<AbstractItem> = items.first + items.second + items.third

        // for every item check if the original item is the same as the item which was converted to txt and back
        for (itemIndex: Int in actual.indices) {
            assertEquals(itemsInOneList[itemIndex].toString(), actual[itemIndex].toString())
        }

    }

    @Test
    fun txtToCode() {
        val expected: MutableList<String> = mutableListOf("Essential Item: EssItem1\n" +
                "    Amount: -\n" +
                "    Categories: [Upload on PC, Game Consoles Stuff]\n" +
                "    To Take: true",
                "Essential Item: EssItem2\n" +
                "    Amount: 3\n" +
                "    Categories: []\n" +
                "    To Take: false",
                "Essential Item: EssItem3\n" +
                "    Amount: ne Menge\n" +
                "    Categories: [Upload on PC]\n" +
                "    To Take: true",
                "Optional Item: OptItem1\n" +
                "    Amount: -\n" +
                "    Categories: []\n" +
                "    To Take: false",
                "One Time Item: OneItem1\n" +
                "    Amount: 2 dutzend\n" +
                "    Categories: [Game Consoles Stuff, Upload on PC]\n" +
                "    To Take: false",
                "One Time Item: OneItem2\n" +
                "    Amount: -\n" +
                "    Categories: [Upload on PC]\n" +
                "    To Take: true")
        val actual: MutableList<AbstractItem> = itemParserRead.txtToCode()
        assertEquals(expected.toString(), actual.toString())
    }

    @Test
    fun extractAttributesFromString() {
        var expected =
            ItemAttributes("EssItem1", "",
                           mutableListOf(Category.ON_PC, Category.GAME_CONSOLES_STUFF), true)
        var actual: ItemAttributes =
            itemParserEmpty.extractAttributesFromString(
                "    NAME: EssItem1",
                "    AMOUNT:",
                "    CATEGORIES: Upload on PC ; Game Consoles Stuff",
                "    TAKE: O")
        assertEquals(expected, actual)

        expected =
            ItemAttributes("OneItem1", "2 dutzend",
                           mutableListOf(Category.GAME_CONSOLES_STUFF, Category.ON_PC), false)
        actual =
            itemParserEmpty.extractAttributesFromString(
                "    NAME: OneItem1",
                "    AMOUNT: 2 dutzend",
                "    CATEGORIES: Game Consoles Stuff ; Upload on PC",
                "    TAKE: X")
        assertEquals(expected, actual)
    }

    @Test
    fun startsWithAndBeginAfterThat() {
        assertEquals("World", itemParserEmpty.startsWithAndBeginAfterThat("HelloWorld", "Hello"))
        assertEquals("yHey", itemParserEmpty.startsWithAndBeginAfterThat("HeyHeyHey", "HeyHe"))
        assertThrows(Exception::class.java) {
            itemParserEmpty.startsWithAndBeginAfterThat("not a", "substring")
        }
    }

    @Test
    fun categoriesStringToEnum() {
        assertEquals(mutableListOf<Category>(), itemParserEmpty.categoriesStringToEnum(""))
        assertEquals(mutableListOf(Category.ON_PC),
            itemParserEmpty.categoriesStringToEnum("Upload on PC"))
        assertEquals(mutableListOf(Category.ON_PC, Category.GAME_CONSOLES_STUFF),
            itemParserEmpty.categoriesStringToEnum("Upload on PC ; Game Consoles Stuff"))
        assertEquals(mutableListOf<Category>(),
            itemParserEmpty.categoriesStringToEnum("something ; that fits in ; no category"))
    }

    @Test
    fun removeBlanks() {
        assertEquals("test", itemParserEmpty.removeBlanks("test"))
        assertEquals("test", itemParserEmpty.removeBlanks("test "))
        assertEquals("test", itemParserEmpty.removeBlanks(" test"))
        assertEquals("test", itemParserEmpty.removeBlanks(" test "))
        assertEquals("test", itemParserEmpty.removeBlanks("   test   "))
        assertEquals("Hello   World", itemParserEmpty.removeBlanks(" Hello   World    "))
        assertEquals("", itemParserEmpty.removeBlanks("     "))
    }

    @Test
    fun codeToTxt() {

        assertThrows(Exception::class.java) {
            // check if it throws an error if the file doesn't exist
            itemParserError.codeToTxt(mutableListOf(), mutableListOf(), mutableListOf())
        }


        val expected: String = testUtils.getStringOfGeneratedItems()

        val items: Triple<MutableList<EssentialItem>, MutableList<OptionalItem>, MutableList<OneTimeItem>> =
            testUtils.generateItems()
        itemParserWrite.codeToTxt(items.first, items.second, items.third)
        val actual = File(itemParserWrite.filepath).readText()

        assertEquals(expected, actual)

    }

    @Test
    fun categoriesEnumToString() {
        assertEquals("Upload on PC ; Game Consoles Stuff",
            itemParserEmpty.categoriesEnumToString(mutableSetOf(Category.ON_PC, Category.GAME_CONSOLES_STUFF)))
        assertEquals("Game Consoles Stuff ; Upload on PC",
            itemParserEmpty.categoriesEnumToString(mutableSetOf(Category.GAME_CONSOLES_STUFF, Category.ON_PC)))
        assertEquals("Upload on PC",
            itemParserEmpty.categoriesEnumToString(mutableSetOf(Category.ON_PC)))
        assertEquals("Game Consoles Stuff",
            itemParserEmpty.categoriesEnumToString(mutableSetOf(Category.GAME_CONSOLES_STUFF)))
    }

    @Test
    fun convertItemsToString() {

        val items: Triple<MutableList<EssentialItem>, MutableList<OptionalItem>, MutableList<OneTimeItem>> =
            testUtils.generateItems()


        var expected = ""
        var actual: String = itemParserEmpty.convertItemsToString(listOf())

        assertEquals(expected, actual)


        expected =
            "ITEM\n" +
            "    NAME: EssItem1\n" +
            "    AMOUNT: \n" +
            "    CATEGORIES: Upload on PC ; Game Consoles Stuff\n" +
            "    TAKE: O\n" +
            "ITEM\n" +
            "    NAME: EssItem2\n" +
            "    AMOUNT: 3\n" +
            "    CATEGORIES: \n" +
            "    TAKE: X\n" +
            "ITEM\n" +
            "    NAME: EssItem3\n" +
            "    AMOUNT: ne Menge\n" +
            "    CATEGORIES: Upload on PC\n" +
            "    TAKE: O\n"
        actual =
            itemParserEmpty.convertItemsToString(items.first)

        assertEquals(expected, actual)


        expected = "ITEM\n" +
                "    NAME: OptItem1\n" +
                "    AMOUNT: \n" +
                "    CATEGORIES: \n" +
                "    TAKE: X\n"
        actual =
            itemParserEmpty.convertItemsToString(items.second)

        assertEquals(expected, actual)


        expected =
            "ITEM\n" +
            "    NAME: OneItem1\n" +
            "    AMOUNT: 2 dutzend\n" +
            "    CATEGORIES: Game Consoles Stuff ; Upload on PC\n" +
            "    TAKE: X\n" +
            "ITEM\n" +
            "    NAME: OneItem2\n" +
            "    AMOUNT: \n" +
            "    CATEGORIES: Upload on PC\n" +
            "    TAKE: O\n"
        actual =
            itemParserEmpty.convertItemsToString(items.third)

        assertEquals(expected, actual)
    }

    @Test
    fun fileOperations() {
        // tests createFile(), deleteFile() and checkFileExists()

        assertEquals(false,
            itemParserCreate.checkFileExists())

        assertEquals(true,
            itemParserCreate.createFile())

        assertEquals(true,
            itemParserCreate.checkFileExists())

        assertEquals(true,
            itemParserCreate.deleteFile())
    }

    @Test
    fun createBackup() {
        assertEquals(false,
            itemParserBackup.checkFileExists())
        assertEquals(true,
            itemParserBackup.createFile())

        val items = testUtils.generateItems()
        itemParserBackup.codeToTxt(items.first, items.second, items.third)

        assertEquals(true,
            itemParserBackup.createBackup(inSingleFile = true))

        // Check if the Backup is complete.
        val backupParser =
            ItemParser("src/main/kotlin/com/stuffToTake/tests/savesTests/itemsBackupTest_Backup.txt")
        assertEquals(itemParserBackup.txtToCode().toString(),
            backupParser.txtToCode().toString())

        // Check if createBackup throws an error on trying to overwrite the file.
        val someOptionalItem = OptionalItem("thing", 2, false)
        itemParserBackup.codeToTxt(
            mutableListOf(),
            mutableListOf(someOptionalItem),
            mutableListOf())
        assertThrows(Exception::class.java) {
            itemParserBackup.createBackup(inSingleFile = true)
        }

        assertEquals(true,
            itemParserBackup.deleteFile())
        assertEquals(true,
            backupParser.deleteFile())
        assertEquals(false,
            itemParserBackup.checkFileExists())
        assertEquals(false,
            backupParser.checkFileExists())
    }

}
