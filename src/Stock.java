public class Stock {
    private final String ticket;
    private double price;
    private double volotility;

    public Stock(String ticket, double price, double volotility) {
        this.ticket = ticket;
        this.price = price;
        this.volotility = volotility;
    }

    public synchronized void updatePrice() {
        double change = (Math.random() * 2 - 1) * volotility;
        price = Math.max(1, price + change);
    }

    public synchronized void applyNewsImpact(double impact) {
        price = Math.max(1, price + impact);
        volotility *= 1.1;//Увеличение волатильности после новостей
    }

    public String getTicket() {
        return ticket;
    }

    public synchronized double getPrice() {
        return price;
    }
}
