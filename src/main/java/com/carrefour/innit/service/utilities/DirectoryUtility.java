package com.carrefour.innit.service.utilities;

import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class DirectoryUtility {

    public static File getFile(final String filename){

        File file;
        final String fileParent = System.getProperty("user.home") + File.separator + "Downloads";
        file = new File( fileParent, filename);

        return file;
    }
}