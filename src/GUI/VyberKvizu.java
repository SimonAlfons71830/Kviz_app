package GUI;

import Entity.Kategoria;
import Entity.Kviz;
import Entity.User;
import StartApp.Start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VyberKvizu implements ActionListener {
    private JPanel vyberKvizuPanel;
    private JTable table1;
    private JButton HRAJButton;
    private JButton spatNaMenuButton;
    private JComboBox comboBoxKategoria;
    private JComboBox comboBoxObtiaznost;

    private Frame frame;
    private Start start;
    private User user;

    public VyberKvizu(Frame frame, Start start, User user) {
        this.frame = frame;
        this.start = start;
        this.user = user;

        HRAJButton.addActionListener(this);
        spatNaMenuButton.addActionListener(this);
        comboBoxKategoria.addActionListener(this);
        comboBoxObtiaznost.addActionListener(this);
        //naplnenie tabulky
        this.nacitajKvizyDoTab();
        //naplnenie komboboxov
        this.nacitajKomboboxy();


    }

    public void nacitajKomboboxy() {
        DefaultComboBoxModel<Kategoria> kategoriaDefaultComboBoxModel = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Integer> obtiaznostDefaultComboBoxModel = new DefaultComboBoxModel<>();

        kategoriaDefaultComboBoxModel.addElement(null);
        for (Kategoria k : Kategoria.values()) {
            kategoriaDefaultComboBoxModel.addElement(k);
        }

        obtiaznostDefaultComboBoxModel.addElement(null);
        for (int i = 1; i <= 5; i++) {
            obtiaznostDefaultComboBoxModel.addElement(i);
        }

        comboBoxKategoria.setModel(kategoriaDefaultComboBoxModel);
        comboBoxObtiaznost.setModel(obtiaznostDefaultComboBoxModel);

    }

    public void nacitajKvizyDoTab() {
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

    public void resetujTabulku() {
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        table1.setModel(model);
    }

    public void aktualizuj(Kategoria kategoria){
        DefaultTableModel model = (DefaultTableModel) table1.getModel();

        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Nazov");
        model.addColumn("Kategória");
        model.addColumn("Obtiažnosť");

        for (Kviz k : this.start.getKvizy()) {
            if (k != null && (kategoria == null || k.getKategoria() == kategoria)) {
                model.addRow(new Object[]{k.getNazovKvizu(), k.getKategoria(), k.getObtiaznost()});
            }
        }
        table1.setModel(model);
    }

    public void aktualizuj(Integer obtiaznost){
        DefaultTableModel model = (DefaultTableModel) table1.getModel();

        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Nazov");
        model.addColumn("Kategória");
        model.addColumn("Obtiažnosť");

        for (Kviz k : this.start.getKvizy()) {
            if (k != null && (obtiaznost == null || k.getObtiaznost() == obtiaznost)) {
                model.addRow(new Object[]{k.getNazovKvizu(), k.getKategoria(), k.getObtiaznost()});
            }
        }
        table1.setModel(model);
    }
    public JPanel getContent() {
        return vyberKvizuPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(spatNaMenuButton)) {
            this.frame.setContent(new Menu(frame, start, user).getContent());
        }
        if (e.getSource().equals(comboBoxKategoria)) {
            Kategoria kategoria = (Kategoria) comboBoxKategoria.getSelectedItem();
            this.resetujTabulku();
            this.aktualizuj(kategoria);

        }
        if (e.getSource().equals(comboBoxObtiaznost)) {
            Integer obtiaznost = (Integer) comboBoxObtiaznost.getSelectedItem();
            this.resetujTabulku();
            this.aktualizuj(obtiaznost);

        }
        if(e.getSource().equals(HRAJButton)){
            Integer zakliknutyRiadok = table1.getSelectedRow();
            if (zakliknutyRiadok >= 0 ){
                String nazovKvizu = table1.getValueAt(zakliknutyRiadok,0).toString();
                for (Kviz k : this.start.getKvizy()){
                    if (k != null && k.getNazovKvizu().equals(nazovKvizu)){
                        this.frame.setContent(new HrajKviz(frame, start, user, k).getContent());
                        break;
                    }
                }

            }else{
                JOptionPane.showMessageDialog(vyberKvizuPanel,"Nebol zakliknuty kviz.");
            }
        }
    }
}
