package edu.iu.habahram.weathermonitoring.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class StatisticsDisplay implements Observer, DisplayElement {

    private float totalTemperature;
    private float avgTemperature;

    private float totalHumidity;
    private float avgHumidity;

    private float totalPressure;
    private float avgPressure;

    private int measurementsCount;

    private Subject weatherData;

    public StatisticsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
    }

    @Override
    public String display() {
        String html = "";
        html += String.format("<div style=\"background-image: " +
                "url(/images/sky.webp); " +
                "height: 400px; " +
                "width: 647.2px;" +
                "display:flex;flex-wrap:wrap;justify-content:center;align-content:center;" +
                "\">");
        html += "<section>";
        html += String.format("<label>Average Temperature: %s</label><br />", avgTemperature);
        html += String.format("<label>Average Humidity: %s</label><br />", avgHumidity);
        html += String.format("<label>Average Pressure: %s</label>", avgPressure);
        html += "</section>";
        html += "</div>";
        return html;
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.measurementsCount++;
        this.avgTemperature = (this.totalTemperature + temperature) / this.measurementsCount;
        this.avgHumidity = (this.totalHumidity + humidity) / this.measurementsCount;
        this.avgPressure = (this.totalPressure + pressure) / this.measurementsCount;

    }

    @Override
    public String name() {
        return "Statistics Display";
    }

    @Override
    public String id() {
        return "statistics-display";
    }

    public void subscribe() {
        weatherData.registerObserver(this);
    }

    public void unsubscribe() {
        weatherData.removeObserver(this);
    }
}
