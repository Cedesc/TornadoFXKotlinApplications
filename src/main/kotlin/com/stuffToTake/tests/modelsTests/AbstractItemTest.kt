package com.stuffToTake.tests.modelsTests

import com.stuffToTake.models.*
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class AbstractItemTest {

    lateinit var essentialItem: EssentialItem
    lateinit var optionalItem: OptionalItem
    lateinit var oneTimeItem: OneTimeItem

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
                "    Categories: [Am PC hochladen, Kleidung]\n" +
                "    To Take: true",
            item2.toString())
        assertEquals("One Time Item: Item 3\n" +
                "    Amount: two\n" +
                "    Categories: [Kleidung]\n" +
                "    To Take: false",
            item3.toString())
        assertEquals("Showed Optional Item: Item 2\n" +
                "    Amount: 2\n" +
                "    Categories: [Am PC hochladen, Kleidung]\n" +
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
        assert(optionalItem.addCategory(Category.NINTENDO_SWITCH))

        // check if categories are right
        assertEquals(2, optionalItem.categories.size)
        assert(optionalItem.categories.contains(Category.ON_PC))
        assert(optionalItem.categories.contains(Category.NINTENDO_SWITCH))


        // add categories
        assert(oneTimeItem.addCategory(Category.ON_PC))
        assert(! oneTimeItem.addCategory(Category.ON_PC))
        assert(oneTimeItem.addCategory(Category.NINTENDO_SWITCH))

        // check if categories are right
        assertEquals(2, oneTimeItem.categories.size)
        assert(oneTimeItem.categories.contains(Category.ON_PC))
        assert(oneTimeItem.categories.contains(Category.NINTENDO_SWITCH))

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

}