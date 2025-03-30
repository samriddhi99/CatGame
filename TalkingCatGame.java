import javax.swing.*;
import java.awt.*;
public class TalkingCatGame extends JFrame {
    private JPanel launchPage;
    private TalkingCat talkingCat;
    public TalkingCatGame() {
        setTitle("Mau Mau the Cat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        setupLaunchPage();
        add(launchPage);
        setVisible(true);}
    private void setupLaunchPage() {
        launchPage = new JPanel(new BorderLayout());
        launchPage.setBackground(Color.PINK);
        JLabel catImage = new JLabel();
        catImage.setHorizontalAlignment(SwingConstants.CENTER);
        catImage.setIcon(new ImageIcon(new ImageIcon("image.png")
                .getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH)));
        launchPage.add(catImage, BorderLayout.WEST);
        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.setBackground(Color.PINK);
        JLabel titleLabel = new JLabel("Meet Mau Mau the Cat!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 35));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(titleLabel, BorderLayout.CENTER);
        JLabel creditsLabel = new JLabel("Made by Samriddhi SE22UCSE240");
        creditsLabel.setFont(new Font("Arial", Font.ITALIC, 30));
        creditsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(creditsLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.PINK);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); 
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 20));
        playButton.setPreferredSize(new Dimension(120, 40)); 
        playButton.addActionListener(e -> launchTalkingCat());
        buttonPanel.add(playButton);
        infoPanel.add(buttonPanel, BorderLayout.SOUTH);
        launchPage.add(infoPanel, BorderLayout.CENTER);
    }    

    private void launchTalkingCat() {
        remove(launchPage);
        talkingCat = new TalkingCat();
        add(talkingCat);
        revalidate();
        repaint(); }
    public static void main(String[] args) {SwingUtilities.invokeLater(TalkingCatGame::new);}
}
