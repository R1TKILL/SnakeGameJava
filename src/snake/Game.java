package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.PrintStream;
import java.util.Random;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{
    public static int widthWindow = 900;
    public static int heightWindow = 600;
    public static int points;

    public static JFrame frame;
    public static Snake snake;
    public static Fruit fruit;

    public static int FruitPositionX = new Random().nextInt(870);
    public static int FruitPositionY = new Random().nextInt(570);

    public Game(){
        this.setPreferredSize(new Dimension(widthWindow, heightWindow));
        this.addKeyListener(this); 

        snake = new Snake();
        fruit = new Fruit(FruitPositionX, FruitPositionY);
    }

    public static void main(String args[]){
        Game game = new Game();

        frame = new JFrame("Snake Game");
        frame.setVisible(true);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new Thread(game).start();
    }

    @Override
    public void run() {
        try {
			while(true){
				draw();
				update();
				Thread.sleep(1000/80);
			}
		} 
        catch (InterruptedException e) {e.printStackTrace();}
    }

    public void draw(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics(); 
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, widthWindow, heightWindow);

        snake.drawSnake(g);
        fruit.drawFruit(g);

        g.setColor(Color.BLACK);
		g.setFont(new Font("MV Boli", Font.BOLD | Font.ITALIC, 30));
		g.drawString("Pontos:"+points, 670, 60);
        bs.show();
    }

    public void update(){
        snake.updateSnakePosition();
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_W){
            if(snake.down){}
            else{snake.up = true; snake.down = false; snake.left = false; snake.right = false;}
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){
            if(snake.right){}
            else{snake.left = true; snake.up = false; snake.down = false; snake.right = false;}
        }
        else if(e.getKeyCode() == KeyEvent.VK_S){
            if(snake.up){}
            else{snake.down = true; snake.up = false; snake.left = false; snake.right = false;}
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){
            if(snake.left){}
            else{snake.right = true; snake.up = false; snake.down = false; snake.left = false;}
        }
    }
    @Override
    public void keyReleased(KeyEvent e){}
    @Override
    public void keyTyped(KeyEvent e){}
}