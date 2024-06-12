import java.util.ArrayList;

class Subject {
    private ArrayList<Observer> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Bericht bericht) {
        for (Observer observer : observers) {
            observer.update(bericht);
        }
    }
}
