package model;

public interface IWorld {
    void  update(double dt);
    ForceSource getExternalForce();
}
