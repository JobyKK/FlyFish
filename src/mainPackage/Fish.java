package mainPackage;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import java.lang.ref.*;
import java.util.*;
import java.math.MathContext;
/**
 * Created by Andrey on 07.03.14.
 */
public class Fish extends Creature {
    public boolean status = false;
    private int points;
    public Fish()     throws SlickException
    {

               img = new Image("res/ffish.png");
               xcoords = 400.0f;
               ycoords = 500.0f;
               xmove = 0f;
               ymove = 0f;
               speed = 0.2f;
            right = false;
        points =0;

    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void move(GameContainer gameContainer/*,ScreenArray screenArray*/,Input input,int delta)
    {
        if(input.isKeyDown(Input.KEY_RIGHT))
        {
                xcoords += speed*delta;
                moveVector.x--;
        }else  if(input.isKeyDown(Input.KEY_LEFT))
        {
                xcoords -= speed*delta;
                moveVector.x++;
        }if(input.isKeyDown(Input.KEY_DOWN))
        {
            ycoords += speed*delta;
            moveVector.y--;
        }   else  if(input.isKeyDown(Input.KEY_UP))
        {
            ycoords -= speed*delta;
            moveVector.y++;
        }
        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            status = false;
        }
        checkRestrictions(gameContainer);
    }
   /* private void eat(ScreenArray screenArray)
    {
      ArrayList<Reference<Creature>> lst = screenArray.findClose((int)xcoords,(int)ycoords);
        for(Reference<Creature> crt:lst)
        {

        }
    }*/
}
