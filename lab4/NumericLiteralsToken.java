package ru.bmstu.iu.zagitova;

public class NumericLiteralsToken extends Token{
    public final String value;
    public NumericLiteralsToken(String value,Position starting, Position following){
        super(DomainTag.NUMERIC_LITERALS,starting,following);
        this.value = value;
    }
    @Override
    public String toString(){
        return  "NUMERIC_LITERALS"+super.toString() + ": " + value;
    }
}
