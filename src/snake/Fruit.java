package snake;

import java.awt.Color;
import java.awt.Graphics;

public class Fruit {
    public static int x;
    public static int y;
    public static int widthFruit;
    public static int heightFruit;
    
    Fruit(int x, int y){
        this.x = x;
        this.y = y;
        widthFruit = 30;
        heightFruit = 30;
    }

    public void drawFruit(Graphics g){
    	g.setColor(Color.RED);
    	g.fillOval(x, y, widthFruit, heightFruit);
    }
}