package com.stuffToTake.tests.modelsTests

import com.stuffToTake.models.Category
import com.stuffToTake.models.EssentialItem
import com.stuffToTake.models.OneTimeItem
import com.stuffToTake.models.OptionalItem
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
        assertEquals("Item: suitcase\n" +
                "    Amount: 1\n" +
                "    To Take: true\n" +
                "    Categories: []",
            essentialItem.toString())
        assertEquals("Item: sunglasses\n" +
                "    Amount: -\n" +
                "    To Take: false\n" +
                "    Categories: []",
            optionalItem.toString())
        assertEquals("Item: B12\n" +
                "    Amount: two packs\n" +
                "    To Take: true\n" +
                "    Categories: []",
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
    fun getTo_take() {
        assertEquals(true, essentialItem.to_take)
        assertEquals(false, optionalItem.to_take)
        assertEquals(true, oneTimeItem.to_take)
    }

    @Test
    fun setTo_take() {
        essentialItem.to_take = false
        assertEquals(false, essentialItem.to_take)

        optionalItem.to_take = true
        assertEquals(true, optionalItem.to_take)

        oneTimeItem.to_take = true
        assertEquals(true, oneTimeItem.to_take)
    }

    @Test
    fun getCategories() {
        assert(essentialItem.categories.isEmpty())
        assert(optionalItem.categories.isEmpty())
        assert(oneTimeItem.categories.isEmpty())
    }

}