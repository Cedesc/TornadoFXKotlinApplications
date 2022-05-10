package com.example.organizationApp.app

import com.example.organizationApp.styles.Styles
import com.example.organizationApp.view.DailyTDLView
import com.example.organizationApp.view.MainView
import javafx.stage.Stage
import tornadofx.App

class MyApp: App(DailyTDLView::class, Styles::class) {
    override fun start(stage: Stage) {
        with(stage) {
            width = 1200.0
            height = 600.0
        }
        super.start(stage)
    }
}