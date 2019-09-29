package ru.bmstu.iu.zagitova;

public enum  DomainTag {
    IDENT(0),
    KEY_WORD(1),
    NUMERIC_LITERALS(2),
    END_OF_PROGRAM(3);

    private final int val;
    DomainTag(int val){
        this.val = val;
    }
    public int getVal(){
        return val;
    }
}
