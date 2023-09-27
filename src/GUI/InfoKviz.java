package GUI;

import Entity.Kategoria;
import Entity.Kviz;
import Entity.User;
import StartApp.Start;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoKviz implements ActionListener {
    private JPanel vytvorKvizPanel;
    private JTextField nazovKvizTextField;
    private JComboBox comboBoxKategoria;
    private JSlider sliderObtiaznost;
    private JButton spatNaMENUButton;
    private JButton pokracujButton;

    private Frame frame;
    private Start start;
    private User user;

    public InfoKviz(Frame frame, Start start, User user) {
        this.frame = frame;
        this.start = start;
        this.user = user;

        comboBoxKategoria.setModel(new DefaultComboBoxModel<>(Kategoria.values()));

        pokracujButton.addActionListener(this);
        spatNaMENUButton.addActionListener(this);

    }

    public JPanel getContent() {
        return vytvorKvizPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(spatNaMENUButton)){
            this.frame.setContent(new AdminMenu(frame,start,user).getContent());
        }
        if (e.getSource().equals(pokracujButton)){
            Kviz kviz = new Kviz();

            if (!nazovKvizTextField.getText().isEmpty() && comboBoxKategoria.getSelectedItem() !=null){
                kviz.setNazovKvizu(nazovKvizTextField.getText());
                kviz.setKategoria((Kategoria) comboBoxKategoria.getSelectedItem());
                kviz.setObtiaznost(sliderObtiaznost.getValue());


                this.frame.setContent(new InfoOtazky(frame,start,user,kviz).getContent());

            }


        }
    }
}
