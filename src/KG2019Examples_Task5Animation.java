/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;

/**
 *
 * @author Alexey
 */
public class KG2019Examples_Task5Animation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DrawPanel panel = new DrawPanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
