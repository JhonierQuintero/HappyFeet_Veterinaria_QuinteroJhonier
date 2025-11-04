package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.entities.ProcedimientoEspecial;
import model.enums.EstadoProcedimiento;
import util.ConexionDB;

public class ProcedimientoEspecialDAO {

    public void agregar(ProcedimientoEspecial pe) {
        String sql = "INSERT INTO procedimientos_especiales (mascota_id, veterinario_id, nombre_procedimiento, fecha_hora, informacion_preoperatoria, detalle_procedimiento, seguimiento_postoperatorio, estado, costo_procedimiento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pe.getMascotaId());
            ps.setInt(2, pe.getVeterinarioId());
            ps.setString(3, pe.getNombreProcedimiento());
            ps.setTimestamp(4, pe.getFechaHora());
            ps.setString(5, pe.getInformacionPreoperatoria());
            ps.setString(6, pe.getDetalleProcedimiento());
            ps.setString(7, pe.getSeguimientoPostoperatorio());
            ps.setString(8, pe.getEstado().name().toUpperCase());
            ps.setDouble(9, pe.getCostoProcedimiento());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){ if(rs.next()) pe.setId(rs.getInt(1)); }
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public ProcedimientoEspecial leerPorId(int id) {
        ProcedimientoEspecial pe = null;
        String sql = "SELECT * FROM procedimientos_especiales WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EstadoProcedimiento estado = EstadoProcedimiento.valueOf(rs.getString("estado").toUpperCase());
                    pe = new ProcedimientoEspecial(rs.getInt("id"), 
                            rs.getInt("mascota_id"), 
                            rs.getInt("veterinario_id"), 
                            rs.getString("nombre_procedimiento"), 
                            rs.getTimestamp("fecha_hora"), 
                            rs.getString("informacion_preoperatoria"), 
                            rs.getString("detalle_procedimiento"), 
                            rs.getString("seguimiento_postoperatorio"), 
                            estado, rs.getDouble("costo_procedimiento"));
                }
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return pe;
    }

    public List<ProcedimientoEspecial> listar() {
        List<ProcedimientoEspecial> lista = new ArrayList<>();
        String sql = "SELECT * FROM procedimientos_especiales";
        try (Connection con = ConexionDB.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                EstadoProcedimiento estado = EstadoProcedimiento.valueOf(rs.getString("estado").toUpperCase());
                lista.add(new ProcedimientoEspecial(rs.getInt("id"), 
                        rs.getInt("mascota_id"), 
                        rs.getInt("veterinario_id"), 
                        rs.getString("nombre_procedimiento"), 
                        rs.getTimestamp("fecha_hora"), 
                        rs.getString("informacion_preoperatoria"), 
                        rs.getString("detalle_procedimiento"), 
                        rs.getString("seguimiento_postoperatorio"), 
                        estado, rs.getDouble("costo_procedimiento")));
            }
        } catch (SQLException ex) { ex.printStackTrace(); }
        return lista;
    }

    public void actualizar(ProcedimientoEspecial pe) {
        String sql = "UPDATE procedimientos_especiales SET mascota_id=?, veterinario_id=?, nombre_procedimiento=?, fecha_hora=?, informacion_preoperatoria=?, detalle_procedimiento=?, seguimiento_postoperatorio=?, estado=?, costo_procedimiento=? WHERE id=?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pe.getMascotaId());
            ps.setInt(2, pe.getVeterinarioId());
            ps.setString(3, pe.getNombreProcedimiento());
            ps.setTimestamp(4, pe.getFechaHora());
            ps.setString(5, pe.getInformacionPreoperatoria());
            ps.setString(6, pe.getDetalleProcedimiento());
            ps.setString(7, pe.getSeguimientoPostoperatorio());
            ps.setString(8, pe.getEstado().name().toUpperCase());
            ps.setDouble(9, pe.getCostoProcedimiento());
            ps.setInt(10, pe.getId());
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM procedimientos_especiales WHERE id = ?";
        try (Connection con = ConexionDB.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) { ex.printStackTrace(); }
    }
}