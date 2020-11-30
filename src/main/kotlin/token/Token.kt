package token

sealed class Token

data class NumberToken(val value: Int) : Token() {
    override fun toString(): String {
        return "NUMBER($value)"
    }
}

sealed class OperationToken : Token() {
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

sealed class BracketToken : Token()

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
