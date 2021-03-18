package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    @FXML PieChart pieChart;

    public void initialize(){
        FileReader reader = new FileReader("resources/weatherwarnings-2015.csv");
        if (reader.hasData()) {
            HashMap<String, Integer> pieTotals = new HashMap<String, Integer>();
            ArrayList<String[]> data = reader.getData(); // gets 2d array list of string arrays of the file
            for (String[] datum : data) { // loop through array list to get the wanted data
                String key = datum[5]; // getting specific data
                if (pieTotals.containsKey(key)) {
                    int number = pieTotals.get(key);
                    number++;
                    pieTotals.replace(key, number);
                } else {
                    pieTotals.put(key, 1);
                }
            }

            // building data for pie chart
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> mapValue: pieTotals.entrySet()){
                PieChart.Data pieDataSlice = new PieChart.Data(mapValue.getKey(), mapValue.getValue());
                pieChartData.add(pieDataSlice);
            }

            // displaying pie chart data
            pieChart.getData().addAll(pieChartData);
        }else{
            System.out.println("Program stopped no data was found");
        }
    }
}
