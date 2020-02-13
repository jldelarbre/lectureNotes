package lectureNotes.lesson4.lsp;

public class LSP4 {

    public static void main(String[] args) {
        Double[] doubles = new Double[2];
        // Java arrays are covariant. Thus Double[] inherits from Number[]
        Number[] numbers = doubles;
        
        // ArrayStoreException thrown
        // Double[] does not fulfill the API of Number[] since it could not accept integers
        // Java arrays are deficient
        numbers[0] = Integer.valueOf(3);
        numbers[1] = Double.valueOf(3.2);
        
        for (Double d : doubles) {
            System.out.println(d);
        }
    }
}
