package mainPackage;

/**
 * Created by Andrey on 07.03.14.
 */
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.awt.*;
import java.util.Random;

public class Fly extends Creature {
    Random rn;
    private int points;
    public Fly(int x,int y, boolean existing) throws SlickException {
        img = new Image("res/fly.png");
        speed = 0.2f;
        xcoords = x;
        ycoords=y;
        rn = new Random();
        exists = existing;
        right = false;
        points = 20;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Point gen_move(GameContainer gc,int waterline,int delta)
    {
            int len = (int)rn.nextInt(30);
            int angle = (int)rn.nextInt(360);
            Float prev_xcoords=new Float(xcoords),prev_ycoords=new Float(ycoords);
            xcoords +=len*Math.cos(Math.toRadians(angle));
            ycoords +=len*Math.sin(Math.toRadians(angle));
            checkRestrictions(gc,waterline);
            float t=xcoords;
            xcoords=prev_xcoords;
            prev_xcoords=t;
            t=ycoords;
            ycoords=prev_ycoords;
            prev_ycoords=t;
        return new Point((int)prev_xcoords.floatValue(),(int)prev_ycoords.floatValue());
    }
    public void move(Point p)
    {
        xcoords = p.x;
        ycoords = p.y;
    }
    public void setFly(Fly inpFly)
    {
        xcoords = inpFly.xcoords;
        ycoords = inpFly.ycoords;
        exists = inpFly.exists;
    }
    private void checkRestrictions(GameContainer gc,int waterline)
    {
        ycoords=Math.min(ycoords, waterline);
        checkRestrictions(gc);
    }

    public <T> void swap(T a,T b)
    {
        T temp=a;
        a=b;
        b=temp;
    }
}
