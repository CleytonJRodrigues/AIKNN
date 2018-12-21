import java.util.Comparator;

public class AuxClass implements Comparator<AuxClass> {

    private String classification;
    private double distance;



    public int compare(AuxClass t1, AuxClass t2) {
        return Double.compare(t1.getDistance(), t2.getDistance());

    }
    public AuxClass () {

    }
    public AuxClass(String classification, double distance) {
        this.classification = classification;
        this.distance = distance;

    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
