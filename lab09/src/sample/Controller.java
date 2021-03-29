package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Controller {
    @FXML Canvas chartCanvas;

    GraphicsContext gc;
    final double inset = 50;
    public void initialize(){
        gc = chartCanvas.getGraphicsContext2D();
        DataCollector collectorDataGOOG = new DataCollector("GOOG", "1262322000", "1451538000", "1mo", "history", "true");
        DataCollector collectorDataAPPL = new DataCollector("AAPL", "1262322000", "1451538000", "1mo", "history", "true");

        double[] googNumberData = parseToDouble(collectorDataGOOG.getDataWithHeader("Close"));
        double[] aaplNumberData = parseToDouble(collectorDataAPPL.getDataWithHeader("Close"));

        drawLinePlot(googNumberData, aaplNumberData);
    }

    private double[] parseToDouble(String[] data){
        double[] newData = new double[data.length];
        try{
            for (int i=0; i<data.length; i++){
                newData[i] = Double.parseDouble(data[i]);
            }
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return newData;
    }

    private void drawLinePlot(double[] data1, double[] data2){
        gc.setStroke(Color.GREY);
        gc.setLineWidth(1);

        double maxWidth = chartCanvas.getWidth();
        double maxHeight = chartCanvas.getHeight();

        //drawing side lines
        gc.strokeLine(inset, inset, inset, maxHeight-inset);
        gc.strokeLine(inset, maxHeight-inset, maxWidth-inset, maxHeight-inset);

        //drawing data
        double highestValue = 0;
        double lowestValue = 0;
        for(double value: data1){
            if (highestValue < value){
                highestValue = value;
            }else if(lowestValue > value){
                lowestValue = value;
            }
        }
        for(double value: data2){
            if (highestValue < value){
                highestValue = value;
            }else if(lowestValue > value){
                lowestValue = value;
            }
        }
        plotLine(data1, Color.RED, highestValue, lowestValue);
        plotLine(data2, Color.BLUE, highestValue, lowestValue);
    }

    private void plotLine(double[] data, Color lineColour, double max, double min){
        gc.setStroke(lineColour);
        double charWidth = chartCanvas.getWidth()-inset*2;
        double charHeight = chartCanvas.getHeight()-inset*2;
        double xPadding = charWidth/(data.length-1);

        for (int i=0; i<data.length-1; i++){
            gc.strokeLine(inset+(i*xPadding), charHeight*(1-data[i]/max)+inset, inset+((i+1)*xPadding),charHeight*(1-data[i+1]/max)+inset);
        }
    }
}
