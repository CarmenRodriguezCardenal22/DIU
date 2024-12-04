package com.example.hotel.controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class DocumentacionController {
    @FXML
    private WebView webView;

    public void abrirDocumentacion() {
        webView.getEngine().load("file:/resources/documentacion/documentacion.fxml");
    }
}