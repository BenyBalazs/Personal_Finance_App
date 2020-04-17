package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

import java.util.ArrayList;

public class PieChartController {

    @FXML
    PieChart pieChart;

    public void initialize(){
        addElementsToChart();
    }

    public void addElementsToChart() {
        pieChart.getData().clear();
        ArrayList<Distribution> eDistData = Loader.storage.getDist();
        for (int i = 0; eDistData.size() > i; i++) {
            PieChart.Data tmp = new PieChart.Data(eDistData.get(i).getName(),eDistData.get(i).getPercentage());
            pieChart.getData().add(tmp);

        }
    }
}

