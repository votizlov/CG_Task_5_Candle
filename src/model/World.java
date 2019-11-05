/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import math.Vector2;
import utils2d.ScreenConverter;
import utils2d.ScreenPoint;

import java.awt.*;

public class World implements IWorld {
    private Puck p;
    private Field f;
    private ForceSource externalForce;

    public World(Puck p, Field f) {
        this.p = p;
        this.f = f;
        this.externalForce = new ForceSource(f.getRectangle().getCenter());
    }
    
    /**
     * Метод обновления состояния мира за указанное время
     * @param dt Промежуток времени, за который требуется обновить мир.
     */
    public void update(double dt) {
        Vector2 np = p.getPosition()
                .add(p.getVelocity().mul(dt))
                .add(p.getAcceleration().mul(dt*dt*0.5));
        Vector2 nv = p.getVelocity()
                .add(p.getAcceleration().mul(dt));
        
        double vx = nv.getX(), vy = nv.getY();
        boolean reset = false;
        if (np.getX() - p.getR() < f.getRectangle().getLeft() || np.getX() + p.getR() > f.getRectangle().getRight()) {
            vx = -vx;
            reset = true;
        }
        if (np.getY() - p.getR() < f.getRectangle().getBottom() || np.getY() + p.getR() > f.getRectangle().getTop()) {
            vy = -vy;
            reset = true;
        }
        nv = new Vector2(vx, vy);
        if (nv.length() < 1e-10)
            nv = new Vector2(0, 0);
        if (reset)
            np = p.getPosition();
        
        Vector2 Fvn = externalForce.getForceAt(np);
        Vector2 Ftr = p.getVelocity().normolized().mul(-f.getMu()*p.getM()*f.getG());
        Vector2 F = Ftr.add(Fvn);
        
        p.setAcceleration(F.mul(1/p.getM()));
        p.setVelocity(nv);
        p.setPosition(np);
    }
    
    /**
     * Метод рисует ткущее состояние мира.
     * На самом деле всю логику рисования стоит вынести из этого класса
     * куда-нибудь в WroldDrawer, унаследованный от IDrawer
     * @param g Графикс, на котором надо нарисовать текущее состояние.
     * @param sc Актуальный конвертер координат.
     */
    public void draw(Graphics2D g, ScreenConverter sc) {
        ScreenPoint tl = sc.r2s(f.getRectangle().getTopLeft());
        int w = sc.r2sDistanceH(f.getRectangle().getWidth());
        int h = sc.r2sDistanceV(f.getRectangle().getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(tl.getI(), tl.getJ(), w, h);
        g.setColor(Color.RED);
        g.drawRect(tl.getI(), tl.getJ(), w, h);
        ScreenPoint pc = sc.r2s(p.getPosition());
        int rh = sc.r2sDistanceH(p.getR());
        int rv = sc.r2sDistanceV(p.getR());
        g.setColor(Color.BLACK);
        g.fillOval(pc.getI() - rh, pc.getJ() - rv, rh + rh, rv + rv);
        
        g.drawString(String.format("Mu=%.2f", f.getMu()), 10, 30);
        g.drawString(String.format("F=%.0f", externalForce.getValue()), 10, 50);
    }

    public Field getF() {
        return f;
    }

    public void setF(Field f) {
        this.f = f;
    }

    public Puck getP() {
        return p;
    }

    public void setP(Puck p) {
        this.p = p;
    }
    
    public ForceSource getExternalForce() {
        return externalForce;
    }
}
