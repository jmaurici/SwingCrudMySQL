package dao;

import model.com.company.Asignatura;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaDAO {
    public List<Asignatura> getAllAsignaturas() throws SQLException {
        List<Asignatura> asignaturas = new ArrayList<>();
        String query = "SELECT * FROM asignatura";
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                asignaturas.add(new Asignatura(resultSet.getInt("id"),
                        resultSet.getString("nombre"),
                        resultSet.getFloat("creditos"),
                        resultSet.getString("tipo"),
                        resultSet.getInt("curso"),
                        resultSet.getInt("cuatrimestre"),
                        resultSet.getInt("id_profesor"),
                        resultSet.getInt("id_grado")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return asignaturas;
    }

    public boolean updateAsignatura(Asignatura asignatura) throws SQLException {
        String query = "UPDATE asignatura SET  nombre=? , creditos =?," + " tipo=?, curso= ?, cuatrimestre=?, id_profesor=?, id_grado=? WHERE id=?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(8, asignatura.getId());
            preparedStatement.setString(1, asignatura.getNombre());
            preparedStatement.setFloat(2, asignatura.getCreditos());
            preparedStatement.setString(3, asignatura.getTipo());
            preparedStatement.setInt(4, asignatura.getCurso());
            preparedStatement.setInt(5, asignatura.getCuatrimestre());
            preparedStatement.setInt(6, asignatura.getIdProfesor());
            preparedStatement.setInt(7, asignatura.getIdGrado());
            preparedStatement.executeUpdate();
            return true;
        } catch (
                SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error UPDATE -> " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public boolean insertAsignatura(Asignatura asignatura) {
        String query = "INSERT INTO asignatura ( nombre, creditos, tipo, curso, cuatrimestre," + "id_profesor, id_grado) VALUES (?, ?,?,?,?,?, ?)";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, asignatura.getNombre());
            preparedStatement.setFloat(2, asignatura.getCreditos());
            preparedStatement.setString(3, asignatura.getTipo());
            preparedStatement.setInt(4, asignatura.getCurso());
            preparedStatement.setInt(5, asignatura.getCuatrimestre());
            preparedStatement.setInt(6, asignatura.getIdProfesor());
            preparedStatement.setInt(7, asignatura.getIdGrado());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    asignatura.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error INSERT -> ", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void deleteAsignatura(int asignaturaId) {
        String query = "DELETE FROM asignatura WHERE id=?";
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, asignaturaId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error DELETE -> ", ex.getMessage(), JOptionPane.ERROR_MESSAGE);

        }
    }
}
