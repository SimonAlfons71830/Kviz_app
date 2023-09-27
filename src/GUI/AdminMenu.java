package GUI;

import Entity.User;
import StartApp.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu implements ActionListener {
    private JPanel adminMenuPanel;
    private JLabel userLabel;
    private JButton spatNaMENUButton;
    private JButton registrovaniPouzivateliaButton;
    private JButton registrovaneKvizyButton;
    private JButton vytvorKvizButton;

    private Frame frame;
    private Start start;
    private User user;

    public AdminMenu(Frame frame, Start start, User user) {
        this.frame = frame;
        this.start = start;
        this.user = user;

        spatNaMENUButton.addActionListener(this);
        registrovaniPouzivateliaButton.addActionListener(this);
        registrovaneKvizyButton.addActionListener(this);
        vytvorKvizButton.addActionListener(this);

        userLabel.setText(this.user.getMeno() + " " + this.user.getPriezvisko());
    }

    public JPanel getContent() {
        return adminMenuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(spatNaMENUButton)) {
            this.frame.setContent(new Menu(frame, start, user).getContent());
        }
        if (e.getSource().equals(registrovaniPouzivateliaButton)) {
            this.frame.setContent(new RegistrovaniPouzivatelia(frame, start, user).getContent());
        }
        if (e.getSource().equals(registrovaneKvizyButton)) {
            this.frame.setContent(new RegistrovaneKvizy(frame, start, user).getContent());
        }
        if (e.getSource().equals(vytvorKvizButton)){
            this.frame.setContent(new InfoKviz(frame,start,user).getContent());
        }
    }
}
