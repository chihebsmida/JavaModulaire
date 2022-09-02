package Interface;
//une interface fonctionnelle contient une seule methode abstraite
@FunctionalInterface
public interface Ilivre
{
    //methode abstraite chaque class implemente l'interface doit redefinir la methode
    void affiche(Livre livre);
    // methode static on peut pas le changer juste on l'utilise
    static void Schow(Livre livre){System.out.println("this is a static method "+livre.toString());}
    // ona deux choix soit on redefenir la methode soit on utilise le code par defaut de l'interface
    default  Livre createLivre(){return new Livre("titre par defaut",0);};
}

@FunctionalInterface
interface constructor
{
    Livre createLivre(String titre,int nbrePage);
}
