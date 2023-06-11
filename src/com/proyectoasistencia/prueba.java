
package com.proyectoasistencia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class prueba extends JPanel {

    private JProgressBar progressBar;
    private Timer timer;
    private int progress;

    public prueba() {
        setLayout(new BorderLayout());

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        add(progressBar, BorderLayout.CENTER);

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                progress++;
                if (progress > 100) {
                    progress = 0;
                }
                progressBar.setValue(progress);
            }
        });
    }

    public void startAnimation() {
        timer.start();
    }

    public void stopAnimation() {
        timer.stop();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Loading Animation Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                prueba animation = new prueba();
                frame.getContentPane().add(animation);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                animation.startAnimation();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
