package dao;

import model.com.company.Grado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GradoDAO {

    public List<Grado> getAllGrados() {
        List<Grado> grados = new ArrayList<>();
        String query = "SELECT * FROM grado";
        try {
            Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                grados.add(new Grado(
                        resultSet.getInt("id"),
                        resultSet.getString("nombre")
                        ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return grados;
    }
}
