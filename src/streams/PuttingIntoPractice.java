package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        
        System.out.println("1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).");
        List<Transaction> task1 = transactions.stream()
        		.filter(transaction -> transaction.getYear() == 2011)
        		.sorted(Comparator.comparingInt(Transaction::getValue))
        		.toList();
        task1.forEach(System.out::println);
        System.out.println();

        System.out.println("2. Вывести список неповторяющихся городов, в которых работают трейдеры.");
        List<String> task2 = transactions.stream()
        		.map(Transaction::getTrader)
        		.map(Trader::getCity)
        		.distinct()
        		.toList();
        task2.forEach(System.out::println);
        System.out.println();

        System.out.println("3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.");
        List<Trader> task3 = transactions.stream()
        		.map(Transaction::getTrader)
        		.filter(trader -> "Cambridge".equals(trader.getCity()))
        		.distinct()
        		.sorted(Comparator.comparing(Trader::getName))
        		.toList();
        task3.forEach(System.out::println);
        System.out.println();

        System.out.println("4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке");
        Optional<String> task4 = transactions.stream()
        		.map(Transaction::getTrader)
        		.map(Trader::getName)
        		.distinct()
        		.sorted()
        		.reduce((s1, s2) -> s1 + ", " + s2);
        task4.ifPresent(System.out::println);
        System.out.println();

        System.out.println("5. Выяснить, существует ли хоть один трейдер из Милана.");
        boolean task5 = transactions.stream()
        		.map(Transaction::getTrader)
        		.map(Trader::getCity)
        		.anyMatch("Milan"::equals);
        System.out.println(task5);
        System.out.println();

        System.out.println("6. Вывести суммы всех транзакций трейдеров из Кембриджа.");
        int[] task6 = transactions.stream()
        		.filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
        		.mapToInt(Transaction::getValue)
        		.toArray();
        for (int value : task6) {
            System.out.println(value);
        }
        System.out.println();

        System.out.println("7. Какова максимальная сумма среди всех транзакций?");
        OptionalInt task7 = transactions.stream().mapToInt(Transaction::getValue)
        		.max();
        task7.ifPresent(System.out::println);
        System.out.println();

		System.out.println("8. Найти транзакцию с минимальной суммой.");
        Optional<Transaction> task8 = transactions.stream()
        		.min(Comparator.comparingInt(Transaction::getValue));
        task8.ifPresent(System.out::println);
        System.out.println();
    }
}