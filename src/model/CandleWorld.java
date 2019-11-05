package model;

import math.Rectangle;
import math.Vector2;
import utils2d.ScreenConverter;

import java.awt.*;
import java.util.LinkedList;

public class CandleWorld implements IWorld {
    private Candlewick candlewick;
    private ForceSource externalForce;
    private LinkedList<FireParticle> particleList = new LinkedList<>();
    private final int maxP = 10;

    public CandleWorld(Rectangle r) {
        particleList = new LinkedList<>();
        externalForce = new ForceSource(r.getCenter());
        candlewick = new Candlewick(r.getCenter(), new Vector2(0.01, 0.05));
    }

    @Override
    public void update(double dt) {
        if (particleList.size() > maxP) //remove exeeding particles
            for (int i = 0; i < particleList.size() - maxP; i++)
                particleList.removeLast();
        for (FireParticle p : particleList//calc existing
        ) {
            Vector2 np = p.getPosition()
                    .add(p.getVelocity().mul(dt))
                    .add(p.getAcceleration().mul(dt * dt * 0.5));
            Vector2 nv = p.getVelocity()
                    .add(p.getAcceleration().mul(dt));

            double vx = nv.getX(), vy = nv.getY();
            boolean reset = false;
            nv = new Vector2(vx, vy);
            if (nv.length() < 1e-10)
                nv = new Vector2(0, 0);
            if (reset)
                np = p.getPosition();

            Vector2 Fvn = externalForce.getForceAt(np);
            p.setVelocity(nv);
            p.setPosition(np);
        }
        for (FireParticle p:candlewick.emitParticles()
             ) {
            particleList.addFirst(p);
        }
    }

    @Override
    public ForceSource getExternalForce() {
        return null;
    }

    public void draw(Graphics2D graphics, ScreenConverter sc) {
        for (FireParticle p:particleList
             ) {
            graphics.drawRect(sc.r2s(p.getPosition()).getI(),sc.r2s(p.getPosition()).getJ(),1,1);
        }
    }
}
