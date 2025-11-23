package com.mycompany.m9087_samartfitness.ml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Generates a random workout dataset for WEKA (both CSV and ARFF)
 * Contains 100+ rows with balanced activities: Running, Cycling, Walking, Gym_Workout
 */

public class DatasetGenerator {

    private static final String[] ACTIVITIES = {"Running", "Cycling", "Walking", "Gym_Workout"};
    private static final String[] NOTES_RUNNING = {"Outdoor jog", "Interval training", "Park run", "Trail run"};
    private static final String[] NOTES_CYCLING = {"Road cycling", "Endurance ride", "Hill ride", "Stationary bike"};
    private static final String[] NOTES_WALKING = {"Evening walk", "Quick walk", "Dog walk", "Leisure walk"};
    private static final String[] NOTES_GYM = {"Strength training", "Weightlifting", "Circuit training", "Crossfit"};

    public static void main(String[] args) {
        generateDataset();
    }

    public static void generateDataset() {
        String csvFile = "workouts_dataset.csv";
        String arffFile = "workouts_dataset.arff";
        int rowsPerActivity = 30; // total 120 rows
        Random random = new Random();

        try (FileWriter csv = new FileWriter(csvFile);
             FileWriter arff = new FileWriter(arffFile)) {

            // CSV header
            csv.write("activity_type,duration,distance,calories,notes\n");

            // ARFF header
            arff.write("@RELATION workouts\n\n");
            arff.write("@ATTRIBUTE duration NUMERIC\n");
            arff.write("@ATTRIBUTE distance NUMERIC\n");
            arff.write("@ATTRIBUTE calories NUMERIC\n");
            arff.write("@ATTRIBUTE class {Running,Cycling,Walking,Gym_Workout}\n\n");
            arff.write("@DATA\n");

            for (String activity : ACTIVITIES) {
                for (int i = 0; i < rowsPerActivity; i++) {
                    double duration = 0;
                    double distance = 0;
                    double calories = 0;
                    String note = "";

                    switch (activity) {
                        case "Running":
                            duration = random.nextInt(60) + 20; // 20–80 min
                            distance = 2 + (10 * random.nextDouble()); // 2–12 km
                            calories = duration * 8 + distance * 10 + random.nextInt(50);
                            note = NOTES_RUNNING[random.nextInt(NOTES_RUNNING.length)];
                            break;

                        case "Cycling":
                            duration = random.nextInt(100) + 30; // 30–130 min
                            distance = 5 + (40 * random.nextDouble()); // 5–45 km
                            calories = duration * 6 + distance * 5 + random.nextInt(50);
                            note = NOTES_CYCLING[random.nextInt(NOTES_CYCLING.length)];
                            break;

                        case "Walking":
                            duration = random.nextInt(90) + 15; // 15–105 min
                            distance = 0.5 + (6 * random.nextDouble()); // 0.5–6.5 km
                            calories = duration * 3.5 + distance * 8 + random.nextInt(20);
                            note = NOTES_WALKING[random.nextInt(NOTES_WALKING.length)];
                            break;

                        case "Gym_Workout":
                            duration = random.nextInt(80) + 20; // 20–100 min
                            distance = 0; // no distance
                            calories = duration * 7 + random.nextInt(50);
                            note = NOTES_GYM[random.nextInt(NOTES_GYM.length)];
                            break;
                    }

                    csv.write(String.format("%s,%.1f,%.1f,%.1f,%s\n",
                            activity, duration, distance, calories, note));

                    arff.write(String.format("%.1f,%.1f,%.1f,%s\n",
                            duration, distance, calories, activity));
                }
            }

            csv.flush();
            arff.flush();

            System.out.println("✅ Dataset generated successfully!");
            System.out.println("CSV file: " + csvFile);
            System.out.println("ARFF file: " + arffFile);
            System.out.println("Rows: " + (ACTIVITIES.length * rowsPerActivity));

        } catch (IOException e) {
            System.out.println("❌ Error writing dataset: " + e.getMessage());
        }
    }
}
