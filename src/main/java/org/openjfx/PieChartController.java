package org.openjfx;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import Database.Loader;
import Modells.Distribution;

import java.util.ArrayList;

public class PieChartController {

    @FXML
    PieChart pieChart;

    public void initialize(){
        addElementsToChart();
    }

    public void addElementsToChart() {
        pieChart.getData().clear();
        Loader.storage.calculateDistributionExpenses();
        ArrayList<Distribution> eDistData = Loader.storage.getDist();
        for (int i = 0; eDistData.size() > i; i++) {
            PieChart.Data tmp = new PieChart.Data(eDistData.get(i).getName(),eDistData.get(i).getPercentage());
            pieChart.getData().add(tmp);

        }
    }
}

