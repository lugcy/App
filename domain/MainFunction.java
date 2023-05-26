package domain;

import ui.Background;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Collection;

public class MainFunction {
    private final int marge = 15;
    private final int WIDTH = 900;

    private int x_person = 0;
    public static int y_person = 0;
    private int width_person = 165;

    private List<Object> elmts = new ArrayList<>();

    private Line line;
    private Boutton btnAdd;
    private Boutton btnDel;
    private Collection<Person> persons = new HashSet<>();

    public MainFunction(){
        btnAdd = new Boutton(Background.WIDTH-marge-100, 15, 100, 50, "Ajouter", Color.green);
        btnDel = new Boutton(Background.WIDTH-marge-100, 70, 100, 50, "Supprimer", Color.red);

        line = new Line(0, 135, Background.WIDTH, 0, Color.black);
        y_person+=135+marge;

        elmts.add(btnAdd);
        elmts.add(line);
        elmts.add(btnDel);
    }

    public void mouseClick(int x, int y){
        if(btnAdd.getX() < x  && x < btnAdd.getX()+btnAdd.getWidth() && btnAdd.getY() < y  && y < btnAdd.getY()+btnAdd.getHeight())
            addPerson();
    }

    public void actualize(){
        line.setWidth(Background.WIDTH);
        btnAdd.setX(Background.WIDTH-marge-100); btnDel.setX(Background.WIDTH-marge-100);

        x_person = 15;
        y_person = 135+15;
        int nbr = Background.WIDTH /(150+15);
        width_person = (Background.WIDTH-(nbr+1)*15)/((int)((Background.WIDTH-(nbr+1)*15)/150));
        int i = 0;
        for(Person p : persons){
            i+=1;
            p.setSize(width_person);
            p.setX(x_person); x_person+= width_person+15;
            p.setY(y_person);
            if(i!=0 && i%nbr==0){
                y_person+=165;
                x_person = 15;
            }
        }
    }

    public void addPerson(){
        persons.add(new Person(x_person, y_person, width_person, "prénom", "nom", 1900, 1900, Color.red));
    }
    public Collection<Person> getPersons(){return persons;}
    public List<Object> getElmts(){return elmts;}
}
