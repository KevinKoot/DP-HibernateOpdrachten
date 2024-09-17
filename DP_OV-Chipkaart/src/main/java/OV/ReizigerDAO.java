package OV;

import OV.Reiziger;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface ReizigerDAO {

    boolean save(Reiziger reiziger);

    boolean update(Reiziger reiziger);

    boolean delete(Reiziger reiziger);

    Reiziger findById(int id);

    List<Reiziger> findByGbDatum(LocalDate date);

    List<Reiziger> findAll();



}
