package com.stuffToTake.tests.modelsTests

import com.stuffToTake.models.*
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class AbstractItemTest {

    private lateinit var essentialItem: EssentialItem
    private lateinit var optionalItem: OptionalItem
    private lateinit var oneTimeItem: OneTimeItem

    @Before
    fun setUp() {
        essentialItem = EssentialItem("suitcase", 1, true)
        optionalItem = OptionalItem("sunglasses", "", false)
        oneTimeItem = OneTimeItem("B12", "two packs", true)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun constructors() {
        val item1 = EssentialItem("Item 1", 55, false)
        val item2 = OptionalItem("Item 2", 2, true, setOf(Category.ON_PC, Category.CLOTHING))
        val item3 = OneTimeItem("Item 3", "two", false,
            setOf(Category.CLOTHING, Category.CLOTHING))
        val item4 = ShowItem(item2)

        assertEquals("Essential Item: Item 1\n" +
                "    Amount: 55\n" +
                "    Categories: []\n" +
                "    To Take: false",
            item1.toString())
        assertEquals("Optional Item: Item 2\n" +
                "    Amount: 2\n" +
                "    Categories: [Upload on PC, Clothing]\n" +
                "    To Take: true",
            item2.toString())
        assertEquals("One Time Item: Item 3\n" +
                "    Amount: two\n" +
                "    Categories: [Clothing]\n" +
                "    To Take: false",
            item3.toString())
        assertEquals("Showed Optional Item: Item 2\n" +
                "    Amount: 2\n" +
                "    Categories: [Upload on PC, Clothing]\n" +
                "    To Take: true",
            item4.toString())

    }

    @Test
    fun hasAmount() {
        assertEquals(true, essentialItem.hasAmount())
        assertEquals(false, optionalItem.hasAmount())
        assertEquals(true, oneTimeItem.hasAmount())
    }

    @Test
    fun addCategory() {

        // add categories
        assert(essentialItem.addCategory(Category.ON_PC))

        // check if categories are right
        assertEquals(1, essentialItem.categories.size)
        assert(essentialItem.categories.contains(Category.ON_PC))


        // add categories
        assert(optionalItem.addCategory(Category.ON_PC))
        assert(optionalItem.addCategory(Category.GAME_CONSOLES_STUFF))

        // check if categories are right
        assertEquals(2, optionalItem.categories.size)
        assert(optionalItem.categories.contains(Category.ON_PC))
        assert(optionalItem.categories.contains(Category.GAME_CONSOLES_STUFF))


        // add categories
        assert(oneTimeItem.addCategory(Category.ON_PC))
        assert(! oneTimeItem.addCategory(Category.ON_PC))
        assert(oneTimeItem.addCategory(Category.GAME_CONSOLES_STUFF))

        // check if categories are right
        assertEquals(2, oneTimeItem.categories.size)
        assert(oneTimeItem.categories.contains(Category.ON_PC))
        assert(oneTimeItem.categories.contains(Category.GAME_CONSOLES_STUFF))

    }

    @Test
    fun testToString() {
        assertEquals("Essential Item: suitcase\n" +
                "    Amount: 1\n" + //To Take: true
                "    Categories: []\n" +
                "    To Take: true",
            essentialItem.toString())
        assertEquals("Optional Item: sunglasses\n" +
                "    Amount: -\n" +
                "    Categories: []\n" +
                "    To Take: false",
            optionalItem.toString())
        assertEquals("One Time Item: B12\n" +
                "    Amount: two packs\n" +
                "    Categories: []\n" +
                "    To Take: true",
            oneTimeItem.toString())
    }

    @Test
    fun getName() {
        assertEquals("suitcase", essentialItem.name)
        assertEquals("sunglasses", optionalItem.name)
        assertEquals("B12", oneTimeItem.name)
    }

    @Test
    fun setName() {
        essentialItem.name = "not a suitcase"
        assertEquals("not a suitcase", essentialItem.name)

        optionalItem.name = "glasses"
        assertEquals("glasses", optionalItem.name)

        oneTimeItem.name = "Iron"
        assertEquals("Iron", oneTimeItem.name)
    }

    @Test
    fun getAmount() {
        assertEquals("1", essentialItem.amount)
        assertEquals("", optionalItem.amount)
        assertEquals("two packs", oneTimeItem.amount)
    }

    @Test
    fun setAmount() {
        essentialItem.amount = "2"
        assertEquals("2", essentialItem.amount)

        optionalItem.amount = "5"
        assertEquals("5", optionalItem.amount)

        oneTimeItem.amount = "three packs"
        assertEquals("three packs", oneTimeItem.amount)
    }

    @Test
    fun getToTake() {
        assertEquals(true, essentialItem.toTake)
        assertEquals(false, optionalItem.toTake)
        assertEquals(true, oneTimeItem.toTake)
    }

    @Test
    fun setToTake() {
        essentialItem.toTake = false
        assertEquals(false, essentialItem.toTake)

        optionalItem.toTake = true
        assertEquals(true, optionalItem.toTake)

        oneTimeItem.toTake = true
        assertEquals(true, oneTimeItem.toTake)
    }

    @Test
    fun getCategories() {
        assert(essentialItem.categories.isEmpty())
        assert(optionalItem.categories.isEmpty())
        assert(oneTimeItem.categories.isEmpty())
    }

    @Test
    fun testEquals() {
        // Compare same items.
        assertEquals(essentialItem, essentialItem)
        assertEquals(optionalItem, optionalItem)
        assertEquals(oneTimeItem, oneTimeItem)

        // Generates new identical items.
        val essentialItem2 = EssentialItem("suitcase", 1, true)
        val optionalItem2 = OptionalItem("sunglasses", "", false)
        val onetimeItem2 = OneTimeItem("B12", "two packs", true)

        // Compare identical items.
        assertEquals(essentialItem, essentialItem2)
        assertEquals(optionalItem, optionalItem2)
        assertEquals(oneTimeItem, onetimeItem2)

        // Compare Show Item to his original item.
        assertNotEquals(essentialItem, ShowItem(essentialItem))
        assertNotEquals(optionalItem, ShowItem(optionalItem))
        assertNotEquals(oneTimeItem, ShowItem(oneTimeItem))

        // Original Item of a Show Item.
        assertEquals(essentialItem, ShowItem(essentialItem).originalItem)
        assertEquals(optionalItem, ShowItem(optionalItem).originalItem)
        assertEquals(oneTimeItem, ShowItem(oneTimeItem).originalItem)

        // Should return false if the other one is not an item (but compared to String version, it should be true).
        val essItemExpected = "Essential Item: suitcase\n" +
                "    Amount: 1\n" +
                "    Categories: []\n" +
                "    To Take: true"
        val optItemExpected = "Optional Item: sunglasses\n" +
                "    Amount: -\n" +
                "    Categories: []\n" +
                "    To Take: false"
        val oneItemExpected = "One Time Item: B12\n" +
                "    Amount: two packs\n" +
                "    Categories: []\n" +
                "    To Take: true"
        assertNotEquals(essItemExpected, essentialItem)
        assertNotEquals(optItemExpected, optionalItem)
        assertNotEquals(oneItemExpected, oneTimeItem)
        assertEquals(essItemExpected, essentialItem.toString())
        assertEquals(optItemExpected, optionalItem.toString())
        assertEquals(oneItemExpected, oneTimeItem.toString())
    }

}