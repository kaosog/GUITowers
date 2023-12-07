package com.example.guitowers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Button largest = new Button();
        largest.setStyle("-fx-background-color: MediumSeaGreen");
        largest.setMinHeight(50);
        largest.setMinWidth(200);
        largest.setLayoutY(100);


        Button middle = new Button();
        middle.setStyle("-fx-background-color: Red");
        middle.setMinHeight(50);
        middle.setMinWidth(125);
        middle.setLayoutY(50);
        middle.setLayoutX(35);



        Button smallest = new Button();
        smallest.setStyle("-fx-background-color: Yellow");
        smallest.setMinHeight(50);
        smallest.setMinWidth(75);
        smallest.setLayoutY(0);
        smallest.setLayoutX(60);




        //actions
//        largest.setOnAction(e-> {
//            if (!(middle.getLayoutX() > largest.getLayoutX()) && !(smallest.getLayoutX() > largest.getLayoutX())) {
//                if (largest.getLayoutX() == 0 && middle.getLayoutX()!=335&&smallest.getLayoutX()!=335) {
//                    largest.setLayoutX(300);
//                } else if (largest.getLayoutX() == 300 && middle.getLayoutX()!=635 &&smallest.getLayoutX()!=635) {
//                    largest.setLayoutX(600);
//                } else if(middle.getLayoutX()!=35&&smallest.getLayoutX()!=60) largest.setLayoutX(0);
//            }
//        });
//
//        middle.setOnAction(e->{
//            if(middle.getLayoutX()==35){
//                middle.setLayoutX(335);
//            }else if(middle.getLayoutX()==335){
//                middle.setLayoutX(635);
//            }else middle.setLayoutX(35);
//        });
//
//        smallest.setOnAction(e->{
//            if(smallest.getLayoutX()==60){
//                smallest.setLayoutX(360);
//            }else if(smallest.getLayoutX()==360){
//                smallest.setLayoutX(660);
//            }else smallest.setLayoutX(60);
//        });


        Pane test2 = new Pane();
        Pane test = new Pane(smallest,largest,middle);
        Pane test3 = new Pane();

        test.setLayoutX(0);
        test2.setLayoutX(300);
        test3.setLayoutX(600);
        FlowPane holder = new FlowPane(test2,test,test3);
        Scene scene = new Scene(holder, 900, 900);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        largest.setOnAction((e->{
        test.getChildren().remove(largest);
        test2.getChildren().add(largest);
        }));
        middle.setOnAction(e->{
            test.getChildren().remove(middle);
            test3.getChildren().add(middle);
                }
                );
    }

    public static void main(String[] args) {

        launch();
    }
}