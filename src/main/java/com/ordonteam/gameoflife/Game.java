package com.ordonteam.gameoflife;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.collect.Iterables.concat;
import static com.google.common.collect.Iterables.transform;

public class Game {

    private Set<Life> lives = new HashSet<Life>();

    Iterable<Life> getLives() {
        return lives;
    }

    public void addLife(Life life) {
        lives.add(life);
    }

    public void addLives(Life... lives) {
        for (Life life : lives) {
            addLife(life);
        }
    }

    public void tick() {
        Iterable<Life> newLives = getSurvivingLives();
        Iterable<Life> emergedLives = getEmergedLives();
        lives = Sets.newHashSet(concat(newLives, emergedLives));
    }

    private Iterable<Life> getEmergedLives() {
        Iterable<Life> toCheckIfShouldEmerge = concat(
                transform(lives, new Function<Life, Iterable<Life>>() {
                    @Override
                    public Iterable<Life> apply(Life life) {
                        return life.getPossibleNeighbors();
                    }
                }));
        return Iterables.filter(toCheckIfShouldEmerge, new Predicate<Life>() {
            @Override
            public boolean apply(Life life) {
                return getNeighboursCount(life) == 3;
            }
        });
    }

    private Iterable<Life> getSurvivingLives() {
        return Iterables.filter(lives, new Predicate<Life>() {
            @Override
            public boolean apply(Life life) {
                int neighboursCount = getNeighboursCount(life);
                return neighboursCount == 2 || neighboursCount == 3;
            }
        });
    }

    private int getNeighboursCount(Life life) {
        Set<Life> possibleNeighbors = life.getPossibleNeighbors();
        possibleNeighbors.retainAll(lives);
        return possibleNeighbors.size();
    }

    public void changeLife(Life life) {
        if (lives.contains(life))
            lives.remove(life);
        else
            lives.add(life);
    }

    public void paint(Graphics g, int side) {
        for (Life life : lives) {
            life.paint(g, side);
        }
    }
}
