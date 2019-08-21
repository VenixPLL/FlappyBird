package pl.venixpll.app.render;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import pl.venixpll.FlappyBird;
import pl.venixpll.app.FlappyBirdAPP;

import java.awt.*;

public class RenderBird {

    private int posY;
    private double motionY = 0;
    private int resetPosY;

    public RenderBird(int posY){
        this.posY = posY;
        resetPosY = posY;
    }

    public void render(GameContainer container, Graphics graphics, final FlappyBirdAPP app){
        final int posX = (container.getWidth() / 2) - 50;
        app.getTextureLoader().getBird().setCenterOfRotation(45 /2,45 / 2);
        app.getTextureLoader().getBird().draw(posX,posY,45,45);

        Rectangle p = new Rectangle(posX + 10,posY + 10,25,25);

        graphics.setColor(Color.red);

        app.getObstacles().forEach(obstacle -> {
            Rectangle r = new Rectangle(obstacle.getPosX(),obstacle.getPosY(),60,obstacle.getPos());
            Rectangle r1 = new Rectangle(obstacle.getPosX(),obstacle.getPos() + 140,60,container.getHeight() - 140);

            graphics.setColor(Color.orange);
            if(r.intersects(p)){
                endgame(app);
            }
            if(r1.intersects(p)){
                endgame(app);
            }
        });
        graphics.setColor(Color.white);

        if(posY == container.getHeight() || posY > container.getHeight()){
            endgame(app);
        }
        if(posY == -10 || posY < -10){
            endgame(app);
        }
    }

    private void endgame(FlappyBirdAPP app){
        posY = resetPosY;
        motionY = 0;
        app.setPaused(true);
        app.setGameOver(true);
        app.getBackgroundSegments().clear();
        app.setBackGroundPos(0);
        app.getBackgroundSegments().add(-350);
    }

    public void click(){
        motionY = 12;
    }

    public void tick(final FlappyBirdAPP app){
        posY -= motionY;
        if(motionY < -4){
            if(app.getTextureLoader().getBird().getRotation() < 50) {
                app.getTextureLoader().getBird().rotate(3);
            }
        }else if (motionY > 0){
            if(app.getTextureLoader().getBird().getRotation() > -20){
                app.getTextureLoader().getBird().rotate(-6);
            }
        }
        if(motionY > -7) {
            motionY -= 1;
        }
    }

}
