package io.github.khanhdpdx01.backend.util;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class QRCodeGenerator {
    private static int width = 250;
    private static int height = 250;

    public static void generate(String name, String data) throws IOException {
        FileOutputStream fos = new FileOutputStream(name);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        QRCode
                .from(data)
                .withSize(width, height)
                .to(ImageType.PNG)
                .writeTo(stream);

        stream.writeTo(fos);

        if(fos != null) {
            fos.close();
        }
    }

    public static void setWidth(int newWidth) {
        width = newWidth;
    }

    public static void setHeight(int newHeight) {
        height = newHeight;
    }
}