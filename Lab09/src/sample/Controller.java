package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Controller {
    @FXML
    private Canvas mainCanvas;
    @FXML
    public GraphicsContext gc;

    private ArrayList<Float> googleStock = new ArrayList<Float>();
    private ArrayList<Float> appleStock = new ArrayList<Float>();

    @FXML
    public void initialize() throws IOException {
        gc = mainCanvas.getGraphicsContext2D();
        googleStock = downloadStockPrices("GOOG");
        appleStock = downloadStockPrices("AAPL");

        drawLinePlot(googleStock, Color.RED);
        drawLinePlot(appleStock, Color.BLUE);

    }



    public ArrayList<Float> downloadStockPrices(String company) throws IOException {
        ArrayList<Float> data = new ArrayList<Float>();
        String Website = "https://query1.finance.yahoo.com/v7/finance/download/"+ company +"?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
        URL link = new URL(Website);
        URLConnection webconnect = link.openConnection();

        InputStreamReader inStream = new InputStreamReader(webconnect.getInputStream());
        BufferedReader reader = new BufferedReader(inStream);
        String line;
        int lineCounter = 0;
        while ((line = reader.readLine()) != null) {
            String[] columns = line.split(",");
            if(lineCounter > 0) {
                data.add(Float.valueOf(columns[4]));
            }
            lineCounter++;
        }
        return data;
    }

    public void plotLine(ArrayList<Float> list) {

        float x_interval = 720.0f/(list.size());
        for(int i = 0; i <(list.size()-1);i++){
            gc.strokeLine(x_interval*i +50,800-list.get(i), x_interval*(i+1)+50, 800-list.get(i+1));
        }
    }


    public void drawLinePlot(ArrayList<Float> data, Color colour) {
        //Draw grid
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(50, 20, 50, 800);
        gc.strokeLine(50, 800, 770, 800);

        gc.setStroke(colour);
        plotLine(data);

    }
}
