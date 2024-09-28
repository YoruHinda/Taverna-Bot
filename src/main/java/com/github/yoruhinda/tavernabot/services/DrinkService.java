package com.github.yoruhinda.tavernabot.services;

import com.github.yoruhinda.tavernabot.model.Drink;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DrinkService {
    private Connection connection;
    public DrinkService(Connection connection) {
        this.connection = connection;
    }

    public void createDrinkTable(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE drink(id int NOT NUL AUTO INCREMENT, drink_name varchar(30));");
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveDrink(Drink drink) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO drink (drink_name) VALUES (?);");
            preparedStatement.setString(1, drink.getDrink_Name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteDrink(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM drink WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Drink> getAllDrinks() {
        try {
            List<Drink> drinks = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM drink;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Drink drink = new Drink(resultSet.getInt("id"), resultSet.getString("drink_name"));
                drinks.add(drink);
            }
            return drinks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Drink getDrinkByName(String drink_name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM drink WHERE drink_name = ?;");
            preparedStatement.setString(1, drink_name);
            ResultSet resultSet = preparedStatement.executeQuery();
            return new Drink(resultSet.getInt("id"), resultSet.getString("drink_name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
