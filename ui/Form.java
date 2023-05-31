package ui;


import domain.Person;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.*;
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
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.requestFocus();
        this.setVisible(true);
    }

    private void init(){
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel JName = new JLabel(" First Name :");
        JLabel JLastName = new JLabel(" Last Name :");
        JLabel JDateOfArrival = new JLabel(" Date of arrival");
        JLabel JDateOfDeparture = new JLabel(" Date of departure");

        JTextField TName = new JTextField();
        JTextField TLastName = new JTextField();

        UtilDateModel modelB = new UtilDateModel();Properties propertiesB = new Properties();
        UtilDateModel modelE = new UtilDateModel();Properties propertiesE = new Properties();
        modelB.setSelected(true); modelE.setSelected(true);
        JDatePanelImpl datePanelB = new JDatePanelImpl(modelB, propertiesB); JDatePanelImpl datePanelE = new JDatePanelImpl(modelE, propertiesE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        JFormattedTextField.AbstractFormatter dateFormatter = new JFormattedTextField.AbstractFormatter() {
            @Override
            public Object stringToValue(String text) throws java.text.ParseException {
                return dateFormat.parseObject(text);
            }
            @Override
            public String valueToString(Object value) throws java.text.ParseException {
                if (value instanceof java.util.Date) {
                    return dateFormat.format((java.util.Date) value);
                }
                return "";
            }
        };
        JDatePickerImpl datePickerBegin = new JDatePickerImpl(datePanelB, dateFormatter);
        JDatePickerImpl datePickerEnd = new JDatePickerImpl(datePanelE, dateFormatter);

        JToggleButton btnExt = new JToggleButton("Extension off");

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(2, 1, 5, 5));

        JPanel panel2one = new JPanel();
        panel2one.setLayout(new GridLayout(3, 2));
        JLabel ExtJDateOfArrival = new JLabel(" Date of arrival :");
        JLabel ExtJDateOfDeparture = new JLabel(" Date of departure :");

        UtilDateModel modelBExt = new UtilDateModel();Properties propertiesBExt = new Properties();
        UtilDateModel modelEExt = new UtilDateModel();Properties propertiesEExt = new Properties();
        modelBExt.setSelected(true); modelEExt.setSelected(true);
        JDatePanelImpl datePanelBExt = new JDatePanelImpl(modelBExt, propertiesBExt); JDatePanelImpl datePanelEExt = new JDatePanelImpl(modelEExt, propertiesEExt);
        JDatePickerImpl datePickerBeginExt = new JDatePickerImpl(datePanelBExt, dateFormatter);
        JDatePickerImpl datePickerEndExt = new JDatePickerImpl(datePanelEExt, dateFormatter);

        JToggleButton btnReminder = new JToggleButton("Reminder off");

        JPanel panel2bis = new JPanel();
        panel2bis.setLayout(new GridLayout(10, 1, 5, 5));
        String[] options = {"1 day before", "2 days before", "3 days before", "4 days before", "5 days before", "6 days before", "1 week before", "2 weeks before", "3 weeks before", "1 month before"};
        SortedMap<Integer, JRadioButton> radioButtons = new TreeMap<>();int i=0;
        for(String option : options){
            radioButtons.put(i, new JRadioButton(option));
            i++;
        }

        JPanel panel3 = new JPanel();
        Button save = new Button("Save");

        panel1.add(JName, BorderLayout.CENTER); panel1.add(TName, BorderLayout.CENTER);
        panel1.add(JLastName, BorderLayout.CENTER);panel1.add(TLastName, BorderLayout.CENTER);
        panel1.add(JDateOfArrival, BorderLayout.CENTER);panel1.add(datePickerBegin, BorderLayout.CENTER);
        panel1.add(JDateOfDeparture, BorderLayout.CENTER);panel1.add(datePickerEnd, BorderLayout.CENTER);
        panel1.add(btnExt, BorderLayout.CENTER);

        panel2one.add(ExtJDateOfArrival, BorderLayout.CENTER); panel2one.add(datePickerBeginExt, BorderLayout.CENTER);
        panel2one.add(ExtJDateOfDeparture, BorderLayout.CENTER); panel2one.add(datePickerEndExt, BorderLayout.CENTER);
        panel2one.add(btnReminder);
        panel2one.setVisible(false); panel2.add(panel2one, BorderLayout.NORTH);
        radioButtons.forEach((key, value) -> panel2bis.add(value, BorderLayout.CENTER));
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
                Date dateBegin = (Date) datePickerBegin.getModel().getValue();
                Date dateEnd = (Date) datePickerEnd.getModel().getValue();
                Date dateBeginExt = (Date) datePickerBeginExt.getModel().getValue();
                Date dateEndExt = (Date) datePickerBeginExt.getModel().getValue();

                if(TName.getText()==null){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "Veuillez rentrer un nom.");
                }
                if(TLastName.getText()==null){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "Veuillez rentrer un prénom.");
                }
                if(dateBegin==null){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "Veuillez indiquer une date de début.");
                }
                if(dateEnd==null){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "Veuillez indiquer une date de fin.");
                }
                if((long)dateBegin.getTime() >= (long)dateEnd.getTime()){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "La date de début est supérieur à la date de fin.");
                }
                if((long)dateEnd.getTime() <= (long)(new Date()).getTime()){
                    validate=false;
                    JOptionPane.showMessageDialog(null, "La date de fin de contrat est déjà passée.");
                }
                if(btnExt.isSelected()){
                    if(dateBeginExt == null){
                        validate=false;
                        JOptionPane.showMessageDialog(null, "Veuillez indiquer une date de renouvellement de contrat.");
                    }
                    if(dateEndExt==null){
                        validate=false;
                        JOptionPane.showMessageDialog(null, "Veuillez indiquer une date de fin de renouvellement du contrat");
                    }
                    if((long)dateBeginExt.getTime() > (long)dateEndExt.getTime()){
                        validate=false;
                        JOptionPane.showMessageDialog(null, "La date de début de renouvellement est supérieure à la date de fin de renouvellement.");
                    }
                    if((long)dateBeginExt.getTime() < (long)dateEnd.getTime()){
                        validate=false;
                        JOptionPane.showMessageDialog(null, "La date de début de renouvellement est inférieure à la date de fin du contrat.");
                    }
                }

                SortedMap<Integer, Boolean> opt = new TreeMap<>();
                radioButtons.forEach((key, value) -> opt.put(key, value.isSelected()));

                if(validate){
                    if(btnExt.isSelected()) {
                        if(btnReminder.isSelected()) {
                            appSNCF.getMainFunction().newPerson(TName.getText(), TLastName.getText(), (long) dateBegin.getTime(), (long) dateEnd.getTime(), true,
                                    (long) dateBeginExt.getTime(), (long) dateEndExt.getTime(), true, opt);
                        }
                        else
                            appSNCF.getMainFunction().newPerson(TName.getText(), TLastName.getText(), (long)dateBegin.getTime(), (long)dateEnd.getTime(), true,
                                    (long)dateBeginExt.getTime(), (long)dateEndExt.getTime(), false, opt);
                    }
                    else
                        appSNCF.getMainFunction().newPerson(TName.getText(), TLastName.getText(), (long)dateBegin.getTime(), (long)dateEnd.getTime(), false,
                                0, 0, false, opt);
                }
            }
        });

        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);
    }
}
