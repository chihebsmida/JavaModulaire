package Collection;

import java.util.*;

public class Collections
{
    public static void main(String args []) {

       // List<Personne> personnes = new ArrayList<>();
        List<Personne> personnes = new LinkedList<>();
        Personne p1=new Personne("personne1","prenom1","HOMME",30);
        personnes.add(p1);
        personnes.add(new Personne("personne1","prenom1","HOMME",30));
        personnes.add(new Personne("personne2","prenom2","FEMME",30));
        personnes.add(new Personne("personne3","prenom3","HOMME",20));
        System.out.println("parcour list avec compteur");
        for(int i=0;i<personnes.size();i++)
        {
            System.out.println(personnes.get(i).toString());
        }
        System.out.println("parcour list avec iterator");
        Iterator<Personne>iterator=personnes.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next().toString());
        System.out.println("parcour list avec une variable de type personne");
        for (Personne personne:personnes)
            System.out.println(personne.toString());
        System.out.println("parcour liste avec methode foreach");
        personnes.forEach(p->{System.out.println(p.toString());});
        //personnes.forEach(Personne::toString);
      //  personnes.sort(((o1, o2) -> {if(o1.getAge()== o2.getAge())return o1.getNom().compareTo(o2.getNom());
      //  return o1.getAge()- o2.getAge();}));
        personnes.sort(new Personne()::compare);
        System.out.println(personnes.toString());
         // exercice ajouter 5 a l'age des personnes
        //1 methode
        personnes.forEach(personne -> personne.setAge(personne.getAge()+5));
        System.out.println("apres ajout de 5 au personnes=="+personnes);
        personnes.replaceAll(personne -> {personne.setAge(personne.getAge()-5);return personne;});
        System.out.println("apres moin de 5 au personnes=="+personnes);
       System.out.println(personnes.remove(new Personne("personne1","prenom1","HOMME",30)));
        System.out.println("new Liste ==="+personnes);

      // System.out.println("les deux premiers personnes sont : "+personnes.subList(0,2));
    //   System.out.println(personnes.remove(p1));

       // personnes.sort(new Personne()::compareVersion2);
        //System.out.println(personnes.toString());

       Set<String> strings=new HashSet<>();
       strings.add("b");
        strings.add("a");
        strings.add("a");
        strings.add("c");
        strings.add("e");
        strings.add("c");
        strings.add("A");
        TreeSet<String> stringTreeSet=new TreeSet<>(strings);
        System.out.println("set des String===="+stringTreeSet);

Set<Personne> personneSet=new HashSet<>();
personneSet.addAll(personnes);
//System.out.println("conversion de notre liste en Set=="+personneSet);
for (Personne personne:personneSet)
{
    System.out.println(personne.hashCode());
    System.out.println(personne);
}
Map<Integer,Personne>personneMap=new HashMap<>();
personneMap.put(1,new Personne("personne1","prenom1","HOMME",30));
personneMap.put(2,new Personne("personne2","prenom2","HOMME",50));
personneMap.put(3,new Personne("personne3","prenom2","HOMME",50));
System.out.println("taille map==="+personneMap.size());
System.out.println(personneMap.keySet());
System.out.println(personneMap);
System.out.println("le personne qui a l'indice numero 2==="+personneMap.get(2));

Personne[]personnesTable={new Personne("personne1","prenom1","HOMME",30),new Personne("personne2","prenom2","HOMME",40),new Personne("personne2","prenom2","HOMME",40)};

Arrays.parallelSort(personnesTable,new Personne()::compareVersion2);
for (Personne personne:personnesTable)
{
    System.out.println(personne);
}
Arrays.parallelPrefix(personnesTable,(personne1,personne2)->
{ personne2.setAge(personne2.getAge()+personne1.getAge());return personne2;}
);
        for (Personne personne:personnesTable)
        {
            System.out.println(personne);
        }

    }

}
