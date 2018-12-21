import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ReadFile {


    static BufferedReader abrirArquivo(File file, FileReader fr, BufferedReader br) {

        try {
            //reading file

            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return br;
    }

    static void populateInstances(BufferedReader br, List<Instances> base) throws IOException {
        int count;
        int auxCount;
        double normalizationVar = 0;
        String aux;


            while(br.ready()) {
                String line = br.readLine();
                if(line.startsWith("@") || line.isEmpty()) {
                    continue;
                }
                Instances nova = new Instances();
                count = 0;
                auxCount = 0;
                for(int i = 0; auxCount!=14; i++) {

                    if(line.codePointAt(i) == ',' || auxCount == 13) {
                        if(auxCount == 13) {
                            aux = line.substring(count, line.length());
                            auxCount++;

                        }else {
                            aux = line.substring(count, i);
                            auxCount++;
                        }

                        switch(auxCount) {
                            case 1: nova.setAcousticness(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 2: nova.setDanceability(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 3: nova.setDuration_ms(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 4: nova.setEnergy(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 5: nova.setInstrumentalness(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 6: nova.setKey(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 7: nova.setLiveness(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 8: nova.setLoudness(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 9: nova.setMode(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 10: nova.setSpeechiness(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 11: nova.setTempo(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 12: nova.setTime_signature(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 13: nova.setValence(Double.parseDouble(aux));
                                normalizationVar += Double.parseDouble(aux);
                                break;
                            case 14: nova.setClassification(aux);
                                continue;

                        }
                        count = i+1;
                    }


                }
                // divides a characteristic by the somatory of all characteristics


                nova.setAcousticness(nova.getAcousticness()/normalizationVar);
                nova.setDanceability(nova.getDanceability()/normalizationVar);
                nova.setDuration_ms(nova.getDuration_ms()/normalizationVar);
                nova.setEnergy(nova.getEnergy()/normalizationVar);
                nova.setInstrumentalness(nova.getInstrumentalness()/normalizationVar);
                nova.setKey(nova.getKey()/normalizationVar);
                nova.setLiveness(nova.getLiveness()/normalizationVar);
                nova.setLoudness(nova.getLoudness()/normalizationVar);
                nova.setMode(nova.getMode()/normalizationVar);
                nova.setSpeechiness(nova.getSpeechiness()/normalizationVar);
                nova.setTempo(nova.getTempo()/normalizationVar);
                nova.setTime_signature(nova.getTime_signature()/normalizationVar);
                nova.setValence(nova.getValence()/normalizationVar);



                base.add(nova);

            }

    }

    static void close(BufferedReader br, FileReader fr) {
        if  (br!= null ) {
            try {
                br.close();
                fr.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

}







