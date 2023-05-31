package domain;

import java.awt.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Serializable {
    private int x;
    private int y;
    private int size_x;
    private int size_y;
    private String firstName;
    private String lastName;
    private Contract contract;
    private boolean mouseOn;
    private Color color;
    private final int arc = 20;
    private final int sizeTxt = 20;

    public Person(int x, int y, int size_x, int size_y, String firstName, String lastName, Contract contract, boolean mouseOn, Color color){
        this.x = x;
        this.y = y;
        this.size_x = size_x;
        this.size_y = size_y;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contract = contract;
        this.mouseOn = mouseOn;
        this.color = color;
    }

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}
    public int getSizeX() {return size_x;}
    public void setSizeX(int size) {this.size_x = size;}
    public int getSizeY() {return size_y;}
    public void setSizeY(int size) {this.size_y = size;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public Contract getContract() {return contract;}
    public void setContract(Contract contract) {this.contract = contract;}
    public boolean isMouseOn() {return mouseOn;}
    public void setMouseOn(boolean mouseOn) {this.mouseOn = mouseOn;}
    public Color getColor() {return color;}
    public void setColor(Color color) {this.color = color;}

    public void pintar(Graphics g){
        g.setColor(color);
        g.drawRoundRect(x, y, size_x, size_y, arc, arc);
        g.drawLine(x, (int) (y+0.2*size_y), x+size_x, (int) (y+0.2*size_y));
        g.drawLine(x, (int) (y+0.55*size_y), x+size_x, (int) (y+0.55*size_y));

        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman", Font.BOLD, 14));
        String str = firstName.toUpperCase().substring(0, 1) + firstName.toLowerCase().substring(1) + " " + lastName.toUpperCase();
        FontMetrics fm = g.getFontMetrics();
        g.drawString(String.valueOf(str), x+(size_x-fm.stringWidth(str))/2, (int) (y+fm.getAscent()+0.05*size_y));

        g.setFont(new Font("Times New Roman", Font.BOLD, sizeTxt-4));
        g.drawString(String.valueOf("- Arrivée:"), x+5, (int) (y+fm.getAscent()+0.24*size_y));
        g.drawString(String.valueOf("- Départ:"), x+5, (int) (y+3*fm.getAscent()+0.24*size_y));

        SimpleDateFormat dateB = new SimpleDateFormat("dd/MM/yyyy");
        g.drawString(String.valueOf(dateB.format(contract.getDateBegin())), x+size_x-fm.stringWidth(String.valueOf(dateB.format(contract.getDateBegin())))-15, (int) (y+fm.getAscent()+0.24*size_y));
        g.drawString(String.valueOf(dateB.format(contract.getDateEnd())), x+size_x-fm.stringWidth(String.valueOf(dateB.format(contract.getDateEnd())))-15, (int) (y+3*fm.getAscent()+0.24*size_y));
    }
}
