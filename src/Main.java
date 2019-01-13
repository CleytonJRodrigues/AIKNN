import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

   static private void cleanMatrix(int[][] confusionMatrix) {
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

        ArrayList<Double> taxasAcertosM = new ArrayList<>();
        ArrayList<Double> taxasAcertosE = new ArrayList<>();
        ArrayList<Double> taxasAcertosC = new ArrayList<>();

        double[] kIndexesM = new double[instanceCount];
        double[] kIndexesE = new double[instanceCount];
        double[] kIndexesC = new double[instanceCount];

        ArrayList<Double[]> betterResultsM = new ArrayList<>();
        ArrayList<Double[]> betterResultsE = new ArrayList<>();
        ArrayList<Double[]> betterResultsC = new ArrayList<>();

        double[][] meansOfK = new double[27][3];

        int testTimes = 50;
        while (testTimes > 0) {

            for (int i = 1; i < 28; i++) {

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
                result1 = KNN.knnCalculator(i, dataSet, confusionMatrix, 1, arrayTestSet);
                System.out.printf("\nManhattan|\n Taxa de acerto: %.4f\nTaxa de erro: %.4f\n", result1, (100 - result1));
                cleanMatrix(confusionMatrix);

                result2 = KNN.knnCalculator(i, dataSet, confusionMatrix, 2, arrayTestSet);
                System.out.printf("\nEuclidean|\n Taxa de acerto: %.4f\nTaxa de erro: %.4f\n", result2, (100 - result2));
                cleanMatrix(confusionMatrix);

                result3 = KNN.knnCalculator(i, dataSet, confusionMatrix, 3, arrayTestSet);
                System.out.printf("\nChebyshev|\n Taxa de acerto: %.4f\nTaxa de erro: %.4f\n", result3, (100 - result3));

                testSet.clear();
                dataSet.clear();

                taxasAcertosM.add(result1);
                kIndexesM[i] = result1;

                taxasAcertosE.add(result2);
                kIndexesE[i] = result2;

                taxasAcertosC.add(result3);
                kIndexesC[i] = result3;

                meansOfK[i-1][0] += result1;
                meansOfK[i-1][1] += result2;
                meansOfK[i-1][2] += result3;
            }

            taxasAcertosM.sort((a1, a2) -> a1.compareTo(a2));
            taxasAcertosE.sort((a1, a2) -> a1.compareTo(a2));
            taxasAcertosC.sort((a1, a2) -> a1.compareTo(a2));


            Double melhorTaxaM;
            Double melhorTaxaE;
            Double melhorTaxaC;

            int numberOfKs = 4;
            while(numberOfKs > 0) {

                melhorTaxaM = taxasAcertosM.get(taxasAcertosM.size() - ((4 - numberOfKs) + 1));
                melhorTaxaE = taxasAcertosE.get(taxasAcertosE.size() - ((4 - numberOfKs) + 1));
                melhorTaxaC = taxasAcertosC.get(taxasAcertosC.size() - ((4 - numberOfKs) + 1));

                Double[] betterResultM = new Double[2];
                Double[] betterResultE = new Double[2];
                Double[] betterResultC = new Double[2];

                for (int i = 1; i < 28; i++) {
                    if (melhorTaxaM == kIndexesM[i]) {
                        betterResultM[0] = ((double) i);
                        betterResultM[1] = melhorTaxaM;
                    }
                    if (melhorTaxaE == kIndexesE[i]) {
                        betterResultE[0] = ((double) i);
                        betterResultE[1] = melhorTaxaE;
                    }
                    if (melhorTaxaC == kIndexesC[i]) {
                        betterResultC[0] = ((double) i);
                        betterResultC[1] = melhorTaxaC;
                    }
                }

                if(betterResultM[0]!=null) betterResultsM.add(betterResultM);
                if(betterResultE[0]!=null) betterResultsE.add(betterResultE);
                if(betterResultC[0]!=null) betterResultsC.add(betterResultC);

                numberOfKs--;
            }
            testTimes--;

            Comparator<Double[]> comparator = new Comparator<Double[]>() {
                @Override
                public int compare(Double[] o1, Double[] o2) {
                    if(o1[1] == null || o2[1] == null) return -1;
                    return Double.compare(o1[1], o2[1]);
                }
            };

            betterResultsM.sort(comparator.reversed());
            betterResultsE.sort(comparator.reversed());
            betterResultsC.sort(comparator.reversed());

            for(int i = 0; i < 4; i++)
                System.out.printf("\nMelhor K Manhattan: %.4f Taxa de Acerto: %f", betterResultsM.get(i)[0], betterResultsM.get(i)[1]);
            for(int i = 0; i < 4; i++)
                System.out.printf("\nMelhor K Euclidean: %.4f Taxa de Acerto: %f", betterResultsE.get(i)[0], betterResultsE.get(i)[1]);
            for(int i = 0; i < 4; i++)
                System.out.printf("\nMelhor K Chebyschev: %.4f Taxa de Acerto: %f", betterResultsC.get(i)[0], betterResultsC.get(i)[1]);
        }
        testTimes = 50;

        for(int i = 0; i < 27; i++) {
            System.out.println("\nMédia de taxas de acerto para distância Manhattan com K = " + (i+1) +": " + ((meansOfK[i][0])/ testTimes));
            System.out.println("Média de taxas de acerto para distância Euclidean com K = " + (i+1) +": " + ((meansOfK[i][1])/ testTimes));
            System.out.println("Média de taxas de acerto para distância Chebyschev com K = "+ (i+1) +": " + ((meansOfK[i][2])/ testTimes));
        }
   }
}
