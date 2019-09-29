
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



class Position {
    private int line, pos;

    Position(int line, int pos){
        this.line = line;
        this.pos = pos;
    }
    void nextLine(){
        this.line ++;
        this.pos = 1;
    }
    void nextPos(int pos){
        this.pos += pos;
    }
    public String toString(){
        return "( " + this.line + "," + this.pos + ")";
    }
    Position copy() {
        return new Position(line, pos);
    }
}

enum Tag { OPER, KEYWORD, IDENT, COMMENT, EOF }

class Token {
    Tag tag;
    Position pos;
    String attr;

    Token(Tag tag,Position pos,String attr){
        this.tag = tag;
        this.pos = pos;
        this.attr = attr;

    }

    @Override
    public String toString() {
        return tag.toString() + pos.toString() + ": " + attr;
    }
}

public class Main {

    private static ArrayList<Token> testMatch(String line,Position pos) {

        String keyWord = "((?i)FOR|(?i)NEXT)";
        String leter = "\\p{L}(:?\\p{L}|0-9)*";
        String ident = leter + "[$@%!]?";
        String oper = "[+\\-/\\\\]";
        String comment = "((?i)REM)[^\\p{L}0-9].*";

        String pattern = "(?<comment>^" + comment + ")|(?<ident>^" + ident + ")|(?<keyword>^" + keyWord + ")|(?<oper>^" + oper + ")";

        Pattern p = Pattern.compile(pattern);
        Matcher m;

        ArrayList<Token> tokens = new ArrayList<>();

        while (!line.equals("")) {
            m = p.matcher(line);
            if (m.find()) {
                if (m.group("oper") != null) {
                    String item = m.group("oper");
                    line = line.substring(line.indexOf(item) + item.length());
                    pos.nextPos(item.length());
                    tokens.add(new Token(Tag.OPER, pos.copy(), item));

                } else  if (m.group("keyword") != null) {
                    String item = m.group("keyword");
                    line = line.substring(line.indexOf(item) + item.length());
                    pos.nextPos(item.length());
                    tokens.add(new Token(Tag.KEYWORD, pos.copy(), item));

                } else  if (m.group("ident") != null) {
                    String item = m.group("ident");
                    line = line.substring(line.indexOf(item) + item.length());
                    pos.nextPos(item.length());
                    tokens.add(new Token(Tag.IDENT, pos.copy(), item));

                } else  if (m.group("comment") != null) {
                    String item = m.group("comment");
                    line = line.substring(line.indexOf(item) + item.length());
                    pos.nextPos(item.length());
                    tokens.add(new Token(Tag.COMMENT, pos.copy(), item));
                }
                //обработка space

            } else if (Character.isWhitespace(line.charAt(0))){
                while(Character.isWhitespace(line.charAt(0))){
                    line = line.substring(1);
                    pos.nextPos(1);
                }
            } else tokens.add(new Token(Tag.EOF,pos," syntax error "));
              while (! m.find() && ! line.equals("")){
                    line = line.substring(1);
                    pos.nextPos(1);
                    m = p.matcher(line);

            }

        }
        return tokens;

    }
    public static void main(String[] args) {


        Position pos = new Position(1,1);
        List<String> lines;
        List<Token> all_tokens = new ArrayList<>();

        String fileName = "/Users/adelinazagitova/IdeaProjects/ReGex/src/tokens";
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            for (String line : lines) {
                all_tokens.addAll(testMatch(line,pos));
                pos.nextLine();
            }
            for (Token t : all_tokens) {
                System.out.println(t);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}