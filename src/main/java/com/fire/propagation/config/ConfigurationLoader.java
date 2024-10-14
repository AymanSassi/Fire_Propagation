package com.fire.propagation.config;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationLoader {
    public static class Config {
        public int width;
        public int height;
        public double propagationProbability;
        public List<int[]> initialFirePositions;

        public Config(int width, int height, double propagationProbability, List<int[]> initialFirePositions) {
            this.width = width;
            this.height = height;
            this.propagationProbability = propagationProbability;
            this.initialFirePositions = initialFirePositions;
        }
    }

    // Lecture du fichier CSV pour charger la configuration depuis le classpath (resources)
    public static Config loadConfigFromCSV(String fileName) {
        int width = 0, height = 0;
        double probability = 0.0;
        List<int[]> firePositions = new ArrayList<>();

        // Charger le fichier config.csv depuis les resources
        try (InputStream inputStream = ConfigurationLoader.class.getClassLoader().getResourceAsStream(fileName);
             CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {

            // dans mon fichier config.csv la première ligne correspond a l'têtes que je dos ignorer si on aura un erreur de parsing
            reader.readNext();

            //ici on va lire les paramètres de config
            String[] configLine = reader.readNext(); // Lire la ligne entière
            width = Integer.parseInt(configLine[0].trim());
            height = Integer.parseInt(configLine[1].trim());
            probability = Double.parseDouble(configLine[2].trim());

            // Lire les positions des cases initialement en feu
            String[] firePositionsStr = configLine[3].split(";");
            for (String pos : firePositionsStr) {
                String[] coords = pos.split(",");
                int x = Integer.parseInt(coords[0].trim());
                int y = Integer.parseInt(coords[1].trim());
                firePositions.add(new int[]{x, y});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Config(width, height, probability, firePositions);
    }
}


