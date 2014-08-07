package com.ordonteam.gameoflife;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Life {
    private final int x;
    private final int y;

    public Life(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Life life = (Life) o;

        if (x != life.x) return false;
        if (y != life.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "X="+x+" Y="+y;
    }

    public Set<Life> getPossibleNeighbors() {
        Set<Life> neighbors = new HashSet<Life>();
        neighbors.add(new Life(x-1,y-1));
        neighbors.add(new Life(x,y-1));
        neighbors.add(new Life(x+1,y-1));
        neighbors.add(new Life(x-1,y));
        neighbors.add(new Life(x+1,y));
        neighbors.add(new Life(x-1,y+1));
        neighbors.add(new Life(x,y+1));
        neighbors.add(new Life(x+1,y+1));
        return neighbors;
    }

    public void paint(Graphics g, int side) {
        g.fillRect(x*side,y*side,side,side);
    }
}
