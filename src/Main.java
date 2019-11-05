import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CandlePanel panel = new CandlePanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
