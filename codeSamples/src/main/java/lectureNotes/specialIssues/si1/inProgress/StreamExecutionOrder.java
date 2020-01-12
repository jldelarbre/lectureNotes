package lectureNotes.specialIssues.si1.inProgress;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class StreamExecutionOrder {

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java Stream: A sequence of elements supporting sequential and parallel aggregate operations".split(" "));

        final List<String> filteredAndModifiedWords = words.stream()
            .filter(w -> {
                System.out.println(w);
                return w.compareToIgnoreCase("l") < 0;
            })
            .limit(2)
            .map(w -> {
                final String upperCase = w.toUpperCase();
                System.out.println("\t" + upperCase);
                return upperCase;
            })
            .filter(w -> {
                System.out.println("\t\t" + w);
                return w.length() <= 4;
            })
            .sorted()
            .map(w -> {
                final String append = w.concat("_selected");
                System.out.println("\t\t\t" + append);
                return append;
            })
            .collect(toList());

        System.out.println("--------------------------------------------");
        filteredAndModifiedWords.forEach(System.out::println);
    }
}
