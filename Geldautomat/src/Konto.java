/**
 * Die Klasse Konto ermöglicht das Erstellen von Konten sowie.
 * das Einzahlen, Auszahlen und Ausgeben von Informationen über diese Konten.
 */

public class Konto
{

    private String kontonummer;
    private double kontostand;

    /**
     * Konstruktor zum Erstellen eines Kontos mit einer angegebenen Kontonummer.
     * Der Kontostand wird dabei auf 0 gesetzt.
     *
     * @param kontonummer Die eindeutige Kontonummer des Kontos
     */
    public Konto(String kontonummer)
    {
        this.kontonummer = kontonummer;
        this.kontostand = 0.0;
    }

    /**
     * gibt den Wert der Variable Kontonummer wieder
     * @return kontonummer
     */
    public String getKontonummer()
    {
        return kontonummer;
    }

    /**
     * Setzt den Wert der Variable Kontonummer auf einen Bestimmten.
     * @param kontonummer - wert der Kontonummer
     */
    public void setKontonummer(String kontonummer)
    {
        this.kontonummer = kontonummer;
    }

    /**
     * gibt den Wert der Variable Kontostand wieder
     * @return konostand
     */
    public double getKontostand()
    {
        return kontostand;
    }

    /**
     * Setzt den Wert der Variable Kontostand auf einen Bestimmten.
     * @param Kontostand - wert des Kontostands
     */
    public void setKontostand(double Kontostand)
    {
        this.kontostand = Kontostand;
    }

    /**
     * Fügt einen bestimmten Betrag dem Konto zu.
     *
     * @param betrag - der zu einzuzahlende Betrag.
     */
    public void einzahlen (double betrag)
    {
        if(betrag > 0)
        {
            kontostand += betrag;
            System.out.println("Es wurden " + betrag + "€ eingezahlt");
        }
        else
        {
            System.out.println("ungültiger Betrag");
        }
    }

    /**
     * Zieht eine Bestimmte Menge vom Konto ab.
     *
     * @param betrag der abzuziehende Betrag
     */
    public void auszahlen(double betrag)
    {
        if (betrag > 0 && kontostand >= betrag)
        {
            kontostand -= betrag;
            System.out.println("es wurden " +betrag + "€ abgebucht");
        }
        else if (kontostand < betrag)
        {
            System.out.println("unzureichende Deckung auf Konto");
        }
        else if (betrag < 0)
        {
            System.out.println("ungültiger Betrag");
        }
    }

    /**
     * Zeigt alle möglichen Infos des Kontos an.
     */
    public void info()
    {
        System.out.println("Kontonummer: " + kontonummer);
        System.out.println("Kontostand: " + kontostand);
    }
}
