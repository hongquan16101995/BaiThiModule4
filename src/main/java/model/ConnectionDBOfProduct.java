package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectionDBOfProduct implements ConnectionDBProduct {

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product(name, price ,amount, color, description, category) " +
            "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_NAME = "SELECT * FROM product WHERE name LIKE ?;";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE id = ?;";
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM product;";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM product WHERE id = ?;";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE product SET name = ?, price = ?,amount = ?, color = ?, " +
            "description = ?, category = ? WHERE id = ?;";
    private static final String SELECT_ALL_CATEGORY = "SELECT * FROM category;";

    public ConnectionDBOfProduct() {
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String jdbcURL = "jdbc:mysql://localhost:3306/baithi?useSSL=false";
            String jdbcUsername = "root";
            String jdbcPassword = "123456";
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private void setValueOfProduct(Product product, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, product.getName());
        preparedStatement.setLong(2, product.getPrice());
        preparedStatement.setInt(3, product.getAmount());
        preparedStatement.setString(4, product.getColor());
        preparedStatement.setString(5, product.getDescription());
        preparedStatement.setString(6, product.getCategory());
    }

    @Override
    public void insertProduct(Product product) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT_SQL)) {
            setValueOfProduct(product, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL)) {
            setValueOfProduct(product, preparedStatement);
            preparedStatement.setInt(7, product.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    @Override
    public List<Product> selectProductByName(String regex) {
        List<Product> products = new ArrayList<>();
        String regexString = "%" + regex + "%";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME)) {
            preparedStatement.setString(1, regexString);
            getListProduct(products, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product selectProductById(int id) {
        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt("id");
                String name = rs.getString("name");
                long price = rs.getLong("price");
                int amount = rs.getInt("amount");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String category = rs.getString("category");
                product = new Product(idProduct, name, price, amount, color, description, category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT)) {
            getListProduct(products, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    private void getListProduct(List<Product> products, PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            int idProduct = rs.getInt("id");
            String name = rs.getString("name");
            long price = rs.getLong("price");
            int amount = rs.getInt("amount");
            String color = rs.getString("color");
            String description = rs.getString("description");
            String category = rs.getString("category");
            products.add(new Product(idProduct, name, price, amount, color, description, category));
        }
    }

    @Override
    public List<Category> selectAllCategory() {
        List<Category> categorys = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String category = rs.getString("category");
                categorys.add(new Category(category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorys;
    }
}