package com.example.universityinfo;


public class Uni{
    private String name;
    private String webPages;
    private String domain;

    public Uni() {
    }

    public Uni(String name, String webPages, String domain) {
        this.name = name;
        this.webPages = webPages;
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebPages() {
        return webPages;
    }

    public void setWebPages(String webPages) {
        this.webPages = webPages;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}