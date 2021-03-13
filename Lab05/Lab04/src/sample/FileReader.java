package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class FileReader {




    private Map<String, Integer> hamWordCounts;
    private Map<String, Integer> spamWordCounts;
    private Map<String, Double> spamProbabilty;
    private Map<String, Double> hamProbabilty;
    private static Map<String, Double> probailityWordIsSpam;

    static double fancyN = 0.0;
    public static double numTruePositives;
    public static double numTrueNegatives;
    public static double numFalsePositives;
    public static double numberofFiles;

    public  double Accuracy;
    public  double Precision;
    public ObservableList<TestFile> fileClasses = FXCollections.observableArrayList();

    public ArrayList<String> blackList = new ArrayList<>();

    public FileReader() {

        hamWordCounts = new TreeMap<>();
        spamWordCounts = new TreeMap<>();
        spamProbabilty = new TreeMap<>();
        hamProbabilty = new TreeMap<>();
        probailityWordIsSpam = new TreeMap<>();

        blackList.add("a");
        blackList.add("by");
        blackList.add("the");
        blackList.add("I");
        blackList.add("you");
        blackList.add("and");
        blackList.add("are");
        blackList.add("or");

    }

    public void parseHam(File file) throws IOException {
       // System.out.println("Starting parsing the file:" + file.getAbsolutePath());
     //   System.out.println(file);

        if (file.isDirectory()) {
            //parse each file inside the directory
            File[] content = file.listFiles();
            for (File current : content) {
                //gets name of file being read
                parseHam(current);
                //System.out.println(current.getName());
            }
        } else {

            //creating temp list
            List<String> temp = new ArrayList<>();

            Scanner scanner = new Scanner(file);
            // scanning token by token
            while (scanner.hasNext()) {
                String token = scanner.next();
                //String fileName = file.getName();

                if (isValidWord(token) && !temp.contains(token) && !blackList.contains(token)){
                    countWordHam(token);
                    temp.add(token);
                }
            }


        }
    }

    public void parseSpam(File file) throws IOException {
      //  System.out.println("Starting parsing the file:" + file.getAbsolutePath());
    //    System.out.println(file);

        if (file.isDirectory()) {
            //parse each file inside the directory
            File[] content = file.listFiles();
            for (File current : content) {
                //gets name of file being read
                parseSpam(current);
                //System.out.println(current.getName());
            }
        } else {

            //creating temp list
            List<String> temp = new ArrayList<>();

            Scanner scanner = new Scanner(file);
            // scanning token by token
            while (scanner.hasNext()) {
                String token = scanner.next();

                if (isValidWord(token) && !temp.contains(token) && !blackList.contains(token)){
                    countWordSpam(token);
                    temp.add(token);
                }
            }
        }
    }

    private static boolean isValidWord(String word) {
        String allLetters = "^[a-zA-Z]+$";
        // returns true if the word is composed by only letters otherwise returns false;
            return word.matches(allLetters);


    }

    private void countWordHam(String word){
        if(hamWordCounts.containsKey(word)){
            int previous = hamWordCounts.get(word);
            hamWordCounts.put(word, previous+1);
        }else{
            hamWordCounts.put(word, 1);
        }
    }

    private void countWordSpam(String word){
        if(spamWordCounts.containsKey(word)){
            int previous = spamWordCounts.get(word);
            spamWordCounts.put(word, previous+1);
        }else{
            spamWordCounts.put(word, 1);
        }
    }

    public void spamProb() {
        Set<String> keys = spamWordCounts.keySet();
        Iterator<String> keyIterator = keys.iterator();

        while (keyIterator.hasNext()) {
            String key = keyIterator.next();

            Double count = 502.0;
            // testing minimum number of occurances

            int spamKey = spamWordCounts.get(key);
            double fileMath = spamKey / count;
            spamProbabilty.put(key, fileMath);



        }
    }

        public void hamProb(){
            Set<String> keys = hamWordCounts.keySet();
            Iterator<String> keyIterator = keys.iterator();

            while (keyIterator.hasNext()) {
                String key = keyIterator.next();

                Double count = 2754.0;
                // testing minimum number of occurances

                int hamKey = hamWordCounts.get(key);
                double fileMath = hamKey/count;
                hamProbabilty.put(key,fileMath);
            }


    }

    public void createWordSpam(){

        Set<String> hamkeys = hamWordCounts.keySet();
        Iterator<String> hamkeyIterator = hamkeys.iterator();

        while (hamkeyIterator.hasNext()) {
            String key = hamkeyIterator.next();

            if(spamWordCounts.containsKey(key) && hamWordCounts.containsKey(key)){
                Double spamKey = spamProbabilty.get(key);
                Double hamKey = hamProbabilty.get(key);

                double fileMath = spamKey/(spamKey +hamKey);
                probailityWordIsSpam.put(key,fileMath);
            }

        }



        Set<String> spamkeys = spamWordCounts.keySet();
        Iterator<String> spamkeyIterator = spamkeys.iterator();

        while (spamkeyIterator.hasNext()) {
            String key = spamkeyIterator.next();

            if(spamWordCounts.containsKey(key) && hamWordCounts.containsKey(key)){
                Double spamKey = spamProbabilty.get(key);
                Double hamKey = hamProbabilty.get(key);

                double fileMath = spamKey/(spamKey +hamKey);
                probailityWordIsSpam.put(key,fileMath);
            }

        }


    }

    public ObservableList<TestFile>  parseTest(File file) throws IOException {
       // System.out.println("Starting parsing the file:" + file.getAbsolutePath());


      //  System.out.println(file);


        if (file.isDirectory()) {
            //parse each file inside the directory
            File[] content = file.listFiles();
            for (File current : content) {
                //gets name of file being read
                parseTest(current);
                //System.out.println(current.getName());
            }
        } else {



            //creating temp list
            fancyN = 0.0;
            List<String> temp = new ArrayList<>();

            Scanner scanner = new Scanner(file);
            // scanning token by token
            while (scanner.hasNext()) {
                String token = scanner.next();

                if (isValidWord(token) && !temp.contains(token) && !blackList.contains(token)){
                    countTest(token);
                    temp.add(token);
                }
            }


            }

        double fileMath = 1/(1+ Math.pow(Math.E, fancyN));


        numberofFiles +=1;

        if(fileMath < 0.5 && file.getParentFile().getName().equals("ham")){

            numTrueNegatives += 1;
        }

        if(fileMath > 0.5 && file.getParentFile().getName().equals("spam")){
            numTruePositives +=1;

        }

        if(fileMath < 0.5 && file.getParentFile().getName().equals("spam")){
            numFalsePositives +=1;

        }


        if(numberofFiles != 0 && numFalsePositives != 0 && numTruePositives != 0) {
            Accuracy = (numTrueNegatives + numTruePositives) / numberofFiles;

            Precision = numTruePositives / (numFalsePositives + numTruePositives);


        }
            if(fileClasses.size() <2799) {

                fileClasses.add(new TestFile(file.getName(), fileMath, file.getParentFile().getName()));
            }
                return fileClasses;
            }




    private static void countTest(String word){

        if(probailityWordIsSpam.containsKey(word)){

             fancyN += Math.log(1-probailityWordIsSpam.get(word)) - Math.log(probailityWordIsSpam.get(word));

        }

    }


}