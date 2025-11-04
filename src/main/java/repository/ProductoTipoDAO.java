package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.ProductoTipo;
import util.ConexionDB;

public class ProductoTipoDAO {

    public List<ProductoTipo> listar() {
        List<ProductoTipo> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto_tipos";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new ProductoTipo(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }
}