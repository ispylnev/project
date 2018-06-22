package sample.animation;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition translateTransition;
    public Shake(Node node){
        translateTransition = new TranslateTransition(Duration.millis(75),node);
        translateTransition.setByX(10f);
        translateTransition.setFromX(0f);
        translateTransition.setCycleCount(3);
        translateTransition.setAutoReverse(true);
    }
    public void playAnim(){
        translateTransition.playFromStart();
    }

}
