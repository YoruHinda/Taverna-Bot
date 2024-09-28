package com.github.yoruhinda.tavernabot.model;

public class Drink {
    private int id;
    private String Drink_Name;

    public Drink(String drink_Name) {
        Drink_Name = drink_Name;
    }

    public Drink(int id, String drink_name) {
        this.Drink_Name = drink_name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDrink_Name() {
        return Drink_Name;
    }

    public void setDrink_Name(String drink_Name) {
        Drink_Name = drink_Name;
    }
}
