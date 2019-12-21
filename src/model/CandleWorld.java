package model;

import math.Rectangle;
import math.Vector2;
import utils2d.ScreenConverter;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.sqrt;

public class CandleWorld implements IWorld {
    private Candlewick candlewick;
    private ForceSource externalForce;
    private ForceSource flameSource;
    private LinkedList<FireParticle> particleList = new LinkedList<>();
    private final int maxP = 650;//600

    public CandleWorld(Rectangle r) {
        particleList = new LinkedList<>();
        externalForce = new ForceSource(r.getCenter());
        candlewick = new Candlewick(r.getCenter(), new Vector2(0.1, 0.1));
        flameSource = new ForceSource(new Vector2(candlewick.getPos().getX(), candlewick.getPos().getY() + 1));
        flameSource.setValue(10);
    }

    @Override
    public void update(double dt) {
        Random r = new Random();
        flameSource.setLocation(
                new Vector2(
                        candlewick.getPos().getX(),//+externalForce.getLocation().getX(),
                        candlewick.getPos().getY() + 1));//+externalForce.getLocation().getY()));
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

            Vector2 Fvn = externalForce.getForceAt(np).add(flameSource.getForceAt(np));
            //Vector2 F
            //Vector2 Ftr = p.getVelocity().normolized().mul(-f.getMu() * p.getM() * f.getG());
            //Vector2 F = Ftr.add(Fvn);

            p.setAcceleration(Fvn);
            p.setVelocity(nv);
            p.setPosition(np);
        }
        for (FireParticle p : candlewick.emitParticles()
        ) {
            particleList.addFirst(p);
        }
    }

    @Override
    public ForceSource getExternalForce() {
        return externalForce;
    }

    public void draw(Graphics2D graphics, ScreenConverter sc) {
        for (FireParticle p : particleList
        ) {
            Random r = new Random();
            int red = 255;
            int green = 165;
            int blue = 0;
            int i = 1;
            double i1 = 0.6;
            Color center = Color.WHITE;
            double l = candlewick.getPos().getLengthBetweenPoints(p.getPosition());
            double k = sqrt(l / i * r.nextDouble());
            red *= k;
            green *= k;
            blue *= k;
            double l2 = candlewick.getPos().add(new Vector2(0, 1.1)).getLengthBetweenPoints(p.getPosition());
            if (l2 < i1) {
                double k2 = 0.2/sqrt(l2 / i1 );//* r.nextDouble());
                red += center.getRed() * k2;
                green += center.getGreen() * k2;
                blue += center.getBlue() * k2;
            }
            if (red > 255)
                red = 255;
            if (green > 255)
                green = 255;
            if (blue > 255)
                blue = 255;
            graphics.setColor(new Color(red, green, blue));
            graphics.drawRect(sc.r2s(p.getPosition()).getI(), sc.r2s(p.getPosition()).getJ(), 1, 1);
        }
    }
}
