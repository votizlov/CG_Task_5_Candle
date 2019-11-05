package model;

import math.Vector2;

import java.util.LinkedList;

public class Candlewick {
    private Vector2 pos;
    private Vector2 size;

    public Candlewick(Vector2 pos, Vector2 size) {
        this.pos = pos;
        this.size = size;
    }
    public LinkedList<FireParticle> emitParticles(){
        LinkedList<FireParticle> linkedList = new LinkedList<>();
        for (double i = 5; i < size.getY()+5; i+=0.01) {
            linkedList.add(new FireParticle(new Vector2(i,5),new Vector2(0,1),new Vector2(0,0)));
        }
        return linkedList;
    }
}
