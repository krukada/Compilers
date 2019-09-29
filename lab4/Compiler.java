package ru.bmstu.iu.zagitova;
import java.util.*;

public class Compiler {
    private SortedMap<Position,Message> messages;
    private HashMap<String,Integer> nameCodes;
    private List<String> names;

    public Compiler(){
        messages = new TreeMap<>();
        nameCodes = new HashMap<>();
        names = new ArrayList<>();
    }

    public int AddName(String name){
        if (nameCodes.containsKey(name))
            return nameCodes.get(name);
        else {
            int code = names.size();
            names.add(name);
            nameCodes.put(name, code);
            return code;
        }
    }
    public String getName(int code){
        return names.get(code);
    }
    public void addMessage(Boolean isErr,Position c,String text){
        messages.put(c, new Message(isErr,text));
    }
    public  void outputMessages(){

        System.out.println("\nMESSAGES:\n");

        for (Map.Entry<Position, Message> entry : messages.entrySet()) {
            System.out.print(entry.getValue().isError ? "Error" : "Message");
            System.out.print(" " + entry.getKey() + ": ");
            System.out.println(entry.getValue().text);

        }
    }
    public ScannerCompil getScanner(String program){
        return new ScannerCompil(program, this);
    }

}
