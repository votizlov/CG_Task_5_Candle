/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package timers;

import model.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class AbstractWorldTimer {// todo снести нахуй и сделать норм
    protected World actualWorld;
    private Timer timer;

    public AbstractWorldTimer(World world, int period) {
        this.actualWorld = world;
        timer = new Timer(period, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                worldAction(actualWorld);
            }
        });
    }
    
    public void start() {
        timer.start();
    }
    public void stop() {
        timer.stop();
    }
    public void setPeriod(int delay) {
        timer.setDelay(delay);
    }
    
    abstract void worldAction(World w);
}
