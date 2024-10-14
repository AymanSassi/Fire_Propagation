package com.fire.propagation.config;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class Config {
    private int height;
    private int width;
    private int initialFireX;
    private int initialFireY;
    private double probability;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getInitialFireX() {
        return initialFireX;
    }

    public int getInitialFireY() {
        return initialFireY;
    }

    public double getProbability() {
        return probability;
    }

    // Méthode pour lire les paramètres du fichier CSV
    public void loadConfig(String csvFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] line = reader.readNext();  // Lire les en-têtes
            line = reader.readNext();  // Lire la ligne avec les valeurs

            this.height = Integer.parseInt(line[0]);
            this.width = Integer.parseInt(line[1]);
            this.initialFireX = Integer.parseInt(line[2]);
            this.initialFireY = Integer.parseInt(line[3]);
            this.probability = Double.parseDouble(line[4]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


