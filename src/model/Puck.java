/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import math.Vector2;

/**
 *
 * Класс, описывающий шайбу.
 * @author Alexey
 */
public class Puck {
    private double m, r;
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    /**
     * Создаём шайбу с нулевой скоростью и ускорением
     * @param m Масса шайбы [кг]
     * @param r Радиус шайбы [м]
     * @param position Положение шайбы относительно начала координат [м]
     */
    public Puck(double m, double r, Vector2 position) {
        this.m = m;
        this.r = r;
        this.position = position;
        this.velocity = new Vector2(0, 0);
        this.acceleration = new Vector2(0, 0);
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public double getM() {
        return m;
    }

    public void setM(double m) {
        this.m = m;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
    
    
}
