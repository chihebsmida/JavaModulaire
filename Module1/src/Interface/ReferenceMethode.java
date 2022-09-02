package Interface;

public class ReferenceMethode
{
    public static void afficheLivre(Livre livre)
    {
        System.out.println("instanciation avec reference methode avec static methode");
    }
    public  void afficheLivre1(Livre livre)
    {
        System.out.println("instanciation avec reference methode sans static methode");
    }
}
