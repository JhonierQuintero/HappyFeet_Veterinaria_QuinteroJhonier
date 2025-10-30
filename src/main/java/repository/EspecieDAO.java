package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Especie;
import util.ConexionDB;

public class EspecieDAO {

    public void agregar(Especie e) {
        String sql = "INSERT INTO especies (nombre, descripcion) VALUES (?, ?)";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDescripcion());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    e.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar especie: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Especie> listar() {
        List<Especie> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, descripcion FROM especies";
        
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Especie e = new Especie(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                );
                lista.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al listar especies: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public Especie leerPorId(int id) {
        Especie e = null;
        String sql = "SELECT id, nombre, descripcion FROM especies WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    e = new Especie(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                    );
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al leer especie por ID: " + ex.getMessage());
            ex.printStackTrace();
        }
        return e;
    }

    public void actualizar(Especie e) {
        String sql = "UPDATE especies SET nombre = ?, descripcion = ? WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getDescripcion());
            ps.setInt(3, e.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar especie: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminar(int idEspecie) {
        String sql = "DELETE FROM especies WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idEspecie);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar especie: " + ex.getMessage());
            System.out.println("Posible causa: Existen razas asociadas a esta especie.");
            ex.printStackTrace();
        }
    }
}