package com.dev.ext.sohbetuygulamasi.Models;

public class MessageModel {

    String text;
    String time;
    Boolean seen;
    String type;
    String from;

    public MessageModel()
    {

    }

    public MessageModel(String from, Boolean seen , String text, String time, String type) {

        this.text = text;
        this.time = time;
        this.type = type;
        this.seen = seen;
        this.from=from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
