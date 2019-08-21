package pl.venixpll.app.render;

import lombok.Data;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import pl.venixpll.app.FlappyBirdAPP;

@Data
public class RenderObstacle {

    private int posX;
    private int posY;
    private int pos;

    private Image upperPipe;
    private Image bottomPipe;

    public RenderObstacle(int posX, int posY, int pos,FlappyBirdAPP app,final GameContainer container){
        this.posX = posX;
        this.posY = posY;
        this.pos = pos;
        try {
            this.upperPipe = new Image("assets/pipe.jpg");
            this.bottomPipe = new Image("assets/pipe.jpg");
        }catch(Exception exc){}
    }

    public void render(GameContainer container, Graphics graphics, final FlappyBirdAPP app){
        graphics.setColor(Color.red);
        this.upperPipe.setCenterOfRotation(60 / 2,pos / 2);
        this.upperPipe.setRotation(180);
        this.upperPipe.draw(posX,0,60,pos);
        this.bottomPipe.draw(posX,pos + 140,60,container.getHeight() - 140);
        graphics.setColor(Color.orange);
        graphics.setColor(Color.white);
    }

    public void tick(final FlappyBirdAPP app,GameContainer container){
        posX -= 2;
    }

}
