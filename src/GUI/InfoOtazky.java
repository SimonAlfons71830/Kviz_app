package GUI;

import Entity.Kviz;
import Entity.Moznost;
import Entity.Otazka;
import Entity.User;
import StartApp.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InfoOtazky implements ActionListener {
    private JPanel otazkyPanel;
    private JTextField textFieldOtazka;
    private JTextField textFieldMoznost1;
    private JTextField textFieldMoznost2;
    private JTextField textFieldMoznost3;
    private JTextField textFieldMoznost4;
    private JButton dalsiaOtazkaButton;
    private JButton KONIECButton;
    private JButton zrusButton;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JLabel infoLabel;
    private JLabel pocetOtazokLabel;

    private Frame frame;
    private Start start;
    private User user;

    private Kviz kviz;

    private ButtonGroup buttonGroup = new ButtonGroup();

    public InfoOtazky(Frame frame, Start start, User user, Kviz kviz) {
        this.frame = frame;
        this.start = start;
        this.user = user;
        this.kviz = kviz;

        KONIECButton.addActionListener(this);
        dalsiaOtazkaButton.addActionListener(this);
        zrusButton.addActionListener(this);

        KONIECButton.setEnabled(false);

        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);

    }


    public JPanel getContent() {
        return otazkyPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(zrusButton)) {
            this.frame.setContent(new AdminMenu(frame, start, user).getContent());
        }
        if (e.getSource().equals(KONIECButton)) {
            this.start.getKvizy().add(kviz);
            //metoda pre pridanie kvizu aj do priecinku kvizy ako .txt file
            this.start.ulozKviz(kviz);
            this.frame.setContent(new AdminMenu(frame, start, user).getContent());
        }
        if (e.getSource().equals(dalsiaOtazkaButton)) {
            if (!textFieldOtazka.getText().isEmpty()
                    && !textFieldMoznost1.getText().isEmpty()
                    && !textFieldMoznost2.getText().isEmpty()
                    && !textFieldMoznost3.getText().isEmpty()
                    && !textFieldMoznost4.getText().isEmpty()) {

                if (buttonGroup.getSelection() == null){
                    infoLabel.setText("* jedna možnosť musí byť správna");
                }else{
                    ArrayList<Moznost> moznosti = new ArrayList<>();

                    moznosti.add(new Moznost(textFieldMoznost1.getText(),radioButton1.isSelected()));
                    moznosti.add(new Moznost(textFieldMoznost2.getText(),radioButton2.isSelected()));
                    moznosti.add(new Moznost(textFieldMoznost3.getText(),radioButton3.isSelected()));
                    moznosti.add(new Moznost(textFieldMoznost4.getText(),radioButton4.isSelected()));

                    kviz.pridajOtazku(new Otazka(textFieldOtazka.getText(), moznosti));

                    //aktualizacia formu na pridanie dalsej otazky
                    textFieldOtazka.setText("");
                    textFieldMoznost1.setText("");
                    textFieldMoznost2.setText("");
                    textFieldMoznost3.setText("");
                    textFieldMoznost4.setText("");
                    infoLabel.setText("");
                    buttonGroup.clearSelection();

                    pocetOtazokLabel.setText("Otazka bola pridaná. " + this.kviz.getOtazky().size() + "/5");

                    if (this.kviz.getOtazky().size() >= 5){
                        KONIECButton.setEnabled(true);
                    }
                }
            }else{
                infoLabel.setText("* musia byt vyplnené všetky polia");
            }
        }
    }
}
