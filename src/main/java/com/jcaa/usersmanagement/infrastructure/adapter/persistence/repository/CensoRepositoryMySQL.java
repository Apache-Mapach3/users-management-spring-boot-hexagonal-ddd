package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.model.CensoModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.CensoEntity;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.CensoPersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.CensoPersistenceMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CensoRepositoryMySQL implements SaveCensoPort, UpdateCensoPort, DeleteCensoPort, GetCensoByIdPort, GetAllCensosPort {

    private final DataSource dataSource;

    public CensoRepositoryMySQL(final DataSource dataSource) {
        this.dataSource = Objects.requireNonNull(dataSource, "El dataSource no puede ser nulo");
    }

    @Override
    public CensoModel save(CensoModel censo) {
        String sql = "INSERT INTO censos (id, nombre, fecha, pais, departamento, ciudad, casa, num_hombres, num_mujeres, num_ancianos_hombres, num_ancianas_mujeres, num_ninos, num_ninas, num_habitaciones, num_camas, tiene_agua, tiene_luz, tiene_alcantarillado, tiene_gas, tiene_otros_servicios, nombre_sensador) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        CensoEntity entity = CensoPersistenceMapper.toEntity(censo);
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            setPreparedStatementParameters(stmt, entity);
            stmt.executeUpdate();
            return censo; // <-- Aquí estaba el error, ahora devolvemos el modelo.
        } catch (SQLException e) {
            throw new CensoPersistenceException("Error al guardar el censo", e);
        }
    }

    @Override
    public void delete(String censoId) {
        String sql = "DELETE FROM censos WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, censoId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CensoPersistenceException("Error al eliminar el censo", e);
        }
    }

    @Override
    public Optional<CensoModel> findById(String censoId) {
        String sql = "SELECT * FROM censos WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, censoId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(CensoPersistenceMapper.toModel(mapResultSetToEntity(rs)));
                }
            }
        } catch (SQLException e) {
            throw new CensoPersistenceException("Error al buscar el censo", e);
        }
        return Optional.empty();
    }

    @Override
    public List<CensoModel> findAll() {
        String sql = "SELECT * FROM censos";
        List<CensoModel> censos = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                censos.add(CensoPersistenceMapper.toModel(mapResultSetToEntity(rs)));
            }
        } catch (SQLException e) {
            throw new CensoPersistenceException("Error al listar los censos", e);
        }
        return censos;
    }

    @Override
    public void update(CensoModel censo) {
        String sql = "UPDATE censos SET nombre=?, fecha=?, pais=?, departamento=?, ciudad=?, casa=?, num_hombres=?, num_mujeres=?, num_ancianos_hombres=?, num_ancianas_mujeres=?, num_ninos=?, num_ninas=?, num_habitaciones=?, num_camas=?, tiene_agua=?, tiene_luz=?, tiene_alcantarillado=?, tiene_gas=?, tiene_otros_servicios=?, nombre_sensador=? WHERE id=?";
        CensoEntity entity = CensoPersistenceMapper.toEntity(censo);
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entity.getNombre());
            stmt.setDate(2, Date.valueOf(entity.getFecha()));
            stmt.setString(3, entity.getPais());
            stmt.setString(4, entity.getDepartamento());
            stmt.setString(5, entity.getCiudad());
            stmt.setString(6, entity.getCasa());
            stmt.setInt(7, entity.getNumHombres());
            stmt.setInt(8, entity.getNumMujeres());
            stmt.setInt(9, entity.getNumAncianosHombres());
            stmt.setInt(10, entity.getNumAncianasMujeres());
            stmt.setInt(11, entity.getNumNinos());
            stmt.setInt(12, entity.getNumNinas());
            stmt.setInt(13, entity.getNumHabitaciones());
            stmt.setInt(14, entity.getNumCamas());
            stmt.setBoolean(15, entity.isTieneAgua());
            stmt.setBoolean(16, entity.isTieneLuz());
            stmt.setBoolean(17, entity.isTieneAlcantarillado());
            stmt.setBoolean(18, entity.isTieneGas());
            stmt.setBoolean(19, entity.isTieneOtrosServicios());
            stmt.setString(20, entity.getNombreSensador());
            stmt.setString(21, entity.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CensoPersistenceException("Error al actualizar el censo", e);
        }
    }

    private void setPreparedStatementParameters(PreparedStatement stmt, CensoEntity entity) throws SQLException {
        stmt.setString(1, entity.getId());
        stmt.setString(2, entity.getNombre());
        stmt.setDate(3, Date.valueOf(entity.getFecha()));
        stmt.setString(4, entity.getPais());
        stmt.setString(5, entity.getDepartamento());
        stmt.setString(6, entity.getCiudad());
        stmt.setString(7, entity.getCasa());
        stmt.setInt(8, entity.getNumHombres());
        stmt.setInt(9, entity.getNumMujeres());
        stmt.setInt(10, entity.getNumAncianosHombres());
        stmt.setInt(11, entity.getNumAncianasMujeres());
        stmt.setInt(12, entity.getNumNinos());
        stmt.setInt(13, entity.getNumNinas());
        stmt.setInt(14, entity.getNumHabitaciones());
        stmt.setInt(15, entity.getNumCamas());
        stmt.setBoolean(16, entity.isTieneAgua());
        stmt.setBoolean(17, entity.isTieneLuz());
        stmt.setBoolean(18, entity.isTieneAlcantarillado());
        stmt.setBoolean(19, entity.isTieneGas());
        stmt.setBoolean(20, entity.isTieneOtrosServicios());
        stmt.setString(21, entity.getNombreSensador());
    }

    private CensoEntity mapResultSetToEntity(ResultSet rs) throws SQLException {
        return CensoEntity.builder()
                .id(rs.getString("id"))
                .nombre(rs.getString("nombre"))
                .fecha(rs.getDate("fecha").toLocalDate())
                .pais(rs.getString("pais"))
                .departamento(rs.getString("departamento"))
                .ciudad(rs.getString("ciudad"))
                .casa(rs.getString("casa"))
                .numHombres(rs.getInt("num_hombres"))
                .numMujeres(rs.getInt("num_mujeres"))
                .numAncianosHombres(rs.getInt("num_ancianos_hombres"))
                .numAncianasMujeres(rs.getInt("num_ancianas_mujeres"))
                .numNinos(rs.getInt("num_ninos"))
                .numNinas(rs.getInt("num_ninas"))
                .numHabitaciones(rs.getInt("num_habitaciones"))
                .numCamas(rs.getInt("num_camas"))
                .tieneAgua(rs.getBoolean("tiene_agua"))
                .tieneLuz(rs.getBoolean("tiene_luz"))
                .tieneAlcantarillado(rs.getBoolean("tiene_alcantarillado"))
                .tieneGas(rs.getBoolean("tiene_gas"))
                .tieneOtrosServicios(rs.getBoolean("tiene_otros_servicios"))
                .nombreSensador(rs.getString("nombre_sensador"))
                .build();
    }
}