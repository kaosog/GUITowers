package com.example.guitowers;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.DirectionalLight;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.input.MouseEvent.MOUSE_EXITED;
import static javafx.scene.input.MouseEvent.MOUSE_RELEASED;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Button largest = new Button();                              //tower 3
        largest.setStyle("-fx-background-color: MediumSeaGreen");
        largest.setMinHeight(50);
        largest.setMinWidth(200);
        largest.setLayoutY(100);


        Button middle = new Button();                               //tower 2
        middle.setStyle("-fx-background-color: Red");
        middle.setMinHeight(50);
        middle.setMinWidth(125);
        middle.setLayoutY(50);
        middle.setLayoutX(35);


        Button smallest = new Button();                             //tower 1
        smallest.setStyle("-fx-background-color: Yellow");
        smallest.setMinHeight(50);
        smallest.setMinWidth(75);
        smallest.setLayoutY(0);
        smallest.setLayoutX(60);





        VBox test2 = new VBox();                            //tower 2
        VBox test = new VBox(smallest, middle, largest);    //tower 1
        VBox test3 = new VBox();                            //tower 3
        test2.setMinSize(200, 150);
        test3.setMinSize(200, 150);
        test.setMinSize(200, 150);
        test.setAlignment(Pos.BOTTOM_CENTER);
        test2.setAlignment(Pos.BOTTOM_CENTER);
        test3.setAlignment(Pos.BOTTOM_CENTER);

        Pane winner = new Pane();
        Pane directions = new Pane();

        Text winnerText = new Text(50,50,"WINNER");
        winnerText.setLayoutX(0);
        winnerText.setLayoutY(450);
        winnerText.setFill(Color.DARKSLATEBLUE);
        winnerText.setFont(Font.font ("Verdana", 200));

        Text dir = new Text(50,50,"Drag and Drop till all 3 are on tower 3");
        directions.getChildren().add(dir);
        directions.setLayoutX(450);
        dir.setLayoutY(400);

        FlowPane holder = new FlowPane(test, test2, test3,winner,directions);   //holder for all panes
        BackgroundFill backgroundFill =
                new BackgroundFill(
                        Color.BLACK,
                        new CornerRadii(10),
                        new Insets(1)
                );
        holder.setBackground(new Background(backgroundFill));
        Scene scene = new Scene(holder, 900, 900);
        stage.setTitle("Tricky Hanoi 3 disks");
        stage.setScene(scene);
        stage.show();


        largest.setOnMouseReleased(e -> {
            if(!(largest.getParent().equals(middle.getParent())||largest.getParent().equals(smallest.getParent()))) {//checks if anything is above
                double temp = largest.getScaleX();
                if (e.getEventType() == MOUSE_RELEASED) {
                    if (e.getSceneX() <= 200 && !(test.getChildren().contains(middle) || test.getChildren().contains(smallest))) {//checks if it can move and moves if possible
                        test2.getChildren().remove(largest);
                        test3.getChildren().remove(largest);
                        if (!test.getChildren().contains(largest)) test.getChildren().add(largest);
                    } else if (e.getSceneX() <= 400 && !(test2.getChildren().contains(middle) || test2.getChildren().contains(smallest))) {
                        test.getChildren().remove(largest);
                        test3.getChildren().remove(largest);
                        if (!test2.getChildren().contains(largest)) test2.getChildren().add(largest);
                    } else if (e.getSceneX() <= 600 && !(test3.getChildren().contains(middle) || test3.getChildren().contains(smallest))) {
                        test.getChildren().remove(largest);
                        test2.getChildren().remove(largest);
                        if (!test3.getChildren().contains(largest)) test3.getChildren().add(largest);
                    } else largest.setLayoutX(temp);
                }
            }
        });
        largest.setOnMouseDragged(y -> {
            largest.setLayoutX(y.getSceneX());
            largest.setLayoutY(y.getSceneY());
        });


        middle.setOnMouseReleased(e -> {
            double temp = middle.getScaleX();
            if(!(middle.getParent().equals(smallest.getParent()))) {    //checks if any are above
                if (e.getEventType() == MOUSE_RELEASED) {
                    if (e.getSceneX() <= 200 && !(test.getChildren().contains(smallest))) { //checks if it can move and moves if possible
                        test2.getChildren().remove(middle);
                        test3.getChildren().remove(middle);
                        if (!test.getChildren().contains(middle)) {
                            test.getChildren().add(middle);
                            if (test.getChildren().contains(largest)) {
                                test.getChildren().remove(largest);
                                test.getChildren().add(largest);
                            }
                        }
                    } else if (e.getSceneX() <= 400 && !(test2.getChildren().contains(smallest))) {
                        test.getChildren().remove(middle);
                        test3.getChildren().remove(middle);
                        if (!test2.getChildren().contains(middle)) {
                            test2.getChildren().add(middle);
                        }
                        if (test2.getChildren().contains(largest)) {
                            test2.getChildren().remove(largest);
                            test2.getChildren().add(largest);
                        }
                    } else if (e.getSceneX() <= 600 && !(test3.getChildren().contains(smallest))) {
                        test.getChildren().remove(middle);
                        test2.getChildren().remove(middle);
                        if (!test3.getChildren().contains(middle)) {
                            test3.getChildren().add(middle);
                            if (test3.getChildren().contains(largest)) {
                                test3.getChildren().remove(largest);
                                test3.getChildren().add(largest);
                            }
                        }
                    } else middle.setLayoutX(temp);
                }
            }
        });
        middle.setOnMouseDragged(y -> {
            middle.setLayoutX(y.getSceneX());
            middle.setLayoutY(y.getSceneY());
        });


        smallest.setOnMouseReleased(e -> {
            double temp = smallest.getScaleX();
            if (e.getEventType() == MOUSE_RELEASED) {
                if (e.getSceneX() <= 200) {
                    test2.getChildren().remove(smallest);
                    test3.getChildren().remove(smallest);
                    if (!test.getChildren().contains(smallest)) {
                        test.getChildren().add(smallest);
                        if (test.getChildren().contains(middle)) {
                            test.getChildren().remove(middle);
                            test.getChildren().add(middle);
                        }
                        if (test.getChildren().contains(largest)) {   //fixing alignment
                            test.getChildren().remove(largest);
                            test.getChildren().add(largest);
                        }
                    }

                } else if (e.getSceneX() <= 400) {
                    test.getChildren().remove(smallest);
                    test3.getChildren().remove(smallest);
                    if (!test2.getChildren().contains(smallest)) {
                        test2.getChildren().add(smallest);
                        if (test2.getChildren().contains(middle)) {
                            test2.getChildren().remove(middle);
                            test2.getChildren().add(middle);
                        }
                        if (test2.getChildren().contains(largest)) {   //fixing alignment
                            test2.getChildren().remove(largest);
                            test2.getChildren().add(largest);
                        }
                    }
                } else if (e.getSceneX() <= 600) {
                    test.getChildren().remove(smallest);
                    test2.getChildren().remove(smallest);
                    if (!test3.getChildren().contains(smallest)) {
                        test3.getChildren().add(smallest);
                        if (test3.getChildren().contains(middle)) {
                            test3.getChildren().remove(middle);
                            test3.getChildren().add(middle);
                        }
                        if (test3.getChildren().contains(largest)) {   //fixing alignment
                            test3.getChildren().remove(largest);
                            test3.getChildren().add(largest);
                        }
                    }
                } else smallest.setLayoutX(temp);
            }
            if(test3.getChildren().contains(largest) && test3.getChildren().contains(smallest) && test3.getChildren().contains(middle)){
                winner.getChildren().add(winnerText);

            }
        });

        smallest.setOnMouseDragged(y -> {
            smallest.setLayoutX(y.getSceneX());
            smallest.setLayoutY(y.getSceneY());

        });
    }




        public static void main (String[]args){
            launch();
        }
    }