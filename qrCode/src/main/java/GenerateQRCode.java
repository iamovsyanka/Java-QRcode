import service.QRCodeService;

import java.awt.image.BufferedImage;
import java.util.Scanner;

public class GenerateQRCode {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter the text: ");
        Scanner in = new Scanner(System.in);

        String text = in.nextLine();

        BufferedImage image = QRCodeService.generateQRCode(text);
        QRCodeService.displayQRCode(image);
    }
}
