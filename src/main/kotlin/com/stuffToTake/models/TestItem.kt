package com.stuffToTake.models

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty

class TestItem(itemName: String, amount: String, toTake: Boolean) {  // TODO delete

    var itemName = SimpleStringProperty(itemName)

    var amount = SimpleStringProperty(amount)

    var toTake = SimpleBooleanProperty(toTake)

}
