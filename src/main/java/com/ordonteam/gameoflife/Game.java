package com.ordonteam.gameoflife;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Game {

    private Set<Life> lives = new HashSet<Life>();

    Iterable<Life> getLives() {
        return lives;
    }

    public void addLife(Life life) {
        this.lives.add(life);
    }

    public void addLives(Life... lives) {
        this.lives.addAll(Arrays.asList(lives));
    }

    public void tick() {
    }

    public void changeLife(Life life) {
        if(lives.contains(life))
            lives.remove(life);
        else
            lives.add(life);
    }

    public void paint(Graphics g, int side) {
        for (Life life : lives) {
            life.paint(g,side);
        }
    }
}
