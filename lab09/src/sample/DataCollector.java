package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class DataCollector {
    URL netURL = null;
    ArrayList<String[]> data = new ArrayList<>();
    HashMap<String, Integer> header = new HashMap<>();
    public DataCollector() {
        this("GOOG", "1262322000", "1451538000", "1mo", "history", "true");
    }

    public DataCollector(String company, String period1, String period2, String interval, String event, String adjustedClose){
        String urlString = generateUrlWithParams(company, period1, period2, interval, event, adjustedClose);
        try {
            netURL = new URL(urlString);
            URLConnection connection = netURL.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);

            InputStream inStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

            String line=reader.readLine();
            String[] headers = line.split(",",-1);
            for (int i=0; i<headers.length; i++){
                header.put(headers[i], i);
            }

            while ((line=reader.readLine()) != null) {
                data.add(line.split(",", -1));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateUrlWithParams(String company, String period1, String period2, String interval, String event, String adjustedClose){
        String output = "https://query1.finance.yahoo.com/v7/finance/download/" + company;
        output += "?period1=" + period1;
        output += "&period2=" + period2;
        output += "&interval=" + interval;
        output += "&events=" + event;
        output += "&includeAdjustedClose=" + adjustedClose;
        return output;
    }

    //getters
    public String[] getDataWithHeader(String headerKey){
        String[] returnableData = new String[data.size()];

        int columnKey = header.get(headerKey);
        int i=0;
        for (String[] rowData : data) {
            returnableData[i] = rowData[columnKey];
            i++;
        }

        return returnableData;
    }
}
