import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

   static void cleanMatrix(int[][] confusionMatrix) {
        for (int k = 0; k < confusionMatrix.length; k++) {
            for (int z = 0; z < confusionMatrix.length; z++) {
                confusionMatrix[k][z] = 0;
            }
        }
    }

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
        List<Instances> testSet; // Stats as a copy of the main base, but has instances moved for a the dataSet List, used for training
        Long instances = Instances.count;
        Long testAux = (instances * 2) / 3l;
//        int testBase = Integer.valueOf(testAux.toString()); // this is for separate 2/3 for data and 1/3 for tests
        int instanceCount = Integer.valueOf(instances.toString());


        //Here i created an array for tests.
//        Instances vetor[] = base.subList(testBase, Integer.valueOf(instances.toString())).toArray(new Instances[Integer.valueOf(instances.toString()) - (testBase)]);

        ArrayList<Instances> dataSet = new ArrayList<>();

        Random rand = new Random();
        int newDataIndex = rand.nextInt(base.size());

        double result1;
        double result2;
        double result3;

        int testTimes = 1;
        while (testTimes > 0) {

            for (int i = 1; i < base.size() - Integer.valueOf(testAux.toString()); i++) {

                testSet = new ArrayList<>(base);
                for (int j = 0; j < testAux; j++) {  // Fills the dataSet List with the 2/3 of data

                    while (dataSet.contains(base.get(newDataIndex))) {   //  Checks if a the object referred by the generated Index is already at the dataSet ArrayList.
                        newDataIndex = rand.nextInt(base.size()); // Chooses a random index of an object to be added at the datSet ArrayList.
                    }
                    dataSet.add(base.get(newDataIndex));    //  Adds the object to the List used for training
                    testSet.remove(base.get(newDataIndex));  // Removes every entry used for training, in the end becoming the 1/3 of the base used for tests.
                }

                Instances[] arrayTestSet = new Instances[testSet.size()];   //  Prepares a new array to be used as the test set array
                testSet.toArray(arrayTestSet);  // Converts the testSet ArrayList to the Instances array to be used at KNN.Calculator

                //creating confusion matrix
                // this loop is responsible for finding out, the number of classes/labels. (dance, rap...)
                List<String> classifications = new ArrayList<>();

                for (Instances n : arrayTestSet) {
                    if (!classifications.contains(n.getClassification())) {
                        classifications.add(n.getClassification());
                    }
                }

                //Creating confusion matrix
                int[][] confusionMatrix = new int[classifications.size()][classifications.size()];

                for (int k = 0; k < confusionMatrix.length; k++) {
                    for (int z = 0; z < confusionMatrix.length; z++) {
                        confusionMatrix[k][z] = 0;
                    }
                }


                //Here i removed the reference for the instances that were added to the tests array;
                //    base = base.subList(0, testBase);
                Instances.count = testAux;//updating the number of instances.


                // we added this int: distanceOption, 1 is for manhattan, 2 euclidian, 3 or other values: chebyshev
                result2 = KNN.knnCalculator(i, dataSet, confusionMatrix, 2, arrayTestSet);
                System.out.printf("Euclidian|\n Taxa de acerto: %.4f\nTaxa de erro: %.4f\n", result2, (100 - result2));
                cleanMatrix(confusionMatrix);

                result1 = KNN.knnCalculator(i, dataSet, confusionMatrix, 1, arrayTestSet);
                System.out.printf("Manhattan|\n Taxa de acerto: %.4f\nTaxa de erro: %.4f\n", result1, (100 - result1));
                cleanMatrix(confusionMatrix);

                result3 = KNN.knnCalculator(i, dataSet, confusionMatrix, 3, arrayTestSet);
                System.out.printf("chebyshev|\n Taxa de acerto: %.4f\nTaxa de erro: %.4f\n", result3, (100 - result3));

                testSet.clear();
                dataSet.clear();
            }

            testTimes--;
        }

    }
}
