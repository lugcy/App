package ui;


import domain.Person;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Form extends JFrame{
    //public static void main(String[] args){new Form();}
    AppSNCF appSNCF;
    public Form(AppSNCF appSNCF){
        super("Form");
        this.setPreferredSize(new Dimension(400, 600));
        init();
        this.appSNCF = appSNCF;
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.requestFocus();
        this.setVisible(true);
    }

    private void init(){
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel JName = new JLabel(" Name :");
        JLabel JLastName = new JLabel(" Last Name :");
        JLabel JDateOfArrival = new JLabel(" Date of arrival");
        JLabel JDateOfDeparture = new JLabel(" Date of departure");

        JTextField TName = new JTextField();
        JTextField TLastName = new JTextField();
        JTextField TDateOfArrival = new JTextField();
        JTextField TDateOfDeparture = new JTextField();

        JToggleButton btnExt = new JToggleButton("Extension off");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1, 5, 5));

        JPanel panel2one = new JPanel();
        panel2one.setLayout(new GridLayout(3, 2));
        JLabel ExtJDateOfArrival = new JLabel(" Date of arrival :");
        JLabel ExtJDateOfDeparture = new JLabel(" Date of departure :");

        JTextField TExtJDateOfArrival = new JTextField();
        JTextField TExtJDateOfDeparture = new JTextField();
        JToggleButton btnReminder = new JToggleButton("Reminder off");

        JPanel panel2bis = new JPanel();
        panel2bis.setLayout(new GridLayout(10, 1, 5, 5));
        String[] options = {"1 day before", "2 days before", "3 days before", "4 days before", "5 days before", "6 days before", "1 week before", "2 weeks before", "3 weeks before", "1 month before"};
        Collection<JRadioButton> radioButtons = new ArrayList<>();
        for(String option : options){
            radioButtons.add(new JRadioButton(option));
        }

        JPanel panel3 = new JPanel();
        Button save = new Button("Save");

        panel1.add(JName, BorderLayout.CENTER); panel1.add(TName, BorderLayout.CENTER);
        panel1.add(JLastName, BorderLayout.CENTER);panel1.add(TLastName, BorderLayout.CENTER);
        panel1.add(JDateOfArrival, BorderLayout.CENTER);panel1.add(TDateOfArrival, BorderLayout.CENTER);
        panel1.add(JDateOfDeparture, BorderLayout.CENTER);panel1.add(TDateOfDeparture, BorderLayout.CENTER);
        panel1.add(btnExt, BorderLayout.CENTER);

        panel2one.add(ExtJDateOfArrival, BorderLayout.CENTER); panel2one.add(TExtJDateOfArrival, BorderLayout.CENTER);
        panel2one.add(ExtJDateOfDeparture, BorderLayout.CENTER); panel2one.add(TExtJDateOfDeparture, BorderLayout.CENTER);
        panel2one.add(btnReminder);
        panel2one.setVisible(false); panel2.add(panel2one, BorderLayout.NORTH);

        for(JRadioButton jrb : radioButtons){
            panel2bis.add(jrb, BorderLayout.CENTER);
        }
        panel2bis.setVisible(false); panel2.add(panel2bis, BorderLayout.NORTH);

        panel3.add(save, BorderLayout.CENTER);

        Dimension maxSize = new Dimension(300, 10);
        panel1.setMaximumSize(maxSize);

        btnExt.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(btnExt.isSelected()){
                    btnExt.setText("Extension on");
                    panel2one.setVisible(true);
                }
                else{
                    btnExt.setText("Extension off"); btnReminder.setText("Reminder off");
                    btnReminder.setSelected(false);
                    panel2one.setVisible(false);panel2bis.setVisible(false);
                }
            }
        });
        btnReminder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(btnReminder.isSelected()){
                    btnReminder.setText("Reminder on");
                    panel2bis.setVisible(true);
                }
                else{
                    btnReminder.setText("Reminder off");
                    panel2bis.setVisible(false);
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean validate = true;
                if(TName.getText()==null){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "Veuillez rentrer un nom.");
                }
                else if(TLastName.getText()==null){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "Veuillez rentrer un prénom.");
                }
                else if(TDateOfArrival.getText()==null){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "Veuillez indiquer une date de début.");
                }
                if(TDateOfDeparture.getText()==null){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "Veuillez indiquer une date de fin.");
                }
                if(Integer.parseInt(TDateOfArrival.getText()) > Integer.parseInt(TDateOfDeparture.getText())){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "La date de début est supérieur à la date de fin.");
                }
                if(btnExt.isSelected()){
                    if(TExtJDateOfArrival.getText()==null){
                        validate=false;
                        JOptionPane.showMessageDialog(null, "Veuillez indiquer une date de renouvellement de contrat.");
                    }
                    if(TExtJDateOfDeparture.getText()==null){
                        validate=false;
                        JOptionPane.showMessageDialog(null, "Veuillez indiquer une date de fin de renouvellement du contrat");
                    }
                    if(Integer.parseInt(TExtJDateOfArrival.getText()) > Integer.parseInt(TExtJDateOfDeparture.getText())){
                        validate=false;
                        JOptionPane.showMessageDialog(null, "La date de début est supérieur à la date de fin.");
                    }
                }

                if(validate){
                    if(btnExt.isSelected()) {
                        if(btnReminder.isSelected())
                            appSNCF.getMainFunction().newPerson(TName.getText(), TLastName.getText(), Integer.parseInt(TDateOfArrival.getText()), Integer.parseInt(TDateOfDeparture.getText()), true,
                                    Integer.parseInt(TExtJDateOfArrival.getText()), Integer.parseInt(TExtJDateOfDeparture.getText()), true, new int[]{10001, 1020324});
                        else
                            appSNCF.getMainFunction().newPerson(TName.getText(), TLastName.getText(), Integer.parseInt(TDateOfArrival.getText()), Integer.parseInt(TDateOfDeparture.getText()), true,
                                    Integer.parseInt(TExtJDateOfArrival.getText()), Integer.parseInt(TExtJDateOfDeparture.getText()), false, new int[]{});
                    }
                    else
                        appSNCF.getMainFunction().newPerson(TName.getText(), TLastName.getText(), Integer.parseInt(TDateOfArrival.getText()), Integer.parseInt(TDateOfDeparture.getText()), false,
                                0, 0, false, new int[]{});
                }
            }
        });

        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);
    }
}
