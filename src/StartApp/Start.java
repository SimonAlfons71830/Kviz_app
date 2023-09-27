package StartApp;

import Entity.Kategoria;
import Entity.Kviz;
import Entity.Moznost;
import Entity.Otazka;
import GUI.Frame;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Start {
    private DbConnection connection;
    private ArrayList<Kviz> kvizy = new ArrayList<>();

    //private String filepath = "kvizy"; //absolutna cesta C:\Users\Simona\Desktop\macrosoft\java03\JAVAII\Kviz_aplikacia\kvizy
    private String filepath = System.getenv("FILEPATH");
    public void start() {
        connection = DbConnection.getInstancia();
        this.nacitajKvizyZPriecinku(filepath);
        Frame frame = new Frame(this);
    }

    public DbConnection getConnection() {
        return connection;
    }

    public void nacitajKvizZTXT(File file) {
        Kviz kviz = new Kviz();

        try {
            List<String> riadky = Files.readAllLines(file.toPath());
            //z prveho riadku si vytiahneme udaje o kvize
            String[] infoOKvize = riadky.get(0).split(";");
            //nazov kvizu
            kviz.setNazovKvizu(infoOKvize[0]);
            //kategoria kvizu
            kviz.setKategoria(Kategoria.valueOf(infoOKvize[1]));
            //obtiaznost kvizu
            kviz.setObtiaznost(Integer.valueOf(infoOKvize[2])); //Integer.parseInt(infoOKvize[2])

            //z ostatnych riadkov z listu nacitavame otazky
            ArrayList<Otazka> otazky = new ArrayList<>();

            for (int i = 1; i < riadky.size(); i++) {
                //pole kde si budeme uchovavat otazku
                String[] otazka = riadky.get(i).split(";");

                String nazovOtazky = otazka[0];

                //novy AL kde sa budu ukladat moznosti
                ArrayList<Moznost> moznosti = new ArrayList<>();
                for (int j = 1; j < otazka.length; j += 2) {
                    moznosti.add(new Moznost(otazka[j], otazka[j + 1].equals("1")));
                }

                //nacitany riadok o otazke sa nam ulozi do arraylistu otazok
                otazky.add(new Otazka(nazovOtazky, moznosti));
            }

            kviz.setOtazky(otazky);
            kvizy.add(kviz);

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void vymazKviz(String nazovKvizu) {
        Kviz kvizNaVymazanie = null;
        for (Kviz k : this.kvizy) {
            if (k.getNazovKvizu().equals(nazovKvizu)) {
                kvizNaVymazanie = k;
                break;
            }
        }

        //vymazanie kvizu z lokalnej kolekcie kvizy
        if (kvizNaVymazanie != null) {
            kvizy.remove(kvizNaVymazanie);
        } else {
            System.out.println("Kviz " + nazovKvizu + " sa nenasiel.");
        }

    }

    /**
     * metoda na nacitanie vsetkych .txt suborov, ktore predstavuju kvizy z priecinka "kvizy"
     * @return z metody bey načítania .txt suborov ak File nie je adresar alebo ak je prázdny
     */
    public ArrayList<Kviz> getKvizy() {
        return kvizy;
    }

    public void nacitajKvizyZPriecinku(String cestaKPriecinku){
        //vytvorime si lokalne v kode priecinok pomocou cesty
        File priecinok = new File(cestaKPriecinku);
        //chceme pokracovat iba ak je to adresar/priecinok kde su ulozene ine subory
        if (!priecinok.exists() || !priecinok.isDirectory()){
            System.out.println("Priecinok neexistuje alebo to nie je adresar!");
            //nepokracujeme v metode
            return;
        }
        //vytvorime si pole, kde si nacitame vsetky subory z adresaru
        File[] poleKvizov = priecinok.listFiles();

        if (poleKvizov == null){
            System.out.println("Adresar/priecinok je prazdny!");
            return;
        }

        for (File f: poleKvizov){
            if (f.getName().endsWith(".txt") && f.isFile()){
                this.nacitajKvizZTXT(f);
            }
        }
    }
}
