package Campi.Service;


import Campi.Entity.Commande;
import Campi.ISerivce.IService;
import Campi.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandeService implements IService<Commande>{
        Connection cnx;
    public CommandeService (){
        cnx = Myconnexion.getInstance().getCnx();}

/*****************************Add Commande***************************/
    @Override
    public void ajouter(Commande commande) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `commande`(`num_cmd`, `date_cmd`,`total_cmd`) VALUES ('"+commande.getNum_cmd()+"','"+commande.getDate_cmd()+"','"+commande.getTotal_cmd()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        }

    @Override
    public List<Commande> afficher() throws SQLException {
        List<Commande> LR = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM commande";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        Commande commande = new Commande();   
        commande.setId(rs.getInt("id"));
        commande.setNum_cmd(rs.getInt("num_cmd"));
        commande.setDate_cmd(rs.getTimestamp(3).toLocalDateTime());
        commande.setTotal_cmd(rs.getFloat("total_cmd"));
        LR.add(commande);
        }
        return LR;
    }

    
 /******************************* Delete Commande *********************************************/
    @Override
    public void supprimer(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from commande where id="+id;
        stm.executeUpdate(query);
    }

    

    
/******************************* FindById Commande *********************************************/
    public Commande SearchById(long id) throws SQLException{
        Statement stm = cnx.createStatement();
        Commande commande = new Commande();
        String query = "select * from commande where id="+id;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        commande.setId(rs.getInt("id"));
        commande.setNum_cmd(rs.getInt("num_cmd"));
        commande.setDate_cmd(rs.getTimestamp(3).toLocalDateTime());
        commande.setTotal_cmd(rs.getFloat("total_cmd"));}
        return commande;
        
        }     
    
/******************************* Modifier Commande *********************************************/
    @Override
    public void modifier(int id_Modifier, Commande commande) throws SQLException {
        Statement stm = cnx.createStatement();
        Commande r =SearchById(id_Modifier);
        String query = "UPDATE `commande` SET `num_cmd`='"+commande.getNum_cmd()+"',`date_cmd`='"+LocalDateTime.now()+"',`total_cmd`='"+commande.getTotal_cmd()+"'where id="+r.getId();
        stm.executeUpdate(query);
    }
/******************************* Nombre Commande *********************************************/     
    public int nbCommande(){
        int nbCommande = 0;
        try {
        ResultSet set = Myconnexion.getInstance().
        getCnx().prepareStatement("SELECT COUNT(id) FROM commande")
        .executeQuery();
        if (set.next()) {
        nbCommande = set.getInt(1);}}
        catch (SQLException ex) {
        Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);}
        return nbCommande;
        }    
}


