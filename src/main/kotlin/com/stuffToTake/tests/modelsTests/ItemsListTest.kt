package com.stuffToTake.tests.modelsTests

import com.stuffToTake.models.EssentialItem
import com.stuffToTake.models.ItemsList
import com.stuffToTake.models.OneTimeItem
import com.stuffToTake.models.OptionalItem
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ItemsListTest {

    lateinit var essentialItem1: EssentialItem
    lateinit var essentialItem2: EssentialItem
    lateinit var optionalItem1: OptionalItem
    lateinit var optionalItem2: OptionalItem
    lateinit var oneTimeItem1: OneTimeItem
    lateinit var oneTimeItem2: OneTimeItem
    lateinit var itemsList: ItemsList

    @Before
    fun setUp() {
        essentialItem1 = EssentialItem("suitcase", 1, true)
        essentialItem2 = EssentialItem("bag", "", false)
        optionalItem1 = OptionalItem("sunglasses", "", false)
        optionalItem2 = OptionalItem("tea can", "one", true)
        oneTimeItem1 = OneTimeItem("B12", "two packs", true)
        oneTimeItem2 = OneTimeItem("Iron", "2", true)
        itemsList = ItemsList()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun addEssentialItem() {
        val expected: MutableList<String> = mutableListOf(
            "Essential Item: suitcase\n" +
                    "    Amount: 1\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "Essential Item: bag\n" +
                    "    Amount: -\n" +
                    "    Categories: []\n" +
                    "    To Take: false"
        )
        itemsList.addEssentialItem(essentialItem1)
        itemsList.addEssentialItem(essentialItem2)
        assertEquals(expected.toString(), itemsList.getListOfAllItems().toString())
    }

    @Test
    fun addOptionalItem() {
        val expected: MutableList<String> = mutableListOf(
            "Optional Item: sunglasses\n" +
                    "    Amount: -\n" +
                    "    Categories: []\n" +
                    "    To Take: false",
            "Optional Item: tea can\n" +
                    "    Amount: one\n" +
                    "    Categories: []\n" +
                    "    To Take: true"
        )
        itemsList.addOptionalItem(optionalItem1)
        itemsList.addOptionalItem(optionalItem2)
        assertEquals(expected.toString(), itemsList.getListOfAllItems().toString())
    }

    @Test
    fun addOneTimeItem() {
        val expected: MutableList<String> = mutableListOf(
            "One Time Item: B12\n" +
                    "    Amount: two packs\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "One Time Item: Iron\n" +
                    "    Amount: 2\n" +
                    "    Categories: []\n" +
                    "    To Take: true"
        )
        itemsList.addOneTimeItem(oneTimeItem1)
        itemsList.addOneTimeItem(oneTimeItem2)
        assertEquals(expected.toString(), itemsList.getListOfAllItems().toString())
    }

    @Test
    fun addArbitraryItemAndGetListOfAllItems() {
        val expected: MutableList<String> = mutableListOf(
            "Essential Item: suitcase\n" +
                    "    Amount: 1\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "Essential Item: bag\n" +
                    "    Amount: -\n" +
                    "    Categories: []\n" +
                    "    To Take: false",
            "Optional Item: tea can\n" +
                    "    Amount: one\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "Optional Item: sunglasses\n" +
                    "    Amount: -\n" +
                    "    Categories: []\n" +
                    "    To Take: false",
            "One Time Item: B12\n" +
                    "    Amount: two packs\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "One Time Item: Iron\n" +
                    "    Amount: 2\n" +
                    "    Categories: []\n" +
                    "    To Take: true"
        )
        itemsList.addArbitraryItem(essentialItem1)
        itemsList.addArbitraryItem(optionalItem2)
        itemsList.addArbitraryItem(oneTimeItem1)
        itemsList.addArbitraryItem(oneTimeItem2)
        itemsList.addArbitraryItem(optionalItem1)
        itemsList.addArbitraryItem(essentialItem2)
        assertEquals(expected.toString(), itemsList.getListOfAllItems().toString())
    }

    @Test
    fun getEssentialItems() {
        val expected: MutableList<String> = mutableListOf(
            "Essential Item: bag\n" +
                    "    Amount: -\n" +
                    "    Categories: []\n" +
                    "    To Take: false",
            "Essential Item: suitcase\n" +
                    "    Amount: 1\n" +
                    "    Categories: []\n" +
                    "    To Take: true"
        )
        itemsList.addArbitraryItem(essentialItem2)
        itemsList.addOneTimeItem(oneTimeItem1)
        itemsList.addArbitraryItem(optionalItem2)
        itemsList.addArbitraryItem(essentialItem1)
        assertEquals(expected.toString(), itemsList.essentialItems.toString())
    }

    @Test
    fun getOptionalItems() {
        val expected: MutableList<String> = mutableListOf(
            "Optional Item: tea can\n" +
                    "    Amount: one\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "Optional Item: sunglasses\n" +
                    "    Amount: -\n" +
                    "    Categories: []\n" +
                    "    To Take: false"
        )
        itemsList.addEssentialItem(essentialItem1)
        itemsList.addArbitraryItem(optionalItem2)
        itemsList.addOptionalItem(optionalItem1)
        assertEquals(expected.toString(), itemsList.optionalItems.toString())
    }

    @Test
    fun getOneTimeItems() {
        val expected: MutableList<String> = mutableListOf(
            "One Time Item: Iron\n" +
                    "    Amount: 2\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "One Time Item: B12\n" +
                    "    Amount: two packs\n" +
                    "    Categories: []\n" +
                    "    To Take: true"
        )
        itemsList.addOneTimeItem(oneTimeItem2)
        itemsList.addArbitraryItem(oneTimeItem1)
        itemsList.addEssentialItem(essentialItem1)
        itemsList.addArbitraryItem(optionalItem2)
        assertEquals(expected.toString(), itemsList.oneTimeItems.toString())
    }
}