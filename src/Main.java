import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        List<Stock> stocks = new ArrayList<>();
        System.out.println("Введите количество акций:");
        int stockCount = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < stockCount; i++) {
            System.out.println("Введите тикер акции:");
            String ticker = scanner.nextLine();
            System.out.println("Введите начальную цену акции:");
            double price = scanner.nextDouble();
            System.out.println("Введите волатильность акции:");
            double volotility = scanner.nextDouble();
            scanner.nextLine();

            stocks.add(new Stock(ticker,price,volotility));
        }
        ExecutorService executor = Executors.newFixedThreadPool(stocks.size()+1);
        for(Stock stock:stocks){
            executor.execute(new Trader(stock));
        }
        executor.execute(new NewsGenerator(stocks));
        System.out.println("Нажмите Enter для завершения симуляции...");
        scanner.nextLine();
        executor.shutdownNow();
        System.out.println("Симуляция завершена");

        System.out.println("\nРезультаты торговли:");
        double totalProfitLoss = 0;
        for (Stock stock:stocks){
            double profitLoss = stock.getProfitLoss();
            totalProfitLoss+=profitLoss;
            System.out.printf("Акция %s: начальная цена: %.2f,конечная цена: %.2f,изменение:%.2f\n",stock.getTicket(),stock.getInitialPrice(),stock.getPrice(),profitLoss);
            System.out.printf("Общий результат:%.2f\n",totalProfitLoss);
        }
    }
}

