package com.example.tutorialExample.view

import com.example.tutorialExample.controllers.VIDEOLoginController
import com.example.tutorialExample.models.UserModel
import javafx.scene.text.FontWeight
import javafx.geometry.Pos
import tornadofx.*

class VIDEOWelcomeScreen : View("Welcome") {
    val user: UserModel by inject()
    val VIDEOLoginController: VIDEOLoginController by inject()

    override val root = vbox(10) {
        setPrefSize(800.0, 600.0)
        alignment = Pos.CENTER

        label("Hello") {
            style {
                fontWeight = FontWeight.BOLD
                fontSize = 24.px
            }
        }

        label(user.name)

        button("Logout").action(VIDEOLoginController::logout)
    }
}
