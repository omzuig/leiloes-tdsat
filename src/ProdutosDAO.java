/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
   public void cadastrarProduto(ProdutosDTO produto) {
    try {
        conn = new conectaDAO().connectDB();
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        prep.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + erro.getMessage());
    }
}
    
    public ArrayList<ProdutosDTO> listarProdutos() {
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    try {
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        
        while(resultset.next()) {
            ProdutosDTO p = new ProdutosDTO();
            p.setId(resultset.getInt("id"));
            p.setNome(resultset.getString("nome"));
            p.setValor(resultset.getInt("valor"));
            p.setStatus(resultset.getString("status"));
            listagem.add(p);
        }
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
    }
    return listagem;
}
    
    
    
        
}

