package com.hit.dm;

public class Request {
    private String header;
    private Object body;

    public Request(String header, Object body) {
        this.header = header;
        this.body = body;
    }

    public String getHeader() {
        return header;
    }

    public Object getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "header: " + this.header + "\n"
                + "body: " + this.body.toString();
    }
}
