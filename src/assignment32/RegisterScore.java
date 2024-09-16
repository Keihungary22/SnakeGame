/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment32;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nakan
 */
public class RegisterScore {
    private String name;
    private int score;
    
    public RegisterScore(int score){
        this.score = score;
        getUserInput();
        displayInformation();
        
        register(name, score);
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void getUserInput() {
        String nameInput = JOptionPane.showInputDialog("Enter your name:");
        setName(nameInput);
    }
    
    public void displayInformation() {
        System.out.println("Name: " + name);
        System.out.println("Score: " + score);
    }
    
    public void register(String name, int score){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        
        String url = "jdbc:mysql://localhost:3307/first"; // データベースのURL
        String user = "root"; // データベースのユーザー名
        String password = "mysql"; // データベースのパスワード

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO data (name, score) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, score);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // エラー処理を追加するか、ログに出力するなどの対応を行うことができます
        }
    }
}
