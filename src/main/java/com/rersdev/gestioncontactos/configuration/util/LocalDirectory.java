package com.rersdev.gestioncontactos.configuration.util;


public final class LocalDirectory {

    private static final String IMAGE_DIRECTORY_PATH = System.getProperty("user.home") + "/Downloads/images/";

    private LocalDirectory() {}

    public static String getImageDirectory() {
        return IMAGE_DIRECTORY_PATH;
    }
}
