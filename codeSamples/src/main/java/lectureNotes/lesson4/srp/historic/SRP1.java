package lectureNotes.lesson4.srp.historic;

// Sometimes it could be very hard to fix a deficient design...
public class SRP1 {

    // A sketch implementation of famous Teletype ASR-33 (1963), one of the first teletype
    // printer that works with the new ASCII character set (published in 1963).
    // The characters sent to the teletype are used both for the text to print and
    // to manage the mechanism that provide a new line (return of the carriage and
    // line feed)
    static class TeleTypeAsr33 {
        
        // Print a string that contains only alphanumeric characters, punctuation symbols and space
        void printReadableString(String readableString) {
            // Print string on ASR 33
            // ...
        }
        
        void doCarriageReturn() {
            // Return the carriage to begin of line
        }
        
        void doLineFeed() {
            // Feed a new line
        }
    }
}
