package model;

import math.Vector2;

import java.util.LinkedList;

public class Candlewick {
    public Vector2 getPos() {
        return pos;
    }

    private Vector2 pos;
    private Vector2 size;

    public Candlewick(Vector2 pos, Vector2 size) {
        this.pos = pos;
        this.size = size;
    }
    public LinkedList<FireParticle> emitParticles(){
        LinkedList<FireParticle> linkedList = new LinkedList<>();
        for (double i = 0; i < size.getX(); i+=0.01) {
            linkedList.add(new FireParticle(new Vector2(pos.getX()-size.getX()+i,5),new Vector2(1,1),new Vector2(-0.1,0)));
        }
        for (double i = 0; i < size.getX(); i+=0.01) {
            linkedList.add(new FireParticle(new Vector2(pos.getX()-size.getX()+i,5),new Vector2(-1,1),new Vector2(0.1,0)));
        }
        return linkedList;
    }
}
