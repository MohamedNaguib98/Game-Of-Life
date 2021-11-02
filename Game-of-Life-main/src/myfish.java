import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class myfish 
{
    Image image;
    ImageView imageView;
    myfish()
    {
        image = new Image("File:Yellow.png");
        imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(25);
        imageView.setPreserveRatio(true);
    }     
}