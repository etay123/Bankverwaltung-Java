/**
 * Klasse Sparkonto erweitert Konto und repräsentiert ein Sparkonto mit Zinssatz.
 */
public class Sparkonto extends Konto
{
    private double zinssatz;

    /**
     * Konstruktor zum Erstellen eines Sparkontos mit Kontonummer und Zinssatz.
     *
     * @param kontonummer Die Kontonummer des Sparkontos
     * @param zinssatz    Der Zinssatz als Dezimalzahl (z.B. 0.02 für 2%)
     */
    public Sparkonto (String kontonummer, double zinssatz)
    {
        super(kontonummer);
        this.zinssatz = zinssatz;
    }

    /**
     * Gibt den aktuellen Zinssatz zurück.
     *
     * @return Der Zinssatz
     */
    public double getZinssatz()
    {
        return zinssatz;
    }

    /**
     * Setzt einen neuen Zinssatz, wenn dieser größer als 0 ist.
     *
     * @param zinssatz Neuer Zinssatz als Dezimalzahl
     */
    public void setZinssatz(double zinssatz)
    {
        if(zinssatz > 0)
        {
            this.zinssatz = zinssatz;
            System.out.println("Der neue Zinssatz ist: " + zinssatz + "€");
        }
        else
        {
            System.out.println("Zinssatz muss > 0 0der = 0 sein");
        }
    }

    /**
     * Berechnet die Zinsen basierend auf dem aktuellen Kontostand und
     * dem Zinssatz, falls der Kontostand positiv ist.
     * Die berechneten Zinsen werden zum Kontostand hinzugefügt.
     */
    public void zinsenberechnen()
    {
        if(getKontostand() > 0)
        {
            double zinsen = getKontostand() * zinssatz;
            setKontostand(getKontostand() + zinsen);
            System.out.println("Zinsen in höhe von " + zinsen + "€ wurden Gutgeschreiben");
        }
        else
        {
            System.out.println("Keine Zinsen wenn der Kontostand <= 0");
        }
    }

    /**
     * Gibt die Informationen zum Sparkonto aus,
     * inklusive Kontonummer, Kontostand und Zinssatz.
     */
    @Override
    public void info()
    {
        super.info();
        System.out.println("Zinssatz: "+zinssatz+ "%");
    }
}
