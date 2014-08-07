package com.ordonteam.gameoflife;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Game {

    private Set<Life> lives = new HashSet<Life>();

    public Iterable<Life> getLives() {
        return lives;
    }

    public void addLife(Life life) {
        this.lives.add(life);
    }

    public void addLives(Life... lives) {
        this.lives.addAll(Arrays.asList(lives));
    }

    public void tick() {
        Iterable<Life> survived = getSurviving();
        Iterable<Life> emerged = getEmerged();
        lives = Sets.newHashSet(Iterables.concat(survived,emerged));
    }

    private Set<Life> getSurviving() {
        return Sets.filter(lives, new Predicate<Life>() {
            @Override
            public boolean apply(Life life) {
                return shouldSurvive(life);
            }
        });
    }

    private boolean shouldSurvive(Life life) {
        int count = getLivingNeighborsCount(life);
        return count == 2 || count == 3;
    }

    private int getLivingNeighborsCount(Life life) {
        Set<Life> livingNeighbors = Sets.filter(life.getPossibleNeighbors(), new Predicate<Life>() {
            @Override
            public boolean apply(Life neighbor) {
                return lives.contains(neighbor);
            }
        });
        return livingNeighbors.size();
    }

    private Iterable<Life> getEmerged() {
//        HashSet<Life> emerged = new HashSet<Life>();
//        for (Life life : lives) {
//            for (Life possibleToEmerge : life.getPossibleNeighbors()) {
//                if(shouldEmerged(possibleToEmerge))
//                    emerged.add(possibleToEmerge);
//            }
//        }
//        return emerged;

        Iterable<Life> allPossibleEmerged = Iterables.concat(Iterables.transform(lives, new Function<Life, Iterable<Life>>() {
            @Override
            public Iterable<Life> apply(Life life) {
                return life.getPossibleNeighbors();
            }
        }));
        Iterable<Life> allEmerged = Iterables.filter(allPossibleEmerged, new Predicate<Life>() {
            @Override
            public boolean apply(Life life) {
                return shouldEmerged(life);
            }
        });
        return allEmerged;
    }

    private boolean shouldEmerged(Life life) {
        if(lives.contains(life))
            return false;
        int count = getLivingNeighborsCount(life);
        return count == 3;
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
