package com.stuffToTake.controllers

import com.stuffToTake.view.AddItemView
import com.stuffToTake.view.MenuView
import com.stuffToTake.view.TestView
import tornadofx.Controller
import tornadofx.UIComponent
import tornadofx.uiComponent

class MenuController : Controller() {

    fun toAddItemView() {
        find(MenuView::class).replaceWith(AddItemView::class, sizeToScene = true, centerOnScreen = true)
    }

    fun toFullListOfItemsView() {
        println("Not yet implemented")
    }

    fun toChooseItemsView() {
        println("Not yet implemented")
    }

    fun toHistoryView() {
        println("Not yet implemented")
    }

    fun toTestView() {  // TODO delete
        find(MenuView::class).replaceWith(TestView::class, sizeToScene = true, centerOnScreen = true)
    }


    fun backToMenuView() {
        primaryStage.uiComponent<UIComponent>()?.replaceWith(MenuView::class, sizeToScene = true, centerOnScreen = true)
    }

}
