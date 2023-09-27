package GUI;

import Entity.Kviz;
import Entity.User;
import StartApp.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HrajKviz implements ActionListener {
    private JPanel hrajKvizPanel;
    private JButton moznost1;
    private JButton moznost2;
    private JButton moznost3;
    private JButton moznost4;
    private JLabel kvizNazovLabel;
    private JLabel otazkaLabel;

    private Frame frame;
    private Start start;
    private User user;
    private Kviz kviz;

    private Integer skore = 0;
    private Integer cisloOtazky = 0;

    public HrajKviz(Frame frame, Start start, User user, Kviz kviz) {
        this.frame = frame;
        this.start = start;
        this.user = user;
        this.kviz = kviz;

        moznost1.addActionListener(this);
        moznost2.addActionListener(this);
        moznost3.addActionListener(this);
        moznost4.addActionListener(this);

        this.kvizNazovLabel.setText(this.kviz.getNazovKvizu());
        this.otazkaLabel.setText((cisloOtazky + 1) + ". " + this.kviz.getOtazky().get(cisloOtazky).getTextOtazky());
        this.moznost1.setText(this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(0).getTextMoznosti());
        this.moznost2.setText(this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(1).getTextMoznosti());
        this.moznost3.setText(this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(2).getTextMoznosti());
        this.moznost4.setText(this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(3).getTextMoznosti());

    }

    public JPanel getContent() {
        return hrajKvizPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(moznost1)) {
            if (this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(0).getJeSpravna()) {
                skore++;
            }
        }
        if (e.getSource().equals(moznost2)) {
            if (this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(1).getJeSpravna()) {
                skore++;
            }
        }
        if (e.getSource().equals(moznost3)) {
            if (this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(2).getJeSpravna()) {
                skore++;
            }
        }
        if (e.getSource().equals(moznost4)) {
            if (this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(3).getJeSpravna()) {
                skore++;
            }
        }

        cisloOtazky++;

        if (cisloOtazky >= this.kviz.getOtazky().size()) {
            JOptionPane.showMessageDialog(hrajKvizPanel, "KVIZ STE DOKONCILI : "
                            + skore + "/" + this.kviz.getOtazky().size(),
                    "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
            //metoda na aktualizovanie skore v DB


            this.frame.setContent(new Menu(frame, start, user).getContent());
        } else {

            this.kvizNazovLabel.setText(this.kviz.getNazovKvizu());
            this.otazkaLabel.setText((cisloOtazky + 1) + ". " + this.kviz.getOtazky().get(cisloOtazky).getTextOtazky());
            this.moznost1.setText(this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(0).getTextMoznosti());
            this.moznost2.setText(this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(1).getTextMoznosti());
            this.moznost3.setText(this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(2).getTextMoznosti());
            this.moznost4.setText(this.kviz.getOtazky().get(cisloOtazky).getMoznosti().get(3).getTextMoznosti());

        }
    }
}
