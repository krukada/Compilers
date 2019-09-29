import java.io.File

fun main(args : Array<String>) {
    var path = "/Users/adelinazagitova/IdeaProjects/lab8compilers/src/test"
    val lexer = Lexer(File(path).readText())
    var tokens = listOf<Token>()
    while (true) {
        val token = lexer.nextToken()
        tokens = tokens.plusElement(token)
        if (token.tag != DomainTag.EndOfProgram)

        else {
            println("OK")
            break
        }
    }
    val parser = Parser(tokens)
    parser.parse()
    val first = First(parser.mapRule)
    first.setFirst()
    first.printFirst()
}