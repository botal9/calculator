package visitor

import token.BracketToken
import token.NumberToken
import token.OperationToken

class PrintVisitor : TokenVisitor {
    override fun visit(token: NumberToken) {
        print("$token ")
    }

    override fun visit(token: BracketToken) {
        print("$token ")
    }

    override fun visit(token: OperationToken) {
        print("$token ")
    }
}