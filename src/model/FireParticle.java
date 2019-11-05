package model;

import math.Vector2;

public class FireParticle {
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public FireParticle(Vector2 position, Vector2 velocity, Vector2 acceleration) {
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }
}
