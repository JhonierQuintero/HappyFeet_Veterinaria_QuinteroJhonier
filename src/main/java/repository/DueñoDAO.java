package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.Dueño;
import util.ConexionDB;

public class DueñoDAO {

    public void agregar(Dueño d) {
        String sql = "INSERT INTO dueño (nombre, numeroDocumento, direccion, telefono, email, contactoEmergencia, fechaRegistro, activo) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getNumeroDocumento());
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getContactoEmergencia());
            ps.setDate(7, new java.sql.Date(d.getFechaRegistro().getTime()));
            ps.setBoolean(8, d.getActivo());

            int filas = ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int idGenerado = rs.getInt(1);
                    System.out.println("Dueño insertado con ID = " + idGenerado);
                }
            }
            System.out.println("Dueño agregado. Filas afectadas: " + filas);
        } catch (SQLException ex) {
            System.out.println("Error SQL al agregar dueño: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Dueño> listar() {
        List<Dueño> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, numeroDocumento, direccion, telefono, email, contactoEmergencia, fechaRegistro, activo FROM dueño";
        try (Connection con = ConexionDB.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String numeroDocumento = rs.getString("numeroDocumento");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");
                String contactoEmergencia = rs.getString("contactoEmergencia");
                Date fechaRegistro = rs.getDate("fechaRegistro");
                boolean activo = rs.getBoolean("activo");

                Dueño d = new Dueño(id, nombre, numeroDocumento, direccion, telefono, email, contactoEmergencia, fechaRegistro, activo);
                lista.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error SQL al listar dueños: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    public Dueño leerPorId(int id) {
        Dueño d = null;
        String sql = "SELECT id, nombre, numeroDocumento, direccion, telefono, email, contactoEmergencia, fechaRegistro, activo FROM dueño WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idDueño = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String numeroDocumento = rs.getString("numeroDocumento");
                    String direccion = rs.getString("direccion");
                    String telefono = rs.getString("telefono");
                    String email = rs.getString("email");
                    String contactoEmergencia = rs.getString("contactoEmergencia");
                    Date fechaRegistro = rs.getDate("fechaRegistro");
                    boolean activo = rs.getBoolean("activo");
                    d = new Dueño(idDueño, nombre, numeroDocumento, direccion, telefono, email, contactoEmergencia, fechaRegistro, activo);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al leer dueño: " + ex.getMessage());
            ex.printStackTrace();
        }
        return d;
    }

    public void actualizar(Dueño d) {
        String sql = "UPDATE dueño SET nombre=?, numeroDocumento=?, direccion=?, telefono=?, email=?, contactoEmergencia=?, activo=? WHERE id=?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, d.getNombre());
            ps.setString(2, d.getNumeroDocumento());
            ps.setString(3, d.getDireccion());
            ps.setString(4, d.getTelefono());
            ps.setString(5, d.getEmail());
            ps.setString(6, d.getContactoEmergencia());
            ps.setBoolean(7, d.getActivo());
            ps.setInt(8, d.getId());
            int filas = ps.executeUpdate();
            System.out.println("Dueño actualizado. Filas afectadas: " + filas);
        } catch (SQLException ex) {
            System.out.println("Error SQL al actualizar dueño: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminar(int idDueño) {
        String sql = "DELETE FROM dueño WHERE id = ?";
        try (Connection con = ConexionDB.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idDueño);
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Dueño con ID " + idDueño + " eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún dueño con ID " + idDueño + ".");
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL al eliminar dueño: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}