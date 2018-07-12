package com.app.myproject.models;

/**
 * Created by minhaj nadeem on 12/07/2018.
 */
public class MyModel {

    private String number;
    private boolean isSelected;

    public MyModel(String number,boolean isSelected){
        setNumber(number);
        setSelected(isSelected);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
