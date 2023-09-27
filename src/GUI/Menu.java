package GUI;

import Entity.User;
import StartApp.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    private JPanel menuPanel;
    private JButton vyberKvizuButton;
    private JButton mojeSkoreButton;
    private JButton odhlasitSaButton;
    private JButton adminMenuButton;
    private JLabel userLabel;

    private Frame frame;
    private Start start;
    private User user;

    public Menu(Frame frame, Start start, User user) {
        this.frame = frame;
        this.start = start;
        this.user = user;

        userLabel.setText(this.user.getMeno() + " " + this.user.getPriezvisko());

        mojeSkoreButton.addActionListener(this);
        vyberKvizuButton.addActionListener(this);
        odhlasitSaButton.addActionListener(this);
        adminMenuButton.addActionListener(this);

        //ak prihlaseny user nie je admin tak sa mu nezobrazi button ktory prepina na admin menu
        if (this.user.getJeAdmin() == 0){
            adminMenuButton.setVisible(false);
        }

    }

    public JPanel getContent() {
        return menuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(odhlasitSaButton)){
            this.frame.setContent(new Login(frame,start).getContent());
        }
        if (e.getSource().equals(adminMenuButton)){
            this.frame.setContent(new AdminMenu(frame,start,user).getContent());
        }

        if (e.getSource().equals(vyberKvizuButton)){
            this.frame.setContent(new VyberKvizu(frame,start,user).getContent());
        }
    }
}
