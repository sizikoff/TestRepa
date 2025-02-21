public class News {
    private final String description;
    private final double impact;

    public News(String description, double impact) {
        this.description = description;
        this.impact = impact;
    }

    public String getDescription() {
        return description;
    }

    public double getImpact() {
        return impact;
    }
}
