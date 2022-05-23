package com.stuffToTake.controllers

import com.stuffToTake.view.*
import tornadofx.Controller
import tornadofx.UIComponent
import tornadofx.uiComponent

class MenuController : Controller() {

    fun toAddItemView() {
        find(MenuView::class).replaceWith(AddItemView::class, sizeToScene = true, centerOnScreen = true)
    }

    fun toListOfItemsView() {
        find(MenuView::class).replaceWith(ListOfItemsView::class, sizeToScene = true, centerOnScreen = true)
    }

    fun toChooseItemsView() {
        find(MenuView::class).replaceWith(ChooseItemsView::class, sizeToScene = true, centerOnScreen = true)
    }

    fun toHistoryView() {
        println("Not yet implemented")
    }

    fun toTestView1() {  // TODO (afterwards) delete
        find(MenuView::class).replaceWith(TestView1::class, sizeToScene = true, centerOnScreen = true)
    }

    fun toTestView2() {  // TODO (afterwards) delete
        find(MenuView::class).replaceWith(TestView2::class, sizeToScene = true, centerOnScreen = true)
    }


    fun backToMenuView() {
        primaryStage.uiComponent<UIComponent>()?.replaceWith(MenuView::class, sizeToScene = true, centerOnScreen = true)
    }

    fun refresh() {  // is this really a refresh?
        primaryStage.uiComponent<UIComponent>()?.onDock()
    }

}
