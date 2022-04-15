package com.stuffToTake.tests.saves

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
