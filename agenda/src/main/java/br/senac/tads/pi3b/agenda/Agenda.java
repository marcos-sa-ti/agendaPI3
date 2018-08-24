/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3b.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author marcos.sbrito2
 */
public class Agenda {

    private Connection obterConexao() throws ClassNotFoundException, SQLException {
        Connection conn = null;

        // Passo 1 : Registrar driver JBDC
        Class.forName("com.mysql.jdbc.Driver");

        //Passo 2 : Obter a conexão
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/agendabd", "root", "");
        return conn;

    }

    public void executar() {
        String querySql = "SELECT ID, NOME , DTNASCIMENTO FROM PESSOA";

        try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(querySql);
                ResultSet resultados = stmt.executeQuery()) {

            while (resultados.next()) {
                long id = resultados.getLong("ID");
                String nome = resultados.getString("NOME");
                Date dtNascimento = resultados.getDate("DTNASCIMENTO");
                System.out.println(id + " " + nome + " " + dtNascimento + " ");

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   
    
    
    public void salvarValores() throws ParseException{
    /*
    Scanner leitor = new Scanner (System.in);    
    System.out.println("Digite um nome para salvar no banco de dados: ");    
    String nomedigitado = leitor.nextLine();
    System.out.println("Digite a data de nascimento para salvar no banco de dados");
    String datadigitada = leitor.next();
    SimpleDateFormat  formatador = new SimpleDateFormat("yyyy-MM-dd"); 
    Date data = formatador.parse(datadigitada);
    */    
        
    String querySql = "INSERT INTO PESSOA(NOME, DTNASCIMENTO)VALUES (?,?)";
     try (Connection conn = obterConexao();
                PreparedStatement stmt = conn.prepareStatement(querySql);
                ResultSet resultados = stmt.executeQuery()) {
                ResultSet resultado = stmt.getResultSet();
         
           
           

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    public static void main(String[] args) throws ParseException {
        Agenda agenda = new Agenda();
        agenda.executar();
        agenda.salvarValores();
        agenda.executar();
        
    }

}
