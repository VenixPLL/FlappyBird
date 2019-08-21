package pl.venixpll.app;

import lombok.Data;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import pl.venixpll.app.render.RenderBird;
import pl.venixpll.app.render.RenderObstacle;
import pl.venixpll.texture.TextureLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class FlappyBirdAPP extends BasicGame {
    public FlappyBirdAPP(String title) {
        super(title);
    }

    private RenderBird bird;
    private TextureLoader textureLoader;
    private int backGroundPos = 0;

    private int score = 0;
    private boolean paused = true;
    private boolean gameOver = true;

    private GameContainer container;

    private final Random r = new Random();

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            this.container = gameContainer;
            textureLoader = new TextureLoader();
            textureLoader.init();
            backgroundSegments.add(-350);
            bird = new RenderBird(gameContainer.getHeight() / 2);
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if(textureLoader != null && textureLoader.isReady() && !paused){
            backGroundPos--;
            int index = 0;
            for(int k : backgroundSegments){
                k -= 1;
                backgroundSegments.set(index,k);
                index++;
            }
            for(RenderObstacle obstacle : obstacles){
                obstacle.tick(this,gameContainer);
            }
            bird.tick(this);
        }
    }

    private List<Integer> backgroundSegments = new CopyOnWriteArrayList<>();
    private List<RenderObstacle> obstacles = new CopyOnWriteArrayList<>();

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if(textureLoader != null && textureLoader.isReady()){
            if(!paused) {

                if(backGroundPos % -135 == 0){
                    this.score++;
                }

                if(backGroundPos % -135 == 0){
                    obstacles.add(new RenderObstacle(gameContainer.getWidth(),0,r.nextInt(gameContainer.getHeight() - 200) + 100,this,gameContainer));
                }
                if (backGroundPos % -350 == 0) {
                    backgroundSegments.add(0);
                }

                if(obstacles.size() > 3){
                    obstacles.remove(0);
                }

                for (int i : backgroundSegments) {
                    if (i < -1500) {
                        backgroundSegments.remove(0);
                    }
                    textureLoader.getBackground().draw(i + 350, 0, 700, 600);
                }
                for(RenderObstacle obstacle : obstacles){
                    obstacle.render(gameContainer,graphics,this);
                }
                bird.render(gameContainer,graphics,this);



            }else{
                if(this.gameOver){
                    textureLoader.getBackground().draw(0,0,700,600);
                    graphics.drawString("CLICK TO START GAME!",(gameContainer.getWidth() / 2) - graphics.getFont().getWidth("CLICK TO START GAME!") / 2,(gameContainer.getHeight() / 2) - 50);
                }
            }


            final String scoreString = "Score: " + score;
            graphics.drawString(scoreString,10,22);
            if(this.paused && !this.gameOver){
                graphics.drawString("PAUSED",(gameContainer.getWidth() / 2) - graphics.getFont().getWidth("PAUSED") / 2,gameContainer.getHeight() / 2);
            }
        }
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if(this.gameOver) {
            this.gameOver = false;
            this.paused = false;
            this.score = 0;
            obstacles.clear();
            obstacles.add(new RenderObstacle(container.getWidth(),0,r.nextInt(container.getHeight() - 200) + 100,this,container));
        }else {
            if (!paused) {
                bird.click();
            }
        }
    }

    @Override
    public void keyPressed(int key, char c) {
        if(key == 1){
            this.paused = !this.paused;
        }
        super.keyPressed(key, c);
    }
}
