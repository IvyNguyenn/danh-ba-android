package com.example.ivyng.danhba.domain;

/**
 * Created by ivyng on 03/20/2018.
 */

public class Contact {
    private boolean avatar;
    private String name;
    private String number;

    public Contact(boolean avatar, String name, String number) {
        this.avatar = avatar;
        this.name = name;
        this.number = number;
    }

    public boolean getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setAvatar(boolean avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
