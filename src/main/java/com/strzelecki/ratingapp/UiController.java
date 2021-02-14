package com.strzelecki.ratingapp;

import com.strzelecki.ratingapp.Model.Category;
import javafx.application.HostServices;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class UiController implements Initializable {



    private final HostServices hostServices;

    @FXML
    public Label label;

    @FXML
    public Button button;


    @FXML
    ListView listView;


    public UiController(HostServices hostServices) {
        this.hostServices = hostServices;
    }

//    @FXML
//    public void initialize () {
//
////        ========== wypełnienie listview checkboxami ==========
//
//        List<Category> categories = Arrays.asList(Category.values());
//                categories.stream().forEach(e -> {
//                   listView.getItems().add(e.name);
//                    System.out.println(e.name);
//                });
//
//
//        listView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
//            @Override
//            public ObservableValue<Boolean> call(String item) {
//                BooleanProperty observable = new SimpleBooleanProperty();
//                observable.addListener((obs, wasSelected, isNowSelected) ->
//                        System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected)
//                );
//                return observable ;
//            }
//        }));
//
//
//
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView = new ListView();
        List<Category> categories = Arrays.asList(Category.values());
        categories.stream().forEach(e -> {
            listView.getItems().add(e.name);
            System.out.println(e.name);
        });

        System.out.println(listView);


        listView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(String item) {
                System.out.println("sth");
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) ->
                        System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected)
                );
                return observable ;
            }
        }));
    }
}
