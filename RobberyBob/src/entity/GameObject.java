package entity;

import controller.Controller;
import core.Movement;
import core.gameplay.footstep;
import core.physics2d.Collision;
import core.Position;
import core.Size;
import game.Game;
import core.gameplay.los;

import java.awt.*;


// Abstract class GameObject, so bale iniinherit ng Player at enemy or any other entities pa yung gameobject which is kung nainherit na nila meron na silang attributes kung ano mang attributes nasa GameObject.
public abstract class GameObject {
    protected Position position;
    protected Size size;
    protected Collision collision;
    protected Controller controller;
    protected footstep footstep;

    public double entitySpeed = 3f;
    public String direction = "";
    public boolean collisionOn = false;
    public boolean caught = false;
    public Game game;
    public String name = "entity";
    public Movement movement;
    public los los;

    public GameObject() {
        size = new Size(64, 64);
        collision = new Collision();
        movement = new Movement();
        los = new los();
        collision.BoxCollision(size.getWidth(), size.getHeight());
    }

    public abstract void update();
    public abstract Image getSprite();


    public Position getPosition() {
        return position;
    }

    public Size getSize() {
        return size;
    }

    public Collision getCollision() { return collision; }

    // vicinity of objects
    public Rectangle getBounds() {
        return new Rectangle(position.getX(), position.getY(), size.getWidth(), size.getHeight());
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }
}

