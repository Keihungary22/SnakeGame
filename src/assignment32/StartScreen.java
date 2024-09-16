/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment32;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author nakan
 */
public class StartScreen extends javax.swing.JFrame{
    public StartScreen(){
        initComponents();
    }
    
    private javax.swing.JButton jB1;
    private javax.swing.JButton jB2;
    private javax.swing.JLabel jL1;
    
    private void initComponents(){
        jB1 = new javax.swing.JButton();
        jB2 = new javax.swing.JButton();
        jL1 = new javax.swing.JLabel();
        
        jL1.setText("Snake");
        jL1.setHorizontalAlignment(JLabel.CENTER);
        
        jB1.setText("Game Start");
        jB1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                jB1Action(e);
            }
        });
        
        jB2.setText("Top 10 Scores");
        jB2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e){
                jB2Action(e);
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(144, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jB1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jL1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(jB2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addComponent(jL1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(jB1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jB2)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }
    
    private void jB1Action(java.awt.event.ActionEvent e){
        Snake snake = new Snake();

        this.dispose();
    }
    
    private void jB2Action(java.awt.event.ActionEvent e){
        showTop10Scores();
    }
    
    private void showTop10Scores() {
        try {
            String url = "jdbc:mysql://localhost:3307/first";
            String user = "root";
            String password = "mysql";
            
            Connection connection = DriverManager.getConnection(url, user, password);
            String query = "SELECT * FROM data ORDER BY score DESC LIMIT 10";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // 取得したデータを表示
            StringBuilder topScores = new StringBuilder();
            int i = 0;
            while (resultSet.next()) {
                i++;
                String playerName = resultSet.getString("name");
                int score = resultSet.getInt("score");
                topScores.append("Rank: ").append(i)
                        .append(", Player: ").append(playerName)
                        .append(", Score: ").append(score)
                        .append("\n");
            }

            JOptionPane.showMessageDialog(this, topScores.toString(), "Top 10 Scores", JOptionPane.INFORMATION_MESSAGE);

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching top scores", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
