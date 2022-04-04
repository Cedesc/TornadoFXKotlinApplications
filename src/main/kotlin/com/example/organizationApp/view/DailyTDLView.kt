package com.example.organizationApp.view

import com.example.organizationApp.controllers.DailyTDLController
import com.example.organizationApp.models.AbstractTask
import com.example.organizationApp.styles.Styles
import tornadofx.*

class DailyTDLView : View("Daily TDL") {
    val todayTDL : Array<AbstractTask> = DailyTDLController().todayTDL
    override val root = vbox {

        label(title) {
            addClass(Styles.heading)
        }

        label("Hello again")

        flowpane {
            for (task in todayTDL) {
                vbox {
                    label(task.name)
                    label(task.date.toString())
                    label(task.notes)
                }
            }
        }

    }
}
