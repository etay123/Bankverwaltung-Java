/**
 * Die Klasse Girokonto erweitert Konto und repräsentiert ein Girokonto mit Dispolimit.
 *
 * Das Dispolimit definiert den maximalen Überziehungsrahmen.
 * Auszahlungen sind bis zum Kontostand plus Dispolimit möglich.
 *
 * Methoden:
 * - auszahlen: Prüft ob Auszahlung im Rahmen von Kontostand + Dispolimit möglich ist.
 * - setDispolimit: Setzt das Dispolimit, wenn es größer als 0 ist.
 * - info: Gibt Konto-Informationen inklusive Dispolimit aus.
 */

public class Girokonto extends Konto
{
    private double dispolimit;

    /**Konstruktor
     *
     * @param kontonummer
     * @param dispolimit
     */
    public Girokonto(String kontonummer, double dispolimit)
    {
        super(kontonummer);
        this.dispolimit = dispolimit;
    }

    /**
     * gibt den Wert der Variable Kontonummer wieder
     *
     * @return dispolimit - Wert der Variable dispolimit
     */
    public double getDispolimit()
    {
        return dispolimit;
    }

    /**
     * Setzt das Dispolimit für das Girokonto.
     *
     * @param dispolimit Der neue Überziehungsrahmen; muss größer als 0 sein.
     *
     * Wenn der Wert gültig ist, wird das Dispolimit gesetzt und eine Bestätigung ausgegeben.
     * Andernfalls erfolgt eine Fehlermeldung.
     */

    public void setDispolimit(double dispolimit)
    {
        if(dispolimit > 0)
        {
            this.dispolimit = dispolimit;
            System.out.println("Das neue Dispolimit ist: " + dispolimit + "€");
        }
        else
        {
            System.out.println("Dispolimit muss > 0 0der = 0 sein");
        }

    }

    /**
     * Zahlt einen Betrag vom Girokonto ab, unter Berücksichtigung des Dispolimits.
     *
     * @param betrag Der Betrag, der ausgezahlt werden soll. Muss größer als 0 sein.
     *
     * Wenn der Kontostand plus Dispolimit ausreicht, wird der Betrag abgezogen und eine Bestätigung ausgegeben.
     * Wenn die Deckung nicht ausreicht, wird eine Fehlermeldung angezeigt.
     * Bei einem ungültigen (negativen) Betrag wird ebenfalls eine Fehlermeldung ausgegeben.
     */
    @Override
    public void auszahlen(double betrag)
    {
        if(betrag > 0 && getKontostand() + dispolimit >= betrag)
        {
            setKontostand(getKontostand() - betrag);
            System.out.println("es wurden " +betrag+ "€ ausgezahlt");
        }
        else if(getKontostand() + dispolimit < betrag)
        {
            System.out.println("unzureichende Deckung auf Konto");
        }
        else if(betrag < 0)
        {
            System.out.println("ungültiger Betrag");
        }
    }

    /**
     * Gibt die Informationen des Girokontos aus.
     *
     * Ruft zuerst die Info-Methode der Basisklasse Konto auf,
     * um Kontonummer und Kontostand anzuzeigen,
     * und gibt anschließend das Dispolimit aus.
     */
    @Override
    public void info()
    {
        super.info();
        System.out.println("Dispolimit: "+dispolimit+ "€");
    }
}
