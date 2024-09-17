package OV;

import lib.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            ReizigerDAOPsql reizigerDAO = new ReizigerDAOPsql(session);

            testReizigerDAO(reizigerDAO);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.shutdown();
        }
    }

    private static void testReizigerDAO(ReizigerDAO rdao) throws SQLException {

        System.out.println("\n---------- Test ReizigerDAO findAll methode -------------");

        // Haal alle reizigers op uit de database
        List<Reiziger> reizigers = rdao.findAll();
        System.out.println("[Test] ReizigerDAO.findAll() geeft de volgende reizigers:");
        for (Reiziger r : reizigers) {
            System.out.println(r);
        }
        System.out.println();

        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

        System.out.println("\n---------- Test ReizigerDAO save methode -------------");
        // Maak een nieuwe reiziger aan en persisteer deze in de database
        String gbdatum = "1981-03-14";
        Reiziger sietske = new Reiziger(8, "S", "", "Koot", java.sql.Date.valueOf(gbdatum));
        System.out.print("[Test] Eerst " + reizigers.size() + " reizigers, na ReizigerDAO.save() ");
        rdao.save(sietske);
        reizigers = rdao.findAll();
        System.out.println(reizigers.size() + " reizigers\n");


//        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

        System.out.println("\n---------- Test ReizigerDAO Update methode -------------");

        Reiziger bestaandeReiziger = rdao.findById(1);

        if (bestaandeReiziger != null) {

            bestaandeReiziger.setVoorLetters("G");
            bestaandeReiziger.setTussenVoegsel("van");
            bestaandeReiziger.setAchterNaam("de jong");
            bestaandeReiziger.setGeboorteDatum(java.sql.Date.valueOf("2002-09-17"));


            rdao.update(bestaandeReiziger);

            String updatedAchternaam = bestaandeReiziger.getAchterNaam();

            System.out.println("Updated achternaam: " + updatedAchternaam);


        }


//        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//

        System.out.println("\n---------- Test ReizigerDAO findById methode -------------");
        int id = 1;

        Reiziger reiziger = rdao.findById(id);

        if (reiziger != null) {
            System.out.println("reiziger: aan de hand van de ID" + reiziger);
        } else {
            System.out.println("geen reiziger gevonden met correcte id");
        }


//        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//


        System.out.println("\n---------- Test ReizigerDAO findByGb methode -------------");

        LocalDate date = LocalDate.of(1981, 3, 14);

        List<Reiziger> reizigerss = rdao.findByGbDatum(date);

        for (Reiziger r : reizigerss) {

            System.out.println("reiziger gevonden aan de hand van GB datum: " + r);
        }


        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------//


        System.out.println("\n---------- Test ReizigerDAO Delete methode -------------");

        List<Reiziger> reizigers2 = rdao.findAll();
        System.out.println("Aantal reizigers voor het verwijderen: " + reizigers2.size());

        rdao.delete(sietske);


        reizigers = rdao.findAll();
        System.out.println("Aantal reizigers na verwijderen: " + reizigers.size());


    }
}




