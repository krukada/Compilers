import kotlin.system.exitProcess


class Parser(val tokens: List<Token>) {
    private var curToken = tokens.first()
    private var ntermsLeft = HashSet<String>()
    private var ntermsRight = HashSet<String>()
    var mapRule = HashMap<String, Rule>() // <left, right>

    fun parse() {
        parseLines()
        mapRule.forEach { key, value -> println("[$key : $value]") }
        if (!ntermsLeft.containsAll(ntermsRight)) {
            println("Undefined non terminal(s)")
            exit()
        }
    }

    private fun nextToken() {
        val index = tokens.indexOf(curToken)
        curToken = tokens[index + 1]
    }

    // Lines = Line {Line}
    private fun parseLines() {
        parseLine()
        while (curToken.tag == DomainTag.Special && curToken.value == "[") {
            parseLine()
        }
        if (curToken.tag != DomainTag.EndOfProgram)
            exit()
    }

    // Line = "[" Expr "]"
    private fun parseLine() {
        if (curToken.tag == DomainTag.Special && curToken.value == "[") {
            nextToken()
            parseExpr()
            if (curToken.tag == DomainTag.Special && curToken.value == "]") {
                nextToken()
                //println("end of line")
            }
            else
                exit()
        } else
            exit()

    }

    // Expr = Term_1 Right {Right}
    private fun parseExpr() {
        val left = curToken.value
        var rule = Rule(RuleTag.Token, null)
        if (curToken.tag == DomainTag.Term_1) {
            ntermsLeft.add(curToken.value)
            nextToken()
            parseRight(rule)
            while (curToken.tag == DomainTag.Special && curToken.value == ":") {
                parseRight(rule)
            }
            mapRule.put(left, rule)
        } else
            exit()
    }

    // Right = ":" {Var}
    private fun parseRight(rule: Rule) {
        if (curToken.tag == DomainTag.Special && curToken.value == ":") {
            rule.addAlternatives()
            nextToken()
            while ((curToken.tag == DomainTag.Special && curToken.value == "[")
                    || curToken.tag == DomainTag.Term_1 || curToken.tag == DomainTag.Term_2) {
                parseVar(rule)
            }
        } else
            exit()

    }

    // Var = "[" Alt "]" ["*"] | Term
    private fun parseVar(rule: Rule) {

        if (curToken.tag == DomainTag.Special && curToken.value == "[") {
            val newRule = Rule(RuleTag.Normal, null)
            newRule.addAlternatives()
            nextToken()
            parseAlt(newRule)
            if (curToken.tag == DomainTag.Special && curToken.value == "]") {
                nextToken()
                var isStar = false
                if (curToken.tag == DomainTag.Special && curToken.value == "*") {
                    isStar = true
                    nextToken()
                }
                if (isStar)
                    newRule.tag = RuleTag.NormalStar
                rule.addRule(newRule)
            }
            else
                exit()
        }
        else {
            parseTerm(rule)
        }
    }

    // Alt = {Var} {Right}
    private fun parseAlt(rule: Rule) {

        while ((curToken.tag == DomainTag.Special && curToken.value == "[")
                || curToken.tag == DomainTag.Term_1 || curToken.tag == DomainTag.Term_2)
            parseVar(rule)
        while (curToken.tag == DomainTag.Special && curToken.value == ":")
            parseRight(rule)
    }

    // Term = (Term_1 | Term_2) ["*"]
    private fun parseTerm(rule: Rule) {

        if (curToken.tag == DomainTag.Term_1 || curToken.tag == DomainTag.Term_2) {
            if (curToken.tag == DomainTag.Term_1)
                ntermsRight.add(curToken.value)
            val tok = curToken
            nextToken()
            var isStar = false
            if (curToken.tag == DomainTag.Special && curToken.value == "*") {
                nextToken()
                isStar = true
            }
            rule.addRule(Rule(if (isStar) RuleTag.TokenStar else RuleTag.Token, tok))
        } else
            exit()
    }

    private fun exit() {
        println("ERROR")
        exitProcess(0)
    }
}