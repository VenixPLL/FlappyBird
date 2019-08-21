package pl.venixpll.texture;

import lombok.Data;
import org.newdawn.slick.Image;

@Data
public class TextureLoader {

    private boolean ready;

    private Image background;
    private Image bird;
    private Image pipe;

    public void init() throws Exception{
        background = new Image("assets/background.png");
        bird = new Image("assets/bird.png");
        pipe = new Image("assets/pipe.jpg");
        ready = true;
    }

}
