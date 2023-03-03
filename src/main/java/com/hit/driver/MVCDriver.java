package com.hit.driver;

import com.hit.view.View;

public class MVCDriver {
    public static void main(String[] args) {
        System.out.println("Starting client...");
        View view = new View();
        view.launchView();
    }
}