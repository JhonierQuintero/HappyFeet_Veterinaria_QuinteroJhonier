package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.BeneficioClub;
import model.enums.TipoBeneficio;
import util.ConexionDB;

public class BeneficioClubDAO {

    public void agregar(BeneficioClub b) {
        String sql = "INSERT INTO beneficios_club (nombre, nivel_requerido, puntos_necesarios, tipo_beneficio, valor_beneficio, activo) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, b.getNombre());
            ps.setString(2, b.getNivelRequerido());
            ps.setInt(3, b.getPuntosNecesarios());
            ps.setString(4, b.getTipoBeneficio().getValorEnDB());
            ps.setDouble(5, b.getValorBeneficio());
            ps.setBoolean(6, b.getActivo());
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) b.setId(rs.getInt(1)); }
        
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public BeneficioClub leerPorId(int id) {
        BeneficioClub b = null;
        String sql = "SELECT * FROM beneficios_club WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    b = new BeneficioClub(rs.getInt("id"), 
                            rs.getString("nombre"), 
                            rs.getString("nivel_requerido"), 
                            rs.getInt("puntos_necesarios"), 
                            TipoBeneficio.fromString(rs.getString("tipo_beneficio")), 
                            rs.getDouble("valor_beneficio"), 
                            rs.getBoolean("activo"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return b;
    }

    public List<BeneficioClub> listar() {
        List<BeneficioClub> lista = new ArrayList<>();
        String sql = "SELECT * FROM beneficios_club";
        
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new BeneficioClub(rs.getInt("id"), 
                        rs.getString("nombre"), 
                        rs.getString("nivel_requerido"), 
                        rs.getInt("puntos_necesarios"), 
                        TipoBeneficio.fromString(rs.getString("tipo_beneficio")), 
                        rs.getDouble("valor_beneficio"), rs.getBoolean("activo")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(BeneficioClub b) {
        String sql = "UPDATE beneficios_club SET nombre=?, nivel_requerido=?, puntos_necesarios=?, tipo_beneficio=?, valor_beneficio=?, activo=? WHERE id=?";
        
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, b.getNombre());
            ps.setString(2, b.getNivelRequerido());
            ps.setInt(3, b.getPuntosNecesarios());
            ps.setString(4, b.getTipoBeneficio().getValorEnDB());
            ps.setDouble(5, b.getValorBeneficio());
            ps.setBoolean(6, b.getActivo());
            ps.setInt(7, b.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM beneficios_club WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}