package Collection;

import java.io.File;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamManipulation
{
    public static void main(String args [])
    {
        //instanciation d'un stream
        //1 instanciation via Collection
        List<String>listString= Arrays.asList("a1","a2","a3");
        listString.stream();
        // 2 instanciation via tableau
        int []tableInt={1,2,3,4};
        Arrays.stream(tableInt);
        // 3 instanciation via range(borninf,bornsup)
        IntStream intStream=IntStream.range(10,20);
        // 4 instanciation d'une stream vide
        Stream.empty();
        //5 instanciation via lines d'un fichier
        File file=new File("/Users/chiheb/Desktop/FormationJava8-13/file.txt");
        try {
            Stream<String> fileLines = Files.lines(file.toPath());
            System.out.println(fileLines.collect(Collectors.toList()));// renvoyer le stream dans un liste
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //6 insta,ciation via la methode of() de l'interface Stream
        Stream<Integer> stream=Stream.of(new Integer[]{1,2,3,4});
        // les methode Intermediaires
        //1 filtrer tous les elements pour n'inclure dans la stream que les elementent qui satisfont le predicat
        //exemple filtrer que les chaines de long >5
        List<String>stringList=new ArrayList<>();
        stringList.add("azerty");
        stringList.add("azexrty");
        stringList.add("azerxty1");
        stringList.add("chx");
      /*  List<String>stringList1=new ArrayList<>();
       for(String s:stringList)
        {
            if(s.length()>5)
                stringList1.add(s);
        }
        System.out.println(stringList1);

        */
       System.out.println(stringList.stream().filter(p->p.length()>5).collect(Collectors.toList()));
       //2 map &mapToInt & mapToDouble
        //permet la projection d'un element de la liste vers un autre type
        //newElement map(oldelement)
        // exemple creer un tableau des entier contien le size de chaque element de la liste
        stringList.stream().mapToInt(e->e.length()).forEach(e->System.out.println(e));
        System.out.println(stringList.stream().map(e->e.toUpperCase(Locale.ROOT)).collect(Collectors.toList()));
//limit (entier n) lorsque le stream atteind le size n il stop le traitement
        // exemple afficher les deux premiers elements de la liste qui contien "x"
        System.out.println(stringList.stream().filter(e->e.contains("x")).limit(2).collect(Collectors.toList()));
// sorted et reversed permet de trier une liste
List<Personne> personneList=new ArrayList<>();
        personneList.add(new Personne("personne1","prenom1","HOMME",30));
        personneList.add(new Personne("personne2","prenom2","FEMME",10));
        personneList.add(new Personne("personne1","prenom3","HOMME",20));
        // sorter la liste par nom sinon par age
        personneList.stream().sorted(Comparator.comparing(Personne::getNom).thenComparing(Personne::getAge)).forEach(System.out::println);
        System.out.println("liste trie inversement");
        personneList.stream().sorted(Comparator.comparing(Personne::getNom).thenComparing(Personne::getAge).reversed()).forEach(System.out::println);
   // les Operation Terminale
        //1 max et min
        //class Wrappers(se sont les classes des type de base comme String ,Integer,Float) avec Class Optional
        int []tableInt1={10,20,1,4};
        OptionalInt max=Arrays.stream(tableInt1).max();
        OptionalInt min=Arrays.stream(tableInt1).min();
        if(max.isPresent())
        {
            System.out.println("le max est ==="+max.getAsInt());
        }
        else
        {
            System.out.println("le max n'existe pas");
        }
        if(min.isPresent())
        {
            System.out.println("le min ===="+min.getAsInt());
        }
        else
            System.out.println("le min n'exite pas");
        System.out.println("le max est egale =="+Arrays.stream(tableInt1).max().orElse(0));
//max et min pour les autre class n'est pas class wrapers
        Optional<Personne>personne=personneList.stream().max(new Personne()::compare);
        if(personne.isPresent())
        {
            System.out.println("le max est egale =="+personne.get());
        }
        //3 findFirst et FindAny
        personne=personneList.stream().findFirst();
        if(personne.isPresent())
        {
            System.out.println("le premier element de la liste ==="+personne.get());
        }
        //4 anyMatch ,all match return boolean si les elements conforment le predicat (condition) ou pas
        System.out.println("tout les personnes ont un age >20"+personneList.stream().allMatch(personne1 -> personne1.getAge()>20));
   // verifier si au moin un element a un age >20
        System.out.println(" un element a un age >20"+personneList.stream().anyMatch(personne1 -> personne1.getAge()>20));
   // count returner le size avg (moyenne) sum (somme)
        // calculer le nbre des personnes on un age >20
        System.out.println("le nbre des elements ont un age>20==="+personneList.stream().filter(e->e.getAge()>20).count());
        System.out.println("la moyenne des ages est===="+personneList.stream().mapToInt(p->p.getAge()).average().orElse(0));
        System.out.println("la somme des ages est===="+personneList.stream().mapToInt(p->p.getAge()).sum());

   //les stream paralleles
        List<String>prenoms=Arrays.asList("prenom1","prenom2","prenom3","prenom4");
        Consumer<String>afficherElement=s -> System.out.println(s+"_"+Thread.currentThread().getName());
        prenoms.parallelStream().forEach(afficherElement);
        System.out.println(prenoms.parallelStream().collect(Collectors.toList()));
        // si on a besoin de l'ordre alors on utilise foreachOrdered
        System.out.println("affichage de la liste avec multiThread mais sellon lordre ");
        prenoms.parallelStream().sorted().forEachOrdered(afficherElement);
        //exercice
        // calculer la moyenne des longuers des prenoms des "HOMME" pour une liste des Personnes
        //1 avec Collection
        //2 avec les stream simple
        //3 avec les stream parallel

        double sum = 0;
        int count = 0;
        for(Personne personne1: personneList) {
            if(null != personne1.getPrenom() && "HOMME".equals(personne1.getGenre()))
            {
                sum += personne1.getPrenom().length();
                count++;
            }
        }
        if(count>0) System.out.println("Somme avec collection = "+ (sum/count));
        System.out.println("------------------------------------------");
        System.out.println("Somme avec stream = "+ personneList.stream()
                .filter(personne1 -> null != personne1.getPrenom() && "HOMME".equals(personne1.getGenre()))
                .mapToInt(p -> p.getPrenom().length())
                .average().orElse(0));
        System.out.println("------------------------------------------");
        System.out.println("Somme avec stream para = "
                + personneList.parallelStream()
                .filter(personne1 -> null != personne1.getPrenom() && "HOMME".equals(personne1.getGenre()))
                .mapToInt(p -> p.getPrenom().length())
                .average().orElse(0));


    }
}
