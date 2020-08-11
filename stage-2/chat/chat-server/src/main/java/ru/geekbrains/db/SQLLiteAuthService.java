package ru.geekbrains.db;

import ru.geekbrains.data.User;

import java.sql.*;
import java.util.ArrayList;

public class SQLLiteAuthService {

        private  Connection connection;
        private static Statement statement;

        // ПАодключение к базе данных
        public SQLLiteAuthService()
        {
            connection = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:ChatUsers.db");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();}
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void dropTable() throws SQLException {
            try (
                    Statement stm = connection.createStatement()) {
                stm.execute(
                        "DROP TABLE IF EXISTS users");
                System.out.println("Таблица очищена.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public ArrayList<User> getAllUsers () {
            ArrayList<User> usersArr = new ArrayList<>();
            ResultSet rs = null;
            try {
                rs = statement.executeQuery("SELECT * FROM users");
                while (rs.next()) {
                    usersArr.add(new User(rs.getString("login"), rs.getString("password"), rs.getString("name")));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return usersArr;
        }

        public void updateUsername(String username, String newUsername) {
            try (PreparedStatement stm = connection.prepareStatement(
                    "UPDATE users SET name=? WHERE name=?")) {
                stm.setString(1,newUsername);
                stm.setString(2,username);
                stm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public String getUsernameByLoginAndPassword(String login, String password) {
            String username = null;
            try (PreparedStatement stm = connection.prepareStatement(
                    "SELECT name FROM users WHERE login=? AND password=?")) {
                stm.setString(1, login);
                stm.setString(2, password);
                ResultSet resSet = stm.executeQuery();
                if (resSet.next()) {
                    username = resSet.getString("name");
                }
                resSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return username;
        }

        public void start() throws SQLException {
            createUserTable();
            writeTestUsersToDB();
        }

        public void stop() {
            closeDB();
        }

        private void createUserTable() {
            // Создание таблицы users
            try (
                    Statement stm = connection.createStatement()) {
                stm.execute(
                        "CREATE TABLE " +
                                "if not exists 'users' " +
                                "('id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "'name' text UNIQUE, 'login' text UNIQUE, 'password' text);");
                System.out.println("Таблица создана или уже существует.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Заполнение таблицы users
        private void writeTestUsersToDB() throws SQLException {
            statement.executeUpdate("INSERT INTO users (name, login, password) VALUES ('admin', 'sysroot', 'admin');");
            statement.executeUpdate("INSERT INTO users (name, login, password) VALUES ('alex', 'alex-st', '123');");
            System.out.println("Таблица 'users' заполнена.");
        }

        // Закрытие
        private void closeDB ()
        {
            try {
                connection.close();
                System.out.println("Соединения закрыты");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


}
