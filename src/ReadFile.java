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
        double maximumDistance = 0;
        double minimumDistance = 0;
        double auxDouble = 0;
        String aux;


            while(br.ready()) {
                String line = br.readLine();
                if(line.startsWith("@") || line.isEmpty()) {
                    continue;
                }
                Instances n = new Instances();
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

                        if(auxCount != 14) {
                            auxDouble = Double.parseDouble(aux);
                        }

                        switch(auxCount) {

                            case 1: n.setAcousticness(auxDouble);
                                maximumDistance = auxDouble;
                                minimumDistance = auxDouble;
                                break;
                            case 2: n.setDanceability(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 3: n.setDuration_ms(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 4: n.setEnergy(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 5: n.setInstrumentalness(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 6: n.setKey(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 7: n.setLiveness(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 8: n.setLoudness(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 9: n.setMode(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 10: n.setSpeechiness(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = Double.parseDouble(aux);
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 11: n.setTempo(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 12: n.setTime_signature(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                }
                                break;
                            case 13: n.setValence(auxDouble);
                                if(auxDouble > maximumDistance) {
                                    maximumDistance = auxDouble;
                                }else if(auxDouble < minimumDistance) {
                                    minimumDistance = auxDouble;
                                };
                                break;
                            case 14: n.setClassification(aux);
                                continue;

                        }
                        count = i+1;
                    }


                }
                // normalization;


                n.setAcousticness((n.getAcousticness() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setDanceability((n.getDanceability() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setDuration_ms((n.getDuration_ms() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setEnergy((n.getEnergy()/ - minimumDistance)/(maximumDistance - minimumDistance));
                n.setInstrumentalness((n.getInstrumentalness() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setKey((n.getKey() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setLiveness((n.getLiveness() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setLoudness((n.getLoudness() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setMode((n.getMode() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setSpeechiness((n.getTempo() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setTime_signature((n.getTime_signature() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setValence((n.getValence() - minimumDistance)/(maximumDistance - minimumDistance));
                n.setTempo((n.getTempo() - minimumDistance)/(maximumDistance - minimumDistance));


                base.add(n);

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







