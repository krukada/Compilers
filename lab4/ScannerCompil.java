package ru.bmstu.iu.zagitova;



public class ScannerCompil {
    public final String program;
    private Compiler compiler;
    private Position cur;

    public ScannerCompil(String program, Compiler compiler){
        this.compiler = compiler;
        this.program = program;
        cur = new Position(program);
    }
    /*идентификаторы - последовательности буквенных символов
    * и цифр, начинающихся с буквы. Числовые литералы - десятичные литералы
    * (последовательность десятичных цифр), 16- начинаются на десятичную,
    * содержат буквы и заканчиваются на h, ключевые слова =  mov. eax*/

    public Token nextToken(){
        while(!cur.isEOF()){
            while (cur.isWhiteSpace())
                cur = cur.next();

            Position start = cur.copy();

            switch (cur.getCp()){
                default:
                    if (cur.isLetter() && !cur.isWhiteSpace()){
                        String curWord = "";
                        while (cur.isLetterOrDigit()) {
                            curWord += (char) (cur.getCp());
                            cur.next();
                            if (curWord.equals("mov") || curWord.equals("eax")) {
                                compiler.addMessage(false, start, "Найдено ключевое слово!");
                                return new KeyWordToken(curWord, start, cur);
                            }
                        }
                        String m = "";
                        for (int i=0;i<curWord.length();i++) {
                            if (curWord.charAt(i) >= '0' && curWord.charAt(i) <= '9') {
                                m += curWord.charAt(i);
                            }
                        }
                            if (m.length() > 0){
                                compiler.addMessage(false,start,"Найден правильный идентификатор");
                                return new IdentToken(curWord, start, cur);
                            } else if (m.length() <= 0){
                                compiler.addMessage(true,start,"Не правильно введен идентификатор!Не хватает чисел");
                                return new IdentToken(curWord, start, cur);
                            }
                    } else if (cur.isDecimalDigit() || !cur.isWhiteSpace()){
                        String curDecimal = "";

                        while (cur.isLetterOrDigit()){
                            curDecimal += (char) (cur.getCp());
                            cur.next();
                        }
                        int p = 0;
                        for (int i=0;i<curDecimal.length();i++) {
// десятичные цирф
                            if (!(curDecimal.charAt(i) >= 'A' && curDecimal.charAt(i) <= 'Z') &&
                                    !(curDecimal.charAt(i) >= 'a' && curDecimal.charAt(i) <= 'z')) {
                                p++;
                            }
                        }
                        if (curDecimal.length() == p && (cur.getCp() != -1)) {
                            compiler.addMessage(false, start, "Введена десятичная цифра");
                            return new NumericLiteralsToken(curDecimal, start, cur);
                        }
                        int t = 0;
                        int b = 0;
                        //System.out.println(curDecimal);
                        for (int i=0;i<curDecimal.length();i++) {
                            if ((curDecimal.charAt(i) >= 'A' && curDecimal.charAt(i) <= 'F') ||
                                    (curDecimal.charAt(i) >= 'a' && curDecimal.charAt(i) <= 'f')) {
                                t++;

                            }
                        }
                        for (int i=0;i<curDecimal.length();i++){

                            if ((curDecimal.charAt(i) >= 'G' && curDecimal.charAt(i) <= 'Z') ||
                                    (curDecimal.charAt(i) >= 'g' && curDecimal.charAt(i) <= 'z')){
                                b++;
                            }

                        }
                        //System.out.println(t+"  "+b);
                        for (int i=0;i<curDecimal.length();i++){

                            if (curDecimal.charAt(curDecimal.length()-1) == 'h'){
                                if ((t >= 0) && (b == 1)){
                                    compiler.addMessage(false, start, "Шестнадцатиричная цифра");
                                    return new NumericLiteralsToken(curDecimal, start, cur);
                                    } else if ((t >= 0) && (b > 1)){
                                        compiler.addMessage(true, start,
                                                " !Не правильно введена шестнадцатиричная цифра, она может"
                                                        + " включать только 16-цифры!");
                                        return new NumericLiteralsToken(curDecimal, start, cur);
                                    }

                            } else  if (b >= 1 || t >= 0){
                                //System.out.println(curDecimal);
                                compiler.addMessage(true, start, "Не верно введена десятичная цифра, уберите буквы!");
                                return new NumericLiteralsToken(curDecimal, start, cur);
                            }

                        }

                    }
                 break;
            }
            cur.next();
        }
        return new UnknownToken(DomainTag.END_OF_PROGRAM,cur,cur);
        //return null;
    }

}
