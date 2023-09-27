package GUI;

import Entity.User;
import StartApp.Start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class MojeSkore implements ActionListener {
    private JPanel mojeSkorePanel;
    private JLabel userSkoreLabel;
    private JTable table1;
    private JButton menuButton;

    private Frame frame;
    private Start start;
    private User user;

    public MojeSkore(Frame frame, Start start, User user) {
        this.frame = frame;
        this.start = start;
        this.user = user;

        menuButton.addActionListener(this);

        userSkoreLabel.setText("Tvoje aktuálne skóre : " + this.user.getSkore());
        this.nacitajUserov();

    }

    public void nacitajUserov() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();

        model.setRowCount(0);
        model.setColumnCount(0);


        model.addColumn("Poradie");
        model.addColumn("Meno");
        model.addColumn("Priezvisko");
        model.addColumn("Email");
        model.addColumn("Skore");

        model.addRow(new Object[]{model.getColumnName(0), model.getColumnName(1), model.getColumnName(2), model.getColumnName(3), model.getColumnName(4)});

        ArrayList<User> pomUsers = this.start.getConnection().nacitajUserov();
        Collections.sort(pomUsers);

        Integer pocitadlo = 1;

        for (User u : pomUsers) {
            if (u != null) {
                model.addRow(new Object[]{pocitadlo, u.getMeno(), u.getPriezvisko(), u.getEmail(), u.getSkore()});
                //zvyraznit riadok ktory je aktualny user
                pocitadlo++;
            }
        }
    }

    public JPanel getContent() {
        return mojeSkorePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(menuButton)) {
            this.frame.setContent(new Menu(frame, start, user).getContent());
        }
    }
}
