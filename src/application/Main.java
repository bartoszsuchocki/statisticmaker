package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import statistic.StatisticCalculator;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("MainLayout.fxml"));
			Scene scene = new Scene(root, 600, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// launch(args);
		StatisticCalculator statisticCalculator = new StatisticCalculator(
				"C:\\Users\\sucha_rakzuks\\workspace\\book-fair", "java");
		try {
			statisticCalculator.calculateAll();
			System.out.println("Total files: " + statisticCalculator.getFilesCount());
			System.out.println("Total lines: " + statisticCalculator.getLinesCount());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("lalla");
	}
}
