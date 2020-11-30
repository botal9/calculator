import token.Tokenizer

fun main() {
    val line = readLine() ?: throw IllegalArgumentException("No input line")
    val tokens = Tokenizer().tokenize(line)
    println(tokens.joinToString(" "))
}