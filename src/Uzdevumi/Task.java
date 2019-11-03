package Uzdevumi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task {
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        task1(lines);
        task1a(lines);
        task2(lines);
        task3(lines);
        task4(lines);
        task5(lines);

    }

    //Task 1
    public static void task1(List<String> lines) {
        lines.stream()
                .map(s -> s.split(" "))
                .map(s -> s.length)
                .forEach(a -> System.out.println(v));
    }

    //Task 1a collect all values and print
    public static void task1a(List<String> lines) {
        lines.stream()
                .map(s -> s.split(" "))
                .map(s -> s.length)
                .collect(Collectors.toList());                  //lai savāktu kolekciju sarakstā
        System.out.println(lines);
    }

    //Task 2 longest and shortest sentence
    public static void task2(List<String> lines) {

        lines.stream()
                .sorted((o1, o2) -> o1.length() - o2.length())
                .findFirst()
                .ifPresent(System.out::println);

        lines.stream()
                .sorted((o1, o2) -> o2.length() - o1.length())
                .findFirst()
                .ifPresent(System.out::println);
    }

    //Task 3 filter elements that has even length as numbers
    public static void task3(List<String> lines) {
        lines.stream()
                .filter(s -> s.length() % 2 == 1)
                .forEach(System.out::println);
    }

    //Task 4 flatMap and reduce to get all "x" added in 1 String
    public static void task4(List<String> lines) {
        lines.stream().flatMap(v -> {
            ArrayList<String> letters = new ArrayList<>(v.length());
            for (int i = 0; i < v.length(); i++) {
                letters.add(v.substring(i, i + 1));
            }
            return letters.stream();
        }).filter(v -> v.equals("x"))                       //atstāj tikai visus "x"
                .reduce(String::concat)                     //savieno visus "x" kopā 1 String
                .ifPresent(System.out::println);
    }
    public static void task5(List<String> lines) {

        class MySupplier implements Supplier<Long> {
            private long currentValue = 0;

            @Override
            public Long get() {
                return currentValue++;
            }
        }
 //       Stream<Long> stream = Stream.generate(new MySupplier());              //bezgalīgs stream, uzkārs kompi
 //       List<Long> longs = stream.collect(Collectors.toList());

        Stream<Long> stream1 = Stream.generate(new MySupplier());
        List<Long> longsLimited = stream1.limit(10).collect(Collectors.toList());
        System.out.println(longsLimited);

        Stream<Long> stream2 = Stream.generate(new MySupplier());
        List<Long> longsSkippedLimited = stream2.skip(5).limit(10).collect(Collectors.toList());
        System.out.println(longsSkippedLimited);
    }
}
