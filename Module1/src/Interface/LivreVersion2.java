package Interface;

public class LivreVersion2 extends Livre
{
    private String Autheur;

    public LivreVersion2(String titre, int nbrePage, String autheur)
    {
        super(titre, nbrePage);
        Autheur = autheur;
    }
    public void affiche(LivreVersion2 livreVersion2)
    {
        super.affiche(livreVersion2);
        System.out.println("autheur==="+this.Autheur);
    }
}
