package Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateManagement
{
    public static void main(String args []) throws ParseException {
        //inconvenient class Date java1
        // les mois sont de 0 a 11 ;
        //l'année commence en 1900
        // elle ne propose aucun support pour l'internationalisation

        Date date=new Date(100,12,25);
        System.out.println(date);

        // classe SimpleDateFormat

        // afficher date sous format String
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/YYYY");
        String dateStr=simpleDateFormat.format(new Date());
        System.out.println("formater date Aujourdhuit "+dateStr);
        simpleDateFormat=new SimpleDateFormat("dd MMMM YYYY hh:mm", Locale.FRENCH);
        dateStr=simpleDateFormat.format(new Date());
        System.out.println("formater date 2 eme version=="+dateStr);
        // creation date a travers String
        simpleDateFormat=new SimpleDateFormat("dd/MM/YYYY");
        Date date1=simpleDateFormat.parse("01/11/2020");
        System.out.println(date1);
        System.out.println(date.getMonth());
        // point faible
        // instanciation est trés couteise
        // ses traitement ne sont pas threadsafe
        // Api date and Time
        /*
        C'est une api introduite par java8 pour resoudre les problemes des api date tel que la securité des thread et la gestion
        des fuseau horaire
        l'api date and time permet de representer les dates , le temp et la duréé et touts ces classes ont regroupé dans une nouvelle package java.time
         */
        // exemples des classes didies
        LocalDate localDate=LocalDate.of(2022, Month.SEPTEMBER,02);//permet la representation du date
        System.out.println("la date est=="+localDate);
        // pour ajouter d'un jour a une date
        LocalDate localDate1=localDate.plus(1, ChronoUnit.DAYS);
        System.out.println("la date + 1 day  est=="+localDate1);
        System.out.println(localDate.plusMonths(1));
        LocalTime time=LocalTime.now();// lacaltime permet la representation du temps
        System.out.println("le temp actuel est "+time);
        System.out.println(time +"- 2 heure ="+time.minusHours(2));
        LocalDate date2=LocalDate.parse("2022-09-01");
        System.out.println("le day of the week =="+date2.getDayOfWeek());
        LocalDate date3=LocalDate.parse("2022-05-30");
        System.out.println("la date 2 > la date3 "+date2.isBefore(date3));
        OffsetTime offsetTime=OffsetTime.now(Clock.systemUTC());
        System.out.println("Time en UTC==="+offsetTime);
        offsetTime=OffsetTime.now(ZoneId.of("Europe/Paris"));
        System.out.println("time en Jakarta"+offsetTime);
        // Linterface TemporalAdjuster c'est un outil d'ajustement d'un objet Temporal pour en creer une copie
        // quel que soit la date introduite changer le month mai et l'annes 2020
        // donc on vas ajuster le temp introduit
       // TemporalAdjuster adjuster=YearMonth.of(2020,5);
        TemporalAdjuster adjuster=MonthDay.of(02,02);
        localDate =LocalDate.now();
        LocalDate adjusterLocalDate= (LocalDate) adjuster.adjustInto(localDate);
        System.out.println("lacaldate avant ajustement:   "+localDate);
        System.out.println("localdate apres ajustement    "+adjusterLocalDate);
        Clock clock=Clock.systemUTC();
        System.out.println(clock);
        // cree une instance sans date ni heure
        System.out.println("instanciation time avec clock:   "+ clock.instant());

        // comment fait l'integration du java.time avec du code anterieur de java8 ?
        // comment passer de java.util vers java.time et l'inverse
        //1 passer de calender vers instant
        Instant instant= Calendar.getInstance().toInstant();
        //2 reciproquement
        // passer de instant vers date
        Date date4=Date.from(instant);








    }
}
