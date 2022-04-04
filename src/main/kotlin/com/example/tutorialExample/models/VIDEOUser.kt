package com.example.tutorialExample.models

import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

class User : JsonModel{
    val nameProperty = SimpleStringProperty()
    var name by nameProperty

}

class UserModel : ItemViewModel<User>() {
    val name = bind(User::nameProperty)
}