import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Application {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        int nNumbersToFind = 300000;

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        JFrame f = new JFrame();

        // Initialize the Button
        JButton b = new JButton("Berechnen");
        b.setBounds(14, 14, 100, 20);

        // initialize the ProgressBar
        JProgressBar pb = new JProgressBar(0, nNumbersToFind);
        pb.setBounds(14, 50, 350, 20);
        pb.setStringPainted(true);
        pb.setValue(0);

        //Initialize the Labels for Output of found nPrimes
        JLabel labelText = new JLabel("Anzahl gefundener Primzahlen:");
        labelText.setBounds(14, 100, 200, 20);
        JLabel labelPrimes = new JLabel("");
        labelPrimes.setBounds(250, 100, 50, 20);

        //Initialize labels for Time
        JLabel labelTextTime = new JLabel("Dauer der Berechnung:");
        labelTextTime.setBounds(14, 150, 200, 20);
        JLabel labelTime = new JLabel("");
        labelTime.setBounds(250, 150, 100, 20);

        class PrimeNumbersTask extends SwingWorker<Integer, Integer> {
            int nPrimes = 0;
            Calendar calStart, calEnd;
            SimpleDateFormat sdDate = new SimpleDateFormat("mm:ss.SSS");

            @Override
            protected Integer doInBackground() {
                calStart = Calendar.getInstance();
                for (int i = 3; i < nNumbersToFind; i++) {
                    for (int z = 2; z < i; z++) {
                        if (i % z == 0) {
                            break;
                        } else if (z == i - 1) {
                            nPrimes++;
                        }
                    }
                    publish(i);
                }
                return nPrimes;
            }

            @Override
            protected void process(List<Integer> chunks) {
                for (Integer number : chunks) {
                    pb.setValue(number + 1);
                    calEnd = Calendar.getInstance();
                    long lDiff = calEnd.getTimeInMillis() - calStart.getTimeInMillis();
                    Date dResult = new Date(lDiff);
                    String strTime = sdDate.format(dResult);
                    labelTime.setText(strTime);
                }
            }

            @Override
            protected void done() {
                labelPrimes.setText(Integer.toString(nPrimes));
                calEnd = Calendar.getInstance();
                long lDiff = calEnd.getTimeInMillis() - calStart.getTimeInMillis();
                Date dResult = new Date(lDiff);
                String strTime = sdDate.format(dResult);
                labelTime.setText(strTime);
            }
        }

        f.add(b);
        f.add(pb);
        f.add(labelText);
        f.add(labelPrimes);
        f.add(labelTextTime);
        f.add(labelTime);
        f.setSize(400, 300);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        PrimeNumbersTask taskPrimes = new PrimeNumbersTask();
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                taskPrimes.execute();
            }
        });
    }
}
