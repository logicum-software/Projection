import javax.swing.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    JButton b = new JButton("Berechnen");

        b.setBounds(14,14,100, 20);
        b.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
        }
    });

        f.setSize(400,300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
}
