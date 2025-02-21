import java.util.List;
import java.util.Random;

public class NewsGenerator implements Runnable{

    private final List<Stock> stocks;
    private final Random random = new Random();
    private final String[]positivePhrases = {"отличные результаты","новый прорыв","успешный звпуск"};
    private final String[]negativePhrases = {"неудачные отчеты","скандал","регулярные проблемы"};
    private final String[]neutralPhrases = {"обновление руководства","анализ рынка","стратегическая встреча"};

    public NewsGenerator(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(10000);//Новости каждые 10 сек
                //Выбираем случайную акцию для новости
                Stock stock = stocks.get(random.nextInt(stocks.size()));
                double typeSelector = random.nextDouble();
                News news;
                if (typeSelector<0.4) {
                    //40% вероятность положительной новости
                    double impact = 1+random.nextDouble()*4;
                    String decription = stock.getTicket()+": "+ positivePhrases[random.nextInt(positivePhrases.length)];
                    news = new News(decription,impact);
                } else if (typeSelector<0.8) {
                    double impact = -(1+random.nextDouble()*4);
                    String description = stock.getTicket()+": "+negativePhrases[random.nextInt(negativePhrases.length)];
                    news = new News(description,impact);
                }else{
                    double impact = (random.nextDouble()*2-1);
                    String description = stock.getTicket()+": "+neutralPhrases[random.nextInt(neutralPhrases.length)];
                    news = new News(description,impact);

                    stock.applyNewsImpact(news.getImpact());
                    System.out.println("ВНИМАНИЕ НОВОСТИ "+news.getDescription()+"с воздействием "+news.getImpact());
                }
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
