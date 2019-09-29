package ru.bmstu.iu.zagitova;

import java.io.File;
import java.util.Scanner;

public class Main{

    public static void main(String args[]) {

        String text = "";
        Scanner sc;

        try {
            sc = new Scanner(new File("/Users/adelinazagitova/IdeaProjects/Lexer/src/ru/bmstu/iu/zagitova/text"));
        } catch (java.io.FileNotFoundException e) {
            System.out.println(e.toString());
            return;
        }

        int i = 0;
       while (sc.hasNextLine()) {
            String line = sc.nextLine();
            text += line + "\n";
            i++;
            System.out.println(i + " [" + line + "]");
        }

        Compiler compiler = new Compiler();
        ScannerCompil scanner = new ScannerCompil(text, compiler);

        System.out.println();
        System.out.println("TOKENS:\n");

        Token t;
        do {
            t = scanner.nextToken();
            System.out.println(t);
        } while (t.tag != DomainTag.END_OF_PROGRAM);

        compiler.outputMessages();
    }

}