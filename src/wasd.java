import javax.swing.*;

public class wasd{
    public static void main(String[] args) {
        JFrame frame = new JFrame("My App");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); // <-- Manual placement

        // ---- MENU BAR ----
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);

        // ---- BUTTONS ----
        JButton btn1 = new JButton("Click Me!");
        btn1.setBounds(50, 50, 120, 40); // x, y, width, height

        JButton btn2 = new JButton("Another");
        btn2.setBounds(200, 150, 120, 40);
        JButton btn3 = new JButton("File");
        btn3.setBounds(125, 250, 120, 40);
        
        
        frame.add(btn1);
        frame.add(btn2);
        frame.add(btn3);
        

        frame.setVisible(true);
    }
}