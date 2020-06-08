package sample;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Paint;

import java.sql.SQLException;

public class Controller {
    private Scene scene;
    Work work = new Work();
    DBStarter starter = new DBStarter();

    public Controller(Scene scene) throws SQLException {
        this.scene = scene;
        this.scene.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton() == MouseButton.PRIMARY){
                try {
                    refresh();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        });

    }

    public void setScene(Scene scene){
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void refresh() throws SQLException {


    }
}