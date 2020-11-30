package visitor

import token.*
import java.util.*
import kotlin.collections.ArrayList

class ParserVisitor : TokenVisitor {
    private var tokenStack = Stack<Token>()
    private var parsedTokens = ArrayList<Token>()

    fun dump(): List<Token> {
        pushFromStack()
        return parsedTokens.also { parsedTokens = ArrayList() }
    }

    override fun visit(token: NumberToken) {
        parsedTokens.add(token)
    }

    override fun visit(token: BracketToken) {
        when (token) {
            is LeftBracketToken -> tokenStack.push(token)
            is RightBracketToken -> {
                while (!tokenStack.empty()) {
                    when (val lastToken = tokenStack.peek()) {
                        is LeftBracketToken -> tokenStack.pop()
                        is OperationToken -> {
                            parsedTokens.add(lastToken)
                            tokenStack.pop()
                        }
                        is RightBracketToken, is NumberToken ->
                            throw IllegalStateException("Unexpected token: $lastToken")
                    }
                }
            }
        }
    }

    override fun visit(token: OperationToken) {
        while (!tokenStack.empty()) {
            val lastToken = tokenStack.peek()
            if (lastToken is OperationToken && token.priority <= lastToken.priority) {
                parsedTokens.add(lastToken)
                tokenStack.pop()
            } else {
                break
            }
        }
        tokenStack.push(token)
    }

    private fun pushFromStack() {
        while (!tokenStack.empty()) {
            val lastToken = tokenStack.peek()
            check (lastToken is OperationToken) { "Non-operation token appeared at final stack dump. Stack: $tokenStack" }

            parsedTokens.add(lastToken)
            tokenStack.pop()
        }
    }
}