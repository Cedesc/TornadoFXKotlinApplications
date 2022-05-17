package com.stuffToTake.controllers

import com.stuffToTake.models.ShowItem
import com.stuffToTake.view.*
import javafx.collections.ObservableList
import tornadofx.Controller
import tornadofx.UIComponent
import tornadofx.uiComponent

class MenuController : Controller() {

    fun toAddItemView() {
        find(MenuView::class).replaceWith(AddItemView::class, sizeToScene = true, centerOnScreen = true)
    }

//    fun toFullListOfToMainzItemsView() {  // TODO delete
//        find(MenuView::class).replaceWith(FullListOfToMainzItemsView::class, sizeToScene = true, centerOnScreen = true)
//    }

//    fun toFullListOfToWWItemsView() {  // TODO delete
////        find(MenuView::class).replaceWith(FullListOfToMainzItemsView::class, sizeToScene = true, centerOnScreen = true)
////        find<FullListOfToMainzItemsView>(mapOf(FullListOfToMainzItemsView::customer to true)).openWindow()
//        find(MenuView::class).replaceWith(find<FullListOfToMainzItemsView>(mapOf(FullListOfToMainzItemsView::customer to true)),
//        sizeToScene = true, centerOnScreen = true)
//    }

    fun toListOfItemsView() {
//        find(MenuView::class).replaceWith(  // TODO delete
//            find<FullListOfToMainzItemsView>(mapOf(FullListOfToMainzItemsView::observableList to observableList)),
//            sizeToScene = true, centerOnScreen = true)
        find(MenuView::class).replaceWith(FullListOfToMainzItemsView::class, sizeToScene = true, centerOnScreen = true)
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
