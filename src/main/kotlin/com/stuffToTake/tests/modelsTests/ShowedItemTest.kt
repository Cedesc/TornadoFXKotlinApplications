package com.stuffToTake.tests.modelsTests

import com.stuffToTake.models.EssentialItem
import com.stuffToTake.models.OneTimeItem
import com.stuffToTake.models.OptionalItem
import com.stuffToTake.models.ShowedItem
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ShowedItemTest {

    lateinit var essentialItem: EssentialItem
    lateinit var optionalItem: OptionalItem
    lateinit var oneTimeItem: OneTimeItem

    lateinit var essentialShowItem: ShowedItem
    lateinit var optionalShowItem: ShowedItem
    lateinit var oneTimeShowItem: ShowedItem

    @Before
    fun setUp() {
        essentialItem = EssentialItem("suitcase", 1, true)
        optionalItem = OptionalItem("sunglasses", "", false)
        oneTimeItem = OneTimeItem("B12", "two packs", true)

        essentialShowItem = ShowedItem(essentialItem)
        optionalShowItem = ShowedItem(optionalItem)
        oneTimeShowItem = ShowedItem(oneTimeItem)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun init() {
        // an Exception should be thrown if a ShowedItem of a ShowedItem should be build
        assertThrows(Exception::class.java) {
            ShowedItem(essentialShowItem)
        }
    }

    @Test
    fun markAsToTake() {

        // check if the initial states are correct
        assertEquals(true, essentialShowItem.to_take)
        assertEquals(false, optionalShowItem.to_take)
        assertEquals(true, oneTimeShowItem.to_take)

        // use markAsToTake
        essentialShowItem.markAsToTake()
        optionalShowItem.markAsToTake()
        oneTimeShowItem.markAsToTake()

        // check if all states are true
        assertEquals(true, essentialShowItem.to_take)
        assertEquals(true, optionalShowItem.to_take)
        assertEquals(true, oneTimeShowItem.to_take)

    }

    @Test
    fun unmarkAsToTake() {

        // check if the initial states are correct
        assertEquals(true, essentialShowItem.to_take)
        assertEquals(false, optionalShowItem.to_take)
        assertEquals(true, oneTimeShowItem.to_take)

        // use markAsToTake
        essentialShowItem.unmarkAsToTake()
        optionalShowItem.unmarkAsToTake()
        oneTimeShowItem.unmarkAsToTake()

        // check if all states are true
        assertEquals(false, essentialShowItem.to_take)
        assertEquals(false, optionalShowItem.to_take)
        assertEquals(false, oneTimeShowItem.to_take)

    }

    @Test
    fun getOriginalItem() {

        assertEquals(essentialItem, essentialShowItem.originalItem)
        val secondEssentialItem = ShowedItem(essentialItem)
        assertNotEquals(essentialShowItem, secondEssentialItem)
        assertEquals(essentialShowItem.originalItem, secondEssentialItem.originalItem)

        assertEquals(optionalItem, optionalShowItem.originalItem)
        val secondOptionalShowItem = ShowedItem(optionalItem)
        assertNotEquals(optionalShowItem, secondOptionalShowItem)
        assertEquals(optionalShowItem.originalItem, secondOptionalShowItem.originalItem)

        assertEquals(oneTimeItem, oneTimeShowItem.originalItem)
        val secondOneTimeShowItem = ShowedItem(oneTimeItem)
        assertNotEquals(oneTimeShowItem, secondOneTimeShowItem)
        assertEquals(oneTimeShowItem.originalItem, secondOneTimeShowItem.originalItem)

    }

    @Test
    fun testToString() {
        assertEquals(essentialItem.toString(), essentialShowItem.toString())
        assertEquals(optionalItem.toString(), optionalShowItem.toString())
        assertEquals(oneTimeItem.toString(), oneTimeShowItem.toString())
    }

}
