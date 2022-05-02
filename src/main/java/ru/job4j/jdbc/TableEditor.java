package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName(properties.getProperty("jdbc.driver"));
        connection = DriverManager.getConnection(
                properties.getProperty("jdbc.url"),
                properties.getProperty("jdbc.login"),
                properties.getProperty("jdbc.password")
        );
    }

    private void executeSQL(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private void executeSQL(String sql, String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.print(getTableScheme(connection, tableName));
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format("CREATE TABLE IF NOT EXISTS %s(%s);", tableName, "id serial primary key");
        executeSQL(sql, tableName);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format("DROP TABLE IF EXISTS %s;", tableName);
        executeSQL(sql);
        System.out.printf("Table \"%s\" was successfully dropped.%s", tableName, System.lineSeparator());
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format("ALTER TABLE %s ADD IF NOT EXISTS %s %s;", tableName, columnName, type);
        executeSQL(sql, tableName);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format("ALTER TABLE %s DROP COLUMN IF EXISTS %s;", tableName, columnName);
        executeSQL(sql, tableName);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws Exception {
        String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        executeSQL(sql, tableName);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
            try (TableEditor editor = new TableEditor(config)) {
                String name = "users";
                System.out.println("*******Создание таблицы*******");
                editor.createTable(name);
                System.out.println("******Добавление столбца******");
                editor.addColumn(name, "new_column", "varchar(255)");
                System.out.println("******Переименование столбца******");
                editor.renameColumn(name, "new_column", "name");
                System.out.println("******Удаление столбца******");
                editor.dropColumn(name, "name");
                System.out.println("******Удаление таблицы******");
                editor.dropTable(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}