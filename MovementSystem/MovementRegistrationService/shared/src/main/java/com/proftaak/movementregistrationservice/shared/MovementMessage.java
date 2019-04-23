package com.proftaak.movementregistrationservice.shared;

public class MovementMessage {
    long trackerId;
    Coordinate coordinate;

    public MovementMessage() {
    }

    public MovementMessage(long trackerId, Coordinate coordinate) {
        this.trackerId = trackerId;
        this.coordinate = coordinate;
    }

    public long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(long trackerId) {
        this.trackerId = trackerId;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString(){
        return "TrackerId: " + trackerId + " Long: " + coordinate.longitude + " Lat: " + coordinate.latitude;
    }
}
