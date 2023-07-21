package com.example.drive_fit_.modelClass;

public class workout {
    String link;
    String name;

    public workout()
    {

    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public workout(String link, String name) {
        this.link = link;
        this.name = name;
    }
}
