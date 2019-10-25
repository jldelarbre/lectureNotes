package lectureNotes.lesson2.organizations.nested;

import lectureNotes.lesson2.organizations.IMyComponent;

// The class has public visibility: allow 'cast' or 'instanceof' outside the package
// Implementation and factory classes have their changes always synchronized in the same file
// Factory nested in implementation class: compact
public final class MyComponent implements IMyComponent {
    
    @Override
    public void doSomething() {
    }

    public static final class MyComponentFactory implements IMyComponent.IFactory {

        @Override
        public IMyComponent build() {
            return new MyComponent();
        }
    }
}
