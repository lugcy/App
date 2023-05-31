package domain;

import ui.AppSNCF;
import ui.Background;
import ui.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    private Form form;
    private Collection<Person> persons = new HashSet<>();
    AppSNCF appSNCF;
    public MainFunction(AppSNCF appSNCF){
        this.appSNCF = appSNCF;

        btnAdd = new Boutton(Background.WIDTH-marge-100, 15, 100, 50, "Ajouter", Color.green);
        btnDel = new Boutton(Background.WIDTH-marge-100, 70, 100, 50, "Supprimer", Color.red);

        line = new Line(0, 135, Background.WIDTH, 0, Color.black);
        y_person+=135+marge;

        elmts.add(btnAdd);elmts.add(line);elmts.add(btnDel);
    }

    public void mouseClick(int x, int y){
        if(btnAdd.getX() < x  && x < btnAdd.getX()+btnAdd.getWidth() && btnAdd.getY() < y  && y < btnAdd.getY()+btnAdd.getHeight())
            addPerson();
        if(btnDel.getX() < x && x < btnDel.getX()+btnDel.getWidth() && btnDel.getY() < y && y < btnDel.getY()+btnAdd.getHeight())
            JOptionPane.showMessageDialog(null, "Passez la souris sur un objet et appuyez sur 'Supr'");
    }
    public void isPressed(char e){
        for(Person p : persons){
            if(e==KeyEvent.VK_DELETE && p.isMouseOn()){
                persons.remove(p);
                break;
            }
        }
    }
    public void mouseMoved(int x, int y){
        for(Person p : persons) {
            if (p.getX() < x && x < p.getX() + p.getSizeX() && p.getY() < y && y < p.getY() + p.getSizeY())
                p.setMouseOn(true);
            else
                p.setMouseOn(false);
        }
    }

    public void actualize(){
        line.setWidth(Background.WIDTH);
        btnAdd.setX(Background.WIDTH-marge-100); btnDel.setX(Background.WIDTH-marge-100);

        x_person = 15; y_person = 135+15;
        int nbr = Background.WIDTH /(150+15);

        width_person = (Background.WIDTH-(nbr+1)*15)/((int)((Background.WIDTH-(nbr+1)*15)/150));

        int i = 0;
        for(Person p : persons){
            i+=1;
            p.setX(x_person); x_person = x_person+width_person+15;
            p.setY(y_person);
            p.setSizeX(width_person); p.setSizeY(150);

            if(p.isMouseOn()){
                int dec = 20;
                p.setX(p.getX()-dec/2);p.setY(p.getY()-dec/2);
                p.setSizeX(width_person+dec);p.setSizeY(150+dec);
            }
            else{
                p.setSizeX(width_person); p.setSizeY(150);
            }

            if(i!=0 && i%nbr==0) {
                y_person += 165;
                x_person = marge;
            }
        }
    }

    public void addPerson(){
        //persons.add(new Person(x_person, y_person, width_person, 150, "prénom", "nom", new Contract(1900, 1900, 11001, new Extension(false, 0, 0, false, [0])), false, Color.red));
        form = new Form(appSNCF);
    }
    public void newPerson(String name, String firstName, long dateBegin, long dateEnd, boolean extend, int dateBeginExtend, int dateEndExtend, boolean reminder, int[] dateOfRemind){
        persons.add(new Person(x_person, y_person, width_person, 150, name, firstName, new Contract(dateBegin, dateEnd, 100, new Extension(extend, dateBeginExtend, dateEndExtend, reminder, dateOfRemind)), false, Color.red));
        form.dispose();
    }
    public Collection<Person> getPersons(){return persons;}
    public List<Object> getElmts(){return elmts;}
}
