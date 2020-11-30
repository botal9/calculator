import token.Tokenizer
import visitor.CalcVisitor
import visitor.ParserVisitor
import visitor.PrintVisitor
import java.lang.Exception

fun main() {
    try {
        val line = readLine() ?: throw IllegalArgumentException("No input line")
        val tokens = Tokenizer().tokenize(line)

        val printVisitor = PrintVisitor()
        val parserVisitor = ParserVisitor()
        val calcVisitor = CalcVisitor()

        tokens.forEach { it.accept(parserVisitor) }
        val parsedTokens = parserVisitor.dump()
        parsedTokens.forEach { it.accept(printVisitor) }
        println()

        parsedTokens.forEach { it.accept(calcVisitor) }
        println("Calculated: ${calcVisitor.calculate()}")
    } catch (e : Exception) {
        println("Error: ${e.message}")
    }
}
