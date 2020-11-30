package visitor

import token.BracketToken
import token.NumberToken
import token.OperationToken

interface TokenVisitor {
    fun visit(token: NumberToken)
    fun visit(token: BracketToken)
    fun visit(token: OperationToken)
}
