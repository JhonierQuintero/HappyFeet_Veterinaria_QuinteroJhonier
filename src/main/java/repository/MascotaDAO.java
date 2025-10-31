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
import util.ConexionDB; // Asegúrate de que tu clase de conexión esté en este paquete

public class MascotaDAO {
    
    public void agregar(Mascota m) {
        String sql = "INSERT INTO mascotas (dueno_id, nombre, raza_id, fecha_nacimiento, sexo, peso_actual, microchip, tatuaje, url_foto, alergias, condiciones_preexistentes, fecha_registro, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, m.getDueñoId());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getRazaId());
            ps.setDate(4, m.getFechaNacimiento());
            ps.setString(5, m.getSexo().name());
            ps.setDouble(6, m.getPesoActual());
            ps.setString(7, m.getMicrochip());
            ps.setString(8, m.getTatuaje());
            ps.setString(9, m.getUrlFoto());
            ps.setString(10, m.getAlergias());
            ps.setString(11, m.getCondicionesPreexistentes());
            ps.setDate(12, m.getFechaRegistro());
            ps.setBoolean(13, m.getActivo());
            
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    m.setId(rs.getInt(1)); 
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar mascota: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Mascota> listar() {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT id, dueno_id, nombre, raza_id, fecha_nacimiento, sexo, peso_actual, microchip, tatuaje, url_foto, alergias, condiciones_preexistentes, fecha_registro, activo FROM mascotas";
        
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            
            while (rs.next()) {
                Mascota m = new Mascota(
                    rs.getInt("id"),
                    rs.getInt("dueno_id"),
                    rs.getString("nombre"),
                    rs.getInt("raza_id"),
                    rs.getDate("fecha_nacimiento"),
                    Sexo.valueOf(rs.getString("sexo")),
                    rs.getDouble("peso_actual"),
                    rs.getString("microchip"),
                    rs.getString("tatuaje"),
                    rs.getString("url_foto"),
                    rs.getString("alergias"),
                    rs.getString("condiciones_preexistentes"),
                    rs.getDate("fecha_registro"),
                    rs.getBoolean("activo")
                );
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
        String sql = "SELECT id, dueno_id, nombre, raza_id, fecha_nacimiento, sexo, peso_actual, microchip, tatuaje, url_foto, alergias, condiciones_preexistentes, fecha_registro, activo FROM mascotas WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    m = new Mascota(
                        rs.getInt("id"),
                        rs.getInt("dueno_id"),
                        rs.getString("nombre"),
                        rs.getInt("raza_id"),
                        rs.getDate("fecha_nacimiento"),
                        Sexo.valueOf(rs.getString("sexo")),
                        rs.getDouble("peso_actual"),
                        rs.getString("microchip"),
                        rs.getString("tatuaje"),
                        rs.getString("url_foto"),
                        rs.getString("alergias"),
                        rs.getString("condiciones_preexistentes"),
                        rs.getDate("fecha_registro"),
                        rs.getBoolean("activo")
                    );
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al leer mascota: " + ex.getMessage());
            ex.printStackTrace();
        }
        return m;
    }

    public List<Mascota> listarPorDueño(int idDueno) {
        List<Mascota> lista = new ArrayList<>();
        String sql = "SELECT id, dueno_id, nombre, raza_id, fecha_nacimiento, sexo, peso_actual, microchip, tatuaje, url_foto, alergias, condiciones_preexistentes, fecha_registro, activo FROM mascotas WHERE dueno_id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idDueno);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mascota m = new Mascota(
                        rs.getInt("id"),
                        rs.getInt("dueno_id"),
                        rs.getString("nombre"),
                        rs.getInt("raza_id"),
                        rs.getDate("fecha_nacimiento"),
                        Sexo.valueOf(rs.getString("sexo").trim().toUpperCase()),
                        rs.getDouble("peso_actual"),
                        rs.getString("microchip"),
                        rs.getString("tatuaje"),
                        rs.getString("url_foto"),
                        rs.getString("alergias"),
                        rs.getString("condiciones_preexistentes"),
                        rs.getDate("fecha_registro"),
                        rs.getBoolean("activo")
                    );
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error SQL al listar mascotas por dueño: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Mascota m) {
        String sql = "UPDATE mascotas SET dueno_id=?, nombre=?, raza_id=?, fecha_nacimiento=?, sexo=?, peso_actual=?, microchip=?, tatuaje=?, url_foto=?, alergias=?, condiciones_preexistentes=?, activo=? WHERE id=?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, m.getDueñoId());
            ps.setString(2, m.getNombre());
            ps.setInt(3, m.getRazaId());
            ps.setDate(4, m.getFechaNacimiento());
            ps.setString(5, m.getSexo().name());
            ps.setDouble(6, m.getPesoActual());
            ps.setString(7, m.getMicrochip());
            ps.setString(8, m.getTatuaje());
            ps.setString(9, m.getUrlFoto());
            ps.setString(10, m.getAlergias());
            ps.setString(11, m.getCondicionesPreexistentes());
            ps.setBoolean(12, m.getActivo());
            ps.setInt(13, m.getId());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar mascota: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminar(int idMascota) {
        String sql = "DELETE FROM mascotas WHERE id = ?";
        
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idMascota);
            int filasAfectadas = ps.executeUpdate();
            
            if (filasAfectadas == 0) {
                System.out.println("No se encontró ninguna mascota con ID " + idMascota + " para eliminar.");
            } else {
                 System.out.println("Mascota con ID " + idMascota + " eliminada correctamente.");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar mascota: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}