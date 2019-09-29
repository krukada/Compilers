package ru.bmstu.iu.zagitova;

public class IdentToken extends Token{
    public final String code;
    public IdentToken(String code,Position starting, Position following){
        super(DomainTag.IDENT,starting,following);
        this.code = code;
    }
    @Override
    public String toString(){
        return "IDENT " + super.toString() + ": " + code;
    }
}
