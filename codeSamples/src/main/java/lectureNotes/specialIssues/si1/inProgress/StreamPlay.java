package lectureNotes.specialIssues.si1.inProgress;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class StreamPlay {

    public static void main(String[] args) {

        IntStream.range(0, 1_000_000)
            .skip(156)
            .filter(n -> (n % 2 != 0))
            .filter(n -> (n % 3 != 0))
            .filter(n -> (n % 5 != 0))
            .limit(40)
            .map(n -> n / (n % 5))
            .sorted()
            .distinct()
            .forEach(n -> System.out.print(n + ", "));
        System.out.println();

        final List<Integer> collect = IntStream.range(0, 1_000_000)
            .skip(156)
            .filter(n -> (n % 2 != 0))
            .filter(n -> (n % 3 != 0))
            .filter(n -> (n % 5 != 0))
            .limit(40)
            .map(n -> n / (n % 5))
            .sorted()
            .distinct()
            .boxed()
            .collect(toList());

        System.out.println(collect);

        int count = (int) IntStream.range(0, 1_000_000)
            .skip(156)
            .filter(n -> (n % 2 != 0))
            .filter(n -> (n % 3 != 0))
            .filter(n -> (n % 5 != 0))
            .limit(40)
            .map(n -> n / (n % 5))
            .sorted()
            .distinct()
            .count();

        System.out.println(count);

        final OptionalInt findFirst = IntStream.range(0, 1_000_000)
            .skip(156)
            .filter(n -> (n % 2 != 0))
            .filter(n -> (n % 3 != 0))
            .filter(n -> (n % 5 != 0))
            .limit(40)
            .map(n -> n / (n % 5))
            .sorted()
            .distinct()
            .findFirst();

        System.out.println(findFirst);

        final OptionalInt reduce = IntStream.range(0, 1_000_000)
            .skip(156)
            .filter(n -> (n % 2 != 0))
            .filter(n -> (n % 3 != 0))
            .filter(n -> (n % 5 != 0))
            .limit(40)
            .map(n -> n / (n % 5))
            .sorted()
            .distinct()
            .reduce((accumulator, item) -> accumulator + item);

        System.out.println(reduce);

        final int sum = IntStream.range(0, 1_000_000)
            .skip(156)
            .filter(n -> (n % 2 != 0))
            .filter(n -> (n % 3 != 0))
            .filter(n -> (n % 5 != 0))
            .limit(40)
            .map(n -> n / (n % 5))
            .sorted()
            .distinct()
            .sum();

        System.out.println(sum);
    }
}
