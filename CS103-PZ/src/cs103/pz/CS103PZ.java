/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs103.pz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class CS103PZ extends Application {

    @Override
    public void start(Stage primaryStage) {

        String pathWithFileName = "";
        String fileName = "";
        String pathWithoutFileName = "";

        BuffImage buffImage = new BuffImage(); //kreira objekat za skladistenje slike za cuvanje
        BuffImage temp = new BuffImage();  // za ucitavanje originalne slike
        BuffImage alternativeBuffImage = new BuffImage(); //kreira objekat za skladistenje slike za slajdere
        BuffImage alternativeBuffImageRGB = new BuffImage(); //kreira objekat za skladistenje slike za redtheimage, bluetheimage,greentheimage
        Functions functions = new Functions();
        BufferedImage img = null;
        File f = null;
        Image image1 = null;
        
        FileChooser chooser = new FileChooser();
        
//        chooser.setInitialDirectory(new File("C:\\Users\\Lenovo\\Documents"));
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new ExtensionFilter("Text Files", "*.txt"),
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));

        File selectedFile = chooser.showOpenDialog(null);
        if (selectedFile != null) {
//                mainStage.display(selectedFile);
            pathWithFileName = selectedFile.getAbsolutePath();
            pathWithoutFileName = selectedFile.getParent();
            fileName = selectedFile.getName();
            System.out.println(pathWithFileName + "\n\r" + pathWithoutFileName + "\n\r" + fileName);
            
             buffImage.setPutanja(pathWithoutFileName);
             
             
        //read image 
        try {                                                       //6a4
//            f = new File("E:\\Kopije sa C diska\\Pictures\\priroda\\010.jpg");
            f = new File(pathWithFileName);
            img = ImageIO.read(f);

        } catch (IOException e) {
            System.out.println(e);
        }

       
        temp.setBuffImage(img);

        //za cuvanje slike
        buffImage.setBuffImage(temp.getBuffImage());
        alternativeBuffImage.setBuffImage(temp.getBuffImage());
        alternativeBuffImageRGB.setBuffImage(temp.getBuffImage());
//         BufferedImage small=new BufferedImage(img.getWidth(),img.getHeight(),img.getType());
//         small.setData(img.getData());

         // menja buffered image to imageview
          image1 = SwingFXUtils.toFXImage(img, null);
//        Image image2 = SwingFXUtils.toFXImage(small, null);
//        Image image1 = functions.convertToFxImage(img);
        }

     
        Text text1 = new Text("Brightness");
        Label brightnessLabel = new Label("Select the Brightness");
        // Creates a slider for brightnes
        Slider brightnessSlider = new Slider(-100, 100, 0);
        // enable the marks 
        brightnessSlider.setShowTickMarks(true);
        // enable the Labels 
        brightnessSlider.setShowTickLabels(true);
        // set Major tick unit 
        brightnessSlider.setMajorTickUnit(50);
        // sets the value of the property blockIncrement 
        brightnessSlider.setBlockIncrement(1);
        
        
        
        Label contrastLabel = new Label("Contrast");
        // slajder za kontrast             -255  255
        Slider contrastSlider = new Slider(-100, 100, 0);
        // enable the marks 
        contrastSlider.setShowTickMarks(true);
        // enable the Labels 
        contrastSlider.setShowTickLabels(true);
        // set Major tick unit 
        contrastSlider.setMajorTickUnit(50);
        // sets the value of the property blockIncrement 
        contrastSlider.setBlockIncrement(1);
        
        
        Label gaussianLabel = new Label("Gaussian Blur");
        // Creates a slider for brightnes
        Slider gaussianSlider = new Slider(1, 10, 1);
        // enable the marks 
        gaussianSlider.setShowTickMarks(true);
        // enable the Labels 
        gaussianSlider.setShowTickLabels(true);
        // set Major tick unit 
        gaussianSlider.setMajorTickUnit(1);
        // sets the value of the property blockIncrement 
        gaussianSlider.setBlockIncrement(2);
        
        
        Label sharpeningLabel = new Label("Sharpness");
        // Creates a slider for brightnes
        Slider sharpeningSlider = new Slider(1, 10, 1);
        // enable the marks 
        sharpeningSlider.setShowTickMarks(true);
        // enable the Labels 
        sharpeningSlider.setShowTickLabels(true);
        // set Major tick unit 
        sharpeningSlider.setMajorTickUnit(1);
        // sets the value of the property blockIncrement 
        sharpeningSlider.setBlockIncrement(1);
        

        Button dots = new Button();
        dots.setText("Dots pattern");
        dots.setTextAlignment(TextAlignment.CENTER);
        dots.setPrefWidth(120);

        Button resizeSmaller = new Button();
        resizeSmaller.setText("Resize smaller");
        resizeSmaller.setTextAlignment(TextAlignment.CENTER);
        resizeSmaller.setPrefWidth(120);

        Button resizeBigger = new Button();
        resizeBigger.setText("Resize bigger");
        resizeBigger.setTextAlignment(TextAlignment.CENTER);
        resizeBigger.setPrefWidth(120);

        Button grayScale = new Button();
        grayScale.setText("Grayscale");
        grayScale.setTextAlignment(TextAlignment.CENTER);
        grayScale.setPrefWidth(120);

        Button redtheimage = new Button();
        redtheimage.setText("R");
        redtheimage.setTextAlignment(TextAlignment.CENTER);
        redtheimage.setPrefWidth(33);

        Button greentheimage = new Button();
        greentheimage.setText("G");
        greentheimage.setTextAlignment(TextAlignment.CENTER);
        greentheimage.setPrefWidth(33);

        Button bluetheimage = new Button();
        bluetheimage.setText("B");
        bluetheimage.setTextAlignment(TextAlignment.CENTER);
        bluetheimage.setPrefWidth(33);

        Button iluminationpixel = new Button();
        iluminationpixel.setText("Pixel ilumination");
        iluminationpixel.setTextAlignment(TextAlignment.CENTER);
        iluminationpixel.setPrefWidth(120);

        Button targetRangeColors = new Button();
        targetRangeColors.setText("Target range colors");
        targetRangeColors.setTextAlignment(TextAlignment.CENTER);
        targetRangeColors.setPrefWidth(120);

        Button sortiranje = new Button();
        sortiranje.setText("Sort");
        sortiranje.setTextAlignment(TextAlignment.CENTER);
        sortiranje.setPrefWidth(120);

        Button sacuvaj = new Button();
        sacuvaj.setText("Save");
        sacuvaj.setTextAlignment(TextAlignment.CENTER);
        sacuvaj.setPrefWidth(120);

        Button otvoriNovu = new Button();
        otvoriNovu.setText("Open new image");
        otvoriNovu.setTextAlignment(TextAlignment.CENTER);
        otvoriNovu.setPrefWidth(120);

        Button reverseColors = new Button();
        reverseColors.setText("Reverse colors");
        reverseColors.setTextAlignment(TextAlignment.CENTER);
        reverseColors.setPrefWidth(120);

        Button mirrorImage = new Button();
        mirrorImage.setText("Mirror image");
        mirrorImage.setTextAlignment(TextAlignment.CENTER);
        mirrorImage.setPrefWidth(120);
        
        Button edgeDetection = new Button();
        edgeDetection.setText("Edge Detection");
        edgeDetection.setTextAlignment(TextAlignment.CENTER);
        edgeDetection.setPrefWidth(120);
        
        // string array 
        String filterNames[] = {"Vertical Filter", "Horizontal Filter", "Mix Vertical and Horizontal",
                                "Sobel Vertical Filter", "Sobel Horizontal Filter", "Scharr Vertical Filter",
                                "Scharr Horizontal Filter"}; 
        // create a choiceBox 
        ChoiceBox filterChoice = new ChoiceBox(FXCollections.observableArrayList(filterNames)); 
       // filterChoice.setTextAlignment(TextAlignment.CENTER);
        filterChoice.setValue(filterNames[0]);
        filterChoice.setPrefWidth(120);
        
         // create a checkbox 
        CheckBox multipleEffects = new CheckBox("Multiple effects");
        multipleEffects.setPrefSize(120,25);
        
        // create a toggle group 
        ToggleGroup tg = new ToggleGroup();

        // create radiobuttons 
        RadioButton r1 = new RadioButton("R");
        RadioButton r2 = new RadioButton("G");
        RadioButton r3 = new RadioButton("B");

        // add radiobuttons to toggle group 
        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);
        r3.setToggleGroup(tg);

       

//kreira slike koje ce biti prikazane u prozoru pane1
        ImageView image3 = new ImageView(image1);
//        ImageView image4 = new ImageView(image2);

        BorderPane borderPane = new BorderPane();

        Pane pane1 = new Pane();
        Pane pane2 = new Pane();

        HBox hbox = new HBox();
//        hbox.setPrefHeight(100);
        hbox.setPadding(new Insets(10, 10, 10, 10));
//        hbox.setSpacing(10);

        HBox hbox2 = new HBox();
        hbox2.setPadding(new Insets(0, 0, 0, 0));
        hbox2.setSpacing(10);

        VBox vbox1 = new VBox();
//        vbox.setPrefHeight(100);
        vbox1.setPadding(new Insets(10, 10, 10, 10));
        vbox1.setSpacing(10);

        VBox vbox2 = new VBox();
//        vbox.setPrefHeight(100);
        vbox2.setPadding(new Insets(10, 10, 10, 10));
        vbox2.setSpacing(10);

        VBox vbox3 = new VBox();
//        vbox.setPrefHeight(100);
        vbox3.setPadding(new Insets(10, 10, 10, 10));
        vbox3.setSpacing(10);

        VBox vbox4 = new VBox();
//        vbox.setPrefHeight(100);
        vbox4.setPadding(new Insets(10, 10, 10, 10));
        vbox4.setSpacing(10);

        VBox vbox5 = new VBox();
//        vbox.setPrefHeight(100);
        vbox5.setPadding(new Insets(10, 10, 10, 10));
        vbox5.setSpacing(10);

        
        
        GridPane gridPane = new GridPane();
        //Setting size for the pane  
        // gridPane.setMinSize(300, 100); 
        //Setting the padding  
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        //Setting the vertical and horizontal gaps between the columns 
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.TOP_CENTER);
        //Arranging all the nodes in the grid - prvi broj za kolone, drugi za redove 
        gridPane.add(text1, 0, 0);
        gridPane.add(brightnessSlider, 1, 0);
        gridPane.add(contrastLabel, 0, 1);
        gridPane.add(contrastSlider, 1, 1);
        
        
        GridPane gridPane2 = new GridPane();
        //Setting size for the pane  
        // gridPane.setMinSize(300, 100); 
        //Setting the padding  
        gridPane2.setPadding(new Insets(10, 10, 10, 10));
        //Setting the vertical and horizontal gaps between the columns 
        gridPane2.setVgap(10);
        gridPane2.setHgap(10);
        //Setting the Grid alignment 
        gridPane2.setAlignment(Pos.TOP_CENTER);
        //Arranging all the nodes in the grid - prvi broj za kolone, drugi za redove 
        gridPane2.add(gaussianLabel, 0, 0);
        gridPane2.add(gaussianSlider, 1, 0);
        gridPane2.add(sharpeningLabel, 0, 1);
        gridPane2.add(sharpeningSlider, 1, 1);

        
        
        
        image3.fitWidthProperty().bind(pane1.widthProperty());
        image3.fitHeightProperty().bind(pane1.heightProperty());
        image3.setPreserveRatio(true);
//         image3.setStyle("-fx-alignment: CENTER;");
//         pane1.setStyle("-fx-alignment: CENTER;");
//         image3.translateXProperty()
//             .bind(pane1.widthProperty().subtract(image3.getX())
//                    .divide(4));
//                   
//    image3.translateYProperty()
//            .bind(pane1.widthProperty().subtract(image3.getY()));
//        image4.fitWidthProperty().bind(pane2.widthProperty());
//        image4.fitHeightProperty().bind(pane2.heightProperty());

        pane1.getChildren().add(image3);
//        pane2.getChildren().add(image4);

        // hbox2.getChildren().addAll(r1, r2, r3);
        hbox2.getChildren().addAll(redtheimage, greentheimage, bluetheimage);
        vbox1.getChildren().addAll(dots, resizeSmaller, resizeBigger);
        vbox2.getChildren().addAll(grayScale, filterChoice , edgeDetection);
        vbox3.getChildren().addAll(targetRangeColors, iluminationpixel, sortiranje);
        vbox4.getChildren().addAll(reverseColors, mirrorImage, hbox2);//brightnessLabel
        vbox5.getChildren().addAll(multipleEffects, otvoriNovu, sacuvaj);
        hbox.getChildren().addAll(vbox1, vbox2, vbox3, vbox4, vbox5, gridPane, gridPane2);

//          ScrollPane scrollPane2 = new ScrollPane(pane1);
//          scrollPane2.setFitToHeight(true);
        //resie big image
        StackPane secondaryLayout = new StackPane();
//         secondaryLayout.getChildren().add(slika3);

        //scroll pane za prikaz uvecane slike
        ScrollPane scrollPane = new ScrollPane(secondaryLayout);
        scrollPane.setFitToHeight(true);

        Scene secondScene = new Scene(scrollPane, 1200, 600);

        // New window (Stage)
        Label secondLabel = new Label("I'm a Label on new Window");
        Stage newWindow = new Stage();
        newWindow.setTitle("Second Stage");
        newWindow.setScene(secondScene);

        // Set position of second window, related to primary window.
        newWindow.setX(primaryStage.getX() + 100);
        newWindow.setY(primaryStage.getY() + 100);

//                newWindow.show();
        borderPane.setLeft(pane1);
        borderPane.setRight(pane2);
//        borderPane.setRight(scrollPane);
        borderPane.setBottom(hbox);

        Scene scene = new Scene(borderPane, 1200, 500);

        pane1.setPrefSize(scene.getWidth() / 2, scene.getHeight());
        pane2.setPrefSize(scene.getWidth() / 2, scene.getHeight());
       
//       pane1.setStyle("-fx-border-color: black");
//        pane1.setBorder(new Border(new BorderStroke(Color.BLACK, 
//            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        secondaryLayout.setPrefSize(scene.getWidth() / 2, scene.getHeight());

        primaryStage.setTitle("Image processing: " + pathWithFileName);
        primaryStage.setScene(scene);
        primaryStage.show();

        brightnessSlider.valueProperty().addListener(
                new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                BufferedImage image;

                if (multipleEffects.isSelected()) {
//                      image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
//                      image.setData(buffImage.getBuffImage().getData());
                      image = new BufferedImage(alternativeBuffImage.getBuffImage().getWidth(), alternativeBuffImage.getBuffImage().getHeight(), alternativeBuffImage.getBuffImage().getType());
                      image.setData(alternativeBuffImage.getBuffImage().getData());
                      
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                int val = (int) brightnessSlider.getValue();

                image.setData(functions.changeBrightness(image, val).getData());

//                l.setText("value: " + newValue);
                buffImage.setBuffImage(image);
//                alternativeBuffImageRGB.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);
            }
        });

        contrastSlider.valueProperty().addListener(
                new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
//                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
//                    image.setData(buffImage.getBuffImage().getData());
                      image = new BufferedImage(alternativeBuffImage.getBuffImage().getWidth(), alternativeBuffImage.getBuffImage().getHeight(), alternativeBuffImage.getBuffImage().getType());
                      image.setData(alternativeBuffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                double val = contrastSlider.getValue();

                image.setData(functions.changeContrast(image, val).getData());

//                l.setText("value: " + newValue);
                buffImage.setBuffImage(image);
//                alternativeBuffImageRGB.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);
            }

        });

        
          gaussianSlider.valueProperty().addListener(
                new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
//                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
//                    image.setData(buffImage.getBuffImage().getData());
                      image = new BufferedImage(alternativeBuffImage.getBuffImage().getWidth(), alternativeBuffImage.getBuffImage().getHeight(), alternativeBuffImage.getBuffImage().getType());
                      image.setData(alternativeBuffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                double val = gaussianSlider.getValue();

                image.setData(functions.changeBlur(image, val).getData());
//                BufferedImage image2 = functions.blur(image, val);
             

//                l.setText("value: " + newValue);
                buffImage.setBuffImage(image);
//                alternativeBuffImageRGB.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);
            }

        });
          
          
          
           sharpeningSlider.valueProperty().addListener(
                new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
//                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
//                    image.setData(buffImage.getBuffImage().getData());
                      image = new BufferedImage(alternativeBuffImage.getBuffImage().getWidth(), alternativeBuffImage.getBuffImage().getHeight(), alternativeBuffImage.getBuffImage().getType());
                      image.setData(alternativeBuffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                double val = sharpeningSlider.getValue();
//                if((int)val % 2 != 0){
                image.setData(functions.changeSharpens(image, val).getData());
//                }

//                l.setText("value: " + newValue);
                buffImage.setBuffImage(image);
//                alternativeBuffImageRGB.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);
            }

        });
        
        
        dots.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.dotsImage(image).getData());

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                alternativeBuffImageRGB.setBuffImage(image);

                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        resizeSmaller.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String broj = JOptionPane.showInputDialog("Unesite koliko puta želite da umanjite sliku:");
                int n = 1;
                if (broj != null && !broj.equals("0")) {
                    n = Integer.parseInt(broj);
                }

                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                int width = image.getWidth();
                int height = image.getHeight();

                BufferedImage small = new BufferedImage(width / n, height / n, image.getType());

                functions.resizeSmaller(image, small, n);

                buffImage.setBuffImage(small);
                alternativeBuffImage.setBuffImage(small);
                alternativeBuffImageRGB.setBuffImage(image);

                Image slika5 = SwingFXUtils.toFXImage(small, null);
                ImageView slika3 = new ImageView(slika5);
//                slika3.fitWidthProperty().bind(pane2.widthProperty());
//                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                slika3.setSmooth(true);
                pane2.getChildren().clear();

                pane2.getChildren().add(slika3);

                secondaryLayout.getChildren().clear();
                secondaryLayout.getChildren().add(slika3);

                newWindow.show();
            }
        });

        resizeBigger.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String broj = JOptionPane.showInputDialog("Unesite koliko puta želite da uvećate sliku:");
                int n = 1;
                if (broj != null && !broj.equals("0")) {
                    n = Integer.parseInt(broj);
                }

                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                int width = image.getWidth();
                int height = image.getHeight();

                BufferedImage big = new BufferedImage(width * n, height * n, image.getType());

                functions.resizeBigger(image, big, n);

                buffImage.setBuffImage(big);
                alternativeBuffImage.setBuffImage(big);
                alternativeBuffImageRGB.setBuffImage(image);

                Image slika5 = SwingFXUtils.toFXImage(big, null);
                ImageView slika3 = new ImageView(slika5);
//                slika3.fitWidthProperty().bind(pane2.widthProperty());
//                slika3.fitHeightProperty().bind(pane2.heightProperty());
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

//                 slika3.fitWidthProperty().bind(pane2.widthProperty());
//                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);

                secondaryLayout.getChildren().clear();
                secondaryLayout.getChildren().add(slika3);

                newWindow.show();

            }
        });

        grayScale.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

//                BufferedImage graysc = new BufferedImage(temp.getWidth(), temp.getHeight(), temp.getType());
//                graysc.setData(temp.getData());
//                BufferedImage graysc = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
//                graysc.setData(temp.getBuffImage().getData());
                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                functions.grayScaling(image);

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                alternativeBuffImageRGB.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        redtheimage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
//                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
//                    image.setData(buffImage.getBuffImage().getData());
//                    image = new BufferedImage(alternativeBuffImage.getBuffImage().getWidth(), alternativeBuffImage.getBuffImage().getHeight(), alternativeBuffImage.getBuffImage().getType());
//                    image.setData(alternativeBuffImage.getBuffImage().getData());
                    image = new BufferedImage(alternativeBuffImageRGB.getBuffImage().getWidth(), alternativeBuffImageRGB.getBuffImage().getHeight(), alternativeBuffImageRGB.getBuffImage().getType());
                    image.setData(alternativeBuffImageRGB.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.convertToRed(image).getData());

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        greentheimage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
//                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
//                   image.setData(buffImage.getBuffImage().getData());
//                    image = new BufferedImage(alternativeBuffImage.getBuffImage().getWidth(), alternativeBuffImage.getBuffImage().getHeight(), alternativeBuffImage.getBuffImage().getType());
//                    image.setData(alternativeBuffImage.getBuffImage().getData());
                     image = new BufferedImage(alternativeBuffImageRGB.getBuffImage().getWidth(), alternativeBuffImageRGB.getBuffImage().getHeight(), alternativeBuffImageRGB.getBuffImage().getType());
                    image.setData(alternativeBuffImageRGB.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.convertToGreen(image).getData());

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        bluetheimage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
//                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
//                    image.setData(buffImage.getBuffImage().getData());
//                      image = new BufferedImage(alternativeBuffImage.getBuffImage().getWidth(), alternativeBuffImage.getBuffImage().getHeight(), alternativeBuffImage.getBuffImage().getType());
//                      image.setData(alternativeBuffImage.getBuffImage().getData());
                    image = new BufferedImage(alternativeBuffImageRGB.getBuffImage().getWidth(), alternativeBuffImageRGB.getBuffImage().getHeight(), alternativeBuffImageRGB.getBuffImage().getType());
                    image.setData(alternativeBuffImageRGB.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.convertToBlue(image).getData());

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        iluminationpixel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.osvetljenostPixela(image).getData());

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                alternativeBuffImageRGB.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        targetRangeColors.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.targetRangePixels(image).getData());

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                alternativeBuffImageRGB.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        sortiranje.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.sortImagePixels(image).getData());

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                alternativeBuffImageRGB.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        reverseColors.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                //ovo dodaje sliku na sliku
//                  BuffImage reddots = new BuffImage();
//                  reddots.setBuffImage(temp.getBuffImage());
//                   reddots2.setBuffImage(buffImage.getBuffImage());
                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.reversingColors(image).getData());

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                alternativeBuffImageRGB.setBuffImage(image);

                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        mirrorImage.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
//                  BuffImage reddots = new BuffImage();
//                  reddots.setBuffImage(temp.getBuffImage());
//                   reddots2.setBuffImage(buffImage.getBuffImage());

                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.rotateY(image).getData());

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                alternativeBuffImageRGB.setBuffImage(image);

                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });
        
        
        edgeDetection.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
//                  BuffImage reddots = new BuffImage();
//                  reddots.setBuffImage(temp.getBuffImage());
//                   reddots2.setBuffImage(buffImage.getBuffImage());

                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

//                image.setData(functions.edgeDetectX(image).getData());
//                BufferedImage image2 = null;
                try {
//                    String st[] = { "Horizontal Filter", "Vertical Filter", "Sobel Vertical Filter", "Sobel Horizontal Filter", "Scharr Vertical Filter", "Scharr Horizontal Filter" }; 
                    image = functions.detectEdges(image, filterChoice.getValue().toString());
                } catch (IOException ex) {
                    Logger.getLogger(CS103PZ.class.getName()).log(Level.SEVERE, null, ex);
                }
                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
                alternativeBuffImageRGB.setBuffImage(image);

                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });
        
        

        // add a change listener 
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                    Toggle o, Toggle n) {

                RadioButton rb = (RadioButton) tg.getSelectedToggle();

//                if (rb != null) { 
                String s = rb.getText();
//                } 

                BufferedImage image;
                if (multipleEffects.isSelected()) {
                    image = new BufferedImage(buffImage.getBuffImage().getWidth(), buffImage.getBuffImage().getHeight(), buffImage.getBuffImage().getType());
                    image.setData(buffImage.getBuffImage().getData());
                } else {
                    image = new BufferedImage(temp.getBuffImage().getWidth(), temp.getBuffImage().getHeight(), temp.getBuffImage().getType());
                    image.setData(temp.getBuffImage().getData());
                }

                image.setData(functions.convertToRedGreenBlue(image, s).getData());
//                r1.selectedProperty().set(false);

                buffImage.setBuffImage(image);
                alternativeBuffImage.setBuffImage(image);
//                alternativeBuffImageRGB.setBuffImage(image);
                
                Image slika2 = SwingFXUtils.toFXImage(image, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane2.widthProperty());
                slika3.fitHeightProperty().bind(pane2.heightProperty());
                slika3.setPreserveRatio(true);
                pane2.getChildren().clear();
                pane2.getChildren().add(slika3);

            }
        });

        sacuvaj.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                FileChooser chooser2 = new FileChooser();
//                chooser2.initialDirectoryProperty().setValue(new File(buffImage.getPutanja()));
                if(buffImage.getPutanja()!= null){
                    chooser2.setInitialDirectory(new File(buffImage.getPutanja()));
                }
                chooser2.getExtensionFilters().addAll(
                        new ExtensionFilter("All Files", "*.*"),
                        new ExtensionFilter("Text Files", "*.txt"),
                        new ExtensionFilter("Image Files", "*.jpg", "*.jpeg"),
                        new ExtensionFilter("Image Files", "*.png"),
                        new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));

                //Set extension filter for text files
//            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
//            chooser2.getExtensionFilters().add(extFilter);
                File selectedFile2 = chooser2.showSaveDialog(null);
                if (selectedFile2 != null) {
////                mainStage.display(selectedFile);
//                    pathWithFileName = selectedFile2.getAbsolutePath();
//                    pathWithoutFileName = selectedFile2.getParent();
//                    fileName = selectedFile2.getName();
//                    System.out.println(pathWithFileName + "\n\r" +  pathWithoutFileName + "\n\r" + fileName );
//                    
                    try {
//                        File f2 = new File(buffImage.getPutanja() + "\\" + naziv);
//                         ImageIO.write(buffImage.getBuffImage(), "jpg", f2);
                        if(buffImage.getBuffImage() != null){
                            ImageIO.write(buffImage.getBuffImage(), "jpg", selectedFile2);
                        }else{
                               JOptionPane.showMessageDialog(null, "Morate prvo otvoriti sliku da biste mogli da je sačuvate.");
                            }

                    } catch (IOException e) {
                        System.out.println(e);
                    }

                }

//                 String naziv = JOptionPane.showInputDialog("Unesite naziv fajla sa ekstenzijom .jpg ili .png:");
                // save image 
//                if (naziv != null && !naziv.equals("")) {
//                    try {
//
//                        File f2 = new File(buffImage.getPutanja() + "\\" + naziv);
//                        ImageIO.write(buffImage.getBuffImage(), "jpg", f2);
////                    ImageIO.write(svimg, "jpg", f2);
//
//                    } catch (IOException e) {
//                        System.out.println(e);
//                    }
//                    JOptionPane.showMessageDialog(null, "Slika uspešno sačuvana");
//                }
            }
        });

        otvoriNovu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

//                BufferedImage svimg = new BufferedImage(temp.getWidth(), temp.getHeight(), temp.getType());
//                svimg.setData(temp.getData());
                String pathWithFileName = "";
                String fileName = "";
                String pathWithoutFileName = "";

                FileChooser chooser = new FileChooser();
                if(buffImage.getPutanja()!= null){
                    chooser.setInitialDirectory(new File(buffImage.getPutanja()));
                }
//                chooser.setInitialDirectory(new File("C:\\Users\\Lenovo\\Documents"));
//                chooser.setInitialDirectory(new File(buffImage.getPutanja()));
                chooser.getExtensionFilters().addAll(
                        new ExtensionFilter("All Files", "*.*"),
                        new ExtensionFilter("Text Files", "*.txt"),
                        new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                        new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));

                File selectedFile = chooser.showOpenDialog(null);
              
                if (selectedFile != null) {
//                mainStage.display(selectedFile);
                    pathWithFileName = selectedFile.getAbsolutePath();
                    pathWithoutFileName = selectedFile.getParent();
                    fileName = selectedFile.getName();
                    System.out.println(pathWithFileName + "\n\r" + pathWithoutFileName + "\n\r" + fileName);

                     buffImage.setPutanja(pathWithoutFileName);

               
                     
                       BufferedImage img2 = null;
                File f2 = null;
                //read image 
                try {                                                       

                    f2 = new File(pathWithFileName);
                    img2 = ImageIO.read(f2);

                } catch (IOException e) {
                    System.out.println(e);
                }

                BufferedImage temp2 = new BufferedImage(img2.getWidth(), img2.getHeight(), img2.getType());
                temp2.setData(img2.getData());
//                 temp.setData(temp2.getData());

                temp.setBuffImage(img2);
                // slika za cuvanje
                buffImage.setBuffImage(temp2);
                alternativeBuffImage.setBuffImage(temp2);
                alternativeBuffImageRGB.setBuffImage(temp2);
                
//                buffImage.setBuffImage(temp.getBuffImage());
//                alternativeBuffImage.setBuffImage(temp.getBuffImage());

                primaryStage.setTitle("Image processing: " + pathWithFileName);
                Image slika2 = SwingFXUtils.toFXImage(img2, null);
                ImageView slika3 = new ImageView(slika2);
                slika3.fitWidthProperty().bind(pane1.widthProperty());
                slika3.fitHeightProperty().bind(pane1.heightProperty());
                slika3.setPreserveRatio(true);
                pane1.getChildren().clear();
                pane1.getChildren().add(slika3);
                pane2.getChildren().clear();
                }
              
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
