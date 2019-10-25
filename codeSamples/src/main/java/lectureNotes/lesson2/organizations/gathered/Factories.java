package lectureNotes.lesson2.organizations.gathered;

import lectureNotes.lesson2.organizations.IMyComponent;

// Shared factory file for each implementation class in the package
// 'Factories' file could change due to any other implementation class changes
public final class Factories {
    
    private Factories() {}

    public final class MyComponentFactory implements IMyComponent.IFactory {

        @Override
        public IMyComponent build() {
            return new MyComponent();
        }
    }
    
    public final class OtherFactory {

        // Build another component in package
    }
}
