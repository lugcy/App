package domain;

import java.awt.*;

public class Boutton {
    private int x;
    private int y;
    private int width;
    private int height;
    private String name;
    private Color color;

    private final int arc = 20;

    public Boutton(int x, int y, int width, int height, String name, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.color = color;
    }

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}
    public int getWidth() {return width;}
    public void setWidth(int width) {this.width = width;}
    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public Color getColor() {return color;}
    public void setColor(Color color) {this.color = color;}

    public void pintar(Graphics g){
        g.setColor(this.color);
        g.drawRoundRect(x, y, width, height, arc, arc);
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        g.drawString(String.valueOf(name), x+(width-fm.stringWidth(name))/2, y+(height-fm.getAscent())/2+15);
    }
}
