package com.tecnoactive.awake;


public class Message {

    private String start_date;
    private String asunto;
    private String content;
    private boolean read;

    public Message(String start_date, String asunto, String content) {
        this.start_date = start_date;
        this.asunto = content;
        this.content = content;
        this.read = false;
    }

    public String getDate() {
        return start_date;
    }

    public void setDate(String date) {
        this.start_date = date;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
}
