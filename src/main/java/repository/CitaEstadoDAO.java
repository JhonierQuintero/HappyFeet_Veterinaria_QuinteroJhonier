package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.CitaEstado;
import util.ConexionDB;

public class CitaEstadoDAO {
    
    public List<CitaEstado> listar() {
        List<CitaEstado> lista = new ArrayList<>();
        String sql = "SELECT * FROM cita_estados";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new CitaEstado(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}