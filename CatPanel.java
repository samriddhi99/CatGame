import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;

public class CatPanel extends JPanel {
    private boolean isSleeping = false;
    private boolean isEating = false;
    private boolean isPetting = false;
    private boolean isDressing = false;

    private int zStep = 0;
    private int mouthAnimationStep = 0;
    private Color bowColor = Color.PINK;
    private double earRotationAngle = 0;

    Color catBody = Color.WHITE;
    Color bg = Color.WHITE;


    public CatPanel() {
        setBackground(Color.GRAY);
    }

    public CatPanel(Color catBody, Color bg) {
        setBackground(bg);
        this.catBody = catBody;
        this.bg = bg;
    }

    public void change(Color catBody, Color bg) {
        setBackground(bg);
        this.catBody = catBody;
        this.bg = bg;
    }
    public void sleep() {
        isSleeping = true;
        zStep = 0;

        Timer zTimer = new Timer(1000, e -> {
            zStep++;
            if (zStep > 3) {
                zStep = 0;
                ((Timer) e.getSource()).stop();
            }
            repaint();
        });
        zTimer.start();

        Timer sleepTimer = new Timer(5000, e -> {
            isSleeping = false;
            zStep = 0;
            repaint();
        });
        sleepTimer.setRepeats(false);
        sleepTimer.start();
    }

    public void eat() {
        isEating = true;
        Timer eatTimer = new Timer(600, e -> {
            mouthAnimationStep = (mouthAnimationStep + 1) % 6;
            repaint();
        });
        eatTimer.start();

        Timer stopEatTimer = new Timer(6000, e -> {
            isEating = false;
            mouthAnimationStep = 0;
            eatTimer.stop();
            repaint();
        });
        stopEatTimer.setRepeats(false);
        stopEatTimer.start();
    }

    public void pet() {
        isPetting = true;
        Timer petTimer = new Timer(200, new ActionListener() {
            private boolean increasing = true;

            public void actionPerformed(ActionEvent e) {
                if (increasing) {
                    earRotationAngle += 0.1;
                    if (earRotationAngle >= 0.5) increasing = false;
                } else {
                    earRotationAngle -= 0.1;
                    if (earRotationAngle <= -0.5) increasing = true;
                }
                repaint();
            }
        });
        petTimer.start();

        Timer stopPetTimer = new Timer(2000, e -> {
            isPetting = false;
            earRotationAngle = 0;
            petTimer.stop();
            repaint();
        });
        stopPetTimer.setRepeats(false);
        stopPetTimer.start();
    }

    public void dress() {
        isDressing = true;
    }

    private void drawZZZ(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Serif", Font.BOLD, 24));

        int baseX = 200;
        int baseY = 150;

        if (zStep == 1) g.drawString("Z", baseX, baseY);
        if (zStep == 2) g.drawString("Z", baseX + 20, baseY - 20);
        if (zStep == 3) g.drawString("Z", baseX + 40, baseY - 40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawBackground(g2d);
        drawTail(g2d);
        drawBody(g2d);
        drawPaws(g2d);
        drawFace(g2d);

        if (isSleeping) drawZZZ(g2d);
        if (isDressing) drawBow(g2d);
    }

    private void drawBackground(Graphics2D g) {
        g.setColor(isSleeping ? Color.GRAY : bg);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawBody(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawOval(170, 180, 180, 200);
        g.setColor(catBody);
        g.fillOval(171, 181, 178, 198);
    }

    private void drawTail(Graphics2D g) {
        if (isPetting) {
            int wagDuration = 5000;
            int wagInterval = 100;
            int wagAmplitude = 20;
            long startTime = System.currentTimeMillis();

            Timer timer = new Timer(wagInterval, null);
            timer.addActionListener(e -> {
                long elapsedTime = System.currentTimeMillis() - startTime;
                if (elapsedTime >= wagDuration) {
                    timer.stop();
                    return;
                }
                double angle = Math.sin((elapsedTime / 100.0) * Math.PI) * wagAmplitude;
                Graphics2D rotatedG = (Graphics2D) g.create();
                rotatedG.rotate(Math.toRadians(angle), 265, 360);
                rotatedG.setColor(Color.BLACK);
                rotatedG.drawOval(245, 360, 40, 130);
                rotatedG.fillOval(245, 360, 39, 129);
                rotatedG.dispose();
            });
            timer.start();
        }
            g.setColor(Color.BLACK);
            g.drawOval(245, 360, 40, 130);
            g.fillOval(245, 360, 39, 129);
        
    }

    private void drawPaws(Graphics2D g) {
        g.setColor(catBody);
        g.fillOval(200, 360, 30, 20);
        g.fillOval(290, 360, 30, 20);
        g.setColor(Color.BLACK);
        g.drawOval(200, 360, 30, 20);
        g.drawOval(290, 360, 30, 20);
    }

    private void drawFace(Graphics2D g) {
        drawEars(g);
        drawEyes(g);
        drawNose(g);
        drawMouth(g);
        drawWhiskers(g);
    }

    private void drawEars(Graphics2D g) {
        AffineTransform oldTransform = g.getTransform();

        g.rotate(isPetting ? earRotationAngle : 0, 240, 180);
        int[] leftEarX = {245, 210, 220};
        int[] leftEarY = {183, 196, 171};
        g.setColor(Color.BLACK);
        g.fillPolygon(leftEarX, leftEarY, 3);
        g.setTransform(oldTransform);

        g.rotate(isPetting ? -earRotationAngle : 0, 270, 180);
        int[] rightEarX = {285, 318, 310};
        int[] rightEarY = {183, 202, 171};
        g.fillPolygon(rightEarX, rightEarY, 3);
        g.setTransform(oldTransform);
    }

    private void drawEyes(Graphics2D g) {
        g.setColor(Color.BLACK);
        if (isSleeping) {
            g.drawArc(235, 220, 20, 10, 0, -180);
            g.drawArc(270, 220, 20, 10, 0, -180);
        } else {
            g.fillOval(235, 210, 15, 17);
            g.fillOval(270, 210, 15, 17);
            g.setColor(Color.WHITE);
            g.fillOval(236, 211, 10, 10);
            g.fillOval(271, 211, 10, 10);
        }
    }

    private void drawNose(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillOval(253, 235, 15, 15);
    }

    private void drawMouth(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawLine(260, 259, 260, 245);

        switch (mouthAnimationStep) {
            case 0 :{
                g.drawArc(241, 253, 20, 10, 0, -180);
                g.drawArc(260, 253, 20, 10, 0, -180);
                break;
            }
            case 1:
                g.fillOval(250, 253, 20, 15);
                break;

            case 3: 
                g.fillOval(250, 253, 20, 15);
                break;
            case 2:g.drawLine(245, 263, 270, 263);
            break;
            case 4:g.drawLine(245, 263, 270, 263);
            break;
        }
    }

    private void drawWhiskers(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawLine(245, 240, 150, 220);
        g.drawLine(245, 245, 150, 245);
        g.drawLine(245, 250, 150, 275);
        g.drawLine(280, 240, 375, 220);
        g.drawLine(280, 245, 375, 245);
        g.drawLine(280, 250, 375, 275);
    }

    private void drawBow(Graphics2D g) {
        g.setColor(bowColor);
        int[] bowX = {240, 240, 270};
        int[] bowY = {160, 180, 170};
        int[] bowX1 = {290, 270, 290};
        int[] bowY1 = {160, 180, 170};
        g.fillPolygon(bowX, bowY, 3);
        g.fillPolygon(bowX1, bowY1, 3);
        g.setColor(Color.BLACK);
        g.drawPolygon(bowX, bowY, 3);
    }
}
