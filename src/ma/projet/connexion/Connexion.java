
package ma.projet.connexion;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Connexion {
    private static String url="jdbc:mysql://localhost:3306/abs";
    private static String username="root";
    private static String password="";
    private static Connection cn = null;
    
    public static Connection getCn(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection(url,username,password);
            
        }catch(ClassNotFoundException ex){
            System.out.println("Driver introuvable !");   
        }catch(SQLException e){
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, e);
        }
        return cn;
    }
   public static void closeCn(){
       if(cn != null){
           try{
             cn.close();
           }catch(SQLException e){
           Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, e);
           }finally{
               cn = null;
           }
       }}
    
}
