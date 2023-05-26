package ui;

import domain.*;
import javax.swing.*;
import java.awt.*;

public class AppSNCF extends JFrame {
    private Background bg;
    private MainFunction mainFunction;

    public static void main(String[] args){new AppSNCF();}

    public AppSNCF(){
        super("AppSNCF");
        this.mainFunction = new MainFunction();
        init();
        new Thread(bg).start();
    }

    public void init(){
        bg = new Background(this);
        JScrollPane scrollPane = new JScrollPane(bg);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);
        this.pack();
        bg.requestFocus();
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public MainFunction getMainFunction(){
        return mainFunction;
    }
}
