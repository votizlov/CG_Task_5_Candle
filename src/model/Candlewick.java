package model;

import math.Vector2;

import java.util.LinkedList;

import static java.lang.Math.*;

public class Candlewick {
    public Vector2 getPos() {
        return pos;
    }

    private Vector2 pos;

    public Vector2 getSize() {
        return size;
    }

    public Vector2 getTipPos() {
        return tipPos;
    }

    public void setTipPos(Vector2 tipPos) {
        this.tipPos = tipPos;
    }

    private Vector2 tipPos;
    private Vector2 size;

    public Candlewick(Vector2 pos, Vector2 size) {
        this.pos = pos;
        this.size = size;
    }
    public LinkedList<FireParticle> emitParticles(){
        LinkedList<FireParticle> linkedList = new LinkedList<>();
        for (double i = 0; i < size.getX(); i+=0.01) {
            linkedList.add(new FireParticle(new Vector2(pos.getX()-size.getX()+i,5+i),new Vector2(1,1),new Vector2(-0.1,0)));
        }
        for (double i = 0; i < size.getX(); i+=0.01) {
            linkedList.add(new FireParticle(new Vector2(pos.getX()-size.getX()+i,5+i),new Vector2(-1,1),new Vector2(0.1,0)));
        }
        for (double i = 0; i<size.getY(); i+=0.01){
            // calculate dx & dy
            double dx = tipPos.getX() - pos.getX();
            double dy = tipPos.getY() - pos.getY();

            // calculate steps required for generating pixels
            double steps = abs(dx) > abs(dy) ? abs(dx) : abs(dy)*10;

            // calculate increment in x & y for each steps
            float Xinc = (float) (dx /  steps);
            float Yinc = (float) (dy /  steps);

            // Put pixel for each step
            float X = (float) pos.getX();
            float Y = (float) pos.getY();
            for (int j = 0; j <= steps; j++)
            {
                linkedList.add(new FireParticle(new Vector2(X,Y),new Vector2(1,1),new Vector2(-0.2,0)));//pos.getY() + size.getY()+i*10
                linkedList.add(new FireParticle(new Vector2(X,Y),new Vector2(-1,1),new Vector2(0.2,0)));
                X += Xinc;           // increment in x at each step
                Y += Yinc;           // increment in y at each step
                // generation step by step
            }
        }
        for (double i = 0; i < size.getY(); i+=0.01) {
           // linkedList.add(new FireParticle(new Vector2(pos.getX()-size.getX()+i,5),new Vector2(-1,1),new Vector2(0.1,0)));
        }
        return linkedList;
    }
}
