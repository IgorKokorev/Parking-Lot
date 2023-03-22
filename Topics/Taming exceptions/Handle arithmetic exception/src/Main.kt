import java.lang.Exception

fun main() {
    val a = readln().toInt()
    val b = readln().toInt()

    if (b == 0) {
        println("Division by zero, please fix the second argument!")
    } else return println(a/b)
}
