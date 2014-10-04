package mainPackage;

import com.sun.deploy.uitoolkit.impl.fx.FXProgressBarSkin;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

import static oracle.jrockit.jfr.events.Bits.swap;
import java.awt.Point;
import java.util.*;
/**
 * Created by Andrey on 04.03.14.
 */
public class Game extends BasicGame{

    Menu myMenu;
    Sound mySound;
    Music myMusic;
    Image back;
    Image soundButton;
    Fish myFish;
    ScreenArray screenArray;
    static int width=800,height=600,waterline=400;
    Timer timer;
    private TimerTask task;
    boolean timerLocked;

    boolean soundOnFlag = true;
    public Game(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        back = new Image("res/horison.jpg");
        soundButton = new Image("res/MenuOutputtedButton/sound.png");
        myMenu = new Menu();
        myFish = new Fish();
        screenArray = new ScreenArray(gc);
        timer=new Timer();
        timerLocked=true;
        myMusic = new Music("res/Music/background.ogg");
        myMusic.setVolume(1);
        myMusic.loop();
    }

    @Override
    public void update(final GameContainer gc,final int delta) throws SlickException {
        Input input = gc.getInput(); //look for keys pressed
        if(Math.sqrt(Math.pow( Mouse.getX() - gc.getWidth() + soundButton.getWidth()/2 , 2)
                + Math.pow( Mouse.getY() - gc.getHeight() + soundButton.getHeight()/2, 2)) <= soundButton.getWidth()/2
                ){
            if( input.isMousePressed(0)) {
                if(soundOnFlag){
                myMusic.setVolume(0);
                    soundOnFlag = false;
                }
                else{
                myMusic.setVolume(1);
                    soundOnFlag = true;
                }
            }
        }
        if(myFish.status) {
            myFish.move(gc, input, delta);
            myMenu.status = !myFish.status;
            myFish.setPoints(myFish.getPoints()+screenArray.eatClose((int)myFish.xcoords,(int)myFish.ycoords));
            if(timerLocked)
            {
                startSampling(gc,waterline,delta);
                timerLocked = false;
            }
        }else
        {
            myMenu.show(gc, input);
            myFish.status = !myMenu.status;
            if(!timerLocked)
            {

                stopSampling();
                timerLocked=true;
            }
        }
        screenArray.CreateFly(gc, waterline);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        //g.drawImage(back, 0, 0);
        //gc.setShowFPS(false);
        gc.setTargetFrameRate(100);
        back.draw(0,0);
        soundButton.draw(gc.getWidth() - soundButton.getWidth(), 0);
        myFish.draw(g);
        screenArray.draw(g);
        g.drawString(((Integer)myFish.getPoints()).toString(),gc.getWidth()-50,10);
        if(myMenu.status)
            g = myMenu.draw(gc ,g);
        else {
            g.clearAlphaMap();
            myFish.status = true;
        }
    }

    void startSampling(final GameContainer gc,final int waterline,final int delta) {
        if (task != null)
            return;

        task = new TimerTask() {
            public void run(){
                try
                {
                    screenArray.move(gc,waterline,delta);
                }catch (SlickException e)
                {}

            }
        };

        timer.scheduleAtFixedRate(task,0, 40);
    }

    void stopSampling() {
        if (task == null)
            return;

        task.cancel();
        task = null;
    }


    public static void main(String args[]) throws SlickException {

        AppGameContainer app = new AppGameContainer(new Game("Flying Fish"));
        app.setDisplayMode(width, height, false);//scale of game window
        app.start();
    }



}
