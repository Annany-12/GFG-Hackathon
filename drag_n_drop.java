import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class drag_n_drop extends JFrame {
    private JButton and_btn, or_btn, not_btn, clear_btn;
    //private JPanel imagePanel;
    private JLabel label;

    private ImageIcon and_image, or_image, not_image;

    private Point imageLocation;

    public drag_n_drop() {
        setTitle("Logic Gate Simulator (by: VHS)");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        and_btn = new JButton("And Gate");
        or_btn = new JButton("Or Gate");
        not_btn = new JButton("Not Gate");
        clear_btn = new JButton("Clear All");

        and_image = new ImageIcon("C:\\Users\\DELL\\Desktop\\GUI\\and.png"); // Provide the actual path to your image file
        or_image = new ImageIcon("C:\\Users\\DELL\\Desktop\\GUI\\or.png"); // Provide the actual path to your image file
        not_image = new ImageIcon("C:\\Users\\DELL\\Desktop\\GUI\\not.png"); // Provide the actual path to your image file

        label = new JLabel();
        label.setLayout(null); // Use null layout to manually set component locations

        and_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addImageToPanel(and_image);
            }
        });

        or_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addImageToPanel(or_image);
            }
        });

        not_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addImageToPanel(not_image);
            }
        });

        clear_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearImages();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(and_btn);
        buttonPanel.add(or_btn);
        buttonPanel.add(not_btn);
        buttonPanel.add(clear_btn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(label, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addImageToPanel(ImageIcon imageIcon) {
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());

        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                imageLabel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                imageLocation = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                imageLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        imageLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (imageLocation != null) {
                    int x = imageLabel.getX() + e.getX() - (int) imageLocation.getX();
                    int y = imageLabel.getY() + e.getY() - (int) imageLocation.getY();
                    imageLabel.setLocation(x, y);
                }
            }
        });

        label.add(imageLabel);
        label.revalidate();
        label.repaint();
    }

    private void clearImages(){
        label.removeAll();
        label.revalidate();
        label.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new drag_n_drop();
            }
        });
    }
}
