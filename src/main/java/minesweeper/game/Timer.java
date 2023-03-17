package minesweeper.game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Timer extends Label {

    private static final int INITIAL = 0;
    private static final int RUNNING = 1;
    private static final int STOPPED = -1;

    private Integer startingTime;
    private IntegerProperty remainingTime;
    private Timeline timeline;
    private Integer status;

    /**
     * Creates an animated countdown timer label. 
     * 
     * @param   seconds the desired duration of the countdown (in seconds)
     *                  
     */
    Timer(Integer seconds) {
        startingTime = seconds;
        remainingTime = new SimpleIntegerProperty(startingTime);
        status = INITIAL;
        setText(startingTime.toString());
        textProperty().bind(remainingTime.asString());
    }

    /**
     * Starts the countdown, from the given starting time until 0, 
     * decreasing by 1 every second.
     */
    public void start() {
        if (status == INITIAL) {
            remainingTime.set(startingTime);
            timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(
                Duration.seconds(startingTime + 1),
                new KeyValue(remainingTime, 0)
            ));
            timeline.playFromStart();
            status = RUNNING;
        }
    }

    /**
     * Stops the countdown.
     */
    public void stop() {
        if (timeline != null) {
            timeline.stop();
            status = STOPPED;
        }
    }

    /**
     * Returns a true-false statement depending on whether the timer has started (false) or not (true).
     * 
     * @return true if the timer's status is the initial, false otherwise 
     */
    public boolean notStarted() {
        return (status == INITIAL);
    }

    /**
     * Returns a true-false statement depending on whether the timer has stopped (true) or not (false).
     * 
     * @return true if the timer is stopped, false otherwise 
     */
    public boolean isStopped() {
        return (status == STOPPED);
    }

    /**
     * Returns a true-false statement depending on whether the timer is running (true) or not (not).
     * 
     * @return true if the timer's status is the initial, false otherwise 
     */
    public boolean isRunning() {
        return (status == RUNNING);
    }
}