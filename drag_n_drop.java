import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Base64;

public class drag_n_drop extends JFrame {
    private JButton and_btn, or_btn, not_btn, clear_btn;
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

        // Decode Base64-encoded images
        and_image = decodeBase64Image("iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAABmJLR0QA/wD/AP+gvaeTAAAEOUlEQVR4nO3cbahkYxwA8N/YTS72FYuynyg2hfVNJGxZoZCoFSHlJQml0LbeykuU908+WIW0iS9aW8rN60beQuxmxZaXEna12LV3L9eHZ6ZzZu/OvbOz85wzs/v/1dQz986d///c/5wz5zzPcx5CCCGEEEIIIYQQQgghhBBCCCGEEELYXY0aYh6AkQzv+xd2ZHjfSlVVkONxO87G4RnjjGELNuIbfIm38RHGM8YdKjdIn9yJGh9b8IL0gZiRd3P3TO495BKsKsUZlz652zLFm4dZOGyK13yPh7ES2zPlMZBG8LPiU/o85lYUex5Owa14DX+bvNf8gEsrymcgXKTY+PewX425zMKVWGtyYd7AUfWlVp37FBt9bc25lJ2Bd7QX5VecW2NOlXhKscFLas5lZw1cgV8UOf6HW+pMKrdyQc6qOZdOjsSo9r3lwVozymgYCkI6DX5Ce1FW1JpRJsNSkJbl2otyTb3p9N+wFQTuV+T8D06uN53+GsaCNKTrpVbeG3BwlQnUeW0wiCZwPb5uPj9Gxd8nU3WdLMMFei/aYmmDSKe9oz2+Tx1OxMeYKfXDLcZXdSY0or8dgsNyyCp7VJH/KzXnooEP7NsFmY1NUv7/SkMI2c3s8PMJnIaFeu8Rvke6Gh5WW/Ak7pYO2zcbrC6g3TaMZ1k7O0Q6/Z3AZmm0M6s4y5ra71jdbM/F+bkDRkGm91KpvTR3sCjI9EalnmA4M3ewKMj0NuHzZvtoLMgZLArSnS9K7WNzBoqCdGd9qR0FGQA/ltpTzWjZY1GQ7vxZas/KGajTlXpot7XUPl2ahdmLHViDdZ1eEAXpzoGl9qnNR6+WS2P5Y7v6ZRyyutPPw9QOxXXNJLGHdGdhqb1S6gnvxTjeNMXE7yhId8qnuivxbq5Accjqzgml9vqOr+qDKMj05ktDuvCtNO00myjI9JYo/k9v5Q4WBZneslJ7Te5gUZCpHYrzmu0/8HrugFOdZc20Z2Pqc3r8u0FyE/ZvtldJw7m1aOBD+/askznSOHpr1slxdSYT87J4XJH/q1UF7XTI2obLpdvS+jFzcdichBub7THcWWMufTOs04AOkub2tnJ/pMrgcZbVroFnsKj5fB3uqjKBKEi7B3BZs71dOmznuqd+l6IghRW4o9lu3UH1aX3p9N+wfIfMwNPiHsOBsEBaOKBcjIdqzSijQb9P/Sqp57aV47i0FMde617Fxg7SNP4leF/7XvGbCubt1u1CxQavVe+ySLNxtV3fhLRamnSw1xvBT4oNf1FapacK86WZIbdJPbRbTS7Ed9JqQHWsqtdR7mQuxsuqWS+rId3DMVcqSCcb8BietY+tl9VyndQf1K+Oyl4em/EczjHg115V7a6LpNl+S3FExjhj0kDSRmn8+zNphsgnhmTNxViVNIQQQgghhBBCCCGEEEIIIYQQQgghhBD663/ksH79DjIaiwAAAABJRU5ErkJggg==");

        or_image = decodeBase64Image("iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAABmJLR0QA/wD/AP+gvaeTAAAFPElEQVR4nO2cW2icRRTHf01irElajZd6Ja1GiXipWKvFokKxVkSKgtgnn4z65pNYEBUEry+K9QIiaEV98oqKSmuhtFaUEhW1ltKHVFMVbavWS2qbtFkfzn6d2e1eyO6e70yT84Mhk+zHN/+T+b6ZM2fOLDiO4ziO4ziO4ziO4ziO4ziO4ziO4ziO4ziOc5QzQ/He84DngVMV24gZA0aBP4ARYDvwHTAEHMxJQ9I8DhQSKP8AHwO3AyeoWpw4N2DfGeXlP+AV4EI9s5tDc8gCeAa4u1jfA1wB7FVsrxuYDQwUy2LgGuD4susKwNvAvcAPinqSoxPYTHhCXzLQ0A4sAVYjw1f8xuwDHiheM23oRwwvABPAdYZaTgQeQib+uGM2AXPtZOXPfQTjtwIdtnLoBVYBhwi6fgeuthSVJ8cgLmhm/B22cg6zFPiV0iFsuamiHLmeYPjPwExbOYc5HficoG0/onVa8CnpvSUAXcCHBG2jwOWminJiCaVzibbbPRk6gTUEfSPAHFNFOfElwejUhoYeJNSS6VtLQg/NU8BviIvYaNkDvIVM6hl3Egx+PQc7Jksf4nFlGgdt5QhdtDZksTC6dzeyWs/G6lnq1kyeFQTtfwIn5dVwtRXqeFFEP3AA8TwaLWuB5xCfP7v3BcAlyJuzBXGJU+J7JMxzHuINzgA+MVWkzC2EJ/BVYy3V6EdC+tn65DRbObr0IJHXArIwS2biLGM14cG531iLOusJxl5krKUa5yPxtwLwIzkEIdu0G6jBF1F9YdWrbNmGrOJBvK8rtRtMpUMWmKmoz2tRfUrHufoIQ9YGYy21mEfQ+bWtFF3aELe4QPq7dsOIznHgOM2GLIesCSReBHAm9nsktRgq/uwA5ms2ZNkhEDqkg7T9/G1R/RzNhqw75O+oPttMRX12RPUzNBuyHiZGo3qXmYr6/BXVB4FFDd7nEPAu8EbTipR4geDBpLyfvYzWBVrHqOEYWA9ZcVg+5XTPzhbe6yvEu6yI9ZDVE9X3mamoT7xFsApJAGyUEeRNqYh1h3RH9dGqV9kTb+VuRdYlKlgPWbGhu81U1Gcgqv9kpiIHdhN25VJmA2FS7jPWosYsgpHfGGupxUxCKuwu7cYsh6x4GNhupqI+iwlu6ibtxiw7JN5bGKp6lT23RvX3tRur5WX1AddSulaYLBPIvseWCp/Fq91Uw9rHIhkoIOukj6yEdFCahNxMOUDliXAHIaSd6lGzQYId71gK6eTIMxSNlnGOjJBeHH3+ma4pDdOOzG2ZzlzOtVQbssaAq5Bzgs0sHrN/ePlC6qaovqaJ+2tyF5KXBZL+us5Qizpxfu+lxloqMYfSEWKZrRxdFhAMTS1jESRH7D2Cxg9s5ejzIsHYlcZaKnEPQd9e4CxbObr0Ek7C7ie9bdubEfc265DbbOXo8zDB2JeNtZSzlJDeWkDC7FOak5E99AKyYEzp2xRWEFKSsnmjmQXxUcGTBINT2VNuBx6h9Hj0OtI5kKrGfGSBmK3cz7WVA0hwcyOli9g3UU6ES4E2JJ6VGf20rRx6gceQByPTNFH8m/WGXS7EbuRO7PKv+oEnCPNYVn5hii/8YhYRTiEVgBtzbLsdOeawEtnHyM55xDG2Z5HvP0kGzZNLvUhYfW7x913IiSQtOpEsllOQYOYAEj4v5yAyVzyKnCWcNjxI65LLWlGGkU44W9PoZtFMAxqpf4kqO4FvkQSF9YSAZtJoH7a8DBm68uLfYhkm7cQ7x3Ecx3Ecx3Ecx3Ecx3Ecx3Ecx3Ecx3Ecx3GmLv8DVp8Gsf1F9t8AAAAASUVORK5CYII=");
        
        not_image = decodeBase64Image("iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAABmJLR0QA/wD/AP+gvaeTAAAEgUlEQVR4nO3c24tVVRgA8N+kGJSlFtkVMSxFukBEUtBDRUEP3ej2UlAE9VARYlgU9AfUg1kUdPMhInwwiiCKMnoqiiSwi0Jo0cUyqdQwaxxHp4c1h7POmZmjzln7HDv7+8FhhrPX/tY3+5u999p7nX0IIYQQQgghhBBCCCGEEEIIIYQQQgghhGk7F6uwEduwBWtwRR9zqq3HMIyxKV5rMLtv2dXMI1o3/n78igNt73+KuX3KsTaWaN3wz+Dk8WWzsRx7s+VfZMtDBV7S3NirpmhzGXZl7b7C/J5kV0ObpY08gpM6tLsYf2gWZTPOqDy7mhnSPJFvPYz2F2KHZlG2YEFl2dXQMZob95vDXGeJNCRurPcjzqkkuxqaTkHgbHyfrbsd5xXProamWxDSoWprtv5vuKBodjXUTUHgdGzKYuzEJcWyq6FuCwKnSsPgRpxduLRIdjVUoiCk4fKGLNYeXNl1djVUqiCkWyqfZfH24uouY9ZOyYLAHHySxRzG9QXi1kbpgsDx+DCLuw83FYo98KooCByH97PYI7i1YPyBVVVBYBbeyuKP4q7CfQycKgtCKsobWR8HcE8F/QyMqgsCM/Bq1s9BPFRRX5WY2e8ECmvsFQdxt3SHebVUqKcnaT8X12LxeJsv8a40Yht4vdhD8r5eyPobw6NtbVZid1ubMemW/20V53dU6GVBaO4d+eHr9vFlz5lYiLG2tvf2IMe+6nVBGp7S+t9/i9aNvxZ34D58nL0/bMDnXvpVkCG8k/U9kv3+8CQ5rs2WP9u7NJOhDstW4U5lT/zzxn9uwvkF4x7KNfig7b2fsVA6POXOlGYpSdczewrmMSqNAlce6YozTfycVMlXL/cQWDRJDus7tJ/sZF/qNarDP/lUC0bxpDSEnNX5bz0i8w7dpBKLJnlvqXSIat9DFkg3Lim/h4zglfG4fdfPc8h7Wd/7st/bh8IzsC5bvrp3afZePwoyJJ0LG/1ux41aDyHrpPte92udY/lX+oDFwOrHdUh+vXEQN48vy4s02euAdKU/0Hp9pf6y1o28oq3NcvxpYjG2SXvRwOtVQWbgNa17xoNTtD1BulB8HE/gOmUHMUe1XhRkponFeKCivv73ejEf8mbWx6ganAe6UWVBjsXbWotxZ+E+Bk6Vc+rrs9j7NEdToYOqPnXyURZ3GDcUij3wShdkrvQcYiPmXukmYjhMJQsyD59n8f7GVV3GrJ1SBZkvzX03Yu2WnksMR6hEQU4bX7cRZyeWFcmuhrotyALpOcNGjB3Sc4hhmropyEJ8l62/XW9nHAfSdAuyRJpubaz7k/Q9KaFL0ynIUvySrfeDyWf/wjQc6XPqF+F3zWJ8i7Mqy66mGg9t7tf5O0yWSSOoRjE2SSOsUNiLmht5qs88XY6/snYbcUpPsquhxdKd2MbGfl563BlOlGbx/smWb9D5O1FCAStMnDbdqfXTIGPS84NzpogRClupdU9of62V7uSGHloknUe+lr6KaSteFzcJQwghhBBCCCGEEEIIIYQQQgghhBBCCCEU9B9TfuBSsI+nfwAAAABJRU5ErkJggg==");
        

        label = new JLabel();
        label.setLayout(null);

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

    private ImageIcon decodeBase64Image(String base64Image) {
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        Image image = Toolkit.getDefaultToolkit().createImage(imageBytes);
        return new ImageIcon(image);
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
