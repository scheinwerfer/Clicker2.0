import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        setTitle("Clicker_v2.0");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,200);

        add(new Clicker());

        setVisible(true);
    }

    public static void main(String[] args) {
        new Window();
    }
}
