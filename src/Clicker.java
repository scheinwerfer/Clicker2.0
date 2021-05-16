import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Clicker extends JPanel implements ActionListener {

    private boolean check1;
    private boolean check2;

    private final String writeMessage = "Написать сообщение";

    private final JLabel lblName = new JLabel("Имя и фамилия");
    private final JLabel lblText = new JLabel("Текст");

    private final JTextField txtArea1 = new JTextField(15);
    private final JTextField txtArea2 = new JTextField(15);

    private final JButton btn = new JButton("Запуск");

    public Clicker() {
        add(lblName);
        add(txtArea1);

        add(lblText);
        add(txtArea2);


        add(btn);
        btn.addActionListener(this);
    }

    public void initClicker() throws AWTException, URISyntaxException, IOException, InterruptedException {
        start();
        ctrlF(); Thread.sleep(200);
        pasteName(txtArea1.getText()); Thread.sleep(500);
        screenShot();
        loop(); Thread.sleep(5000);
        ctrlF(); Thread.sleep(200);
        pasteWM(writeMessage); Thread.sleep(500);
        screenShot();
        loop(); Thread.sleep(350);
        pasteText(txtArea2.getText()); Thread.sleep(1500);
        ctrlEnter();
    }

    public boolean checkOnEmpty() {
        boolean checkOnEmpty = true;

        if (txtArea1.getText().isEmpty() || txtArea1.getText().equals("Имя введи, чепуха!")) {
            txtArea1.setText("Имя введи, чепуха!");
        } else {check1 = true;}
        if (txtArea2.getText().isEmpty() || txtArea2.getText().equals("Введите текст")) {
            txtArea2.setText("Введите текст");
        } else {check2 = true;}

        if (check1 && check2) {
            checkOnEmpty = false;
        }
        return checkOnEmpty;
    }

    public void start() throws URISyntaxException, IOException, InterruptedException {
        URI url = new URI("https://vk.com/friends");
        Desktop.getDesktop().browse(url);
        Thread.sleep(10000);
    }

    public void ctrlF() throws AWTException {
        Robot ctrlF = new Robot();
        ctrlF.keyPress(KeyEvent.VK_CONTROL);
        ctrlF.keyPress(KeyEvent.VK_F);
        ctrlF.keyRelease(KeyEvent.VK_F);
        ctrlF.keyRelease(KeyEvent.VK_CONTROL);
    }

    public void ctrlV() throws AWTException {
        Robot ctrlV = new Robot();
        ctrlV.keyPress(KeyEvent.VK_CONTROL);
        ctrlV.keyPress(KeyEvent.VK_V);
        ctrlV.keyRelease(KeyEvent.VK_V);
        ctrlV.keyRelease(KeyEvent.VK_CONTROL);
    }

    public void ctrlEnter() throws AWTException {
        Robot ctrlEnter = new Robot();
        ctrlEnter.keyPress(KeyEvent.VK_CONTROL);
        ctrlEnter.keyPress(KeyEvent.VK_ENTER);
        ctrlEnter.keyRelease(KeyEvent.VK_ENTER);
        ctrlEnter.keyRelease(KeyEvent.VK_CONTROL);
    }

    public void pasteName(String str) throws AWTException {
        StringSelection selectStr = new StringSelection(str);
        Clipboard copyStr = Toolkit.getDefaultToolkit().getSystemClipboard();
        copyStr.setContents(selectStr,null);

        ctrlV();
    }

    public void pasteWM(String str) throws AWTException {
        StringSelection selectStr = new StringSelection(str);
        Clipboard copyStr = Toolkit.getDefaultToolkit().getSystemClipboard();
        copyStr.setContents(selectStr,null);

        ctrlV();
    }

    public void pasteText(String str) throws AWTException {
        StringSelection selectStr = new StringSelection(str);
        Clipboard copyStr = Toolkit.getDefaultToolkit().getSystemClipboard();
        copyStr.setContents(selectStr,null);

        ctrlV();
    }

    public void screenShot() throws AWTException, IOException {
        BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(image, "png", new File("D:\\", "ScreenShot.png"));
    }

    public void loop() throws IOException, AWTException {
        BufferedImage image = ImageIO.read(new File("D:\\", "ScreenShot.png"));

        int h = image.getHeight();
        int w = image.getWidth();

        outLoop:
        for (int i = 250; i < h; i+=3) {
            for (int j = 450; j < w; j+=3) {

                int pixel = image.getRGB(j,i);
                int red = (pixel & 0x00ff0000) >> 16;
                int green = (pixel & 0x0000ff00) >> 8;
                int blue = pixel & 0x000000ff;

                if (red == 255 && green == 150 && blue == 50) {
                    Robot mm1 = new Robot();
                    mm1.mouseMove(j,i);
                    mm1.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    mm1.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    mm1.mouseMove(0,0);
                    break outLoop;
                }

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn) {
            checkOnEmpty();

            if (!checkOnEmpty()) {
                try {
                    initClicker();
                } catch (URISyntaxException | IOException | InterruptedException | AWTException uriSyntaxException) {
                    uriSyntaxException.printStackTrace();
                }
            }
        }
    }
}
