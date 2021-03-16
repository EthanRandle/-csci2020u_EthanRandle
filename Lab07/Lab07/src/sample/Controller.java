package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    public GraphicsContext gc;

    private static double[] dataArray = {
            1243.0, 112.0, 4564.0, 1245215.0, 1.0
    };

    @FXML
    public void initialize() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        FileLoader file = new FileLoader("../Lab07/weatherwarnings-2015.csv");
        Map<String, Double> disaster = file.loadFile();
        gc = mainCanvas.getGraphicsContext2D();
        drawCircle(500, 200, pieColours, disaster);
        drawLegend(300, 200, pieColours, disaster);
    }



    public void drawCircle(int w, int h,  Color[] pieColours, Map< String , Double> disaster){
        float x = 0;
        int i =0;
        double totalDisasters =0;



        for(Map.Entry<String, Double> data :disaster.entrySet()){

            totalDisasters += data.getValue();
        }

        for(Map.Entry<String, Double> data :disaster.entrySet()){
            gc.setFill(pieColours[i]);
            gc.fillArc(w,h,300,300,x,data.getValue()/totalDisasters*360, ArcType.ROUND);
            gc.strokeArc(w,h,300,300,x,data.getValue()/totalDisasters*360, ArcType.ROUND);
            x +=data.getValue()/totalDisasters*360;
            i +=1;

        }


    }

    public void drawLegend(int w , int h , Color[] pieColours, Map<String, Double> disaster){
        int i =0 ;
        int j = 100;
        for(Map.Entry<String, Double> data :disaster.entrySet()){

            gc.setFill(pieColours[i]);
            gc.fillRect(w, h*1/2+j, 50,50);

            gc.setFill(Color.BLACK);
           gc.fillText(data.getKey(), w +60, h-80+j);

           i +=1;
           j+=100;
        }
    }


    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };



    public class FileLoader {

        public Map<String, Double> disasterCounts;
        private String filename;

        public FileLoader(String filename){
            disasterCounts = new TreeMap<>();
            this.filename = filename;
        }
        public Map<String, Double> loadFile(){

            String line = "";

            try{
                BufferedReader br = new BufferedReader(new FileReader(this.filename));
                while ((line = br.readLine())!=null){
                    String[] columns = line.split(",");

                    if (disasterCounts.containsKey(columns[5])) {
                        double count = disasterCounts.remove(columns[5]);
                        count  +=1;
                        disasterCounts.put(columns[5], count);
                    }
                    else{
                        disasterCounts.put(columns[5], 1.0);
                    }



                }

            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return disasterCounts;
     }
    }
}
