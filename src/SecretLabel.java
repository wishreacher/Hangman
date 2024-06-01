import javax.swing.*;

public class SecretLabel extends JLabel {
    boolean letterChecked = false;
    String secret;
    SecretLabel(String text, boolean letterChecked, String secret){
        super(text);
        this.letterChecked = letterChecked;
        this.secret = secret;
    }

    public void check(){
        letterChecked = true;
    }
}
