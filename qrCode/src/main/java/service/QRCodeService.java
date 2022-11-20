package service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import net.glxn.qrgen.javase.QRCode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class QRCodeService {
    private static final int QRCODE_WIDTH = 250;
    private static final int QRCODE_HEIGHT = 250;
    private static final String CHARSET = "utf-8";

    public static BufferedImage generateQRCode(@NonNull String barcodeText) throws Exception {
        byte[] bytes = QRCode.from(barcodeText)
                .withSize(QRCODE_WIDTH, QRCODE_HEIGHT)
                .withCharset(CHARSET)
                .stream()
                .toByteArray();
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

        return ImageIO.read(bis);
    }

    public static String decodeQRCode(@NonNull BufferedImage image) throws NotFoundException {
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(luminanceSource);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap);
        return qrCodeResult.getText();
    }

    public static void displayQRCode(@NonNull BufferedImage bufferedImage) {
        ImageIcon imageIcon = new ImageIcon(bufferedImage);

        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);

        JFrame jFrame = new JFrame();
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(QRCODE_WIDTH * 2, QRCODE_HEIGHT * 2);
        jFrame.add(jLabel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
