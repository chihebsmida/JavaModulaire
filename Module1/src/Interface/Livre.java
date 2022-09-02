package Interface;

public class Livre  implements  Ilivre{
    private String titre;
    public  int nbrePage;

    public Livre() {
    }

    public Livre(String titre, int nbrePage)
    {
        this.titre = titre;
        this.nbrePage = nbrePage;
    }


    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getNbrePage() {
        return nbrePage;
    }

    public void setNbrePage(int nbrePage) {
        this.nbrePage = nbrePage;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", nbrePage=" + nbrePage +
                '}';
    }

    @Override
    public void affiche(Livre livre)
    {
       System.out.println(livre.toString());
    }


    @Override
    public Livre createLivre() {
        return new Livre("NULL",0);
    }


}
