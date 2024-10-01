
package test;
import ma.projet.connexion.Connexion;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger; 
import ma.projet.devdata.DevData;
import java.util.Scanner;
import java.sql.ResultSetMetaData;

public class ExoJDBC {
    
    public static void ajouter(DevData d){
        Statement st = null;
        try{
            String req ="INSERT INTO devdata VALUES ('"+d.getDeveloppeur()+"','"+d.getJour()+"',"+d.getNbscripts()+")" ;
                  
            st=Connexion.getCn().createStatement();
            st.executeUpdate(req);
        }catch(SQLException e){
            System.out.println("erreur SQL!");
         }finally{     
        try{
        if(st != null)
         st.close();
        Connexion.closeCn();
        }catch(SQLException e){
            System.out.println("Erreur lors de la fermeture des ressources");
        }}    
    }
   public static void maxScripts() {
    Statement st = null;
     
    try {
     
        String req = "SELECT Jour, MAX(NbScripts) AS max FROM devdata GROUP BY Jour";
        st = Connexion.getCn().createStatement();
        ResultSet rs = st.executeQuery(req);

        System.out.println("Jour | Developpeurs | NbScripts");

        while (rs.next()) {
            String jour = rs.getString("Jour");
            int max = rs.getInt("max");

            String req2 = "SELECT Developpeurs FROM devdata WHERE Jour = '" + jour + "' AND NbScripts = " + max;
            Statement st2 = Connexion.getCn().createStatement();
            ResultSet rs2 = st2.executeQuery(req2);

            while (rs2.next()) {
                String developpeur = rs2.getString("Developpeurs");
                System.out.println(jour + " | " + developpeur + "        | " + max);
            }
            st2.close(); 
        }
        
    } catch (SQLException e) {
        System.out.println("Erreur SQL: " + e.getMessage());
    } finally {
        try {
          if (st != null) st.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture des ressources: " + e.getMessage());
        }
    }
    Connexion.closeCn();
}
   public static void trier(){
       Statement st=null;
            try{
                String req ="SELECT Developpeurs, sum(NBScripts) AS c " +
                            "FROM devdata GROUP BY Developpeurs "
                            + "ORDER BY c desc";
                          
                            
                st = Connexion.getCn().createStatement();
                ResultSet rs = st.executeQuery(req);
                System.out.println("Developpeurs | "+"Scripts");
                while (rs.next()) {
                
                System.out.println(rs.getString(1) + "        | " + rs.getInt(2));
            }
            }catch (SQLException e) {
                     
               System.out.println("erreur SQL"+e.getMessage());
        }finally{
                try{
             if (st != null) st.close();
             
            } catch(SQLException e){
              System.out.println("Erreur lors de la fermeture des ressources");
            }
            }
            Connexion.closeCn();
        
   }
   public static void scriptsSemaine(){
        Statement st=null;
            try{
                String req =" SELECT SUM(NbScripts) AS total FROM devdata "
           + "WHERE Jour IN ('lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi', 'dimanche')";
                          
                            
                st = Connexion.getCn().createStatement();
                ResultSet rs = st.executeQuery(req);
                System.out.println("Total des scripts ");
                while (rs.next()) {
                
                System.out.println(rs.getString(1));
            }
            }catch (SQLException e) {
                     
               System.out.println("erreur SQL"+e.getMessage());
        }finally{
                try{
             if (st != null) st.close();
             
            } catch(SQLException e){
              System.out.println("Erreur lors de la fermeture des ressources");
            }
            }
            Connexion.closeCn();
       
   }
   public static void scriptsProg(String p){
      Statement st=null;
            try{
                String req =" SELECT Developpeurs ,SUM(NbScripts) AS somme FROM devdata WHERE Developpeurs ='" + p + "'";             
                st = Connexion.getCn().createStatement();
                ResultSet rs = st.executeQuery(req);
                System.out.println("Total des scripts pour "+p+" :");
                while (rs.next()) {
                
                System.out.println(rs.getInt(2));
            }
            }catch (SQLException e) {
                     
               System.out.println("erreur SQL"+e.getMessage());
        }finally{
                try{
             if (st != null) st.close();
             
            } catch(SQLException e){
              System.out.println("Erreur lors de la fermeture des ressources");
            }
            }
            Connexion.closeCn();
            
   }
    public static void executeFreeQuery() {
        Statement st = null;
        ResultSet rs = null;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre requête SQL: ");
        String query = scanner.nextLine(); 

        try {
            st = Connexion.getCn().createStatement();

          
            if (query.trim().toLowerCase().startsWith("SELECT")) {
                rs = st.executeQuery(query);
                ResultSetMetaData metaData = rs.getMetaData();

                
                int columnCount = metaData.getColumnCount();
                System.out.println("Nombre de colonnes: " + columnCount);

           
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String columnType = metaData.getColumnTypeName(i);
                    System.out.println("Colonne " + i + ": " + columnName + " - Type: " + columnType);
                }


                System.out.println("\nContenu du résultat:");
                while (rs.next()) {
                    StringBuilder row = new StringBuilder();
                    for (int i = 1; i <= columnCount; i++) {
                        row.append(rs.getString(i)).append(" | ");
                    }
                    System.out.println(row.toString());
                }
            } else {
             
                int rowsAffected = st.executeUpdate(query);
                System.out.println("Nombre de lignes modifiées: " + rowsAffected);
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des ressources: " + e.getMessage());
            }
        }
        Connexion.closeCn();
        scanner.close();
    }
   public static void main(String[] args){
       //ajouter(new DevData("ALAMI", "Lundi", 1));
       //ajouter(new DevData("WAFI", "Lundi", 2));
       //ajouter(new DevData("SLAMI", "Mardi", 9));
       
       //maxScripts();
       //trier();
       //scriptsSemaine();
       //scriptsProg("WAFI");
       
       executeFreeQuery();
       
   }
    
}
