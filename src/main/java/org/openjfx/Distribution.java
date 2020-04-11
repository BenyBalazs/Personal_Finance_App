package org.openjfx;

import javafx.scene.control.DatePicker;

 class Distribution {

    String Name;
    Integer Amount;
    Double Percentage;

    public Distribution(String name, Integer amount){
        Name = name;
        Amount = amount;
    }

    public String getName() {
        return Name;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }
}
