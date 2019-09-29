package ru.bmstu.iu.zagitova;

public class Message {
    public final Boolean isError;
    public final String text;
    Message(Boolean isError,String text){
        this.isError = isError;
        this.text = text;
    }
}
