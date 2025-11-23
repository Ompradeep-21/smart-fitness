package com.mycompany.m9087_samartfitness.ml;

import weka.classifiers.Classifier;
import weka.core.*;
import java.util.*;

public class PredictFromModel {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== FitLife Activity Predictor ===");

        System.out.print("Enter duration (minutes): ");
        double duration = sc.nextDouble();

        System.out.print("Enter distance (km): ");
        double distance = sc.nextDouble();

        System.out.print("Enter calories burned: ");
        double calories = sc.nextDouble();

        String predicted = predict(duration, distance, calories);
        System.out.println("\nPredicted Activity Type: " + predicted);
    }

    public static String predict(double duration, double distance, double calories) {
        try {
            String modelFile = "weka_workout_model.model";
            Classifier model = (Classifier) SerializationHelper.read(modelFile);

            ArrayList<Attribute> attrs = new ArrayList<>();
            attrs.add(new Attribute("duration"));
            attrs.add(new Attribute("distance"));
            attrs.add(new Attribute("calories"));
            ArrayList<String> classes = new ArrayList<>(Arrays.asList(
                    "Running", "Cycling", "Walking", "Gym_Workout"));
            attrs.add(new Attribute("class", classes));

            Instances data = new Instances("Test", attrs, 0);
            data.setClassIndex(data.numAttributes() - 1);

            double[] vals = {duration, distance, calories, Utils.missingValue()};
            Instance inst = new DenseInstance(1.0, vals);
            inst.setDataset(data);

            double predIdx = model.classifyInstance(inst);
            return data.classAttribute().value((int) predIdx);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
}
