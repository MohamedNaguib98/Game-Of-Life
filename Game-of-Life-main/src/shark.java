import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
public class shark 
{
    Random rand = new Random();
    int x = 100 + rand.nextInt(500);
    int y = 100 + rand.nextInt(500);
    Image image;
    ImageView imageView;
    shark()
    {
        image = new Image("File:FShark.png");
        imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        imageView.relocate(x, y);
    }
    public void motion(Bounds bounds)
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<ActionEvent>()
                {
                    double x = Math.random();
                    double y = Math.random();
                    @Override
                    public void handle(ActionEvent event)
                    {         
                        imageView.setLayoutX(imageView.getLayoutX() + x);
                        imageView.setLayoutY(imageView.getLayoutY() + y);
                        if(imageView.getLayoutX() <= (bounds.getMinX() + imageView.getFitWidth()))
                        {
                            imageView.setImage(new Image("File:FShark.png"));
                            x = -x;
                        }
                        else if(imageView.getLayoutX() >= (bounds.getMaxX() - imageView.getFitWidth()))
                        {
                            imageView.setImage(new Image("File:Shark.png"));
                            x = -x;
                        }
                        if(imageView.getLayoutY() <= (bounds.getMinY() + imageView.getFitHeight()) ||
                                imageView.getLayoutY() >= (bounds.getMaxY() - imageView.getFitHeight()))
                        {
                            y = -y;
                        }
                    }
                }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
    }
}