/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import math.Rectangle;
import model.CandleWorld;
import model.Field;
import model.Puck;
import model.World;
import timers.AbstractWorldTimer;
import timers.UpdateWorldTimer;
import utils2d.ScreenConverter;
import utils2d.ScreenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class CandlePanel extends JPanel implements ActionListener,
        MouseListener, MouseMotionListener, MouseWheelListener {
    private ScreenConverter sc;
    private CandleWorld w;
    private AbstractWorldTimer uwt;
    private Timer drawTimer;
    private long last;
    public CandlePanel() {
        super();
        //Field f = new Field(  new Rectangle(0, 10, 10, 10),            0.1, 9.8);
        Rectangle r = new Rectangle(0, 10, 10, 10);
        w = new CandleWorld( r);
        sc = new ScreenConverter(r, 450, 450);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addMouseWheelListener(this);
        
        //(uwt = new UpdateWorldTimer(w, 10)).start();
        drawTimer = new Timer(40, this);
        last = System.currentTimeMillis();
        drawTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        w.draw((Graphics2D)bi.getGraphics(), sc);
        g.drawImage(bi, 0, 0, null);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        long time = System.currentTimeMillis();
        w.update((time - last) * 0.001);//*0.001
        last = time;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int direction = 0;
        if (e.getButton() == MouseEvent.BUTTON1)
            direction = 1;
        else if (e.getButton() == MouseEvent.BUTTON3)
            direction = -1;
        w.getExternalForce().setValue(10*direction);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        w.getExternalForce().setValue(0);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        w.getExternalForce().setLocation(sc.s2r(new ScreenPoint(e.getX(), e.getY())));
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        /*double oldMu = w.getF().getMu();
        oldMu = Math.round(oldMu*100 + e.getWheelRotation())*0.01;
        
        if (oldMu < -1)
            oldMu = -1;
        else if (oldMu > 1)
            oldMu = 1;
        else if (Math.abs(oldMu) < 0.005)
            oldMu = 0;
        w.getF().setMu(oldMu);
        */
    }
    
}
