package com.stuffToTake.tests.modelsTests

import com.stuffToTake.models.*
import com.stuffToTake.saves.ItemParser
import com.stuffToTake.tests.TestUtilities
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import java.io.File

class ItemsListTest {

    private val testUtils = TestUtilities()

    private lateinit var essentialItem1: EssentialItem
    private lateinit var essentialItem2: EssentialItem
    private lateinit var optionalItem1: OptionalItem
    private lateinit var optionalItem2: OptionalItem
    private lateinit var oneTimeItem1: OneTimeItem
    private lateinit var oneTimeItem2: OneTimeItem
    private lateinit var itemsList: ItemsList

    @Before
    fun setUp() {
        essentialItem1 = EssentialItem("suitcase", 1, true)
        essentialItem2 = EssentialItem("bag", "", false)
        optionalItem1 = OptionalItem("sunglasses", "", false)
        optionalItem2 = OptionalItem("tea can", "one", true)
        oneTimeItem1 = OneTimeItem("B12", "two packs", true)
        oneTimeItem2 = OneTimeItem("Iron", "2", true)
        itemsList = ItemsList("Item List")
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
        fillItemList()
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

    @Test
    fun loadSavedItems() {

        // Create parser
        itemsList.itemParser =
            ItemParser("src/main/kotlin/com/stuffToTake/tests/modelsTests/loadSavedItemsTest.txt")

        // Create file
        createFileSafely(itemsList.itemParser)


        // Generate items
        val items = testUtils.generateItems()
        val essItems = items.first
        val optItems = items.second
        val oneItems = items.third

        // Convert to txt file
        itemsList.itemParser.codeToTxt(essItems, optItems, oneItems)

        // Convert back to code in the itemsList
        assertEquals(true,
            itemsList.loadSavedItems())

        // Check if the re-converted items are the same as the items before the conversion
        assertEquals(essItems.toString(), itemsList.essentialItems.toString())
        assertEquals(optItems.toString(), itemsList.optionalItems.toString())
        assertEquals(oneItems.toString(), itemsList.oneTimeItems.toString())


        // Delete created file.
        assertEquals(true,
            itemsList.itemParser.deleteFile())
    }

    @Test
    fun loadSavedItemsFail() {
        // Method returns false because the filepath is empty.
        assertEquals(false,
            itemsList.loadSavedItems())

        // Method returns false because the item lists aren't empty.
        itemsList.itemParser = ItemParser("fail test")
        itemsList.addArbitraryItem(EssentialItem("thing", "", false))
        assertEquals(false,
            itemsList.loadSavedItems())

        // Method returns false because the filepath is empty and the item lists aren't empty.
        itemsList.itemParser = ItemParser("")
        assertEquals(false,
            itemsList.loadSavedItems())
    }

    @Test
    fun saveItems() {

        // Test with empty filepath in parser
        itemsList.itemParser =
                ItemParser("")
        assertEquals(false,
            itemsList.saveItems())


        // Create parser
        itemsList.itemParser =
            ItemParser("src/main/kotlin/com/stuffToTake/tests/modelsTests/saveItemsTest.txt")

        // Create file
        createFileSafely(itemsList.itemParser)


        // Generate items
        val items = testUtils.generateItems()
        val essItems = items.first
        val optItems = items.second
        val oneItems = items.third

        // Add to itemsList
        for (item in essItems)
            itemsList.addArbitraryItem(item)
        for (item in optItems)
            itemsList.addArbitraryItem(item)
        for (item in oneItems)
            itemsList.addArbitraryItem(item)

        // Save items in a txt file
        assertEquals(true,
            itemsList.saveItems())

        val expected: String = testUtils.getStringOfGeneratedItems()
        val actual = File(itemsList.itemParser.filepath).readText()
        assertEquals(expected, actual)


        // Delete created file.
        assertEquals(true,
            itemsList.itemParser.deleteFile())
    }

    @Test
    fun deleteArbitraryItem() {
        val expected: MutableList<String> = mutableListOf(
            "Essential Item: suitcase\n" +
                    "    Amount: 1\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "Optional Item: tea can\n" +
                    "    Amount: one\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "One Time Item: B12\n" +
                    "    Amount: two packs\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "One Time Item: Iron\n" +
                    "    Amount: 2\n" +
                    "    Categories: []\n" +
                    "    To Take: true"
        )
        fillItemList()

        // delete essentialItem2
        assertEquals(true, itemsList.deleteArbitraryItem(essentialItem2))
        // delete optionalItem1
        assertEquals(true,
            itemsList.deleteArbitraryItem(OptionalItem("sunglasses", "", false)))
        assertEquals(expected.toString(), itemsList.getListOfAllItems().toString())

        // try to delete items that are not in the list
        assertEquals(false, itemsList.deleteArbitraryItem(optionalItem1))
        assertEquals(false,
            itemsList.deleteArbitraryItem(EssentialItem("not existing", "", false)))

        // delete rest
        assertEquals(true, itemsList.deleteArbitraryItem(essentialItem1))
        assertEquals(true, itemsList.deleteArbitraryItem(optionalItem2))
        assertEquals(true, itemsList.deleteArbitraryItem(oneTimeItem1))
        assertEquals(true, itemsList.deleteArbitraryItem(oneTimeItem2))
        assertEquals("[]", itemsList.getListOfAllItems().toString())
    }

    @Test
    fun editArbitraryItem() {
        val expected: MutableList<String> = mutableListOf(
            "Essential Item: suitcase\n" +
                    "    Amount: 1\n" +
                    "    Categories: []\n" +
                    "    To Take: true",
            "Essential Item: bigger bag\n" +
                    "    Amount: 2\n" +
                    "    Categories: [Kleidung]\n" +
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
                    "    To Take: true",
            "One Time Item: smaller tea can\n" +
                    "    Amount: -\n" +
                    "    Categories: []\n" +
                    "    To Take: false"
        )
        fillItemList()

        // create new item
        var newItem: AbstractItem = EssentialItem("bigger bag", "2", true)
        newItem.addCategory(Category.CLOTHING)
        // replace essentialItem2 with the new item
        assertEquals(true, itemsList.editArbitraryItem(essentialItem2, newItem))

        // create new item
        newItem = OneTimeItem("smaller tea can", "", false)
        // replace optionalItem2 with the new item
        assertEquals(true, itemsList.editArbitraryItem(optionalItem2, newItem))
        assertEquals(expected.toString(), itemsList.getListOfAllItems().toString())

        // try to add a show item
        assertEquals(false, itemsList.editArbitraryItem(essentialItem1, ShowItem(newItem)))
        // the old item (essentialItem1) shouldn't be deleted
        assertEquals(expected.toString(), itemsList.getListOfAllItems().toString())
    }

    /**
     * Creates a file safely (checks if the file existed before and after creation).
     */
    private fun createFileSafely(parser: ItemParser) {
        assertEquals(false,
            parser.checkFileExists())
        assertEquals(true,
            parser.createFile())
        assertEquals(true,
            parser.checkFileExists())
    }

    /**
     * Fills the item list with elements.
     */
    private fun fillItemList() {
        itemsList.addArbitraryItem(essentialItem1)
        itemsList.addArbitraryItem(optionalItem2)
        itemsList.addArbitraryItem(oneTimeItem1)
        itemsList.addArbitraryItem(oneTimeItem2)
        itemsList.addArbitraryItem(optionalItem1)
        itemsList.addArbitraryItem(essentialItem2)
    }
}
