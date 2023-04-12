package model;

import java.util.Calendar;
import java.util.Date;


/**
 * Represents an alarm system event.
 * <p>
 * Code from Alarm system example.
 */
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    /**
     * EFFECTS: Creates an event with the given description
     * and the current date/time stamp.
     *
     * @param description a description of the event
     */
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    /**
     *  EFFECTS: Gets the date of this event (includes time).
     *
     * @return the date of the event
     */
    public Date getDate() {
        return dateLogged;
    }

    /**
     * EFFECTS: Gets the description of this event.
     *
     * @return the description of the event
     */
    public String getDescription() {
        return description;
    }

    // EFFECTS: Overrides the equals method
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }


        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    // EFFECTS: Overrides HashCode
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    // EFFECTS: Overrides toString
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
