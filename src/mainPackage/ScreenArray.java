package mainPackage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import java.awt.Point;
import java.util.*;
/**
 * Created with IntelliJ IDEA.
 * User: Sasha
 * Date: 12.03.14
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
public class ScreenArray {
    int width,height;
    Fly zeroFly;
    int flyMaxNumber;
    ArrayList<Point> flies;
    ArrayList<ArrayList<Fly>> mas;
    public ScreenArray(GameContainer gc) throws SlickException {
        flyMaxNumber = 5;
        zeroFly = new Fly(0,0,false);
        width = gc.getWidth();
        height = gc.getHeight();
        flies = new ArrayList<Point>();
        mas = new ArrayList<ArrayList<Fly>>();
        ArrayList<Fly> zeromas = new ArrayList<Fly>();
        for(int i=0;i<gc.getHeight();i++)
            zeromas.add(zeroFly);
        for(int i=0;i<gc.getWidth();i++)
            mas.add(zeromas);
    }
    public void CreateFly(GameContainer gc, int waterline) throws SlickException {
        if(flies.size()>=flyMaxNumber)
            return;
        int xcoords;
        int ycoords;
        Random rn = new Random();
        do {

            xcoords = (int)rn.nextInt(gc.getWidth()-1);
            ycoords = (int)rn.nextInt(waterline-1);
        }while(mas.get(xcoords).get(ycoords).isExists());
        Fly addFly = new Fly(xcoords,ycoords,true);
        mas.get(xcoords).set(ycoords,addFly);
        flies.add(new Point(xcoords,ycoords));
        return;
    }
    public void move(GameContainer gc,int waterline,int delta)   throws SlickException
    {
        ArrayList<Point> newflies = new ArrayList<Point>();
        for(Point p: flies)
        {
               Point newp;
               do {
                   newp =mas.get(p.x).get(p.y).gen_move(gc,waterline,delta);
               }while(mas.get(newp.x).get(newp.y).isExists());
            Fly temp = new Fly(0, 0, false);
            temp.setFly(mas.get(p.x).get(p.y));
            mas.get(p.x).get(p.y).setFly(mas.get(newp.x).get(newp.y));
            //((Fly)mas.get(newp.x).get(newp.y)).setFly(temp);
            //((ArrayList<Fly>) mas.get(newp.x)).set(newp.y,temp);
            setElement(temp,newp);
            setElement(zeroFly,p);
            mas.get(newp.x).get(newp.y).move(newp);
            newflies.add(newp);
        }
        flies=newflies;
    }
    public ArrayList<Fly> findClose(int x,int y)
    {
        ArrayList<Fly> ans=new ArrayList<Fly>();
        int lookWidth=10;
        for(int i=Math.max(0, x - lookWidth);i<Math.min(width, x + lookWidth);i++)
            for(int j=Math.max(0,y-lookWidth);j<Math.min(height,y+lookWidth);j++)
            {
                if(mas.get(i).get(j)!=null)
                    ans.add((Fly)mas.get(i).get(j));
            }
        return ans;
    }
    public int eatClose(int x,int y)
    {
        ArrayList<Fly> eat = findClose(x,y);
        int ans=0;
        for(Fly eaten: eat)
        {
            setElement(zeroFly,new Point((int)eaten.xcoords,(int)eaten.ycoords));
            ans+=eaten.getPoints();
        }
        return  ans;
    }
    public <T> void swap(T a,T b)
    {
        T temp=a;
        a=b;
        b=temp;
    }
    public void draw(Graphics g)
    {
        for(Point p: flies)
        {
            mas.get(p.x).get(p.y).draw(g);
        }
    }
    private void setElement(Fly temp,Point p)
    {
        ArrayList<Fly> t_mas = mas.get(p.x);
        t_mas.set(p.y,temp);
        mas.set(p.x,t_mas);
    }
}
