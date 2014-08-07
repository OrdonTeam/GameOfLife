package com.ordonteam.gameoflife;

import org.junit.Test;

import java.awt.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GameTest {

    public static final int SIDE = 10;
    private final Game game = new Game();

    @Test
    public void shouldCreateWorld() throws Exception {
    }

    @Test
    public void shouldCreateEmptyWorld() throws Exception {
    }

    @Test
    public void shouldAddedLifeBeOnBoard() throws Exception {
    }

    @Test
    public void shouldLivesBeSameOnTheSameCoordinates() throws Exception {
    }

    @Test
    public void shouldAllAddedLivesBeOnBoard() throws Exception {
    }

    @Test
    public void shouldDieWithoutEnoughNeighbors() throws Exception {
    }

    @Test
    public void shouldSurviveWhit2Neighbors() throws Exception {
    }

    @Test
    public void shouldDieWhenFriendsAreTooFar() throws Exception {
    }

    @Test
    public void shouldBeAbleToAddLifeAfterTick() throws Exception {
    }

    @Test
    public void shouldEmergeWithThreeNeighbors() throws Exception {
    }

    @Test
    public void shouldChangeLifeStatusToLiving() throws Exception {


        game.changeLife(new Life(0, 0));

        assertThat(game.getLives()).contains(new Life(0, 0));
    }

    @Test
    public void shouldChangeLifeStatusToDead() throws Exception {
        game.changeLife(new Life(0,0));

        game.changeLife(new Life(0,0));

        assertThat(game.getLives()).doesNotContain(new Life(0, 0));
    }

    @Test
    public void shouldDrawRectangles() throws Exception {
        game.addLife(new Life(0, 0));
        Graphics g = mock(Graphics.class);

        game.paint(g, SIDE);

        verify(g).fillRect(0, 0, SIDE, SIDE);
        verifyNoMoreInteractions(g);
    }

    @Test
    public void shouldDrawManyRectangles() throws Exception {
        game.addLives(new Life(0, 0), new Life(5, 5));
        Graphics g = mock(Graphics.class);

        game.paint(g, SIDE);

        verify(g).fillRect(0, 0, SIDE, SIDE);
        verify(g).fillRect(50, 50, SIDE, SIDE);
        verifyNoMoreInteractions(g);
    }
}
