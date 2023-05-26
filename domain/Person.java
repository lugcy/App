package domain;

import java.awt.*;

public class Person {
    private int x;
    private int y;
    private int size;
    private String firstName;
    private String lastName;
    private long dateOfArrival;
    private long dateOfDeparture;
    private Color color;

    private final int size_y = 150;
    private final int arc = 20;
    private final int sizeTxt = 20;

    public Person(int x, int y, int size, String firstName, String lastName, long dateOfArrival, long dateOfDeparture, Color color){
        this.x = x;
        this.y = y;
        this.size = size;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfArrival = dateOfArrival;
        this.dateOfDeparture = dateOfDeparture;
        this.color = color;
    }

    public int getX() {return x;}
    public void setX(int x) {this.x = x;}
    public int getY() {return y;}
    public void setY(int y) {this.y = y;}
    public int getSize() {return size;}
    public void setSize(int size) {this.size = size;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public long getDateOfArrival() {return dateOfArrival;}
    public void setDateOfArrival(long dateOfArrival) {this.dateOfArrival = dateOfArrival;}
    public long getDateOfDeparture() {return dateOfDeparture;}
    public void setDateOfDeparture(long dateOfDeparture) {this.dateOfDeparture = dateOfDeparture;}
    public Color getColor() {return color;}
    public void setColor(Color color) {this.color = color;}

    public void pintar(Graphics g){
        g.setColor(color);
        g.drawRoundRect(x, y, size, size_y, arc, arc);
        g.drawLine(x, y+30, x+size, y+30);

        g.setColor(Color.black);
        g.setFont(new Font("Times New Roman", Font.BOLD, sizeTxt));
        String str = firstName.toUpperCase().substring(0, 1) + firstName.toLowerCase().substring(1) + " " + lastName.toUpperCase();
        FontMetrics fm = g.getFontMetrics();
        g.drawString(String.valueOf(str), x+(size-fm.stringWidth(str))/2, y+fm.getAscent()+5);

        g.setFont(new Font("Times New Roman", Font.BOLD, sizeTxt-4));
        g.drawString(String.valueOf("- Arrivée:"), x+5, y+fm.getAscent()+35);
        g.drawString(String.valueOf("- Départ:"), x+5, y+2*fm.getAscent()+35);
    }
}
