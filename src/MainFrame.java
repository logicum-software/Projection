import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    JButton b = new JButton("Berechnen");

        b.setBounds(14,14,100, 20);
        b.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        }
    });
}
