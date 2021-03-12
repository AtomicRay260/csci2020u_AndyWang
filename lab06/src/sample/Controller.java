package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.paint.Color;

public class Controller {
    private static final double[] avgHousingPricesByYear = { 247381.0,264171.4,287715.3,294736.1, 308431.4,322635.9,340253.0,363153.7};
    private static final double[] avgCommercialPricesByYear = { 1121585.3,1219479.5,1246354.2,1295364.8, 1335932.6,1472362.0,1583521.9,1613246.3};

    private static final String[] ageGroups = { "18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
    private static final int[] purchasesByAgeGroup = { 648, 1021, 2453, 3173, 1868, 2247};
    private static final Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};

    @FXML
    BarChart barChart;
    @FXML
    CategoryAxis barXAxis;
    @FXML
    NumberAxis barYAxis;

    @FXML
    PieChart pieChart;


    public void initialize(){
        //Building bar chart form data
        barXAxis.setLabel("Year");
        barYAxis.setLabel("Prices by year");

        XYChart.Series houseData = new XYChart.Series();
        houseData.setName("House");

        XYChart.Series commercialData = new XYChart.Series();
        commercialData.setName("Commercial");

        for(int i=0; i<avgHousingPricesByYear.length; i++){
            String category = "200"+Integer.toString(i);
            houseData.getData().add(new XYChart.Data(category, avgHousingPricesByYear[i]));
            commercialData.getData().add(new XYChart.Data(category, avgCommercialPricesByYear[i]));
        }
        barChart.getData().addAll(houseData, commercialData);

        //Building pie chart from data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        PieChart.Data []pieDataNodes = new PieChart.Data[ageGroups.length];
        for (int i=0; i<ageGroups.length; i++){
            PieChart.Data singleData = new PieChart.Data(ageGroups[i], purchasesByAgeGroup[i]);
            //singleData.getNode().setStyle(String.format("-fx-pie-color: #%06x;", pieColours[i].hashCode()));
            pieDataNodes[i] = singleData;
            pieChartData.add(singleData);
        }

        pieChart.setClockwise(true);
        pieChart.setTitle("Purchases");
        pieChart.getData().addAll(pieChartData);
        int index = 0;
        for (PieChart.Data singleData : pieDataNodes){
            singleData.getNode().setStyle(String.format("-fx-pie-color: #%06x;", pieColours[index].hashCode()));
            index++;
        }
        index = 0;
    }
}
