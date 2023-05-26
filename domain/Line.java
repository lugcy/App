package domain;

import java.awt.*;

public class Line {
    private int x;
    private int y;
    private int width;
    private int heigth;
    private Color color;

    public Line(int x, int y, int width, int heigth, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.color = color;
    }

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}
    public int getWidth() {return width;}
    public void setWidth(int width) {this.width = width;}
    public int getHeigth() {return heigth;}
    public void setHeigth(int heigth) {this.heigth = heigth;}
    public Color getColor() {return color;}
    public void setColor(Color color) {this.color = color;}

    public void pintar(Graphics g){
        g.setColor(color);
        g.drawLine(x, y, x+width, y+heigth);
    }
}
