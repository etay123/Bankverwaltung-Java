import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Die Klasse Bankverwaltung stellt ein einfaches Verwaltungssystem für Bankkonten dar.
 * Sie ermöglicht das Anlegen von Giro- und Sparkonten, Ein- und Auszahlungen,
 * das Berechnen von Zinsen sowie das Anzeigen von Kontoinformationen über ein Konsolenmenü.
 *
 * Die Konten werden in einer ArrayList gespeichert und über deren Kontonummer verwaltet.
 */
public class Bankverwaltung
{
    private ArrayList<Konto> konten = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    /**
     * Startet das Hauptmenü der Bankverwaltung.
     * Über eine Endlosschleife wird der Benutzer aufgefordert, eine Option auszuwählen.
     * Je nach Auswahl können Konten angelegt, angezeigt, bearbeitet oder gelöscht werden.
     * Zusätzlich lassen sich Zinsen berechnen und alle Konten ausgeben.
     *
     * Das Programm läuft, bis der Benutzer die Option 0 (Beenden) wählt.
     */
    public void starten()
    {
        boolean running = true;
        while(running)
        {
            System.out.println("\n--- Bank Verwaltung Menü ---");
            System.out.println("1. Konto anlegen");
            System.out.println("2. Konto anzeigen");
            System.out.println("3. Einzahlen");
            System.out.println("4. Abheben");
            System.out.println("5. Zinsen berechnen (nur Sparkonto)");
            System.out.println("6. Zinsen aller Sparkonten berechnen");
            System.out.println("7. Alle Konten Anzeigen");
            System.out.println("8. Konto löschen");
            System.out.println("0. Beenden");
            System.out.print("Wähle eine Option: ");

            int auswahl = scanner.nextInt();
            scanner.nextLine();

            switch(auswahl)
            {
                case 1 -> kontoAnlegen();
                case 2 -> kontoAnzeigen();
                case 3 -> einzahlen();
                case 4 -> auszahlen();
                case 5 -> zinsenberechen();
                case 6 -> zinsenfueralleBerechnen();
                case 7 -> alleKontenAnzeigen();
                case 8 -> kontoLoeschen();
                case 0 ->
                {
                    running = false;
                    System.out.println("Programm wird Beendet");
                }
                default -> System.out.println("ungültige Eingabe!");
            }
        }
        scanner.close();
    }

    /**
     * Legt ein neues Konto an.
     * Der Benutzer wird nach der Kontonummer und der gewünschten Kontoart gefragt.
     *
     * Je nach Auswahl wird entweder ein Girokonto mit Dispolimit oder ein Sparkonto mit Zinssatz erstellt
     * und zur Kontenliste hinzugefügt.
     *
     * Bei ungültiger Eingabe der Kontoart wird eine Fehlermeldung ausgegeben.
     */

    private void kontoAnlegen()
    {
        System.out.println("Kontonummer: ");
        String nummer = scanner.nextLine();
        System.out.println("Kontoart:");
        System.out.println("1 = Girokonto");
        System.out.println("2 = Sparkonto");
        int art = scanner.nextInt();
        scanner.nextLine();

        if(art == 1)
        {
            System.out.println("Dispolimit setzten: ");
            double dispo = scanner.nextDouble();
            scanner.nextLine();
            Girokonto gk = new Girokonto(nummer, dispo);
            konten.add(gk);
            System.out.println("Girokonto angelegt");
        }
        else if (art == 2)
        {
            System.out.println("Zinssatz setzen (z.B. 0.02 für 2%:");
            double zinssatz;
            zinssatz = scanner.nextDouble();
            scanner.nextLine();
            Sparkonto spk = new Sparkonto(nummer, zinssatz);
            konten.add(spk);
            System.out.println("Sparkonto angelegt");
        }
        else
        {
            System.out.println("ungültige Kontoart!!");
        }
    }

    /**
     * Sucht ein Konto anhand der eingegebenen Kontonummer.
     *
     * Der Benutzer wird aufgefordert, eine Kontonummer einzugeben.
     * Anschließend wird die Kontenliste durchsucht.
     *
     * @return das gefundene Konto-Objekt oder {@code null}, wenn kein Konto mit der eingegebenen Nummer existiert.
     */

    private Konto kontoSuchen()
    {
        System.out.println("Kontonummer eingeben");
        String nummer = scanner.nextLine();
        for(Konto k : konten)
        {
            if(k.getKontonummer().equals(nummer))
            {
                return k;
            }
        }
        System.out.println("Konto nicht gefunden");
        return null;
    }

    /**
     * Zeigt die Informationen eines bestimmten Kontos an.
     *
     * Es wird zunächst nach der Kontonummer gefragt.
     * Falls das Konto existiert, werden die Kontoinformationen ausgegeben.
     */

    private void kontoAnzeigen()
    {
        Konto k = kontoSuchen();
        if(k != null)
        {
            k.info();
        }
    }

    /**
     * Ermöglicht das Einzahlen eines Betrags auf ein bestimmtes Konto.
     *
     * Es wird nach der Kontonummer gesucht, und falls das Konto gefunden wird,
     * wird der Benutzer nach dem Einzahlungsbetrag gefragt, der anschließend gutgeschrieben wird.
     */

    private void einzahlen()
    {
        Konto k = kontoSuchen();
        if(k != null)
        {
            System.out.println("Betrag zum einzahlen: ");
            double betrag = scanner.nextDouble();
            scanner.nextLine();
            k.einzahlen(betrag);
        }
    }

    /**
     * Ermöglicht das Abheben eines Betrags von einem bestimmten Konto.
     *
     * Zuerst wird die Kontonummer gesucht. Wenn das Konto gefunden wird,
     * fragt das Programm nach dem Auszahlungsbetrag und zieht diesen vom Konto ab,
     * sofern genügend Guthaben (inklusive Dispolimit bei Girokonto) vorhanden ist.
     */

    private void auszahlen()
    {
        Konto k = kontoSuchen();
        if(k != null)
        {
            System.out.println("Betrag zum Auszahlen ");
            double betrag = scanner.nextDouble();
            scanner.nextLine();
            k.auszahlen(betrag);
        }
    }

    /**
     * Berechnet die Zinsen für ein Sparkonto.
     *
     * Die Methode sucht zuerst ein Konto anhand der Kontonummer.
     * Wenn das Konto ein Sparkonto ist, werden die Zinsen berechnet und dem Kontostand gutgeschrieben.
     * Für andere Kontoarten wird eine entsprechende Meldung ausgegeben.
     */

    private void zinsenberechen()
    {
        Konto k = kontoSuchen();
        if(k != null)
        {
            if(k instanceof Sparkonto spk)
            {
                spk.zinsenberechnen();
            }
            else
            {
                System.out.println("Diese Funktion ist nur für Sparkonten verfügbar");
            }
        }
    }

    /**
     * Berechnet die Zinsen für alle vorhandenen Sparkonten.
     *
     * Die Methode durchläuft alle Konten und führt für jedes Sparkonto die Zinsberechnung durch.
     * Falls keine Sparkonten vorhanden sind, wird eine entsprechende Meldung ausgegeben.
     */

    private void zinsenfueralleBerechnen()
    {
        boolean gefunden = false;
        for(Konto k : konten)
        {
            if(k instanceof Sparkonto spk)
            {
                spk.zinsenberechnen();
                gefunden = true;
            }
        }
        if(!gefunden)
        {
            System.out.println("Keine Sparkonten vorhanden.");
        }
    }

    /**
     * Gibt alle vorhandenen Konten mit ihren Informationen aus.
     *
     * Falls keine Konten vorhanden sind, wird eine entsprechende Meldung ausgegeben.
     */


    private void alleKontenAnzeigen()
    {
        if(konten.isEmpty())
        {
            System.out.println("Keine Konten Vorhhaden");
        }
        else
        {
            for(Konto k: konten)
            {
                System.out.println("--------------------");
                k.info();
                System.out.println("--------------------");
            }
        }
    }

    /**
     * Löscht ein Konto basierend auf der eingegebenen Kontonummer.
     * Fragt vor dem Löschen eine Bestätigung ab.
     *
     * Gibt eine Meldung aus, wenn das Konto nicht gefunden wird oder
     * wenn der Löschvorgang abgebrochen wurde.
     */


    private void kontoLoeschen()
    {
        System.out.println("Kontonummer eingeben zum löschen:");
        String nummer = scanner.nextLine();

        Konto gefundenesKonto = null;
        for(Konto k : konten)
        {
            if(k.getKontonummer().equals(nummer))
            {
                gefundenesKonto = k;
                break;
            }
        }
        if(gefundenesKonto != null)
        {
            System.out.println("bist du sicher, dass du das Konto löschen möchtest? (j/n)");
            String eingabe = scanner.nextLine().toLowerCase();
            if(eingabe.equals("j"))
            {
                konten.remove(gefundenesKonto);
                System.out.println("Konto wurde gelöscht");
            }
            else
            {
                System.out.println("Löschen abgebrochen.");
            }
        }
        else
        {
            System.out.println("Konto nicht gefunden");
        }
    }

}
