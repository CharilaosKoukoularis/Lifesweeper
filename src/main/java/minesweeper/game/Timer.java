package minesweeper.game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer {

    protected Label label = new Label();
    protected Button button = new Button("Timer");

    protected Integer startingTime;
    protected IntegerProperty remainingTime;
    protected Timeline timeline;

    Timer(Integer seconds) {

        startingTime = seconds;
        remainingTime = new SimpleIntegerProperty(startingTime); 

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // if (timeline != null) {
                //     timeline.stop();
                // }
                remainingTime.set(startingTime);
                timeline = new Timeline();
                timeline.getKeyFrames().add(
                        new KeyFrame(Duration.seconds(startingTime + 1),
                        new KeyValue(remainingTime, 0)));
                timeline.playFromStart();
            }
        });

        label.setText(startingTime.toString());
        label.textProperty().bind(remainingTime.asString());
    }

    Timeline getTimeline() {
        return timeline;
    
    }
}