import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
            ReadFile.populateInstances(ReadFile.abrirArquivo(file, fr, br), base);
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            ReadFile.close(br, fr);
        }
//tests

        Instances vetor[] = base.subList(100, 500).toArray(new Instances[500-100]);
        WeightedKNN.WeightedKNNCalculator(3, base, vetor);

    }
}
