package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Raza;
import util.ConexionDB;

public class RazaDAO {

    public void agregar(Raza r) {
        String sql = "INSERT INTO razas (especie_id, nombre, caracteristicas) VALUES (?, ?, ?)";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, r.getEspecieId());
            ps.setString(2, r.getNombre());
            ps.setString(3, r.getCaracteristicas());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    r.setId(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar raza: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Raza> listar() {
        List<Raza> lista = new ArrayList<>();
        String sql = "SELECT id, especie_id, nombre, caracteristicas FROM razas";
        
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Raza r = new Raza(
                    rs.getInt("id"),
                    rs.getInt("especie_id"),
                    rs.getString("nombre"),
                    rs.getString("caracteristicas")
                );
                lista.add(r);
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al listar todas las razas: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public Raza leerPorId(int id) {
        Raza r = null;
        String sql = "SELECT id, especie_id, nombre, caracteristicas FROM razas WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    r = new Raza(
                        rs.getInt("id"),
                        rs.getInt("especie_id"),
                        rs.getString("nombre"),
                        rs.getString("caracteristicas")
                    );
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al leer raza por ID: " + ex.getMessage());
            ex.printStackTrace();
        }
        return r;
    }

    public List<Raza> listarPorEspecie(int especieId) {
        List<Raza> lista = new ArrayList<>();
        String sql = "SELECT id, especie_id, nombre, caracteristicas FROM razas WHERE especie_id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, especieId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Raza r = new Raza(
                        rs.getInt("id"),
                        rs.getInt("especie_id"),
                        rs.getString("nombre"),
                        rs.getString("caracteristicas")
                    );
                    lista.add(r);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al listar razas por especie: " + ex.getMessage());
            ex.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Raza r) {
        String sql = "UPDATE razas SET especie_id = ?, nombre = ?, caracteristicas = ? WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, r.getEspecieId());
            ps.setString(2, r.getNombre());
            ps.setString(3, r.getCaracteristicas());
            ps.setInt(4, r.getId());
            
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar raza: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminar(int idRaza) {
        String sql = "DELETE FROM razas WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idRaza);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar raza: " + ex.getMessage());
            System.out.println("Posible causa: Existen mascotas asociadas a esta raza.");
            ex.printStackTrace();
        }
    }
}