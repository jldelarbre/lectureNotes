package lectureNotes.lesson2.organizations.dedicated;

import lectureNotes.lesson2.organizations.IMyComponent;

// Dedicated factory file for each implementation class in the package: cumbersome
// Implementation and factory classes have their changes always synchronized with the 2 same files
public final class MyComponentFactory implements IMyComponent.IFactory {

    @Override
    public IMyComponent build() {
        return new MyComponent();
    }
}
