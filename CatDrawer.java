import java.awt.*;
import java.awt.geom.AffineTransform;

public class CatDrawer {
    private CatState catState;

    public CatDrawer(CatState catState) {
        this.catState = catState;
        
    }

    public void drawCat(Graphics2D g, boolean isSleeping, boolean isEating, boolean isPetting, boolean isDressing) {
        drawBackground(g, isSleeping);
        drawTail(g, isPetting);
        drawBody(g);
        drawPaws(g);
        drawFace(g, isSleeping, isEating, isPetting);
        if (isSleeping) drawZZZ(g);
        if (isDressing) drawBow(g);
    }

    private void drawBackground(Graphics2D g, boolean isSleeping) {
        if (!isSleeping)
            g.setColor(Color.PINK);
        else g.setColor(Color.GRAY);

        g.fillRect(0, 0, 500, 500); // Fixed size; can adjust based on panel size
    }

    private void drawBody(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawOval(170, 180, 180, 200);
        g.setColor(Color.WHITE);
        g.fillOval(171, 181, 178, 198);
    }

    private void drawTail(Graphics2D g, boolean isPetting) {
        if (isPetting) {
            animateTailWag(g);
        } else {
            g.setColor(Color.BLACK);
            g.drawOval(245, 360, 40, 130);
            g.fillOval(245, 360, 39, 129);
        }
    }

    private void drawPaws(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillOval(200, 360, 30, 20);
        g.fillOval(290, 360, 30, 20);
        g.setColor(Color.BLACK);
        g.drawOval(200, 360, 30, 20);
        g.drawOval(290, 360, 30, 20); // Right back paw
    }

    private void drawFace(Graphics2D g, boolean isSleeping, boolean isEating, boolean isPetting) {
        drawEars(g, isPetting);
        drawEyes(g, isSleeping);
        drawNose(g);
        drawMouth(g, isEating);
        drawWhiskers(g);
    }

    private void drawEars(Graphics2D g, boolean isPetting) {
        AffineTransform oldTransform = g.getTransform();

        // Left ear
        g.rotate(isPetting ? catState.getEarRotationAngle() : 0, 240, 180);
        int[] leftEarX = {245, 210, 220};
        int[] leftEarY = {183, 196, 171};
        g.setColor(Color.BLACK);
        g.fillPolygon(leftEarX, leftEarY, 3);
        g.setTransform(oldTransform);

        // Right ear
        g.rotate(isPetting ? -catState.getEarRotationAngle() : 0, 270, 180);
        int[] rightEarX = {285, 318, 310};
        int[] rightEarY = {183, 202, 171};
        g.fillPolygon(rightEarX, rightEarY, 3);
        g.setTransform(oldTransform);
    }

    private void drawEyes(Graphics2D g, boolean isSleeping) {
        g.setColor(Color.BLACK);
        if (isSleeping) {
            g.drawArc(235, 220, 20, 10, 0, -180);
            g.drawArc(270, 220, 20, 10, 0, -180); // Right closed eye
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

    private void drawMouth(Graphics2D g, boolean isEating) {
        g.setColor(Color.BLACK);
        int mouthStep = isEating ? catState.getMouthAnimationStep() : 0;
        switch (mouthStep) {
            case 0:
                g.drawArc(241, 253, 20, 10, 0, -180);
                g.drawArc(260, 253, 20, 10, 0, -180);
                break;
            case 1:
                g.fillOval(250, 253, 20, 15);
                break;
            case 2:
                g.drawLine(245, 263, 270, 263);
                break;
            case 3:
                g.fillOval(250, 253, 20, 15);
                break;
        }
    }

    private void drawWhiskers(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawLine(245, 240, 150, 220); // Left whisker, upper
        g.drawLine(245, 245, 150, 245); // Left whisker, middle
        g.drawLine(245, 250, 150, 275); // Left whisker, lower
        g.drawLine(280, 240, 375, 220); // Right whisker, upper
        g.drawLine(280, 245, 375, 245); // Right whisker, middle
        g.drawLine(280, 250, 375, 275);
    }

    private void drawBow(Graphics2D g) {
        g.setColor(catState.getBowColor());
        int[] bowX = {240, 260, 270};
        int[] bowY = {160, 150, 170};
        g.setColor(Color.RED);
        g.fillPolygon(bowX, bowY, 3);
        g.setColor(Color.WHITE);
        g.fillOval(248, 168, 10, 10);
    }

    private void drawZZZ(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.setFont(new Font("Serif", Font.BOLD, 24));
        int zStep = catState.getZStep();
        int baseX = 200;
        int baseY = 150;
        if (zStep >= 1) g.drawString("Z", baseX, baseY);
        if (zStep >= 2) g.drawString("Z", baseX + 20, baseY - 20);
        if (zStep >= 3) g.drawString("Z", baseX + 40, baseY - 40);
    }

    private void animateTailWag(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.drawOval(245, 360, 40, 130);
        g.fillOval(245, 360, 39, 129);
    }
}
