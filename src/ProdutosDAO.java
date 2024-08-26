/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import com.mysql.cj.Query;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto) throws SQLException{
      
        try{
        conn = new conectaDAO().connectDB();
        
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?, ?, ?)";
       prep = conn.prepareStatement(sql);
        
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        
        prep.execute();
        
        conn.close();
        prep.close();
          JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso.");
        }
        catch(SQLException se ){
            JOptionPane.showMessageDialog(null, "Erro ao tentar cadastrar o item.");
        }
        
        
    }
    
    public  ArrayList<ProdutosDTO> listarProdutos() throws SQLException {
        conn =  new conectaDAO().connectDB();
        
        String sql = "SELECT * FROM produtos";
        prep = conn.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();
        
        while(rs.next()){
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(rs.getInt("id"));
            produto.setNome(rs.getString("nome"));
            produto.setValor(rs.getInt("valor"));
            produto.setStatus(rs.getString("status"));
            listagem.add(produto);
        }
        rs.close();
        prep.close();
        conn.close();
        return listagem;
    }
    
    public void venderProduto(ProdutosDTO produtos) throws SQLException {
   
        
    conn = new conectaDAO().connectDB();
    
    String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
    prep = conn.prepareStatement(sql);
    
    prep.setInt(1, produtos.getId());
    prep.execute();
    
    prep.close();
    conn.close();
}
        
    
    
    
    
        
}

