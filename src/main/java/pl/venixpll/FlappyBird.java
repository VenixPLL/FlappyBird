package pl.venixpll;

import org.newdawn.slick.AppGameContainer;
import pl.venixpll.app.FlappyBirdAPP;

public class FlappyBird {

    public static void main(String[] args) throws Exception{
        final AppGameContainer appgc = new AppGameContainer(new FlappyBirdAPP("FlappyBird"));
        appgc.setDisplayMode(350,600,false);
        appgc.setVSync(true);
        appgc.setTargetFrameRate(60);
        appgc.start();
    }

}
