package GUI;

import Entity.User;
import StartApp.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login implements ActionListener {
    private JPanel loginPanel;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;
    private JButton prihlasSaButton;
    private JButton RESITRACIAButton;
    private JLabel warningLabel;
    private Frame frame;
    private Start start;

    public Login(Frame frame, Start start) {
        this.frame = frame;
        this.start = start;

        this.RESITRACIAButton.addActionListener(this);
        this.prihlasSaButton.addActionListener(this);
    }

    public JPanel getContent() {
        return loginPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.prihlasSaButton)) {
            //prihlasenie usera

            //skontrolovat ci sa v DB nachadza zadany email
            //ak nie tak vypisem ze pouzivatel nie je registrovany
            // ak ano tak pokracujem na kontrolu hesla
            // ak zadam spravne heslo => MENU form
            // ak zadam zle heslo tak vypisem "ZLE PRIHLASOVACIE UDAJE"

            String email = textFieldEmail.getText();
            char[] poleHeslo = passwordField.getPassword();
            String heslo = new String(poleHeslo);

            User pomUser = null;
            if (!email.isEmpty() && !heslo.equals("")){
                for (User u : this.start.getConnection().nacitajUserov()) {
                    if (u.getEmail().equals(email)){
                        //pouzivatel je uz registrovany
                        pomUser = u;
                        break;
                    }
                }
            }
            if (pomUser != null){
                //kontrola hesla
                if (heslo.equals(pomUser.getHeslo())){
                    //pustime ho dalej do APP => MENU form
                    //JOptionPane.showMessageDialog(frame, "Použivateľ úspešne prihlasený.", "Úspech", JOptionPane.INFORMATION_MESSAGE);
                    this.frame.setContent(new Menu(frame,start,pomUser).getContent());
                }else{
                    warningLabel.setText("");
                    warningLabel.setText("* nespravne heslo");
                }
            }else{
                warningLabel.setText("");
                warningLabel.setText("* email nie je registrovany");
            }
        }
        if (e.getSource().equals(this.RESITRACIAButton)) {
            frame.setContent(new Registracia(frame, start).getContent());
        }
    }
}
