package com.stuffToTake.tests.modelsTests

import com.stuffToTake.models.AbstractItem
import com.stuffToTake.models.EssentialItem
import com.stuffToTake.models.OneTimeItem
import com.stuffToTake.models.OptionalItem
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class AbstractItemTest {

    lateinit var anEssentialItem: EssentialItem
    lateinit var anOptionalItem: OptionalItem
    lateinit var anOneTimeItem: OneTimeItem

    @Before
    fun setUp() {
        anEssentialItem = EssentialItem("suitcase", 1, true)
        anOptionalItem = OptionalItem("sunglasses", "", false)
        anOneTimeItem = OneTimeItem("B12", "two packs", true)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun hasAmount() {
    }

    @Test
    fun addCategory() {
    }

    @Test
    fun testToString() {
    }

    @Test
    fun getName() {
        assertEquals("suitcase", anEssentialItem.name)
        assertEquals("sunglasses", anOptionalItem.name)
        assertEquals("B12", anOneTimeItem.name)
    }

    @Test
    fun setName() {
        anEssentialItem.name = "not a suitcase"
        assertEquals("not a suitcase", anEssentialItem.name)

        anOptionalItem.name = "glasses"
        assertEquals("glasses", anOptionalItem.name)

        anOneTimeItem.name = "Iron"
        assertEquals("Iron", anOneTimeItem.name)
    }

    @Test
    fun getAmount() {
        assertEquals("1", anEssentialItem.amount)
        assertEquals("", anOptionalItem.amount)
        assertEquals("two packs", anOneTimeItem.amount)
    }

    @Test
    fun setAmount() {
        anEssentialItem.amount = "2"
        assertEquals("2", anEssentialItem.amount)

        anOptionalItem.amount = "5"
        assertEquals("5", anOptionalItem.amount)

        anOneTimeItem.amount = "three packs"
        assertEquals("three packs", anOneTimeItem.amount)
    }

    @Test
    fun getTo_take() {
        assertEquals(true, anEssentialItem.to_take)
        assertEquals(false, anOptionalItem.to_take)
        assertEquals(true, anOneTimeItem.to_take)
    }

    @Test
    fun setTo_take() {
        anEssentialItem.to_take = false
        assertEquals(false, anEssentialItem.to_take)

        anOptionalItem.to_take = true
        assertEquals(true, anOptionalItem.to_take)

        anOneTimeItem.to_take = true
        assertEquals(true, anOneTimeItem.to_take)
    }
}