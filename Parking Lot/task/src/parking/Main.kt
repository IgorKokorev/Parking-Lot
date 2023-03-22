package parking

class ParkingEmptyException(message: String) : Exception(message)
/*  Parking - list of parking slots of cars = list of Strings (RegNum + Color)
    If a parking slot is empty, the corresponding list is empty. */
val parking: MutableList<MutableList<String>> = mutableListOf()

fun main() {
    // Starting operations
    do {
        val inp = readln().split("\\s".toRegex()).toMutableList()
        when (inp[0]) {
            "exit" -> break
            "park" -> park(inp)
            "leave" -> leave(inp)
            "create" -> create(inp)
            "status" -> status()
            "reg_by_color" -> regbycolor(inp)
            "spot_by_color" -> spotbycolor(inp)
            "spot_by_reg" -> spotbyreg(inp)
            else -> println("Wrong operation")
        }
    } while (true)
}

// Checking if spots were created
fun isParkingEmpty() {
    if (parking.size == 0) {
        println("Sorry, a parking lot has not been created.")
        throw ParkingEmptyException("Empty parking.")
    }
}

fun regbycolor( inp: MutableList<String> ) {
    try { isParkingEmpty() } catch (e: ParkingEmptyException) { return }

    // Checking arguments
    if (inp.size < 2) {
        println("You have to specify color.")
        return
    }

    // List of reg nums of specified color
    val regs : MutableList<String> = mutableListOf()
    for (i in 0..parking.lastIndex) {
        if (parking[i].isEmpty()) continue
        if (parking[i][1].lowercase().equals(inp[1].lowercase())) {
            regs.add(parking[i][0])
        }
    }
    if (regs.isEmpty()) {
        println("No cars with color ${inp[1]} were found.")
    } else {
        println(regs.joinToString())
    }
}


fun spotbycolor( inp: MutableList<String> ) {
    try { isParkingEmpty() } catch (e: ParkingEmptyException) { return }

    // Checking arguments
    if (inp.size < 2) {
        println("You have to specify color.")
        return
    }

    // List of reg nums of specified color
    val spots : MutableList<Int> = mutableListOf()
    for (i in 0..parking.lastIndex) {
        if (parking[i].isEmpty()) continue
        if (parking[i][1].lowercase().equals(inp[1].lowercase())) {
            spots.add(i+1)
        }
    }
    if (spots.isEmpty()) {
        println("No cars with color ${inp[1]} were found.")
    } else {
        println(spots.joinToString())
    }
}


fun spotbyreg( inp: MutableList<String> ) {
    try { isParkingEmpty() } catch (e: ParkingEmptyException) { return }

    // Checking arguments
    if (inp.size < 2) {
        println("You have to specify registration number.")
        return
    }

    // List of reg nums of specified color
    val spots : MutableList<Int> = mutableListOf()
    for (i in 0..parking.lastIndex) {
        if (parking[i].isEmpty()) continue
        if (parking[i][0].lowercase().equals(inp[1].lowercase())) {
            spots.add(i+1)
        }
    }
    if (spots.isEmpty()) {
        println("No cars with registration number ${inp[1]} were found.")
    } else {
        println(spots.joinToString())
    }
}

fun status() {
    try { isParkingEmpty() } catch (e: ParkingEmptyException) { return }

    var isEmpty = true
    for (i in 0..parking.lastIndex) {
        if (parking[i].isNotEmpty()) {
            isEmpty = false
            println("${i+1} ${parking[i][0]} ${parking[i][1]}")
        }
    }
    if (isEmpty) println("Parking lot is empty.")
}

fun create( inp: MutableList<String> ) {
    // Checking arguments
    if (inp.size < 2) {
        println("You have to enter number of spots to add.")
        return
    }
    val spotsToAdd = inp[1].toIntOrNull() ?: 0
    if (spotsToAdd <= 0) {
        println("Number of spots to add should be positive.")
        return
    }

    // Filling our parking with empty spots
    parking.clear()
    val emptySpot: MutableList<String> = mutableListOf()
    for (i in 1..spotsToAdd) parking.add(emptySpot)
    println("Created a parking lot with $spotsToAdd spots.")

}

//Parking a car with details in inp
fun park( inp: MutableList<String> ) {
    try { isParkingEmpty() } catch (e: ParkingEmptyException) { return }

    // Checking number of input data
    if (inp.size < 3) {
        println("Not enough car data for parking.")
    } else {
        var isParked = false
        var spot = 0
        inp[2] = inp[2].capitalize()
        for (i in 0..parking.lastIndex) {
            if (parking[i].isEmpty()) {
                parking[i] = inp.subList(1, inp.size)
                isParked = true
                spot = i
                break
            }
        }
        if (isParked) {
            println("${inp[2]} car parked in spot ${spot+1}.")
        } else {
            println("Sorry, the parking lot is full.")
        }
    }
}

fun leave( inp: MutableList<String> ) {

    // Checking if spots were created
    if (parking.size == 0) {
        println("Sorry, a parking lot has not been created.")
        return
    }

    if(inp.size < 2) {
        println("You should enter the parking spot to leave.")
        return
    }

    val spot = inp[1].toIntOrNull() ?: 0
    if (spot <= 0 || spot > parking.size ) {
        println("Incorrect spot.")
        return
    }

    if (parking[spot-1].isEmpty()) {
        println("There is no car in spot $spot.")
    } else {
        parking[spot-1].clear()
        println("Spot $spot is free.")
    }
}