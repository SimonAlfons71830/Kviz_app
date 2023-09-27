package GUI;

import Entity.User;
import StartApp.Start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrovaniPouzivatelia implements ActionListener {
    private JPanel registrovaniPouzivateliaPanel;
    private JTable table1;
    private JButton vymazPouzivatelaButton;
    private JButton spatNaMENUButton;

    private Frame frame;
    private Start start;
    private User user;

    public RegistrovaniPouzivatelia(Frame frame, Start start, User user) {
        this.frame = frame;
        this.start = start;
        this.user = user;

        vymazPouzivatelaButton.addActionListener(this);
        spatNaMENUButton.addActionListener(this);

        this.nacitajUserovDoTab();
    }

    public JPanel getContent() {
        return registrovaniPouzivateliaPanel;
    }

    public void nacitajUserovDoTab() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();

        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Meno");
        model.addColumn("Priezvisko");
        model.addColumn("Email");
        model.addColumn("Je Admin?");
        model.addRow(new Object[]{model.getColumnName(0), model.getColumnName(1),
                model.getColumnName(2), model.getColumnName(3)});

        for (User u : this.start.getConnection().nacitajUserov()) {
            if (u != null) {
                //if (!u.getEmail().equals(this.user.getEmail())) {
                    model.addRow(new Object[]{u.getMeno(), u.getPriezvisko(),
                            u.getEmail(), u.getJeAdmin() == 1 ? "admin" : "x"});
                //}
            }
        }
        table1.setModel(model);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(spatNaMENUButton)) {
            this.frame.setContent(new AdminMenu(frame, start, user).getContent());
        }
        if (e.getSource().equals(vymazPouzivatelaButton)) {
            Integer zakliknutyRiadok = table1.getSelectedRow();

            if (zakliknutyRiadok != -1) { //aby bol zakliknuty nejaky riadok
                if (zakliknutyRiadok != 0) { //aby nebola zakliknuta hlavicka tabulky
                    if (table1.getValueAt(zakliknutyRiadok, 3) == "admin") { //aby nebol zakliknuty pouzivatel ADMIN
                        JOptionPane.showMessageDialog(registrovaniPouzivateliaPanel, "Nemôžeš vymazať iného ADMINA", "WARNING", JOptionPane.WARNING_MESSAGE);
                    } else {
                        //ziskanie email-u zo zakliknuteho riadku
                        String email = table1.getValueAt(zakliknutyRiadok, 2).toString();
                        //metoda na vymazanie usera
                        this.start.getConnection().vymazUsera(email);
                        //aktualizacia tabulky
                        this.nacitajUserovDoTab();
                    }
                } else {
                    JOptionPane.showMessageDialog(registrovaniPouzivateliaPanel, "Nie je zakliknuty riadok!!!", "WARNING", JOptionPane.WARNING_MESSAGE);
                }

            }
        }
    }
}
