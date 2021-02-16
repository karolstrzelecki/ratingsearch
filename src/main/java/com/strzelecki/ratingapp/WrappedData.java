package com.strzelecki.ratingapp;

import javafx.beans.property.SimpleStringProperty;

public class WrappedData {
    SimpleStringProperty title;
    SimpleStringProperty hIndexSciMagor;

    public WrappedData(SimpleStringProperty title, SimpleStringProperty hIndexSciMagor) {
        this.title = title;
        this.hIndexSciMagor = hIndexSciMagor;
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String gethIndexSciMagor() {
        return hIndexSciMagor.get();
    }

    public SimpleStringProperty hIndexSciMagorProperty() {
        return hIndexSciMagor;
    }

    public void sethIndexSciMagor(String hIndexSciMagor) {
        this.hIndexSciMagor.set(hIndexSciMagor);
    }
}
