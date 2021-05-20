/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import IService.IService;
import Utils.MyConnexion;
import entites.produit;
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
public class produit_Service implements IService<produit> {



    private Connection c = MyConnexion.getInsCon().getcnx();
 public void decrementer(int quantite,int id) throws SQLException {
         
        PreparedStatement ps;

        
        String query = "UPDATE produit set quantite= quantite-?  where id = ? ";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, quantite);
             ps.setInt(2, id);
            
          
            
         
            ps.execute();
            
          
        } catch (Exception e) {
              
       
            System.out.println(e);

        }
  }
    @Override
    public void Ajouter(produit u) throws SQLException {
         
        PreparedStatement ps;

        
        String query = "INSERT INTO `produit`( `prix`, `type`, `labelle`, `quantite`, `image`) VALUES (?,?,?,?,?)";
        try {
            ps = c.prepareStatement(query);
             ps.setInt(1, u.getPrix());
             ps.setString(2, u.getType());
             ps.setString(3, u.getLabelle());
             ps.setInt(4, u.getQuantite());
              ps.setString(5, u.getImage());
            
         
            ps.execute();
            
            System.out.println(u);
        } catch (Exception e) {
              
       
            System.out.println(e);

        }
  }
     public produit get_produit_affichage(int i) {
        produit p = null;
        int nombre = 0;
      String requete = "SELECT * FROM  produit where quantite!=0 "       ;
         try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (i == nombre) {
                    
       p=new produit(rs.getInt("id"),rs.getInt("prix"),rs.getString("type"),rs.getString("labelle"),rs.getInt("quantite"),rs.getString("image"));
               
                }
                nombre++;
                         }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return p;

    }

    @Override
    public void Supprimer(int t) throws SQLException {
     PreparedStatement ps;

        String query = "DELETE FROM `produit` WHERE `id`=?  ";
      //  Acheter_Service s = new Acheter_Service();
      //  s.SupprimerU(id);
        try {
            ps = c.prepareStatement(query);

            ps.setInt(1, t);

            ps.execute();

            System.out.println("suppression de USer");
        } catch (Exception e) {
            System.out.println(e);
        } }
  public int Affichertaille() throws SQLException {
        int nbr = 0;        
        String requete = "SELECT COUNT(*) as nbr FROM `produit` where quantite!=0"       ;
                try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              nbr=rs.getInt("nbr");
            }
                   
        } catch (SQLException ex) {
            System.out.println(ex);
        }
                        System.out.println("le nombre est : "+nbr);   
        return nbr;

    }
    @Override
    public void Modifier(produit u, int id) throws SQLException {
   PreparedStatement ps;
        String query = "UPDATE `produit` SET `prix`=?,`type`=?,`labelle`=?,`quantite`=?,`image`=? WHERE  id = ?";
        try {
            
            ps = c.prepareStatement(query);
        ps.setInt(1, u.getPrix());
             ps.setString(2, u.getType());
             ps.setString(3, u.getLabelle());
             ps.setInt(4, u.getQuantite());
              ps.setString(5, u.getImage());
               ps.setInt(6, id);
           
            ps.execute();
    

        } catch (Exception e) {
        }
    
    }

    @Override
    public ObservableList<produit> Affichertout() throws SQLException {
    ObservableList<produit> list = FXCollections.observableArrayList();
      String requete = "select * from produit ";
        try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(new produit(rs.getInt("id"),rs.getInt("prix"),rs.getString("type"),rs.getString("labelle"),rs.getInt("quantite"),rs.getString("image")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
       public ObservableList<produit> serach(String cas) throws SQLException {
        ObservableList<produit> list = FXCollections.observableArrayList();
        String requete = "SELECT * FROM  produit p where  p.labelle LIKE '%" + cas + "%'or  p.type LIKE '%" + cas + "%' or  p.prix LIKE '%" + cas   + "%' or  p.quantite LIKE '%" + cas + "%' ";
       try {
            PreparedStatement ps = c.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
   list.add(new produit(rs.getInt("id"),rs.getInt("prix"),rs.getString("type"),rs.getString("labelle"),rs.getInt("quantite"),rs.getString("image")));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
