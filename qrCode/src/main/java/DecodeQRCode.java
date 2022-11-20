import service.QRCodeService;

import java.awt.image.BufferedImage;

public class DecodeQRCode {
    private static final String TEXT = "Puk";

    public static void main(String[] args) throws Exception {
        BufferedImage image = QRCodeService.generateQRCode(TEXT);
        QRCodeService.displayQRCode(image);

        String decodeText = QRCodeService.decodeQRCode(image);
        System.out.println("Is equal? " + TEXT.equals(decodeText));
    }
}
