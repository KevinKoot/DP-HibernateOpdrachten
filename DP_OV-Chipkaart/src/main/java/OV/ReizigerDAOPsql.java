package OV;

import OV.Reiziger;
import OV.ReizigerDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPsql implements ReizigerDAO {

    private final Session session;


    public ReizigerDAOPsql(Session session) {
        this.session = session;
    }


    @Override
    public boolean save(Reiziger reiziger) {
        try{
            session.beginTransaction();
            session.persist(reiziger);
            session.getTransaction().commit();
            return true;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try{
            session.beginTransaction();
            session.merge(reiziger);
            session.getTransaction().commit();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try{
            session.beginTransaction();
            session.remove(reiziger);
            session.getTransaction().commit();

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Reiziger findById(int id) {
        try{
            return session.createQuery("from Reiziger where id = :id", Reiziger.class)
                    .setParameter("id", id).uniqueResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Reiziger> findAll() {
        try {
            return session.createQuery("from Reiziger", Reiziger.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Reiziger> findByGbDatum(LocalDate date) {
       try{
           return session.createQuery("from Reiziger where geboorteDatum = :geboorteDatum", Reiziger.class)
                   .setParameter("geboorteDatum", date).list();
       } catch(Exception e){
           e.printStackTrace();
           return new ArrayList<>();
       }
    }
}
