package token

import visitor.TokenVisitor

sealed class Token {
    abstract fun accept(tokenVisitor: TokenVisitor)
}

data class NumberToken(val value: Int) : Token() {
    override fun accept(tokenVisitor: TokenVisitor) {
        tokenVisitor.visit(this)
    }

    override fun toString(): String {
        return "NUMBER($value)"
    }
}

sealed class OperationToken : Token() {
    override fun accept(tokenVisitor: TokenVisitor) {
        tokenVisitor.visit(this)
    }

    abstract val apply: (Int, Int) -> Int
}

object PlusToken : OperationToken() {
    override val apply = { a: Int, b: Int -> a + b }

    override fun toString(): String {
        return "PLUS"
    }
}

object MinusToken : OperationToken() {
    override val apply = { a: Int, b: Int -> a - b }

    override fun toString(): String {
        return "MINUS"
    }
}

object MulToken : OperationToken() {
    override val apply = { a: Int, b: Int -> a * b }

    override fun toString(): String {
        return "MUL"
    }
}

object DivToken : OperationToken() {
    override val apply = { a: Int, b: Int -> a / b }

    override fun toString(): String {
        return "DIV"
    }
}

sealed class BracketToken : Token() {
    override fun accept(tokenVisitor: TokenVisitor) {
        tokenVisitor.visit(this)
    }
}

object LeftBracketToken : BracketToken() {
    override fun toString(): String {
        return "LEFT"
    }
}

object RightBracketToken : BracketToken() {
    override fun toString(): String {
        return "RIGHT"
    }
}
