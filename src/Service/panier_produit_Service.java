/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.panier_produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Aymen
 */
public class panier_produit_Service implements IService<panier_produit> {



    private Connection c = MyConnexion.getInsCon().getcnx();

    @Override
    public void Ajouter(panier_produit u) throws SQLException {
      PreparedStatement ps;

        
        String query = "INSERT INTO `panier_produit`( `produit_id`, `user_id`, `quantite`) VALUES (?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getId_produit());
          ps.setInt(2, u.getId_user());
             ps.setInt(3, u.getQuantite());
            
         
            ps.execute();
            
            System.out.println(u);
        } catch (Exception e) {
              
       
            System.out.println(e);

        }
    
    }
 public int prixtotal()
       {
           int total=0;
               String requete = "SELECT SUM(p.prix * pa.quantite) as total FROM `panier_produit` pa inner join produit p where p.id = pa.produit_id";
                   
                     try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                total= rs.getInt("total");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
           
           return total;
       }
    @Override
    public void Supprimer(int t) throws SQLException {
    PreparedStatement ps;

        String query = "DELETE FROM `panier_produit` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        }  }

    @Override
    public void Modifier(panier_produit t, int id) throws SQLException {
     PreparedStatement ps;
        String query = "UPDATE `panier_produit` SET `quantite`=? WHERE  id = ?";
        try {
            
            ps = c.prepareStatement(query);
        ps.setInt(1, t.getQuantite());
          
               ps.setInt(2, id);
           
            ps.execute();
    

        } catch (Exception e) {
        }  }

    @Override
    public ObservableList<panier_produit> Affichertout() throws SQLException {
        ObservableList<panier_produit> list = FXCollections.observableArrayList();
      String requete = "SELECT pa.id, `produit_id`, `user_id`, pa.quantite,labelle FROM `panier_produit` pa INNER JOIN produit p WHERE p.id = pa.produit_id";

       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new panier_produit(rs.getInt("id"),rs.getInt("produit_id"),rs.getInt("user_id"),rs.getInt("quantite"),rs.getString("labelle")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
  
    
    }
      public ObservableList<panier_produit> Affichertout_user(int id) throws SQLException {
        ObservableList<panier_produit> list = FXCollections.observableArrayList();
      String requete = "SELECT pa.id, `produit_id`,`user_id`, pa.quantite,labelle FROM `panier_produit` pa INNER JOIN produit p WHERE p.id = pa.produit_id and user_id = "+id;

       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new panier_produit(rs.getInt("id"),rs.getInt("produit_id"),rs.getInt("user_id"),rs.getInt("quantite"),rs.getString("labelle")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
  
    
    }
}
