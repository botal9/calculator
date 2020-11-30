package visitor

import token.BracketToken
import token.NumberToken
import token.OperationToken
import java.util.*

class CalcVisitor : TokenVisitor {
    private val tokenStack = Stack<Int>()

    fun calculate(): Int {
        check(tokenStack.size == 1) {
            "Expected exactly one element in stack after calculation, found ${tokenStack.size}"
        }
        return tokenStack.pop()
    }

    override fun visit(token: NumberToken) {
        tokenStack.push(token.value)
    }

    override fun visit(token: BracketToken) {
        throw IllegalArgumentException("No brackets allowed in parsed expression. Use ParserVisitor")
    }

    override fun visit(token: OperationToken) {
        check(tokenStack.size >= 2) { "Expected at least two elements in stack for operation $token" }
        val b = tokenStack.pop()
        val a = tokenStack.pop()
        tokenStack.push(token.apply(a, b))
    }
}
