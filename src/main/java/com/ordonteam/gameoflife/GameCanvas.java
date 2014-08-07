package com.ordonteam.gameoflife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class GameCanvas extends Canvas{

    public static void main(String... args) {
        JFrame gameView = new JFrame();
        gameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameView.add(new GameCanvas(new Game()));
        gameView.pack();
        gameView.setVisible(true);
    }

    private static final int SIDE = 10;
    private static final Color lifeColor = Color.BLACK;

    private final Game game;

    public GameCanvas(Game game) {
        this.game = game;
        setSize(400, 400);
        addMouseListener(new MouseEventHandler());
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(lifeColor);
        game.paint(g, SIDE);
    }

    public class MouseEventHandler extends EmptyMouseListener implements Runnable{
        private boolean stopped = true;

        @Override
        public void mouseClicked(MouseEvent e) {
            game.changeLife(new Life(e.getX()/SIDE, e.getY()/SIDE));
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            stopped = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            stopped = false;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                while(!stopped){
                    game.tick();
                    repaint();
                    Thread.sleep(125);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
