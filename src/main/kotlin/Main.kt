import token.Tokenizer
import visitor.PrintVisitor
import java.lang.Exception

fun main() {
    try {
        val line = readLine() ?: throw IllegalArgumentException("No input line")
        val tokens = Tokenizer().tokenize(line)

        val printVisitor = PrintVisitor()
        tokens.forEach { it.accept(printVisitor) }
    } catch (e : Exception) {
        println("Error: ${e.message}")
    }
}