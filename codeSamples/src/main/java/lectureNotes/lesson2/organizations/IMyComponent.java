package lectureNotes.lesson2.organizations;

public interface IMyComponent {
    
    void doSomething();

    interface IFactory {
        IMyComponent build();
    }
}
