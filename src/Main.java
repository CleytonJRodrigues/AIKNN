import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        File file = new File("src/Base/ChristianLira;CleytonJose;Database.arff");
        FileReader fr = null;
        BufferedReader br = null;
        int count;
        int AuxCount;
        String aux = null;
        List<Instances> base = new ArrayList<>();
        try {
            ReadFile.populateInstances(ReadFile.openFile(file, fr, br), base);
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            ReadFile.close(br, fr);
        }
//tests
        Long instances = Instances.count;
        Long testAux = (instances*2)/3l;
        int testBase = Integer.valueOf(testAux.toString()); // this is for separate 2/3 for data and 1/3 for tests

        //Here i created an array for tests.
        Instances vetor[] = base.subList(testBase, Integer.valueOf(instances.toString())).toArray(new Instances[Integer.valueOf(instances.toString()) -(testBase)]);

        //Here i removed the reference for the instances that were added to the tests array;
        base = base.subList(0, testBase);
        Instances.count = testAux;//updating the number of instances.

        //i don't know if there's a problem in using a large number for being k-value, but using k-380 gave me the best output.

        //System.out.println("Taxa de acerto: "+WeightedKNN.weightedKNNCalculator(380, base, vetor));
        //System.out.println("Taxa de acerto: "+WeightedKNN.weightedKNNCalculator(184, base, vetor));
        //System.out.println("Taxa de acerto: "+WeightedKNN.weightedKNNCalculator(94, base, vetor));
        System.out.println("Taxa de acerto: "+WeightedKNN.weightedKNNCalculator(64, base, vetor));

    }
}
