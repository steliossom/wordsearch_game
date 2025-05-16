import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardMenu extends JFrame {
     private final JMenuItem fileExit;
     private final JMenuItem fileExit1;
     private final JMenu editM;
     private final JMenuItem editAbout;
     private final JMenuItem editHelp;
     private final MenuHandler handler;

BoardMenu(){
    //ButtonFrame call
    ButtonFrame board = new ButtonFrame();
    handler = new MenuHandler();
    // create a new instance of the MenuHandler class

JMenuBar menuBar = new JMenuBar();  
    // creates a new JMenuBar object, which is a container for JMenu objects

JMenu fileMenu = new JMenu("Μενού");
    // creates a new JMenu object with the label "Μενού"
    
menuBar.add(fileMenu);
    // adds the fileMenu JMenu object to the menuBar container


    // Create the File -> New menu item
    JMenuItem newMenuItem = new JMenuItem("Νέο παιχνίδι");
    newMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        
      }
    });
    fileMenu.add(newMenuItem);
    
    fileExit1 = new JMenuItem("Ακύρωση/Τερματισμός παιχνιδιού");
    fileExit1.addActionListener(handler);
    fileMenu.add(fileExit1);
    
    fileMenu.add(fileExit1);

    // Add the File menu to the menu bar
    
    JMenuItem newMenuItem2 = new JMenuItem("Εισαγωγή στοιχείων παίκτη");
    newMenuItem2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          String enter = JOptionPane.showInputDialog("Enter your name:");
          board.setTitle("Βρες τη λέξη!" + "  Παίκτης: " + enter);    
      }
    });
    fileMenu.add(newMenuItem2);
    // Set the menu bar for the frame
    board.setJMenuBar(menuBar);

    JMenuItem newMenuItem3 = new JMenuItem("Ρυθμίσεις βοηθειών");
    newMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fileMenu.add(newMenuItem3);
    
    JMenuItem newMenuItem4 = new JMenuItem("Αναζήτηση αρχείου λέξεων");
    newMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fileMenu.add(newMenuItem4);
    
    
    fileExit = new JMenuItem("Έξοδος");
    fileExit.addActionListener(handler);
    fileMenu.add(fileExit);
    editM = new JMenu("Εργαλεία");
    menuBar.add(editM);

    editHelp = new JMenuItem("Βοήθεια");
    editHelp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Add your code here to handle the New menu item action
        // Create the instructions window
    JFrame instructionsWindow = new JFrame("Instructions");
    instructionsWindow.setSize(600, 400);

    // Create the instructions panel
    JPanel instructionsPanel = new JPanel();

    // Create the instructions label usng html
    String instructionsText = "<html><h1>Οδηγίες παιχνιδιού:</h1><p>Καλώς ορίσατε στο παιχνίδι. Για να παίξετε, ακολουθήστε τις παρακάτω οδηγίες:</p><hr></hr><ol><li>Βασικός στόχος του παιχνιδιού είναι να δημιουργήσετε όσο το δυνατόν\n" +
"μεγαλύτερες λέξεις επιλέγοντας τα κατάλληλα γράμματα.\n" +
"παιχνιδιού.</li><li>Οι πόντοι που χαρίζει κάθε γράμμα αναγράφονται στο κάτω μέρος κάθε πλαισίου του γράμματος που είναι τοποθετημένο στο ταμπλό.</li><li>Ο παίκτης κερδίζει το παιχνίδι όταν θα έχει\n" +
"καταφέρει να συγκεντρώσει τουλάχιστον 100 πόντους χωρίς να έχουν εξαντληθεί οι βοήθειες που δίνονται δίπλα από το ταμπλό του παιχνιδιού.<li>Στο παιχνίδι υπάρχουν 4 διαφορετικά είδη γραμμάτων:</li><ul><li>Κλασσικά Γράμματα με άσπρο φόντο: η βαθμολογία που\n" +
"αντιστοιχεί σε αυτού τους είδους τα γράμματα είναι ακριβώς αυτή που αναγράφεται στο\n" +
"κάτω μέρος τους.</li></ul><ul><li>Γράμματα με κόκκινο φόντο: κατά\n" +
"τον υπολογισμό της βαθμολογίας της λέξης, η βαθμολογία του συγκεκριμένου γράμματος θα\n" +
"είναι η διπλάσια από αυτήν που αναγράφεται.</li></ul><ul><li>Γράμματα με μπλε φόντο: αν σε μία λέξη υπάρχουν\n" +
"περισσότερα από ένα γράμματα με μπλε φόντο ο διπλασιασμός των πόντων θα συμβεί μόνο μία\n" +
"φορά.</li></ul><ul><li>Σύμβολο μπαλαντέρ (?): ο παίκτης έχει τη δυνατότητα να το επιλέξει και να καθορίσει ο ίδιος ποιο γράμμα\n" +
"θα πρέπει να τοποθετηθεί σε αυτή την περιοχή.</li></ul></ol></html>";
    JLabel instructionsLabel = new JLabel(instructionsText);
    instructionsPanel.add(instructionsLabel);

    // Create the close button
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        instructionsWindow.dispose(); // Close the instructions window
      }
    });
    instructionsPanel.add(closeButton);

    // Add the instructions panel to the instructions window
    instructionsWindow.add(instructionsPanel);

    // Make the instructions window visible
    instructionsWindow.setVisible(true);  
      }
    });
    editM.add(editHelp);
    editAbout = new JMenuItem("About...");
    editAbout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Add your code here to handle the New menu item action
        // Create the instructions window
    JFrame instructionsWindow = new JFrame("About us");
    instructionsWindow.setSize(600, 400);

    // Create the instructions panel
    JPanel instructionsPanel = new JPanel();

    // Create the instructions label
    String instructionsText = "<html><h1>Μέλη ομάδας:</h1><hr></hr><p>Σωματόπουλος Στυλιανός: icsd21061</p>Σωματοπούλου Κυριακή-Μαρία: icsd19206\n<p><hr></hr>Μάθημα: Αντικειμενοστρεφής προγραμματισμός ΙΙ\n</p><p>Προπτυχιακοί φοιτητές του τμήματος ΜΠΕΣ</p></ol></html>";
    JLabel instructionsLabel = new JLabel(instructionsText);
    instructionsPanel.add(instructionsLabel);

    // Create the close button
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        instructionsWindow.dispose(); // Close the instructions window
      }
    });
    instructionsPanel.add(closeButton);

    // Add the instructions panel to the instructions window
    instructionsWindow.add(instructionsPanel);

    // Make the instructions window visible
    instructionsWindow.setVisible(true);  
    }
    });
    editM.add(editAbout);

    board.pack();
    board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    board.setVisible(true);
    }
class MenuHandler implements ActionListener {
    // implements ActionListener means this class is going to listen for action events
    public void actionPerformed(ActionEvent e) {
        // actionPerformed is called when an action is performed (such as a button click)
        if (e.getSource() == fileExit || e.getSource() == fileExit1)
            // check if the source of the action event is the fileExit or fileExit1 component
            System.exit(0);
            // if true, exit the program with a status code of 0 (indicating a normal exit)
    }
}

}