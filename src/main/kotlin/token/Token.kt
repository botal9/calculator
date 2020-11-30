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

    abstract fun apply(a: Int, b: Int): Int
    abstract val priority: Int
}

object PlusToken : OperationToken() {
    override fun apply(a: Int, b: Int) = a + b
    override val priority = 1

    override fun toString(): String {
        return "PLUS"
    }
}

object MinusToken : OperationToken() {
    override fun apply(a: Int, b: Int) = a - b
    override val priority = 1

    override fun toString(): String {
        return "MINUS"
    }
}

object MulToken : OperationToken() {
    override fun apply(a: Int, b: Int) = a * b
    override val priority = 2

    override fun toString(): String {
        return "MUL"
    }
}

object DivToken : OperationToken() {
    override fun apply(a: Int, b: Int) = a / b
    override val priority = 2

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
