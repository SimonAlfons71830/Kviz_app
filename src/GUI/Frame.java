package GUI;

import StartApp.Start;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(Start start) {
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //zvacsovanie a zmensovanie dialogoveho okna
        setResizable(false);
        //dialogove okno je na celu obrazovku
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //nastavenie nazvu dialogoveho okna
        setTitle("Kviz aplikacia");
        //po otvoreni dialogoveho okna sa vycetruje na stred obrazovky
        setLocationRelativeTo(null);
        //nastavenie farby
        getContentPane().setBackground(Color.WHITE);

        Registracia registracia = new Registracia(this, start);
        setContent(registracia.getContent());

        //okno bude viditelne
        setVisible(true);
    }

    public void setContent(JPanel novyContent) {
        setContentPane(novyContent);
        //refresh dialogoveho okna
        revalidate();
        //premalovanie contentu
        repaint();
    }
}
