package StartApp;

import Entity.User;

import java.sql.*;
import java.util.ArrayList;

public class DbConnection {
    //SINGLETON - jedna instancia triedy DbConnetion v celom projekte
    // - privatny konstruktor
    // - get Metoda, ktora vrati celu instanciu triedy
    private static DbConnection instancia; //instance
    private Connection connection;

    //privatny konstruktor
    private DbConnection() {
        try {

            //1 sposob : ulozenie si hesla do systemovych premennych zariadenia
            //String heslo = System.getenv("DB_CON_PASSWORD");
            //2 sposob : ulozenie si hesla do systemovych premennych konfiguracie spustenia aplikacie
            String heslo = System.getenv("DB_CONN");
            //3 sposob : vytiahnutie hesla zo suboru .env
            //otestovanie driveru
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Podarilo sa najst driver.");
            //vytvorenie pripojenia na lokalnu databazu
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/java_db_conn", "root", heslo);
            System.out.println("Podarilo sa pripojit na DB");

        } catch (ClassNotFoundException e) {
            System.out.println("Nenasiel sa Driver na pripojenie k DB");
        } catch (SQLException e) {
            System.out.println(e + "\nNepodarilo sa vytvorit spojenie s DB");
        }
    }

    //get metoda na vratenie instancie
    public static DbConnection getInstancia() {
        if (instancia == null) {
            instancia = new DbConnection();
        }
        return instancia;
    }

    public void registrujUsera(User user) {
        String query = "INSERT INTO Users(meno, priezvisko, email, heslo, jeAdmin) VALUES (?,?,?,?,?)";
        //naplnit query datami namiesto otaznikov
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getMeno());
            preparedStatement.setString(2, user.getPriezvisko());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getHeslo());
            preparedStatement.setInt(5, 0);

            System.out.println("Podarilo sa vlozit usera: " + preparedStatement.executeUpdate() + " row(s) affected.");

        } catch (SQLException e) {
            System.out.println("Nepodarilo sa poslat query na DB " + e);
        }
    }

    public ArrayList<User> nacitajUserov() {
        ArrayList<User> listUserov = new ArrayList<>();

        String query = "SELECT * FROM Users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User(rs.getString("meno"),
                        rs.getString("priezvisko"),
                        rs.getString("email"),
                        rs.getString("heslo"));
                user.setUserId(rs.getInt("userId"));
                user.setJeAdmin(rs.getInt("jeAdmin"));
                user.setSkore(rs.getInt("skore"));

                listUserov.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return listUserov;
    }


    /**
     * metoda na vymazanie usera podla emailu z DB
     * @param email - pouzivatela zakliknuteho v tabulke
     * @return true - bol pouzivatel vymazany, false - nebol vymazany alebo nastala chyba
     */
    public Boolean vymazUsera(String email){
        String query = "DELETE FROM Users WHERE email = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1,email);

            Integer rowsAffected = preparedStatement.executeUpdate();

            /*if (rowsAffected > 0){
                return true;
            }else{
                return false;
            }*/

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
