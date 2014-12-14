package com.ordonteam.gameoflife;

import org.junit.Ignore;
import org.junit.Test;

import java.awt.*;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GameTest {

    public static final int SIDE = 10;
    private final Game game = new Game();

    @Test
    public void shouldCreateEmptyWorld() throws Exception {
        assertThat(game.getLives()).isEmpty();
    }

    @Test
    public void shouldAddedLifeBeOnBoard() throws Exception {
        Life life = new Life(0, 0);

        game.addLife(life);

        assertThat(game.getLives()).contains(life);
    }

    @Test
    public void shouldLivesBeSameOnTheSameCoordinates() throws Exception {
        Life life1 = new Life(0,0);
        Life life2 = new Life(0,0);

        assertThat(life1).isEqualTo(life2);
    }

    @Test
    public void shouldAllAddedLivesBeOnBoard() throws Exception {
        Life life1 = new Life(0,0);
        Life life2 = new Life(0,0);
        Life life3 = new Life(0,0);

        game.addLives(life1, life2, life3);

        assertThat(game.getLives()).contains(life1, life2, life3);
    }

    @Test
    public void shouldDieWithoutEnoughNeighbors() throws Exception {
        Life life = new Life(0,0);
        game.addLife(life);

        game.tick();

        assertThat(game.getLives()).isEmpty();
    }

    @Test
    public void shouldSurviveWhit2Neighbors() throws Exception {
        game.addLives(new Life(0,0), new Life(0,1), new Life(0,2));

        game.tick();

        assertThat(game.getLives()).contains(new Life(0,1));
    }

    @Test
    public void shouldDieWhenFriendsAreTooFar() throws Exception {
        game.addLives(new Life(0,0), new Life(0,2), new Life(0,4));

        game.tick();

        assertThat(game.getLives()).isEmpty();
    }

    @Test
    public void shouldEmergeWithThreeNeighbors() throws Exception {
        game.addLives(new Life(0,0), new Life(0,2), new Life(1,1));

        game.tick();

        assertThat(game.getLives()).contains(new Life(0,1));
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
        game.changeLife(new Life(0, 0));
        Graphics g = mock(Graphics.class);

        game.paint(g, SIDE);

        verify(g).fillRect(0, 0, SIDE, SIDE);
        verifyNoMoreInteractions(g);
    }

    @Test
    public void shouldDrawManyRectangles() throws Exception {
        game.changeLife(new Life(0, 0));
        game.changeLife(new Life(5, 5));
        Graphics g = mock(Graphics.class);

        game.paint(g, SIDE);

        verify(g).fillRect(0, 0, SIDE, SIDE);
        verify(g).fillRect(50, 50, SIDE, SIDE);
        verifyNoMoreInteractions(g);
    }
}
