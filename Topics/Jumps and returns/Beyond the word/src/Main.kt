fun main() {
    val word = readln()

    for (ch in 'a'..'z') {
        if ( !word.contains(ch) ) print(ch)
    }
}