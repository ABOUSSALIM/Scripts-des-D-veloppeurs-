
package ma.projet.devdata;
   
public class DevData {
    private static String developpeur;
    private static String jour;
    private static int Nbscripts;

    public DevData(String developpeur,String jour,int Nbscripts) {
        this.developpeur=developpeur;
        this.jour=jour;
        this.Nbscripts= Nbscripts;
    }
    

    public String getDeveloppeur() {
        return developpeur;
    }

    public String getJour() {
        return jour;
    }

    public int getNbscripts() {
        return Nbscripts;
    }

    public void setDeveloppeur(String developpeur) {
        DevData.developpeur = developpeur;
    }

    public void setJour(String jour) {
        DevData.jour = jour;
    }

    public void setNbscripts(int Nbscripts) {
        DevData.Nbscripts = Nbscripts;
    }

    
   
    
}
