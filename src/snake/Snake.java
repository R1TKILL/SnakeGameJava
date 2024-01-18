package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Snake {
    public int widthSnake;
    public int heightSnake;
    public int medidasSnake;
    public int[] snakePositionY; 
    public int[] snakePositionX;
    public int bory_snake = 6;
    public boolean up, down, left, right = true;

    Snake(){
        this.widthSnake = 30;
        this.heightSnake = 30;
        //Isso me serve para mapear toda a tela.
        this.medidasSnake = Game.widthWindow * Game.heightWindow;
        this.snakePositionX = new int[medidasSnake];
        this.snakePositionY = new int[medidasSnake];
    }

    public void drawSnake(Graphics g){
        //Desenhando a cobra, sempre verificando a quantidade em bory_snake e adicionando.
        for(int i = 0; i < bory_snake; i++){
            g.setColor(new Color(0, 215, 0));
            g.fillRect(snakePositionX[i], snakePositionY[i], widthSnake, heightSnake);            
        }  
    }

    public void updateSnakePosition(){
        /*Para seguir a cabeça, a variavel i contêm todo o corpo da cobra, ele terá
         * a tarefa de verificar as atualizaçãoes dos eixos redesenhando as posições
         * e eliminando as que já não existem.*/
        for(int i = bory_snake; i > 0; i--){
            snakePositionX[i] = snakePositionX[i - 1];
            snakePositionY[i] = snakePositionY[i - 1];
        }

        //Colisão.
        Rectangle checkFruit = new Rectangle(Fruit.x, Fruit.y, Fruit.widthFruit, Fruit.heightFruit);
        Rectangle checkSnake = new Rectangle(snakePositionX[0], snakePositionY[0], widthSnake, heightSnake);

        //Se bater no própio corpo, causa de morte 2.
        /*if(checkSnake.intersects(checkSnake)){
            System.out.println("Bateu em si!");
        }*/
        
        if(checkSnake.intersects(checkFruit)){
            Game.FruitPositionX = new Random().nextInt(870);
            Game.FruitPositionY = new Random().nextInt(570);
            Fruit.x = Game.FruitPositionX;
            Fruit.y = Game.FruitPositionY;
            bory_snake+=6;
            Game.points+=1;
        }

        //Para o movimento contínuo.
        if(up){snakePositionY[0]--;}
        else if(down){snakePositionY[0]++;}
        else if(left){snakePositionX[0]--;}
        else if(right){snakePositionX[0]++;}

        //Controle para não sair da tela, causa de morte 1.
        if(snakePositionY[0] + heightSnake > Game.heightWindow){snakePositionY[0] = Game.heightWindow - heightSnake;}
        else if(snakePositionY[0] < 0){snakePositionY[0] = 0;}
        else if(snakePositionX[0] + widthSnake > Game.widthWindow){snakePositionX[0] = Game.widthWindow - widthSnake;}
        else if(snakePositionX[0] < 0){snakePositionX[0] = 0;}
    }
}