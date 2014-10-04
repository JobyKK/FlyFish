package mainPackage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Andrey on 08.03.14.
 */


public abstract class Creature extends Controller implements IWater, ISky {
    Image img;
    float xcoords, ycoords;
    float xmove, ymove;
    float speed;
    boolean exists;
    boolean right;
    Vector2f moveVector = new Vector2f(xmove, ymove);

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public void draw(Graphics g)
    {
        moveVector = moveVector.normalise();
        if(moveVector.getTheta() >= 90 && moveVector.getTheta() <= 270 ){
            if(!right)
            {
                img = img.getFlippedCopy(true,false);
                right = true;
            }
            g.drawImage(img ,xcoords,ycoords);
            img.setRotation(180f +(float) moveVector.getTheta());
           }
        else{
            if(right)
            {
                img = img.getFlippedCopy(true,false);
                right = false;
            }
           // if(moveVector.getTheta() != 0)System.out.println(moveVector.getTheta());
            g.drawImage(img, xcoords, ycoords);
            img.setRotation( (float)moveVector.getTheta());
        }
        //img.setCenterOfRotation(xcoords + img.getHeight()/2,ycoords + img.getWidth()/2);
        //img.setRotation(moveVector.);
    }
    protected void checkRestrictions(GameContainer gameContainer)
    {
         xcoords=Math.max(0,Math.min(xcoords,(float)gameContainer.getWidth()-img.getWidth()));    //making object fit in window
         ycoords=Math.max(0,Math.min(ycoords,(float)gameContainer.getHeight()-img.getHeight()));
    }
}
