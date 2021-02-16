package com.strzelecki.ratingapp;

import com.strzelecki.ratingapp.Model.Category;
import com.strzelecki.ratingapp.WrappedData;
import com.strzelecki.ratingapp.Services.PeriodicService;
import javafx.application.HostServices;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Component
public class UiController {

    @Autowired
    PeriodicService periodicService;



//    private final HostServices hostServices;

    @FXML
    public Label label;

    @FXML
    public Button button;


    @FXML
    ListView listView;

    @FXML
    TableView tableView;
    @FXML
    private TableColumn<WrappedData, SimpleStringProperty> title;
    @FXML
    private TableColumn<WrappedData, SimpleStringProperty> hIndexSciMagor;

//@Autowired
//    public UiController(HostServices hostServices) {
//        this.hostServices = hostServices;
//    }


    public UiController() {
    }

    @FXML
    public void initialize() {

//        ========== wypełnienie listview checkboxami ==========

        List<Category> categories = Arrays.asList(Category.values());
        categories.stream().forEach(e -> {
            listView.getItems().add(e.name);
        });


        listView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(String item) {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) ->
                        System.out.println("Check box for " + item + " changed from " + wasSelected + " to " + isNowSelected)
                );
                return observable;
            }
        }));

// wypełnienie tablicy danymi

        ObservableList<WrappedData> data;
        data = FXCollections.observableArrayList(
            periodicService.example().stream().map(x->{
              WrappedData tmp = new WrappedData(new SimpleStringProperty(x.getTitle()), new SimpleStringProperty( x.getHIndexSciMagor()));
              return tmp;
            }).collect(Collectors.toList())
        );

//        System.out.println(data);
//        data.stream().forEach(x->{
//            System.out.println(x.gethIndexSciMagor());
//            System.out.println(x.getTitle());
//        });

        //Creating columns
        title.setCellValueFactory(new PropertyValueFactory<WrappedData, SimpleStringProperty>("title"));
        hIndexSciMagor.setCellValueFactory(new PropertyValueFactory<WrappedData, SimpleStringProperty>("hIndexSciMagor"));

        //Adding data to the table


        ObservableList<String> list = FXCollections.observableArrayList();
        tableView.getItems().setAll(data);

//        periodicService.example().stream().forEach(x->{
//            System.out.println(x);
//        });



        // Wypełnienie listy 20 wynikami z DB




    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        listView = new ListView();
//        List<Category> categories = Arrays.asList(Category.values());
//        categories.stream().forEach(e -> {
//            listView.getItems().add(e.name);
//            System.out.println(e.name);
//        });
//
//        System.out.println(listView);
//
//
//        listView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
//
//            @Override
//            public ObservableValue<Boolean> call(String item) {
//                System.out.println("sth");
//                BooleanProperty observable = new SimpleBooleanProperty();
//                observable.addListener((obs, wasSelected, isNowSelected) ->
//                        System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected)
//                );
//                return observable ;
//            }
//        }));
//    }
//}
}
