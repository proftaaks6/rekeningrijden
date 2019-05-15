package com.proftaak.invoicesystem.models;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class SquareRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<RegionPoint> points = new ArrayList<>();

    @Column
    private double price;

    @Transient
    private List<RegionPoint> cachedOrderedPoints;

    public int getId() {
        return id;
    }

    public List<RegionPoint> getPoints() {
        return points;
    }

    public void setPoints(List<RegionPoint> points) {
        this.points = points;
    }

    public boolean isPointInside(int x, int y){
        return isPointInside(new RegionPoint(x,y));
    }

    public void setPoints(double topLeftX, double topLeftY,  double bottomRightX, double bottomRightY){
        getPoints().clear();
        addPoint(topLeftX, topLeftY);
        addPoint(bottomRightX, topLeftY);
        addPoint(topLeftX, bottomRightY);
        addPoint(bottomRightX, bottomRightY);
    }
    private void addPoint(double x, double y){
        getPoints().add(new RegionPoint(x,y));
    }

    private List<RegionPoint> getCachedOrderedPoints(){
        if(cachedOrderedPoints != null){
            return cachedOrderedPoints;
        }
        cachedOrderedPoints = getPoints().stream().sorted((o1, o2) ->{
            if(o1.getLongitude() < o2.getLongitude()){ //o1 is left of o2
                return -1; //always greater
            } else {
                //o2 is at the same x or at the right side
                if(o1.getLatitude() > o2.getLatitude()){ //o1 is above o2
                    return -1;
                }
                else {
                    if(o1.getLongitude() == o2.getLongitude() && o1.getLatitude() == o2.getLatitude()){
                        return 0;
                    }
                    return 1;
                }
            }
        } ).collect(Collectors.toList());
        return cachedOrderedPoints;
    }

    public boolean isPointInside(RegionPoint point) {
        //please note: the array might be shuffled, reordering is mandatory
        if(getCachedOrderedPoints().size() != 4){
            return false;
        }
        return (
                getTopLeft().getLongitude() <= point.getLongitude() && getBottomRight().getLongitude() >= point.getLongitude() && getTopLeft().getLatitude() >= point.getLatitude() && getBottomRight().getLatitude() <= point.getLatitude()
        );

    }

    public RegionPoint getTopLeft(){
        if(getCachedOrderedPoints().size() != 4){
            return null;
        }
        return getCachedOrderedPoints().get(0);
    }

    public RegionPoint getBottomRight(){
        if(getCachedOrderedPoints().size() != 4){
            return null;
        }
        return getCachedOrderedPoints().get(3);
    }
}
