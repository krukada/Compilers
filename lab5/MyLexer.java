package ade.iu.bmstu;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class MyLexer {
    static Integer[][] table = {
                /* Wh,Di, -, +, :, n, e, q,Lt, X,\n */
            /*0 */{ 1,6, 9, 7,10, 5, 3, 4, 2,12, 13},
            /*1 */{ 1,-1,-1,-1,-1,-1,-1,-1,-1,12,-1},
            /*2 */{-1, 2,-1,-1,-1, 2, 2, 2, 2,12,-1},
            /*3 */{-1, 2,-1,-1,-1, 2, 2, 4, 2,12,-1},
            /*4 */{-1, 2,-1,-1,-1, 2, 2, 2, 2,12,-1},
            /*5 */{-1, 2,-1,-1,-1, 2, 3, 2, 2,12,-1},
            /*6 */{-1, 6,-1,-1,-1,-1,-1,-1,-1,12,-1},
            /*7 */{-1,-1, 8,-1,-1,-1,-1,-1,-1,12,-1},
            /*8 */{-1,-1,-1,-1,-1,-1,-1,-1,-1,12,-1},
            /*9 */{-1,-1,-1, 8,-1,-1,-1,-1,-1,12,-1},
            /*10*/{10,10,-1,-1,11,10,10,10,10,12,-1},//:
            /*11*/{-1,-1,-1,-1,10,-1,-1,-1,-1,12,-1},//::
            /*12*/{-1,-1,-1,-1,-1,-1,-1,-1,-1,12,-1},
            /*13*/{-1,-1,-1,-1,-1,-1,-1,-1,-1,12, 0},
    };
    static int numberColumn(Position ch){
        if (ch.isEOF())
            return -1;
        else if (ch.isWhitespace()) {
            return 0;
        }
        else if (Character.isDigit(ch.getCode())) {
            return 1;
        }
        else if (ch.getCode() == '-') {
            return 2;
        }
        else if (ch.getCode() == '+') {
            return 3;
        }
        else if (ch.getCode() == ':') {
            return 4;
        }
        else if (ch.getCode() == 'n') {
            return 5;
        }
        else if (ch.getCode() == 'e') {
            return 6;
        }
        else if (ch.getCode() == 'q') {
            return 7;
        }
        else if (ch.getCode() == '\n') {
            return 13;
        }

        else if (Character.isLetter(ch.getCode())) {
            return 8;
        }
        else {
            return 9;
        }
    }
    public static void main(String[] args) throws IOException {

        String text_program = new String(Files.readAllBytes(Paths.get(
                "/Users/adelinazagitova/IdeaProjects/LexerTable/src/ade/iu/bmstu/test")));

        Position cur = new Position(text_program);

        ArrayList<Token> tokens = new ArrayList<>();
        ArrayList<ErrorMessage> errorMessages = new ArrayList<>();

        Position start,end;

        int state,prev_state;

        System.out.println("\nTOKENS:\n");

        while(!cur.isEOF()){

            while (cur.isWhitespace())
                cur = cur.next();
            state = 0;
            prev_state = 0;
            start = new Position(cur);
            while (state != -1) {
                int symbols = numberColumn(cur);
                prev_state = state;
                state = (symbols == -1) ? -1: table[state][symbols];
                if(state!=-1){
                    cur = cur.next();
                }
            }
            if (prev_state!=1 && prev_state!=0 && prev_state!= 15){
                end = new Position(cur);
                if (prev_state == 10)
                    errorMessages.add(new ErrorMessage(true,
                            "error:  Незавершенный литерал на строке ",cur));

                else if (prev_state == 7|| prev_state == 9){
                    errorMessages.add(new ErrorMessage(true,
                            "error:  Не верно введен оператор! ",cur));

                } else if (prev_state == 12){
                    errorMessages.add(new ErrorMessage(true,
                            "error: Неизвестный токен \"" + text_program.substring(start.getIndex(),end.getIndex()) + "\"",cur));
                } else
                    tokens.add(new Token(DomainTag.values()[prev_state],
                            text_program.substring(start.getIndex(),end.getIndex()),start,end));
            }
            else {
                while (!cur.isEOF() && numberColumn(cur) == -1) {
                    cur = cur.next();
                }
                end = new Position(cur);
                errorMessages.add(new ErrorMessage(true,
                        "error: Неизвестный токен \"" + text_program.substring(start.getIndex(),end.getIndex()) + "\"",cur));
            }
        }

        for(Token token: tokens)
            System.out.println(token);

        if (!errorMessages.isEmpty()) {
            System.out.println("\nERRORS:\n");
            for(ErrorMessage mes: errorMessages)
                System.out.println(mes.toString());
        }
    }
}
