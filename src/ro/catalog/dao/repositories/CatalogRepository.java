package ro.catalog.dao.repositories;

import ro.catalog.dao.configuration.DatabaseConfiguration;
import ro.catalog.entitati.Catalog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogRepository {

    private static CatalogRepository catalogRepository;

    private CatalogRepository() {
    }

    public static CatalogRepository getCatalogRepository() {
        if (catalogRepository == null) {
            catalogRepository = new CatalogRepository();
        }
        return catalogRepository;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS catalog" +
                "(id int PRIMARY KEY AUTO_INCREMENT, grupa int)";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //CREATE - INSERT, READ - SELECT, UPDATE, DELETE

    //INSERT
    public void insert(int grupa) {
        String insertSql = "INSERT INTO catalog(grupa) VALUES(?)";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setInt(1, grupa);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //READ
    public List<Catalog> select() {
        String selectSql = "SELECT * FROM catalog";

        Connection connection = DatabaseConfiguration.getConnection();

        List<Catalog> listaCatalog = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next()) {
                listaCatalog.add(new Catalog(resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCatalog;
    }

    // UPDATE
    public void update(int id, int grupa) {
        String updateSql = "UPDATE catalog SET grupa=? WHERE id=?";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, grupa);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void delete(int id) {
        String deleteSql = "DELETE FROM catalog WHERE id=?";

        Connection connection = DatabaseConfiguration.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
