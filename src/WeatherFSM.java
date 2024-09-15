import java.util.Random;

public class WeatherFSM {
    // Defining the states as an enum
    enum State {
        CLEAR, CLOUDY, RAINING, SEVERE_WEATHER
    }

    // Defining the events
    enum Event {
        GETTING_WARMER, GETTING_COLDER, HUMIDITY_INCREASING, WIND_INCREASING
    }

    // for the Current State of the FSM
    private State currentState;

    // Constructor to initialize the FSM with the initial state
    public WeatherFSM() {
        this.currentState = State.CLEAR; // Starts with Clear
    }

    // Simulating a single event with a random transition
    public void simulateEvent() {
        Random random = new Random();
        int transition = random.nextInt(3); // Random numbers: 0, 1, or 2

        switch (transition) {
            case 0:
                moveLeft();
                break;
            case 1:
                System.out.println("State remains the same: " + currentState);
                break;
            case 2:
                moveRight();
                break;
        }

        // Printing out the current state and associated event
        printEvent();
    }

    // Moving to the previous state if possible
    private void moveLeft() {
        switch (currentState) {
            case CLOUDY:
                currentState = State.CLEAR;
                break;
            case RAINING:
                currentState = State.CLOUDY;
                break;
            case SEVERE_WEATHER:
                currentState = State.RAINING;
                break;
            default:
                System.out.println("Cannot move left from " + currentState);
        }
    }

    // Moving to the next state if possible
    private void moveRight() {
        switch (currentState) {
            case CLEAR:
                currentState = State.CLOUDY;
                break;
            case CLOUDY:
                currentState = State.RAINING;
                break;
            case RAINING:
                currentState = State.SEVERE_WEATHER;
                break;
            default:
                System.out.println("Cannot move right from " + currentState);
        }
    }

    // Printing the current state and the event that caused the transition
    private void printEvent() {
        Event event = getRandomEvent();
        System.out.println("Current state: " + currentState + " | Event: " + event);
    }

    // Getting a random event
    private Event getRandomEvent() {
        Random random = new Random();
        int eventIndex = random.nextInt(Event.values().length);
        return Event.values()[eventIndex];
    }

    // Simulating the 7 days with 5 events each day
    public void simulateWeek() {
        for (int day = 1; day <= 7; day++) {
            System.out.println("\nDay " + day + ":");
            for (int event = 1; event <= 5; event++) {
                simulateEvent();
            }
        }
    }

    public static void main(String[] args) {
        WeatherFSM weatherFSM = new WeatherFSM();
        weatherFSM.simulateWeek();
    }
}
