fun main() {
    val str = readln()
    for (ch in str) {
        if (ch.isDigit()) {
            println(ch)
            break
        }
    }
}