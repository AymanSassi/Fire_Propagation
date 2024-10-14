package com.fire.propagation;

import com.fire.propagation.persistence.HibernateUtil;
import com.fire.propagation.model.PropagationStep;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TreeFirePropagation {


    private static final int HEIGHT = 5;  // Hauteur de notre grille
    private static final int WIDTH = 5;   // Largeur de notre grille
    private static char[][] forest = new char[HEIGHT][WIDTH];
    private static Random random = new Random();
    private static final double PROPAGATION_PROBABILITY = 0.5;

    public static void main(String[] args) {
        initializeForest();

        // Début de la simulation
        int step = 0;
        while (isFireBurning()) {
            propagateFire();
            saveStepToDatabase(step);
            step++;
        }
        System.out.println("End of Simulation !!!");
    }

    // la forêt aura au début quelques arbres ( cases ) en feu
    private static void initializeForest() {
        // Exemple : Case au milieu est en feu
        forest[2][2] = 'R';  // 'R' pour en feu
        // les autres cases seront considérés comme des arbres ('T')
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (forest[i][j] != 'R') {
                    forest[i][j] = 'T';
                }
            }
        }
    }

    // Propagation du feu dans les arbres
    private static void propagateFire() {
        char[][] newForest = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (forest[i][j] == 'R') {
                    newForest[i][j] = 'A';  // Le feu s'éteint, devient cendre
                    propagateToNeighbors(i, j, newForest);
                } else if (forest[i][j] == 'A') {
                    newForest[i][j] = 'A';  // Reste cendre arbre morte et feu eteint
                } else {
                    newForest[i][j] = forest[i][j];  // Sinon, reste tel quel
                }
            }
        }
        forest = newForest;
    }

    // Propagation du feu aux arbres voisins selon la probabilité
    private static void propagateToNeighbors(int i, int j, char[][] newForest) {
        // Haut
        if (i > 0 && forest[i - 1][j] == 'T' && random.nextDouble() < PROPAGATION_PROBABILITY) {
            newForest[i - 1][j] = 'R';
        }
        // Bas
        if (i < HEIGHT - 1 && forest[i + 1][j] == 'T' && random.nextDouble() < PROPAGATION_PROBABILITY) {
            newForest[i + 1][j] = 'R';
        }
        // Gauche
        if (j > 0 && forest[i][j - 1] == 'T' && random.nextDouble() < PROPAGATION_PROBABILITY) {
            newForest[i][j - 1] = 'R';
        }
        // Droite
        if (j < WIDTH - 1 && forest[i][j + 1] == 'T' && random.nextDouble() < PROPAGATION_PROBABILITY) {
            newForest[i][j + 1] = 'R';
        }
    }

    // il faut vérifier si le feu continue de brûler !!!
    private static boolean isFireBurning() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (forest[i][j] == 'R') {
                    return true;
                }
            }
        }
        return false;
    }

    // ici on va enregistrer l'étape dans la base de données
    private static void saveStepToDatabase(int step) {
        List<String> burningTreesCoords = new ArrayList<>();
        List<String> deadCoords = new ArrayList<>();

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (forest[i][j] == 'R') {
                    burningTreesCoords.add(i + "," + j);  // Coordonnées des arbres en feu
                } else if (forest[i][j] == 'A') {
                    deadCoords.add(i + "," + j);  // Coordonnées des arbres mortes et en cendres
                }
            }
        }

        // Créer un objet PropagationStep et le sauvegarder in D.B.
        PropagationStep propagationStep = new PropagationStep(step, burningTreesCoords, deadCoords);
        HibernateUtil.savePropagationStep(propagationStep);
    }
}


