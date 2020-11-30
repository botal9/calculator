package token

import java.lang.UnsupportedOperationException

class Tokenizer {
    private var tokens = ArrayList<Token>()
    private var stateMachine: State = BeginState()

    fun tokenize(expr: String): List<Token> {
        stateMachine = BeginState()
        tokens.clear()

        expr.forEach { stateMachine.traverse(it) }
        stateMachine.terminate()
        require(stateMachine is EndState)

        return tokens
    }

    private interface State {
        fun traverse(c: Char)
        fun terminate()
    }

    private inner class BeginState : State {
        override fun traverse(c: Char) {
            when (c) {
                '+' -> this@Tokenizer.tokens.add(PlusToken)
                '-' -> this@Tokenizer.tokens.add(MinusToken)
                '*' -> this@Tokenizer.tokens.add(MulToken)
                '/' -> this@Tokenizer.tokens.add(DivToken)
                '(' -> this@Tokenizer.tokens.add(LeftBracketToken)
                ')' -> this@Tokenizer.tokens.add(RightBracketToken)
                in '0'..'9' -> {
                    this@Tokenizer.stateMachine = NumberState()
                    this@Tokenizer.stateMachine.traverse(c)
                }
                else -> {
                    require(c.isWhitespace()) { "Unexpected character $c" }
                }
            }
        }

        override fun terminate() {
            this@Tokenizer.stateMachine = EndState()
        }
    }

    private inner class NumberState : State {
        private var number = 0

        override fun traverse(c: Char) {
            when (c) {
                in '0'..'9' -> {
                    number = number * 10 + (c - '0')
                }
                else -> {
                    this@Tokenizer.tokens.add(NumberToken(number))
                    this@Tokenizer.stateMachine = BeginState()
                    this@Tokenizer.stateMachine.traverse(c)
                }
            }
        }

        override fun terminate() {
            this@Tokenizer.tokens.add(NumberToken(number))
            this@Tokenizer.stateMachine = EndState()
        }
    }

    private inner class EndState : State {
        override fun traverse(c: Char) {
            throw UnsupportedOperationException("Trying to perform traverse at EndState")
        }

        override fun terminate() { }
    }
}
