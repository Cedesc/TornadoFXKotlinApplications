package com.stuffToTake.app

import com.stuffToTake.controllers.ItemsListController
import com.stuffToTake.styles.Styles
import com.stuffToTake.view.MenuView
import javafx.stage.Stage
import tornadofx.App

class MyApp : App(MenuView::class, Styles::class) {

    private val itemsListController: ItemsListController by inject()

    override fun start(stage: Stage) {
        with(stage) {
            minWidth = 200.0
            minHeight = 150.0
            super.start(this)
        }
    }

    override fun stop() {
        // Save items in txt file.
        itemsListController.saveItems()

        super.stop()
    }
}
