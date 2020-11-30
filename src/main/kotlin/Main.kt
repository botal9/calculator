import token.Tokenizer
import visitor.ParserVisitor
import visitor.PrintVisitor
import java.lang.Exception

fun main() {
    try {
        val line = readLine() ?: throw IllegalArgumentException("No input line")
        val tokens = Tokenizer().tokenize(line)

        val printVisitor = PrintVisitor()
        tokens.forEach { it.accept(printVisitor) }
        println()

        val parserVisitor = ParserVisitor()
        tokens.forEach { it.accept(parserVisitor) }
        val parsedTokens = parserVisitor.dump()
        parsedTokens.forEach { it.accept(printVisitor) }
    } catch (e : Exception) {
        println("Error: ${e.message}")
    }
}