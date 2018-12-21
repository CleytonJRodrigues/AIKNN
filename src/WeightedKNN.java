import java.util.*;

public class WeightedKNN {


    static double euclidianDistance(Instances instance, Instances instance2) {
        double valor[] = new double[13];
        double soma = 0;
        valor[0] = Math.pow(instance.getAcousticness() - instance2.getAcousticness(), 2);
        valor[1] = Math.pow(instance.getDanceability() - instance2.getDanceability(), 2);
        valor[2] = Math.pow(instance.getDuration_ms() - instance2.getDuration_ms(), 2);
        valor[3] = Math.pow(instance.getEnergy() - instance2.getEnergy(), 2);
        valor[4] = Math.pow(instance.getInstrumentalness() - instance2.getInstrumentalness(), 2);
        valor[5] = Math.pow(instance.getKey() - instance2.getKey(), 2);
        valor[6] = Math.pow(instance.getLiveness() - instance2.getLiveness(), 2);
        valor[7] = Math.pow(instance.getLoudness() - instance2.getLoudness(), 2);
        valor[8] = Math.pow(instance.getSpeechiness() - instance2.getSpeechiness(), 2);
        valor[9] = Math.pow(instance.getMode() - instance2.getMode(), 2);
        valor[10] = Math.pow(instance.getTempo() - instance2.getTempo(), 2);
        valor[11] = Math.pow(instance.getTime_signature() - instance2.getTime_signature(), 2);
        valor[12] = Math.pow(instance.getValence() - instance2.getValence(), 2);
        
        for(int i = 0; i < valor.length; i++) {
            soma += valor[i];
        }

        double sqrt = Math.sqrt(soma);
        return sqrt;
        

    }


//Isn't using weight yet, i'll probably add this funcionality tomorrow.
    static void WeightedKNNCalculator(int k,List<Instances> baseInstances, Instances... instance) {  // using varargs
        List<AuxClass> aux = new ArrayList<>();
        AuxClass compara = new AuxClass();
        int countPop, countRap, countDance, count;

        for(Instances x: instance) {
            countPop = 0;
            countRap = 0;
            countDance = 0;
            count = 0;
            for(Instances y: baseInstances) {
                AuxClass obj = new AuxClass(y.getClassification(), WeightedKNN.euclidianDistance(x, y));
                aux.add(obj);

            }

            Collections.sort(aux, compara);


            for (AuxClass var: aux) {

                if(count == k) {
                    break;
                }
                if(var.getClassification().equals("hip-hop/rap")) {
                    countRap++;
                }else if(var.getClassification().equals("pop")) {
                    countPop++;
                }else {
                    countDance++;
                }
                count++;
            }
            if(countRap - countPop > 0 && countRap - countDance > 0) {
                System.out.println("Sua instância de acordo com o KNN é da classe: hip-hop/rap");
            }else if(countPop - countRap > 0 && countPop - countDance > 0) {
                System.out.println("Sua instância de acordo com o KNN é da classe: pop");
            }else {
                System.out.println("Sua instância de acordo com o KNN é da classe: dance");
            }
            aux.clear();

        }



    }
}