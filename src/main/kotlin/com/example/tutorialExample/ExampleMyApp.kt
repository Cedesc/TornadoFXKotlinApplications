package com.example.tutorialExample

import com.example.tutorialExample.view.ExampleMainView
import javafx.stage.Stage
import tornadofx.App

class ExampleMyApp: App(ExampleMainView::class, ExampleStyles::class) {
    override fun start(stage: Stage) {
        with(stage) {
            width = 1200.0
            height = 600.0
        }
        super.start(stage)
    }
}