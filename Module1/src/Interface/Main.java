package Interface;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Livre livre=new Livre("test1",20);
       Livre livre1= livre.createLivre();
       livre1.affiche(livre1);
       Ilivre.Schow(livre);
       Ilivre ilivre=new Ilivre() {
           @Override
           public void affiche(Livre livre) {
           System.out.println("instanciation Ilivre sans passer par la class livre");
           }
       };
       ilivre.affiche(new Livre());
        // une expression lambda nous permet l'instanciation d'une interface fonctionnell
        Ilivre ilivre2=(l)->{ System.out.println("instanciation Ilivre avec expression lambda");};
        ilivre2.affiche(new Livre());
        Function<String,Integer> getLength=str->str.length();
        getLength=getLength.andThen(l->l*2);
        getLength=getLength.compose(str->str+"t");
        System.out.println(getLength.apply("test"));

        Predicate<String>verifLen=str->str.length()>5;
        System.out.println(verifLen.test("testtt"));
        Consumer<Livre> setNbrePage=(livre2 -> {livre2.setNbrePage(10);livre2.setTitre("consummer");}); // void consummer(Livre livre)
        Livre livre2=new Livre();
        setNbrePage.accept(livre2);
        System.out.println(livre2.toString());
        Supplier<Livre> Constructeur=()->{Livre livre3=new Livre();return livre3;};
        Livre livre3=Constructeur.get();

       // Ilivre ilivre1=(l)->{System.out.println("instanciation avec reference methode");};
        Ilivre ilivre1=ReferenceMethode::afficheLivre;
        Ilivre ilivre3=new ReferenceMethode()::afficheLivre1;
        ilivre1.affiche(new Livre());
        ilivre3.affiche(new Livre());
        constructor constructor=Livre::new;

        String[]strings=new String[5];
        Optional<String>stringOptional=Optional.ofNullable(strings[2]);
        if(stringOptional.isPresent())//si la valeur n'est pas null
        {
            System.out.println(strings[2].toString());
        }
        else
        {
            System.out.println("la chaine est vide");
        }
        LivreVersion2 livreVersion2=new LivreVersion2("test1",1,"autheur1");
        livreVersion2.affiche(livreVersion2);
        ArrayList<Livre> livres=new ArrayList<>();
        livres.add(new Livre("aaa",20));
        livres.add(new LivreVersion2("bb",10,"AUtheur1"));
        livres.add(new Livre("bbb",30));
        for (Livre livre4:livres)
        {
            livre4.affiche(livre4);
        }

    }

}
