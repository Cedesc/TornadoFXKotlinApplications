package com.stuffToTake.controllers

import com.stuffToTake.view.AddItemView
import com.stuffToTake.view.MenuView
import com.stuffToTake.view.TestView1
import com.stuffToTake.view.TestView2
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

    fun toTestView1() {  // TODO delete
        find(MenuView::class).replaceWith(TestView1::class, sizeToScene = true, centerOnScreen = true)
    }

    fun toTestView2() {  // TODO delete
        find(MenuView::class).replaceWith(TestView2::class, sizeToScene = true, centerOnScreen = true)
    }


    fun backToMenuView() {
        primaryStage.uiComponent<UIComponent>()?.replaceWith(MenuView::class, sizeToScene = true, centerOnScreen = true)
    }

    fun refresh() {  // is this really a refresh?
        primaryStage.uiComponent<UIComponent>()?.onDock()
    }

}
