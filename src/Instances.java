import java.util.Comparator;

public class Instances {


    private double acousticness;
    private double danceability;
    private double energy;
    private double instrumentalness;
    private double liveness;
    private double loudness;
    private double speechiness;
    private double valence;
    private String classification;
    public static long count;

    static {
        // this counter is to help us know how many instances of this class were created.
        count = 0;
    }

    public Instances() {
        Instances.count++;

    }


    public Instances(double acousticness, double danceability, double energy, double instrumentalness,
                     double liveness, double loudness, double speechiness, double valence, String classification) {

        this.acousticness = acousticness;
        this.danceability = danceability;
        this.energy = energy;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.loudness = loudness;
        this.speechiness = speechiness;
        this.valence = valence;
        this.classification = classification;
    }


    public double getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(double acousticness) {
        this.acousticness = acousticness;
    }

    public double getDanceability() {
        return danceability;
    }

    public void setDanceability(double danceability) {
        this.danceability = danceability;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(double instrumentalness) {
        this.instrumentalness = instrumentalness;
    }


    public double getLiveness() {
        return liveness;
    }

    public void setLiveness(double liveness) {
        this.liveness = liveness;
    }

    public double getLoudness() {
        return loudness;
    }

    public void setLoudness(double loudness) {
        this.loudness = loudness;
    }

    public double getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(double speechiness) {
        this.speechiness = speechiness;
    }

    public double getValence() {
        return valence;
    }

    public void setValence(double valence) {
        this.valence = valence;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instances)) return false;
        Instances instances = (Instances) o;
        return Double.compare(instances.getAcousticness(), getAcousticness()) == 0 &&
                Double.compare(instances.getDanceability(), getDanceability()) == 0 &&
                Double.compare(instances.getEnergy(), getEnergy()) == 0 &&
                Double.compare(instances.getInstrumentalness(), getInstrumentalness()) == 0 &&
                Double.compare(instances.getLiveness(), getLiveness()) == 0 &&
                Double.compare(instances.getLoudness(), getLoudness()) == 0 &&
                Double.compare(instances.getSpeechiness(), getSpeechiness()) == 0 &&
                Double.compare(instances.getValence(), getValence()) == 0 &&
                getClassification().equals(instances.getClassification());
    }

    @Override
    public String toString() {
        return "Instances{" +
                "acousticness=" + acousticness +
                ", danceability=" + danceability +
                ", energy=" + energy +
                ", instrumentalness=" + instrumentalness +
                ", liveness=" + liveness +
                ", loudness=" + loudness +
                ", speechiness=" + speechiness +
                ", valence=" + valence +
                ", classification='" + classification + '\'' +
                '}';
    }
}
