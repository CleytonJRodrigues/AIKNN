import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ReadFile.close(br, fr);
        }
//tests
        Long instances = Instances.count;
        Long testAux = (instances * 2) / 3l;
        int testBase = Integer.valueOf(testAux.toString()); // this is for separate 2/3 for data and 1/3 for tests

        //Here i created an array for tests.
        Instances vetor[] = base.subList(testBase, Integer.valueOf(instances.toString())).toArray(new Instances[Integer.valueOf(instances.toString()) - (testBase)]);

        //creating confusion matrix
        // this loop is responsible for finding out, the number of classifications. (dance, rap...)
        List<String> classifications = new ArrayList<>();

        for(Instances n: vetor) {
            if(!classifications.contains(n.getClassification())) {
                classifications.add(n.getClassification());
            }
        }


        int[][] confusionMatrix = new int [classifications.size()][classifications.size()];

        for(int i = 0; i < confusionMatrix.length; i++) {
            for(int j = 0; j< confusionMatrix.length; j++) {
                confusionMatrix[i][j] = 0;
            }
        }




        //Here i removed the reference for the instances that were added to the tests array;
        base = base.subList(0, testBase);
        Instances.count = testAux;//updating the number of instances.


        double result = KNN.knnCalculator(183, base, confusionMatrix, vetor);

        System.out.printf("Taxa de acerto: %.4f\nTaxa de erro: %.4f", result, (100 - result));


    }
}
