package ru.bmstu.iu.zagitova;

public class KeyWordToken extends Token{
    public final String value;

    protected KeyWordToken(String value,Position starting,Position following){
        super(DomainTag.KEY_WORD,starting,following);
        this.value = value;
    }
    @Override
    public String toString(){
        return "KEY_WORD " + super.toString() + ": " + value;
    }
}
