package com.stuffToTake.tests.savesTests

import com.stuffToTake.models.AbstractItem
import com.stuffToTake.models.Category
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

import com.stuffToTake.saves.*

class ItemParserKtTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun printSomething() {
    }

    @Test
    fun txtToCode() {
        val filepath: String = "src/main/kotlin/com/stuffToTake/tests/savesTests/itemsTest.txt"
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
        txtToCode("src/main/kotlin/com/stuffToTake/tests/savesTests/itemsErrorTest.txt")
    }

    @Test
    fun extractAttributesFromString() {
        var expected: ItemAttributes =
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
    }
}
