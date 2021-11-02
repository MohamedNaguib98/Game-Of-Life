import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
public class GameOf extends Application 
{       
    private ImageView gif;
    private double previous = 0;
    private double current = 0;
    
    @Override
    public void start(Stage s) throws Exception
    {
        StackPane stackPane = new StackPane();
        Label l = new Label("Game Over!");
        l.setAlignment(Pos.CENTER);
        l.setFont(Font.font("Arial", 50));
        MediaPlayer eat = new MediaPlayer(new Media(new File("eat.mp3").toURI().toString()));
        myfish myf = new myfish();
        
        Group g = new Group();
        stackPane.getChildren().add(g);
        gif = new ImageView();
        gif.setImage(new Image("file:Sea.gif"));
        gif.setFitWidth(1380);
        gif.setFitHeight(800);
        g.getChildren().add(gif);
        Scene sc = new Scene(stackPane);
        for(int i = 0; i < 10; i++)
        {      
            redfish r = new redfish();
            bluefish b = new bluefish();
            orangefish o = new orangefish();
            shark sh = new shark();
            g.getChildren().add(r.imageView);
            r.motion(g.getBoundsInLocal());
            g.getChildren().add(b.imageView);
            b.motion(g.getBoundsInLocal());
            g.getChildren().add(o.imageView);
            o.motion(g.getBoundsInLocal());
            g.getChildren().add(sh.imageView);
            sh.motion(g.getBoundsInLocal());
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
                
                if(myf.imageView.intersects(o.imageView.getBoundsInParent())&& myf.imageView.getFitWidth() > o.imageView.getFitWidth())
                {
                    g.getChildren().remove(o.imageView);
                    //eat.setAutoPlay(true);
                    o.eatSound();
                    if(g.getChildren().size() == 32)
                    {
                        myf.imageView.setFitHeight(35);
                        myf.imageView.setFitWidth(45);
                    }
                }
                if(myf.imageView.intersects(r.imageView.getBoundsInParent()) && myf.imageView.getFitWidth() > r.imageView.getFitWidth())
                {
                    g.getChildren().remove(r.imageView);
                    eat.setAutoPlay(true);
                    if(g.getChildren().size() == 22)
                    {
                        myf.imageView.setFitHeight(55);
                        myf.imageView.setFitWidth(65);
                    }
                }
                else if(myf.imageView.intersects(r.imageView.getBoundsInParent()) && myf.imageView.getFitWidth() < r.imageView.getFitWidth())
                {
                    g.getChildren().remove(myf.imageView);
                    stackPane.getChildren().add(l);
                    sc.setCursor(Cursor.DEFAULT);
                }
                if(myf.imageView.intersects(b.imageView.getBoundsInParent()) && myf.imageView.getFitWidth() > b.imageView.getFitWidth())
                {
                    g.getChildren().remove(b.imageView);
                    eat.setAutoPlay(true);
                }
                else if(myf.imageView.intersects(b.imageView.getBoundsInParent()) && myf.imageView.getFitWidth() < b.imageView.getFitWidth())
                {
                    g.getChildren().remove(myf.imageView);
                    stackPane.getChildren().add(l);
                    sc.setCursor(Cursor.DEFAULT);
                }
                if(myf.imageView.intersects(sh.imageView.getBoundsInParent()) && myf.imageView.getFitWidth() < sh.imageView.getFitWidth())
                {
                    g.getChildren().remove(myf.imageView);
                    stackPane.getChildren().add(l);
                    sc.setCursor(Cursor.DEFAULT);
                }
                if(g.getChildren().size() == 12)
                {
                    l.setText("WINNER!!!!");
                    g.getChildren().remove(myf.imageView);
                    stackPane.getChildren().add(l);
                    sc.setCursor(Cursor.DEFAULT);
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
        g.getChildren().add(myf.imageView);
        
        sc.setOnMouseMoved((MouseEvent event) ->
        {
            myf.imageView.setX(event.getX()-(myf.imageView.getFitWidth()/2));
            myf.imageView.setY(event.getY()-(myf.imageView.getFitHeight()/2));
            current=event.getX();
            System.out.println("Current - Previous = " + (current - previous));
        });
        
        sc.setOnMouseClicked((MouseEvent event) -> {
            
            myf.imageView.setX(event.getX()-(myf.imageView.getFitWidth()/2) - 100);
        });
        l.setOnTouchPressed((event) -> {
        //remove scene. put start scene;
        });
        MediaPlayer background = new MediaPlayer(new Media(new File("Water.mp3").toURI().toString()));
        background.play();
        sc.setCursor(Cursor.NONE);
        s.setScene(sc);
        s.show();
        s.setFullScreen(true);
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}