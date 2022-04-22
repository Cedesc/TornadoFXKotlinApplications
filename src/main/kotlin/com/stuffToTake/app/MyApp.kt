package com.stuffToTake.app

import com.stuffToTake.styles.Styles
import com.stuffToTake.view.MenuView
import tornadofx.App

class MyApp : App(MenuView::class, Styles::class)
