package com.example.diu;

import com.example.diu.controller.HelloController;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

public class Contador extends Application {
    private IntegerProperty num = new SimpleIntegerProperty(0);
    private Label lbContador=new Label();

    /*public void botonPulsado(int n) {
        if(n==0){
            num.set(0);
        }
        else if(n==1){
            num.set(num.get()+1);
        }
        else if(n==-1){
            num.set(num.get()-1);
        }
        lbContador.setText(String.valueOf(num.getValue()));
    }
    public void set(IntegerProperty num){
        this.num=num;
    }
    public IntegerProperty get(){
        return num;
    }*/
    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene((Pane)fxmlLoader.load(), 320, 240);
            escenarioPrincipal.setTitle("Hello!");
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();

            Stage escenario2=new Stage();
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene2 = new Scene((Pane)fxmlLoader2.load(), 320, 240);
            escenario2.setTitle("Hello!");
            escenario2.setScene(scene2);
            escenario2.show();

            HelloController controlador = fxmlLoader.getController();
            HelloController controlador2 = fxmlLoader2.getController();
            controlador.numProperty().bindBidirectional(controlador2.numProperty());


            /*Stage escenarioSecundario=new Stage();
            Pane raiz = new Pane();
            raiz.getStyleClass().add("raiz");

            Button btPositivo, btNegativo, btCero;
            btPositivo = new Button();
            btNegativo = new Button();
            btCero = new Button();

            num.addListener((o,oldVal,newVal) -> lbContador.setText(newVal.toString()));

            btPositivo.setText("+");
            btPositivo.setLayoutX(150);
            btPositivo.setLayoutY(10);
            btPositivo.setId("btPositivo");
            btNegativo.setText("-");
            btNegativo.setLayoutX(250);
            btNegativo.setLayoutY(10);
            btNegativo.setId("btNegativo");
            btCero.setText("0");
            btCero.setLayoutX(350);
            btCero.setLayoutY(10);
            btCero.setId("btCero");

            lbContador = new Label();
            lbContador.setWrapText(true);
            lbContador.setText("0");
            lbContador.setLayoutX(260);
            lbContador.setLayoutY(100);
            lbContador.setId("lbContador");

            btPositivo.setFont(Font.font(30));
            btNegativo.setFont(Font.font(30));
            btCero.setFont(Font.font(30));
            lbContador.setFont(Font.font(40));

            btPositivo.setOnAction(e -> botonPulsado(1));
            btNegativo.setOnAction(e -> botonPulsado(-1));
            btCero.setOnAction(e -> botonPulsado(0));

            raiz.getChildren().addAll(btPositivo, btNegativo, btCero, lbContador);

            Scene escena = new Scene(raiz, 500, 200);
            escena.getStylesheets().add(getClass().getResource("/estilos/estilosContador.css").toExternalForm());
            escenarioPrincipal.setTitle("Contador");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();*/

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public IntegerProperty numProperty(){
        return num;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
