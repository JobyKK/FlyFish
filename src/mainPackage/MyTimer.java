package mainPackage;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.util.TimerTask;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sasha
 * Date: 22.03.14
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class MyTimer extends Timer{
    TimerTask task;
    public void initTask(TimerTask inp_task)
    {
        task = inp_task;
    }
    public void startSampling(final TimerTask inp_task) {
        if (inp_task == null)
            return;
        scheduleAtFixedRate(inp_task,0, 200);
    }

    public void stopSampling() {
        if (task == null)
            return;

        task.cancel();
        task = null;
    }
}
