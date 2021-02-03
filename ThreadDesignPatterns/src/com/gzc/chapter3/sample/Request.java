package com.gzc.chapter3.sample;

public class Request {
    private final String name;

    public Request(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ Request " + name + " ]";
    }
}
