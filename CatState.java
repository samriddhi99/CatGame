
import java.awt.*;

public class CatState {
    public enum Action {
        IDLE, SLEEPING, EATING, PETTING, DRESSING
    }
    
    private Action currentAction = Action.IDLE;
    private int zStep = 0;
    private int mouthAnimationStep = 0;
    private Color bowColor = Color.RED;
    private double earRotationAngle = 0;

    public Action getCurrentAction() { return currentAction; }
    public void setCurrentAction(Action action) { this.currentAction = action; }
    
    public int getZStep() { return zStep; }
    public void setZStep(int zStep) { this.zStep = zStep; }
    
    public int getMouthAnimationStep() { return mouthAnimationStep; }
    public void setMouthAnimationStep(int step) { this.mouthAnimationStep = step; }
    
    public Color getBowColor() { return bowColor; }
    public void setBowColor(Color color) { this.bowColor = color; }
    
    public double getEarRotationAngle() { return earRotationAngle; }
    public void setEarRotationAngle(double angle) { this.earRotationAngle = angle; }
}
