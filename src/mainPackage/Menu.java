package mainPackage;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

/**
 * Created by Andrey on 12.03.14.
 */

/*class OptionMenu extends Menu{

    private final Animation soundValueButton;
    private final Animation exit;
    private final Image incSoundValueButton;
    private final Image decSoundValueButton;

    public OptionMenu() throws SlickException {

        soundValueButton = new Animation();
        exit = new Animation();
        incSoundValueButton = new Image("");
        decSoundValueButton = new Image("");
    }
}*/

public class Menu {


    Image[] bubblePlaySet = {new Image("res/BubbleAnimation/bubble1a.png"), new Image("res/BubbleAnimation/bubble2a.png"),
            new Image("res/BubbleAnimation/bubble3a.png"), new Image("res/BubbleAnimation/bubble4a.png")};
    Image[] bubbleOptionsSet = {new Image("res/OptionsAnimation/s1.png"), new Image("res/OptionsAnimation/s2.png"),
            new Image("res/OptionsAnimation/s3.png"), new Image("res/OptionsAnimation/s4.png")};
    Image[] bubbleExitSet = {new Image("res/ExitAnimation/e1.png"), new Image("res/ExitAnimation/e2.png"),
            new Image("res/ExitAnimation/e3.png"), new Image("res/ExitAnimation/e4.png")};
    private boolean bubblePlayFlag = false;
    private boolean bubbleOptionsFlag = false;
    private boolean bubbleExitFlag = false;
    private boolean visibleOptionMenu = false;
    private final Animation bubbleOptionsAnimation;
    private final Animation bubblePlayAnimation;
    private final Animation bubbleExitAnimation;
    public boolean status;

    public Menu() throws SlickException {

        bubblePlayAnimation = new Animation(bubblePlaySet, 100);
        bubbleOptionsAnimation = new Animation(bubbleOptionsSet, 100);
        bubbleExitAnimation = new Animation(bubbleExitSet, 100);
        status = true;
    }
    public Graphics draw(GameContainer gc, Graphics g){

        if(bubblePlayFlag) g.drawAnimation(bubblePlayAnimation, (gc.getWidth() - bubblePlaySet[0].getWidth())/2, (gc.getHeight())/4);
            else g.drawImage(bubblePlaySet[0], (gc.getWidth() - bubblePlaySet[0].getWidth())/2, (gc.getHeight())/4);

        if(bubbleOptionsFlag) g.drawAnimation(bubbleOptionsAnimation, (gc.getWidth() - bubblePlaySet[0].getWidth())/2 - bubbleOptionsSet[0].getWidth(), (gc.getHeight())/4 + bubblePlaySet[0].getHeight());
            else g.drawImage(bubbleOptionsSet[0], (gc.getWidth() - bubblePlaySet[0].getWidth())/2 - bubbleOptionsSet[0].getWidth(), (gc.getHeight())/4 + bubblePlaySet[0].getHeight());

        if(bubbleExitFlag) g.drawAnimation(bubbleExitAnimation, (gc.getWidth() + bubblePlaySet[0].getWidth() - bubbleExitSet[0].getWidth())/2, (gc.getHeight())/4 + bubblePlaySet[0].getHeight() + bubbleOptionsSet[0].getHeight() - bubbleExitSet[0].getHeight());
            else g.drawImage(bubbleExitSet[0], (gc.getWidth() + bubblePlaySet[0].getWidth() - bubbleExitSet[0].getWidth())/2, (gc.getHeight())/4 + bubblePlaySet[0].getHeight() + bubbleOptionsSet[0].getHeight() - bubbleExitSet[0].getHeight());
        return g;
    }
    public void show(GameContainer gc, Input input){
        int x = Mouse.getX();
        int y = Mouse.getY();
        double tt = Math.sqrt(Math.pow( x - (gc.getWidth())/2 , 2) + Math.pow( y - 3*(gc.getHeight())/4, 2));
        if(Math.sqrt(Math.pow( x - (gc.getWidth())/2 , 2) + Math.pow( y - 3*(gc.getHeight())/4 +  bubblePlaySet[0].getHeight()/2, 2))
                <= bubblePlaySet[0].getWidth()/2){
            bubblePlayFlag = true;
            if(input.isMousePressed(0)) {
            status = false;
            }
        }
        else{
            bubblePlayFlag = false;
        }
        if(Math.sqrt(Math.pow( x - (gc.getWidth())/2 + bubblePlaySet[0].getWidth()/2 + bubbleOptionsSet[0].getWidth()/2, 2) +
                Math.pow( y - 3*(gc.getHeight())/4 +  bubblePlaySet[0].getHeight() + bubbleOptionsSet[0].getHeight()/2, 2))
                <= bubbleOptionsSet[0].getWidth()/2){
            bubbleOptionsFlag = true;
            if(input.isMousePressed(0)){

                status = false;
            }
        }
        else{
            bubbleOptionsFlag = false;
        }
        if(Math.sqrt(Math.pow( x - (gc.getWidth() + bubblePlaySet[0].getWidth())/2, 2) +
                Math.pow(y - (+ 3*(gc.getHeight())/4 - bubblePlaySet[0].getHeight() - bubbleOptionsSet[0].getHeight() + bubbleExitSet[0].getHeight()/2), 2))
                <= bubbleExitSet[0].getWidth()/2){
            bubbleExitFlag = true;
            if(input.isMousePressed(0)){
               System.exit(0);
            }
        }
        else{
            bubbleExitFlag = false;
        }

        if(input.isKeyPressed(Input.KEY_ESCAPE)) status = false;


    }
}
