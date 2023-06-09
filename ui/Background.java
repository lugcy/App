package ui;

import domain.Boutton;
import domain.MainFunction;
import domain.Person;
import domain.Line;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Background extends JPanel implements Runnable {
    private AppSNCF appSNCF;
    private Image bg;
    private Image logo;
    private int[] mouse = new int[]{0, 0};
    public static int WIDTH;


    public Background(AppSNCF appSNCF){
        try{
            bg = ImageIO.read(new File("Ressources/fond.png"));
            logo = ImageIO.read(new File("Ressources/logo.png"));
        }
        catch(IOException e){
            System.out.println("Image not found");
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                appSNCF.getMainFunction().mouseClick(e.getX(), e.getY());
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                WIDTH = getWidth();
                int height = getHeight();
            }
        });
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                appSNCF.getMainFunction().isPressed(e.getKeyChar());
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                appSNCF.getMainFunction().mouseMoved(e.getX(), e.getY());
            }
        });

        this.appSNCF = appSNCF;
        this.setPreferredSize(new Dimension(900, 500));
    }

    public void refreshScrollBar() {
        this.setPreferredSize(new Dimension(900, MainFunction.y_person+165));
        revalidate();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, null);
        g.drawImage(logo, 5, 25, null);
        for(Object obj : this.appSNCF.getMainFunction().getElmts()){
            if (obj instanceof Person)
                ((Person) obj).pintar(g);
            if (obj instanceof Boutton)
                ((Boutton) obj).pintar(g);
            if(obj instanceof Line)
                ((Line) obj).pintar(g);
        }
        this.appSNCF.getMainFunction().getPersons().forEach(p->p.pintar(g));
    }

    @Override
    public void run() {
        while(true){
            long time1 = System.nanoTime();

            appSNCF.getMainFunction().actualize();
            refreshScrollBar();
            appSNCF.setMinimumSize(new Dimension(920, 500));

            double timeTotal = System.nanoTime() - time1;
            repaint();
            try {
                double refreshRate = (1.0 / 120 * 1000);
                int timeSleep = (int) (refreshRate - timeTotal / 1000000);
                if (timeSleep < 0)
                    timeSleep = 0;
                Thread.sleep(timeSleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
