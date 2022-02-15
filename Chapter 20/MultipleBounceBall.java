/*
 * Kevin Drake
 * 2/9/22
 * Rewrote this program to generate randomly sized balls when clicking '+' button, When you click the 
 *  '-' button it will remove the largest of the balls.
 */
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.*;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class MultipleBounceBall extends Application {
	@Override
	public void start(Stage primaryStage) {
		MultipleBallPane ballPane = new MultipleBallPane();
		ballPane.setStyle("-fx-border-color: green");
		
		Button btAdd = new Button("+");
		Button btSubtract = new Button("-");
		HBox hBox = new HBox(10);
		hBox.getChildren().addAll(btAdd, btSubtract);
		hBox.setAlignment(Pos.CENTER);
		
		// Add or Remove a Ball
		btAdd.setOnAction(e -> ballPane.add());
		btSubtract.setOnAction(e -> ballPane.subtract());
		
		// Pause and resume animation
		ballPane.setOnMousePressed(e -> ballPane.pause());
		ballPane.setOnMouseReleased(e -> ballPane.play());
		
		// Use a scroll bar to control animation speed
		ScrollBar sbSpeed = new ScrollBar();
		sbSpeed.setMax(20);
		sbSpeed.setValue(10);
		ballPane.rateProperty().bind(sbSpeed.valueProperty());
		
		BorderPane pane = new BorderPane();
		pane.setCenter(ballPane);
		pane.setTop(sbSpeed);
		pane.setBottom(hBox);
		
		Scene scene = new Scene(pane, 250, 150);
		primaryStage.setTitle("Multiple Bouncing Balls");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
 class MultipleBallPane extends Pane {
	private Timeline animation;
	
	public MultipleBallPane() {
		// Create the animation for moving the ball
		animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();
	}
	public void add() {
		Color color = new Color(Math.random(), Math.random(), Math.random(), 0.5);
		getChildren().add(new Ball(30, 30,Math.random() * 16 + 5, color));
	}
	public void subtract() {
		if (getChildren().size() > 0) {
			Ball ball = (Ball)(getChildren().get(0));
			for (Node node: getChildren())
				if (((Ball)node).getRadius() > ball.getRadius())
					ball = (Ball)node;
			
			getChildren().remove(ball);
		}
	}
	public void play() {
		animation.play();
	}
	public void pause() {
		animation.pause();
	}
	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}
	public void decreaseSpeed() {
		animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0);
	}
	public DoubleProperty rateProperty() {
		return animation.rateProperty();
	}
	protected void moveBall() {
		for (Node node: this.getChildren()) {
			Ball ball = (Ball)node;
			// Check Boundaries
			if (ball.getCenterX() < ball.getRadius() || 
					ball.getCenterX() > getWidth() - ball.getRadius()) {
				ball.dx *= -1; // Change ball direction
			}
			if (ball.getCenterY() < ball.getRadius() || 
					ball.getCenterY() > getHeight() - ball.getRadius()) {
				ball.dy *= -1;
			}
			
			// Adjust Ball Position
			ball.setCenterX(ball.dx + ball.getCenterX());
			ball.setCenterY(ball.dy + ball.getCenterY());		
		}
	}
}
 class Ball extends Circle implements Comparable<Ball>{
	 protected double dx = 1, dy = 1;
	 
	 Ball(double x, double y, double radius, Color color) {
		 super(x, y, radius);
		 setFill(color);
	 }
	@Override
	public int compareTo(Ball o) {
		if (this.getRadius() - o.getRadius() < 0) {
			return -1;
		}
		else if (this.getRadius() - o.getRadius() == 0) {
		return 0;	
		}
		else {
			return 1;
		}
	}
 }
