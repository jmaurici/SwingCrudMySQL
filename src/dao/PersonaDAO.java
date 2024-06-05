package dao;

import model.com.company.Persona;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    public List<Persona> getAllPersonas() {
        List<Persona> personas = new ArrayList<>();
        String query = "SELECT * FROM persona";
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                personas.add(new Persona(
                        resultSet.getInt("id"),
                        resultSet.getString("nif"),
                        resultSet.getString("nombre"),
                        resultSet.getString("apellido1"),
                        resultSet.getString("apellido2"),
                        resultSet.getString("ciudad"),
                        resultSet.getString("direccion"),
                        resultSet.getString("telefono"),
                        resultSet.getString("fecha_nacimiento"),
                        resultSet.getString("sexo"),
                        resultSet.getString("tipo")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personas;
    }

    public boolean updatePersona(Persona persona) throws SQLException {
        String query = "UPDATE Persona SET nif=?, nombre=? , apellido1 =?," +
                " apellido2=?, ciudad= ?, direccion=?, telefono=?, fecha_nacimiento=?," +
                " sexo=?, tipo=? WHERE id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, persona.getNif());
            preparedStatement.setString(2, persona.getNombre());
            preparedStatement.setInt(11, persona.getId());
            preparedStatement.setString(3, persona.getApellido1());
            preparedStatement.setString(4, persona.getApellido2());
            preparedStatement.setString(5, persona.getCiudad());
            preparedStatement.setString(6, persona.getDireccion());
            preparedStatement.setString(7, persona.getTelefono());
            preparedStatement.setString(8, persona.getFechaNac());
            preparedStatement.setString(9, persona.getSexo());
            preparedStatement.setString(10, persona.getTipo());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error UPDATE -> " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean insertPersona(Persona persona) {
        String query = "INSERT INTO Persona (nif, nombre, apellido1, apellido2, ciudad, direccion," +
                "telefono, fecha_nacimiento, sexo, tipo) VALUES (?, ?,?,?,?,?, ?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, persona.getNif());
            preparedStatement.setString(2, persona.getNombre());
            preparedStatement.setString(3, persona.getApellido1());
            preparedStatement.setString(4, persona.getApellido2());
            preparedStatement.setString(5, persona.getCiudad());
            preparedStatement.setString(6, persona.getDireccion());
            preparedStatement.setString(7, persona.getTelefono());
            preparedStatement.setString(8, persona.getFechaNac());
            preparedStatement.setString(9, String.valueOf(persona.getSexo()));
            preparedStatement.setString(10, persona.getTipo());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    persona.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error INSERT -> ", e.getMessage(), JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean deletePersona(int personId) {
        String query = "DELETE FROM Persona WHERE id=?";
        try {
            int confirmarBorrado = JOptionPane.YES_NO_OPTION;
            confirmarBorrado = JOptionPane.showConfirmDialog(null, "Está seguro de borrar a id = " + personId);
            if (confirmarBorrado == JOptionPane.OK_OPTION) {
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, personId);
                preparedStatement.executeUpdate();
                System.out.println("Borrado de la tabla");
                return true;
            } else {
                System.out.println("Vale, no lo borro...");
                return false;
            }

        } catch (SQLException ex) {
            return false;
        }
    }
}