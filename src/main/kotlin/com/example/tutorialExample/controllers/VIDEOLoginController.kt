package com.example.tutorialExample.controllers

import com.example.tutorialExample.models.User
import com.example.tutorialExample.models.UserModel
import com.example.tutorialExample.view.VIDEOLoginScreen
import com.example.tutorialExample.view.VIDEOWelcomeScreen
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class VIDEOLoginController : Controller() {
    val statusProperty = SimpleStringProperty("")
    var status by statusProperty
    val user : UserModel by inject()

    fun login(username: String, password: String) {
        runLater { status = "already executed login" }
        runLater {
            user.item = User()
            user.item.name = "Hans Mueller"
            find(VIDEOLoginScreen::class).replaceWith(VIDEOWelcomeScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun logout() {
        primaryStage.uiComponent<UIComponent>()?.replaceWith(VIDEOLoginScreen::class, sizeToScene = true, centerOnScreen = true)
    }

}