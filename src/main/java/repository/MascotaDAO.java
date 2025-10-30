package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Mascota;
import model.enums.Sexo;
import util.ConexionDB;

public class MascotaDAO {
     public void agregar(Mascota m) {
        String sql = "INSERT INTO mascota (dueñoId, nombre, razaId, fechaNacimiento, sexo, pesoActual, microchip, tatuaje, urlFoto, alergias, condicionesPreexistentes, fechaRegistro, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, m.getDueñoId());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getRazaId());
            ps.setDate(4, m.getFechaNacimiento());
            ps.setString(5, m.getSexo().toString());
            ps.setDouble(6, m.getPesoActual());
            ps.setString(7, m.getMicrochip());
            ps.setString(8, m.getTatuaje());
            ps.setString(9, m.getUrlFoto());
            ps.setString(10, m.getAlergias());
            ps.setString(11, m.getCondicionesPreexistentes());
            ps.setDate(12, m.getFechaRegistro());
            ps.setBoolean(13, m.getActivo());
            
            int filas = ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    System.out.println("Mascota insertada con ID = " + idGenerado);
                }
            }
            System.out.println("Mascota agregada. Filas afectadas: " + filas);
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar mascota: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Mascota> listar() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT id, dueñoId, nombre, razaId, fechaNacimiento, sexo, pesoActual, microchip, tatuaje, urlFoto, alergias, condicionesPreexistentes, fechaRegistro, activo FROM mascota";
        
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                int id = rs.getInt("id");
                int dueñoId = rs.getInt("dueñoId");
                String nombre = rs.getString("nombre");
                int razaId = rs.getInt("razaId");
                Date fechaNacimiento = rs.getDate("fechaNacimiento");
                Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                double pesoActual = rs.getDouble("pesoActual");
                String microchip = rs.getString("microchip");
                String tatuaje = rs.getString("tatuaje");
                String urlFoto = rs.getString("urlFoto");
                String alergias = rs.getString("alergias");
                String condicionesPreexistentes = rs.getString("condicionesPreexistentes");
                Date fechaRegistro = rs.getDate("fechaRegistro");
                boolean activo = rs.getBoolean("activo");
                
                Mascota m = new Mascota(id, dueñoId, nombre, razaId, fechaNacimiento, sexo, pesoActual, microchip, tatuaje, urlFoto, alergias, condicionesPreexistentes, fechaRegistro, activo);
                lista.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL al listar mascotas: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }
    
    public Mascota leerPorId(int id) {
        Mascota m = null;
        String sql = "SELECT id, dueñoId, nombre, razaId, fechaNacimiento, sexo, pesoActual, microchip, tatuaje, urlFoto, alergias, condicionesPreexistentes, fechaRegistro, activo FROM mascota WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int dueñoId = rs.getInt("dueñoId");
                    String nombre = rs.getString("nombre");
                    int razaId = rs.getInt("razaId");
                    Date fechaNacimiento = rs.getDate("fechaNacimiento");
                    Sexo sexo = Sexo.valueOf(rs.getString("sexo"));
                    double pesoActual = rs.getDouble("pesoActual");
                    String microchip = rs.getString("microchip");
                    String tatuaje = rs.getString("tatuaje");
                    String urlFoto = rs.getString("urlFoto");
                    String alergias = rs.getString("alergias");
                    String condicionesPreexistentes = rs.getString("condicionesPreexistentes");
                    Date fechaRegistro = rs.getDate("fechaRegistro");
                    boolean activo = rs.getBoolean("activo");
                    
                    m = new Mascota(id, dueñoId, nombre, razaId, fechaNacimiento, sexo, pesoActual, microchip, tatuaje, urlFoto, alergias, condicionesPreexistentes, fechaRegistro, activo);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al leer mascota: " + ex.getMessage());
            ex.printStackTrace();
        }
        return m;
    }

    public void actualizar(Mascota m) {
        String sql = "UPDATE mascota SET dueñoId=?, nombre=?, razaId=?, fechaNacimiento=?, sexo=?, pesoActual=?, microchip=?, tatuaje=?, urlFoto=?, alergias=?, condicionesPreexistentes=?, activo=? WHERE id=?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, m.getDueñoId());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getRazaId());
            ps.setDate(4, m.getFechaNacimiento());
            ps.setString(5, m.getSexo().toString());
            ps.setDouble(6, m.getPesoActual());
            ps.setString(7, m.getMicrochip());
            ps.setString(8, m.getTatuaje());
            ps.setString(9, m.getUrlFoto());
            ps.setString(10, m.getAlergias());
            ps.setString(11, m.getCondicionesPreexistentes());
            ps.setBoolean(12, m.getActivo());
            ps.setInt(13, m.getId());
            
            int filas = ps.executeUpdate();
            System.out.println("Mascota actualizada. Filas afectadas: " + filas);
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar mascota: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminar(int idMascota) {
        String sql = "DELETE FROM mascota WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idMascota);
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas > 0) {
                System.out.println("Mascota con ID " + idMascota + " eliminada correctamente.");
            } else {
                System.out.println("No se encontró ninguna mascota con ID " + idMascota + ".");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar mascota: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}