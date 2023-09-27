package GUI;

import Entity.Kviz;
import Entity.User;
import StartApp.Start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrovaneKvizy implements ActionListener {
    private JPanel registrovaneKvizyPanel;
    private JTable table1;
    private JButton vymazKvizButton;
    private JButton spatNaMENUButton;

    private Frame frame;
    private Start start;
    private User user;

    public RegistrovaneKvizy(Frame frame, Start start, User user) {
        this.frame = frame;
        this.start = start;
        this.user = user;

        this.naplnTabulku();

        vymazKvizButton.addActionListener(this);
        spatNaMENUButton.addActionListener(this);

    }

    public void naplnTabulku() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();

        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Nazov");
        model.addColumn("Kategória");
        model.addColumn("Obtiažnosť");

        for (Kviz k : this.start.getKvizy()) {
            if (k != null) {
                model.addRow(new Object[]{k.getNazovKvizu(), k.getKategoria(), k.getObtiaznost()});
            }
        }
        table1.setModel(model);

    }

    public JPanel getContent() {
        return registrovaneKvizyPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(spatNaMENUButton)) {
            this.frame.setContent(new AdminMenu(frame, start, user).getContent());
        }
        if (e.getSource().equals(vymazKvizButton)) {
            Integer zakliknutyRiadok = table1.getSelectedRow();

            if (zakliknutyRiadok >= 0) {
                String nazovKvizu = table1.getValueAt(zakliknutyRiadok, 0).toString();
                //metoda na vymazanie kvizu
                this.start.vymazKviz(nazovKvizu);

                //odstranenie riadku z tabulky
                ((DefaultTableModel) table1.getModel()).removeRow(zakliknutyRiadok);
            } else {
                JOptionPane.showMessageDialog(registrovaneKvizyPanel, "Nie je zakliknuty kviz.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
