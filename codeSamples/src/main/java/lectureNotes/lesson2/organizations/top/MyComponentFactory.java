package lectureNotes.lesson2.organizations.top;

import lectureNotes.lesson2.organizations.IMyComponent;

// Factory (public in package) is presented on "top", the implementation is nested private:
// Seems unnatural to have only factory as public full fledged class
// Implementation and factory classes have their changes always synchronized with the 2 same files
public final class MyComponentFactory implements IMyComponent.IFactory {

    @Override
    public IMyComponent build() {
        return new MyComponent();
    }
    
    final private class MyComponent implements IMyComponent {

        @Override
        public void doSomething() {
        }
    }
}
