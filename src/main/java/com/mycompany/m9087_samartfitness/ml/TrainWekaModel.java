package com.mycompany.m9087_samartfitness.ml;

import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.SerializationHelper;
import weka.filters.Filter;
import weka.filters.supervised.instance.StratifiedRemoveFolds;

import java.util.Random;

/**
 * Trains and evaluates a RandomForest model using WEKA (CLI-based).
 */
public class TrainWekaModel {
    public static void main(String[] args) {
        try {
            String datasetPath = "workouts_dataset.arff";
            DataSource src = new DataSource(datasetPath);
            Instances data = src.getDataSet();
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);

            // Split 80/20 stratified
            StratifiedRemoveFolds filter = new StratifiedRemoveFolds();
            filter.setNumFolds(5);
            filter.setSeed(1);
            filter.setFold(1);
            filter.setInvertSelection(true);
            filter.setInputFormat(data);
            Instances train = Filter.useFilter(data, filter);

            filter = new StratifiedRemoveFolds();
            filter.setNumFolds(5);
            filter.setSeed(1);
            filter.setFold(1);
            filter.setInvertSelection(false);
            filter.setInputFormat(data);
            Instances test = Filter.useFilter(data, filter);

            System.out.println("\nTrain size: " + train.numInstances() +
                               " | Test size: " + test.numInstances());

            // ✅ FIX: For WEKA 3.8.6+, use setNumIterations instead of setNumTrees
            RandomForest rf = new RandomForest();
            rf.setNumIterations(100);  // number of trees
            rf.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(rf, test);

            System.out.println("\n=== Evaluation Summary ===");
            System.out.println(eval.toSummaryString());
            System.out.println(eval.toClassDetailsString());
            System.out.println(eval.toMatrixString());

            String modelFile = "weka_workout_model.model";
            SerializationHelper.write(modelFile, rf);
            System.out.println("\n✅ Model saved as: " + modelFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
