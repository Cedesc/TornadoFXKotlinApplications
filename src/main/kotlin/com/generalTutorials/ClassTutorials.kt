package com.generalTutorials

// Interfaces
// It is possible that classes inherit from multiple interfaces but not from multiple classes!
interface SomeInterface {
    val firstVal: String
    val secondVal: String

    abstract fun firstFunc()
    fun secondFunc(): String {
        return "Hello! It is allowed to write a body in interface methods!"
    }
}

interface SomeOtherInterface {
    val otherVal: Int

    fun secondFunc(): String {
        return " Yeah boy!"
    }
}

// Multiple Inheritance
class MultipleInheritanceClass: SomeInterface, SomeOtherInterface {
    override val firstVal: String
        get() = "Hello"
    override val secondVal: String
        get() = "World"

    override fun firstFunc() {
    }

    override val otherVal: Int
        get() = 10

    // this class can access both "secondFunc" functions
    override fun secondFunc(): String {
        return super<SomeInterface>.secondFunc() + super<SomeOtherInterface>.secondFunc()
    }


}




// Inheriting class
abstract class FatherOfTheJoonge() {

    abstract var name: String
        protected set

    protected open var height: Double = 2.0

    public fun catchphrase(): String {
        return "So ein Feuerball, tutorials.Joonge!"
    }

    protected open fun printMe() {
        println("Get off my property!")
    }

}


class Joonge(name: String, protected override var height: Double = 1.62): FatherOfTheJoonge() {

    override var name: String = name
        get() = "It's name is \"$field\"" // Getter and Setter
        protected set(value) {
            if (value.length > 5) {
                field = value
            }
        }

    var numberOfArms: Int = 2
        get() = field
        set(value) {
            field = when {
                value > 10 -> 10
                value < 0 -> 0
                else -> value
            }
        }

    // Secondary Constructor
    constructor(name: String, height: Double, numberOfArms: Int): this(name, height) {
        this.numberOfArms = numberOfArms
    }

    // Init-Block Constructor
    // Caution! Pay Attention to the Initialization of the variables!!
    // Methods can be used, but undefined variables get default values
    init {
        println("Hello World! $name has arrived!")
        printMe()
    }

    // Override
    protected override fun printMe() {
        println("tutorials.Joonge, I am $name and I have $numberOfArms arms and I am " + this.height + "m, tutorials.Joonge!")
    }

    override fun toString(): String {
        return "tutorials.Joonge(height=$height, name='$name', numberOfArms=$numberOfArms)"
    }

    fun dropCatchphraseWithExtra(): String {
        return super.catchphrase() + " Und Finger weg von mein Nuggets! I am " + super.height + "m!"
    }
}


// lateinit
class School() {
    // if a variable cannot instantiate at the instantiation of the class, then one can use lateinit to make it
    // possible to wait with the initialization of the variable
    lateinit var boy: Joonge

    // setter
    fun catchBoy(boy: Joonge) {
        this.boy = boy
    }

    // getter
    fun pullOutBoy(): Joonge? {
        return if (this::boy.isInitialized) {
            boy
        } else {
            null
        }
    }
}


// Extension
fun School.sayTheSchoolName(): String {
    return "Mr. Poopybutthole Elementary tutorials.School"
}




fun main(args: Array<String>) {
    val loki: Joonge = Joonge("The Boyyyy")
    val lauchBoy: Joonge = Joonge("Lauch Boy", 1.79, 3)

    print("\n")
    println(loki.name)
    println(loki)
    println(lauchBoy)

    print("\n")
    println("Lokis catchphrase:  " + loki.catchphrase())
    println("Lokis catchphrase with extra:  " + loki.dropCatchphraseWithExtra())

    print("\n")
    val school: School = School()
    println("Print method of a tutorials.School per extension:  " + school.sayTheSchoolName())
}


// Skipped
// #57, #58 TypeCasts (self created classes)
