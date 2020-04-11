package org.openjfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class PieChartController {

    @FXML
    PieChart pieChart;

    public void addElementsToChart() {
        var eDistData = Loader.storage.getDist();
        for (int i = 0; eDistData.size() > i; i++) {
            PieChart.Data tmp = new PieChart.Data(eDistData.get(i).getName(),eDistData.get(i).getPercentage());
            pieChart.getData().add(tmp);

        }
    }
}
