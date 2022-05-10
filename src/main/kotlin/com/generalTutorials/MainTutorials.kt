package com.generalTutorials


// default parameter
// one line function
fun func1(number1: Int = 1337, number2: Int = 42, number3: Int): Int = number1 + number2 + number3

// in default parameters there can be functions too
// Unit is the default return Type
fun func2(arr: Array<Int>, len: Int = arr.size): Unit {
    return
}

// test function for the above ones
fun main2() {
    println(func1(number2 = 42, number3 = 5))
    println(func1(5, 1, 1))
}


fun main(args: Array<String>) {

    // Constants, variables and print commands
    val width: Int = 1920
    val height: Int = 1080
    println("Width: $width  Height: $height")

    var numberOfSillyThoughts: Int = 0
    numberOfSillyThoughts++
    print("Was? ")
    println(numberOfSillyThoughts)

    val pi: Double = 3.14 // Double: 64 Bit
    println("Hello World!")

    val foo: Boolean = (1920 == width) && ( !(height == 1920) || "Hallo"[4] == 'o')
    println(foo)



    // Arrays
    print("\n")
    var myArray = arrayOf(2, "So ein Feuerball!")
    println(myArray[1])
    myArray = Array(2){i -> i} // [0, 1]
    println(myArray[1])



    // if clauses
    print("\n")
    if (pi > 1) {
        println("Obvious")
    } else {
        println("Never!")
    }

    // more efficient if clauses
    val aIf = 42
    val bIf = 1337
    val maxIf1 = if(aIf > bIf) aIf else bIf
    val maxIf2 = if(aIf > bIf) {
        aIf
    } else {
        bIf
    }




    // Loops

    // for loops
    print("\n")
    val myString: String = "Hello World!"
    for (letter: Char in myString) {
        if (letter !in "aeiou") {
            print(letter + 3)
            print(" + ")
        }
    }
    println()
    for (index: Int in myString.indices) {
        print(" - $index - ")
    }

    // while loops
    print("\n")
    do {
        while (true) {
            break
        }
        continue
    } while (false)

    // break and continue
    var loopResult: String = ""
    var xLoop: Int = 5
    customName@while (xLoop > 1) {
        for (char: Char in "hello darkness") {
            if (char != 'h' && char != 'e') {
                break@customName // break of the outer loop
            }
            loopResult += char
        }
        xLoop--
        loopResult += "  "
    }
    println("Rest from \"hello darkness\": $loopResult")

    // Ranges
    print("Range from 0 to 20: ")
    for (i in 0..20) {
        print("$i ")
        // The numbers in between and both (!) 0 and 20 are output
    }
    print("\nRange from 20 to 0 in steps of 3: ")
    for (i in 20 downTo 0 step 3) {
        print("$i ")
    }
    print("\n")

    // foreach loop
    val myForEachArr = arrayOf(42, 4, 42, 24, 2, 4)
    print("foreach-loop output: ")
    myForEachArr.forEach {
        print("$it ") // "it" is the current Iterator in foreach-loops
    }
    print("\n")

    // index and element
    val myOtherArr = arrayOf(1337, 42, 420)
    for ((index, number) in myOtherArr.withIndex()) {
        print("Index: $index  Number: $number    ")
    }
    print("\n")




    // user input and casting
    print("\n")
    print("What you type here will be printed below: ")
    val userInput: String = ""//readLine()
    println(userInput)
    print("Write a number: ")
    var numberInput = Integer.valueOf(1)//readLine())
    numberInput++
    println("Your number plus 1: $numberInput")




    // Maybe Typen
    print("\n")
    val myString1: String? = null
    println("Finger weg von meinen Nuggets: " + myString1?.length) // ? only execute if it is not null
    val myString2 = "Ok"
    println("Sooooooo ein Feuerball: " + myString2!!.length) // !! assume that it is not null

    println(myString1?.length ?:"String is null") // ?: if previous is null returns following
    val length: Int = myString1?.length ?: 0
    myString1?.let {
        println("If you see this in the console, myString1 is certainly not null!")
        // "let" if myString is not null, execute arbitrary code
    }




    // nullable Booleans
    print("\n")
    val myBool: Boolean? = null
    if (myBool == true) { // "if (myBool)" is not good
        println(myBool)
    } else {
        println("false or null")
    }
    myBool?.let {
        println("Hello")
    }




    // Swap variables
    print("\n")
    var aSwap: Int = 111
    var bSwap: Int = 222
    aSwap = bSwap.also { // inside the brackets, the previous command isn't executed
        bSwap = aSwap
    }
    println("aSwap: $aSwap  bSwap: $bSwap")




    // Typecasts
    print("\n")
    val myVar1: Int = 1337
    val myVar2: Byte = myVar1.toByte()
    val myVar3: Int = 3
    val myVar4: Char = myVar3.toChar()

    println("After Typecast: $myVar2 and $myVar4")




    // Pattern Matching with "when"
    print("\n")
    val xWhen: Int = 42
    var outputWhen:String = when(xWhen) {
        0, 1, 2, 3, 4 -> "xWhen is between 0 and 4"
        in 35..45 -> "xWhen is between 35 and 45"
        else -> {
            "xWhen is something"
        }
    }
    println(outputWhen)
    when{
        xWhen > 5 -> println("xWhen is greater than 5")
        xWhen > 100 -> println("xWhen is greater than 100, what is true, but this message won't be displayed")
    }




    // Lambda Functions
    print("\n")
    val lambdaFunctionAdd = {a: Int, b: Int -> a+b}




    // Arrays of special Types and Lists
    val charArray: CharArray = charArrayOf('h', 'e', 'y') // Array of Chars
    val hiString: String = String(charArray)

    val basicArray = arrayOf(1, 2, 3, 4)
    val basicList = listOf(11, 22, 33, 44)
    println("Print for array: $basicArray   Print for list: $basicList")
    println("Get element of the list with getorelse: " + basicList.getOrElse(10) { "Element not found" })
    println("Get third element more efficient with component3: " + basicList.component3())

    // concatenate, Add and delete
    var extendedList = basicList + listOf(55, 66, 77, 77, 88)
    println("Extended list: $extendedList")
    var extendedListAfterDeletion = extendedList - 55
    extendedListAfterDeletion -= listOf(77, 22, 33, 187)
    println("Extended list after deletion: $extendedListAfterDeletion")

    // reverse, partition, slice, chunked
    println("Extended list reversed: " + extendedList.reversed() + "  But the list itself is unchanged: $extendedList")
    val partitionedList = extendedList.partition { it in 40..50 || it == 88 }
    println("Partitioned List: $partitionedList")
    val slicedList = extendedList.slice(1..4)
    println("Sliced List: $slicedList")
    val chunkedList = extendedList.chunked(4)
    println("Chunked List: $chunkedList")





    // Exercises

    // exercise integer overflow
    print("\n")
    var myInteger: Int = 2147483647
    // myInteger = 1
    while (myInteger > 0) {
        myInteger++
    }
    myInteger--
    //println("Maximum size of an Integer: $myInteger")


    // exercise modulo #31

    // exercise faculty #36

    // exercise abs (Betrag) #39

    // exercise sum over array #40

    // exercise calculate sinus by hand #41

    // exercise letter replacement #42

    // exercise inheritance #51


}

class MyClass{
    fun foo(a: Int): Int {
        return a + 42
    }
}