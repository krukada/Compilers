package ru.bmstu.iu.zagitova;

public class UnknownToken extends Token {

    public UnknownToken(DomainTag tag, Position starting, Position following) {
        super(tag, starting, following);
    }
}