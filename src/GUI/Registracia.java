package GUI;

import Entity.User;
import StartApp.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Registracia implements ActionListener {
    private JPanel registraciaPanel;
    private JTextField textFieldMeno;
    private JTextField textFieldPriezvisko;
    private JTextField textFieldEmail;
    private JButton registrujButton;
    private JPasswordField passwordField1;
    private JLabel emailKontrolaLabel;
    private JLabel hesloKontrolaLabel;
    private JButton LOGINButton;
    private Frame frame;
    private Start start;

    public Registracia(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;

        this.registrujButton.addActionListener(this);
        this.LOGINButton.addActionListener(this);
    }

    public JPanel getContent() {
        return registraciaPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.registrujButton)) {

            //ziskanie hesla z passwordField - je ulozene ako sekvencia znakov => premenime na String
            char[] poleHeslo = passwordField1.getPassword();
            String heslo = new String(poleHeslo);

            if (!textFieldMeno.getText().isEmpty() && !textFieldPriezvisko.getText().isEmpty()
                    && !textFieldEmail.getText().isEmpty() && !heslo.isEmpty()) {

                //vsetky polia su vyplnene pokracujeme kontrolou
                String regexHeslo = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!*])(?=.*[a-zA-Z]).{8,}$";
                String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                if (Pattern.matches(regexHeslo, heslo) && Pattern.matches(regexEmail, textFieldEmail.getText())) {
                    //ak input splna podmienky regexu => pouzivatel registrovany


                    User user = new User(textFieldMeno.getText(), textFieldPriezvisko.getText(), textFieldEmail.getText(), heslo);
                    user.setJeAdmin(0);
                    this.start.getConnection().registrujUsera(user);
                    //prepneme novy frame, MENU aplikacie
                    //JOptionPane.showMessageDialog(frame, "Použivateľ úspešne registrovaný.", "Úspech", JOptionPane.INFORMATION_MESSAGE);
                    this.frame.setContent(new Menu(frame,start,user).getContent());

                } else {
                    String chybajucePrvky = "";

                    String velkePismeno = ".*[A-Z].*";
                    String malePismeno = ".*[a-z].*";
                    String cislo = ".*\\d.*";
                    String specialnyZnak = ".*[@#$%^&+=!*].*";
                    String pocetZnakov = "^.{8,}$";

                    if (!Pattern.matches(velkePismeno, heslo)) {
                        chybajucePrvky += "\t\t velke pismeno |";
                    }
                    if (!Pattern.matches(malePismeno, heslo)) {
                        chybajucePrvky += "\t\t male pismeno |";
                    }
                    if (!Pattern.matches(cislo, heslo)) {
                        chybajucePrvky += "\t\t cislo |";
                    }
                    if (!Pattern.matches(specialnyZnak, heslo)) {
                        chybajucePrvky += "\t\t specialny znak |";
                    }
                    if (!Pattern.matches(pocetZnakov, heslo)) {  //heslo.length() < 8
                        chybajucePrvky += "\t\t aspon 8 znakov";
                    }

                    hesloKontrolaLabel.setText("* " + chybajucePrvky);

                    Boolean pomEmail = Pattern.matches(regexEmail, textFieldEmail.getText());
                    emailKontrolaLabel.setText("");
                    if (!pomEmail) {
                        emailKontrolaLabel.setText("* zly format");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(frame, "Nie sú vyplnené všetky polia.", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        }

        if (e.getSource().equals(this.LOGINButton)) {
            frame.setContent(new Login(frame, start).getContent());
        }
    }
}
