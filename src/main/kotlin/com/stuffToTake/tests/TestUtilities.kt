package com.stuffToTake.tests

import com.stuffToTake.models.Category
import com.stuffToTake.models.EssentialItem
import com.stuffToTake.models.OneTimeItem
import com.stuffToTake.models.OptionalItem

class TestUtilities {

    fun generateItems(): Triple<MutableList<EssentialItem>, MutableList<OptionalItem>, MutableList<OneTimeItem>> {

        val essItem1 = EssentialItem("EssItem1", "", true)
        essItem1.addCategory(Category.ON_PC)
        essItem1.addCategory(Category.NINTENDO_SWITCH)
        val essItem2 = EssentialItem("EssItem2", 3, false)
        val essItem3 = EssentialItem("EssItem3", "ne Menge", true)
        essItem3.addCategory(Category.ON_PC)

        val optItem1 = OptionalItem("OptItem1", "", false)

        val oneItem1 = OneTimeItem("OneItem1", "2 dutzend", false)
        oneItem1.addCategory(Category.NINTENDO_SWITCH)
        oneItem1.addCategory(Category.ON_PC)
        val oneItem2 = OneTimeItem("OneItem2", "", true)
        oneItem2.addCategory(Category.ON_PC)

        return Triple(
            mutableListOf(essItem1, essItem2, essItem3),
            mutableListOf(optItem1),
            mutableListOf(oneItem1, oneItem2)
        )
    }

    fun getStringOfGeneratedItems(): String {
        return "ESSENTIAL ITEMS\n" +
                "\n" +
                "ITEM\n" +
                "    NAME: EssItem1\n" +
                "    AMOUNT: \n" +
                "    CATEGORIES: Upload on PC ; Nintendo Switch Stuff\n" +
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
                "    TAKE: O\n" +
                "\n" +
                "\n" +
                "OPTIONAL ITEMS\n" +
                "\n" +
                "ITEM\n" +
                "    NAME: OptItem1\n" +
                "    AMOUNT: \n" +
                "    CATEGORIES: \n" +
                "    TAKE: X\n" +
                "\n" +
                "\n" +
                "ONE TIME ITEMS\n" +
                "\n" +
                "ITEM\n" +
                "    NAME: OneItem1\n" +
                "    AMOUNT: 2 dutzend\n" +
                "    CATEGORIES: Nintendo Switch Stuff ; Upload on PC\n" +
                "    TAKE: X\n" +
                "ITEM\n" +
                "    NAME: OneItem2\n" +
                "    AMOUNT: \n" +
                "    CATEGORIES: Upload on PC\n" +
                "    TAKE: O\n"
    }

}
