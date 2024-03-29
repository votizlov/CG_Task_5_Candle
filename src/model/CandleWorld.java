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
    private Physical wickTip;
    private ForceSource externalForce;
    private ForceSource flameSource;
    private LinkedList<FireParticle> particleList = new LinkedList<>();
    private final int maxP = 2100;//650

    public CandleWorld(Rectangle r) {
        particleList = new LinkedList<>();
        externalForce = new ForceSource(r.getCenter());
        candlewick = new Candlewick(r.getCenter(), new Vector2(0.1, 0.1));
        flameSource = new ForceSource(new Vector2(candlewick.getPos().getX(), candlewick.getPos().getY() + 1.15));
        flameSource.setValue(10);
    }

    @Override
    public void update(double dt) {
        Random r = new Random();
        flameSource.setLocation(
                externalForce.getValue()!=0?new Vector2(candlewick.getPos(),externalForce.getLocation()).normolized().mul(candlewick.getSize().getY() / 2 + 1.15).add(candlewick.getPos())
                        :candlewick.getPos().add(new Vector2(0,1.15)));
        candlewick.setTipPos(flameSource.getLocation());
        //candlewick.getPos().getX(),//+externalForce.getLocation().getX(),
        //candlewick.getPos().getY() + 1.15));//+externalForce.getLocation().getY()));
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
        Color[] rgb = new Color[particleList.size()];
        int j = 0;
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
                double k2 = 0.2 / sqrt(l2 / i1);//* r.nextDouble());
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
            rgb[j] = new Color(red, green, blue);
            j++;
            //graphics.setColor(new Color(red, green, blue));
            //graphics.drawRect(sc.r2s(p.getPosition()).getI(), sc.r2s(p.getPosition()).getJ(), 1, 1);
        }
        int[] triangleX = new int[3];
        int[] triangleY = new int[3];
        for(int i = 1;i<rgb.length-1;i+=2){
            //for(int n = 0;n<3;n++){
                triangleX[0] = sc.r2s(particleList.get(i-1).getPosition()).getI();
                triangleY[0] =  sc.r2s(particleList.get(i-1).getPosition()).getJ();
                triangleX[1] = sc.r2s(particleList.get(i).getPosition()).getI();
                triangleY[1] =  sc.r2s(particleList.get(i).getPosition()).getJ();
                triangleX[2] =  sc.r2s(particleList.get(i+1).getPosition()).getI();
                triangleY[2] = sc.r2s(particleList.get(i+1).getPosition()).getJ();
            //}
            graphics.setColor(rgb[i]);
            graphics.fillPolygon(triangleX,triangleY,3);
        }
        graphics.setColor(Color.CYAN);
        graphics.drawRect(sc.r2s(flameSource.getLocation()).getI(), sc.r2s(flameSource.getLocation()).getJ(), 4, 4);
    }
}
