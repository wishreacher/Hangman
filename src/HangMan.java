import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class HangMan extends JFrame {
    String wordToGuees = "Prohorov";
    int letterIndex = 0;
    String result = "________";
    JPanel wordGaps;
    ArrayList<SecretLabel> labels = new ArrayList<>();

    HangMan(){
        super();

        setTitle("HangMan");
        setSize(800, 600);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttons = new JPanel();
        String[] letters = "abcdefghijklmnopqrstuvwxyz".split("");
        for(int i = 0; i < 26; i++){
            letterButton button = new letterButton(letters[i]);
            button.addActionListener(e -> {
                checkLetter(button.getText());
            });
            buttons.add(button);
        }

        wordGaps = new JPanel();
        String[] a = wordToGuees.split("");
        for(int i = 0; i < wordToGuees.length(); i++){
            SecretLabel gap = new SecretLabel("_", false, a[i]);
            labels.add(gap);
            wordGaps.add(gap);
        }

        this.add(buttons, BorderLayout.CENTER);
        this.add(wordGaps, BorderLayout.NORTH);
    }

    public void checkLetter(String letter){
        boolean change = false;
        for(int i = 0; i < labels.size(); i++){
            if(labels.get(i).secret.equalsIgnoreCase(letter) && !labels.get(i).letterChecked){
                labels.get(i).letterChecked = true;
                rightGuess(letter, i);
                change = true;
                break;
            }
        }
        if(!change){
            wrongGuess();
        }
    }

    public void wrongGuess(){
        JOptionPane.showMessageDialog(null, "Wrong Guess");
    }

    public void rightGuess(String letter, int j){
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < result.length(); i++){
            if(i == j){
                temp.append(letter);
            } else{
                temp.append(result.charAt(i));
            }
        }
        result = temp.toString();
        redrawGaps(result);
        if(result.equalsIgnoreCase(wordToGuees)){
            JOptionPane.showMessageDialog(null, "Victory aaaaa");
        }
    }

    public void redrawGaps(String s){
        String[] a = s.split("");

        for(int i = 0; i < labels.size(); i++){
            if(labels.get(i).letterChecked){
                labels.get(i).setText(a[i]);
            }
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args){
        JFrame ui = new HangMan();
        ui.setVisible(true);
    }
}