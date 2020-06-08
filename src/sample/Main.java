package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends Application {
    public static final int HEIGHT = 720;
    public static final int WIDTH = 1080;

    @Override
    public void start(Stage stage) throws Exception{
        Work work = new Work();
        DBStarter starter = new DBStarter();
        Statement statement = starter.getStatement();
        stage.setTitle("Data analyze");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("запросы в секунду");
        yAxis.setLabel("время ответа на запрос в мс");
        final LineChart<Number, Number> lineChart =
                new LineChart<>(xAxis,yAxis);
        lineChart.setTitle("optimization results");
        Scene scene = new Scene(lineChart, WIDTH, HEIGHT);
        XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series4 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series5 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series6 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series7 = new XYChart.Series<>();
        XYChart.Series<Number, Number> series8 = new XYChart.Series<>();
/*
        series1 = oneThread(40, 1, statement);
        series2 = oneThread(40, 2, statement);
        series3 = oneThread(40, 3, statement);
        series4 = oneThread(40, 4, statement);
        series1.setName("One Thread without optimization");
        series2.setName("One Thread with prepare");
        series3.setName("One Thread with index");
        series4.setName("One Thread with all");
*/
        series5 = manyThreads(20,400,1);
        series6 = manyThreads(20,400,2);
        work.indexDropper(statement);
        work.indexSetter(statement);
        series7 = manyThreads(20,400,3);
        work.indexDropper(statement);
        work.indexSetter(statement);
        series8 = manyThreads(20,400,4);
        work.indexDropper(statement);
        series5.setName("Many Threads without optimization");
        series6.setName("Many Threads with prepare");
        series7.setName("Many Threads with index");
        series8.setName("Many Threads with all");
        yAxis.setLabel("количество потоков");




        lineChart.getData().addAll(series5, series6, series7, series8);

        stage.setScene(scene);
        stage.show();
    }

    public XYChart.Series<Number, Number> oneThread(int queryCounter, int switcher, Statement statement) throws SQLException {
        ArrayList<Dot> dots1;
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        Thread1 thread1 = new Thread1(statement, queryCounter, switcher);
        thread1.start();
        while (thread1.isAlive()){

        }
        dots1 = thread1.getDots();
        for (Dot dot : dots1) {
            series.getData().add(new XYChart.Data<>(dot.getX(), dot.getY()));
        }
        return series;
    }

    public XYChart.Series<Number, Number> manyThreads(int threadCounter, int queryCounter, int switcher) throws SQLException {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        ArrayList<Dot> threadsDots = new ArrayList<>();
        for(int i = 0; i < threadCounter; i++){
            System.out.println("количество потоков " + (i + 1));
            ArrayList<Thread2> threads = new ArrayList<>();
            ArrayList<ArrayList<Dot>> dotss = new ArrayList<>();
            for(int j = 0; j <= i; j++){
                Thread2 thread = new Thread2(queryCounter, switcher);
                threads.add(thread);
            }
            for(Thread2 thr : threads){
                thr.start();
            }
            boolean alive = false;
            while (threads.get(threads.size()-1).isAlive()){

            }
            for(Thread2 thr : threads){
                dotss.add(thr.getDots());
            }
            threadsDots.add(new Dot(i, dotsExtractor(dotss)));
        }

        for (Dot dot : threadsDots) {
            series.getData().add(new XYChart.Data<>(dot.getX(), dot.getY()));
        }
        return series;
    }

    public double dotsExtractor(ArrayList<ArrayList<Dot>> dotss){
        double res = 0.0;
        int size = 0;
        for (ArrayList<Dot> dots : dotss){
            for(Dot dot : dots){
                res += dot.getY();
                size++;
            }
        }
        return res/size;
    }

    public static void main(String[] args) {
        launch(args);
    }



}