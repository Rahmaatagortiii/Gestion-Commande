package Campi.Service;


import Campi.Entity.Facture;
import Campi.ISerivce.IService;
import Campi.Utils.Myconnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceFacture implements IService<Facture>{
        Connection cnx;
    public ServiceFacture (){
        cnx = Myconnexion.getInstance().getCnx();}

/*****************************Add promotion***************************/
    @Override
    public void ajouter(Facture facture) {
        Statement st;
        try {
        st = cnx.createStatement();
        String query ="INSERT INTO `facture`(`num_fact`, `total_fact`, `date_fact`) VALUES ('"+facture.getNum_fact()+"','"+facture.getTotal_fact()+"','"+facture.getDate_fact()+"')";
        st.executeUpdate(query);}         
        catch (SQLException ex) {
        System.out.println(ex.getMessage());}
        }

    @Override
    public List<Facture> afficher() throws SQLException {
        List<Facture> LR = new ArrayList<>();
        Statement stm = cnx.createStatement();
        String query = "SELECT * FROM facture";
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        Facture facture = new Facture();   
        facture.setId(rs.getInt("id"));
        facture.setNum_fact(rs.getInt("num_fact"));
        facture.setTotal_fact(rs.getFloat("total_fact"));
        facture.setDate_fact(rs.getTimestamp(4).toLocalDateTime());
        LR.add(facture);
        }
        return LR;
    }

    
 /******************************* Delete promotion *********************************************/
    @Override
    public void supprimer(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "delete from facture where id="+id;
        stm.executeUpdate(query);
    }

    

    
/******************************* FindById promotion *********************************************/
    public Facture SearchById(long id) throws SQLException{
        Statement stm = cnx.createStatement();
        Facture facture = new Facture();
        String query = "select * from facture where id="+id;
        ResultSet rs= stm.executeQuery(query);
        while (rs.next()){
        facture.setId(rs.getInt("id"));
        facture.setNum_fact(rs.getInt("num_fact"));
        facture.setTotal_fact(rs.getFloat("total_fact"));
        facture.setDate_fact(rs.getTimestamp(4).toLocalDateTime());}
        return facture;
        
        }     
    
/******************************* Modifier promotion *********************************************/
    @Override
    public void modifier(int id_Modifier, Facture facture) throws SQLException {
        Statement stm = cnx.createStatement();
        Facture p =SearchById(id_Modifier);
        String query = "UPDATE `facture` SET `num_fact`='"+facture.getNum_fact()+"',`total_fact`='"+facture.getTotal_fact()+"',`date_fact`='"+facture.getDate_fact()+"' where id="+p.getId();
        stm.executeUpdate(query);
    }
    
/******************************* Nombre Facture *********************************************/     
    public int nbFacture(){
        int nbFacture = 0;
        try {
        ResultSet set = Myconnexion.getInstance().
        getCnx().prepareStatement("SELECT COUNT(id) FROM facture")
        .executeQuery();
        if (set.next()) {
        nbFacture = set.getInt(1);}}
        catch (SQLException ex) {
        Logger.getLogger(ServiceFacture.class.getName()).log(Level.SEVERE, null, ex);}
        return nbFacture;
        }
    
             public HashMap<String, Double> StatistiqueParNom() {
        HashMap<String, Double> data = new HashMap<>();
        try {
            Statement stm = cnx.createStatement();
            String query = "SELECT num_fact, COUNT(*) as nb FROM facture GROUP BY num_fact;";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                int nb = rs.getInt("nb");
                String key = nb + " " + rs.getString("num_fact");
                data.put(key, new Double(nb));
            }
            System.out.println(data.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return data;
}
}
