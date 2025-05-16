import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ButtonFrame extends JFrame implements ActionListener {
       private int number=0;
       public JButton[][] tiles;
       private int enable1, enable2, enable3, enable4, enable5;
       private int num1, num2, num3, num4, num5;
       private JLabel l_exchange, delete, rearrangement, r_column, r_row; 
       private final JLabel  help, goal, g_points, total, word, find, congrats;
       private JLabel total_points = new JLabel("0", JLabel.RIGHT);
       private JLabel word_points = new JLabel("0", JLabel.RIGHT);
       private JLabel find_words = new JLabel("0", JLabel.RIGHT);
       private JLabel con_words = new JLabel("   ", JLabel.RIGHT);
       private JTextField del_row, rear_col, rear_row, swap1, swap2, swap3, swap4;       
       private final JPanel row, row1, row2, row3, row4, row5, row6, row7, row8, row9, row10;
       private final JPanel l_row11;
       ArrayList<String> words = new ArrayList<>();
       private int score=0, total_score=0;
       private  int  red=0;
       private  int  blue=0;
       ArrayList<String> check_word = new ArrayList<>();
       ArrayList<String> selected_red = new ArrayList<>();

    public ButtonFrame() {
        
        String question_mark = JOptionPane.showInputDialog("Enter your name:");
        BufferedReader in;
        try {
            //Create a new BufferedReader to read the file "words.txt"
            in = new BufferedReader(new FileReader("words.txt"));
            //Create a variable to hold each line of the file as it is read
            String str;
            //Continue reading the file as long as there is a line to read
            while ((str = in.readLine()) != null) {
                //Add the current line to the "words" list
                words.add(str);
            }
            //Close the BufferedReader
            in.close();
        } //If there is an IOException, print the stack trace
        catch (IOException e) {
            e.printStackTrace();
        }
   
        ArrayList<Character> word_letters = new ArrayList<>();
        words.forEach((s) -> {
            for (char c : s.toCharArray()) {
                word_letters.add(c);
            }
        });
        Random rand = new Random();
        Collections.shuffle(word_letters, rand);
        setTitle("Βρες τη λέξη!" + "  Παίκτης: " + question_mark);
        setSize(1000, 1000);
        // Set the layout to GridLayout with 2 rows and 1 column
        setLayout(new GridLayout(1, 1));

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();

        // Set the layout of the panel to GridLayout with 10 rows and 10 columns
        buttonPanel.setLayout(new GridLayout(11, 20));

        int index = 0;
//Create a 2D array of JButtons with a size of 10x10
        tiles = new JButton[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                //Create a new JButton
                tiles[row][col] = new JButton();
                //Create a new JButton
                JButton button = new JButton();
                //Assign the new JButton to the current position in the "tiles" array
                tiles[row][col] = button;
                //Set the text of the button to a letter from the "word_letters" list, using the "fill" method from the "FrameManipulation" class
                button.setText(FrameManipulation.fill(word_letters, index));
                //Increment the index so that the next button will be assigned the next letter in the "word_letters" list
                index++;
                //Set the size of the button to 40x40 pixels
                button.setPreferredSize(new Dimension(40, 40));
                //Set the background color of the button to white
                button.setBackground(Color.white);
                //Set the text color of the button to black
                button.setForeground(Color.black);
                //Set the font of the button's text to "Times New Roman" with a size of 20
                button.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                //Add an action listener to the button that calls the "ButtonListener" class, passing in the current row and col
                button.addActionListener(new ButtonListener(row, col));
                //if the index goes beyond the size of word_letters list, set the text of button to a random letter
                if (index > word_letters.size()) {
                    button.setText(FrameManipulation.fill_random());
                }
                //Add an action listener to the button that changes its background color to yellow when clicked
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //add the letter of the button to check_word list
                        check_word.add(button.getText());
                        //get the current color of the button
                        Color c = button.getBackground();
                        //increment the red if the button color is red
                        if (button.getBackground() == Color.red) {
                            red++;
                            selected_red.add(button.getText());
                        }
                        //if the button color is blue, set blue to 1
                        if (button.getBackground() == Color.blue) {
                            blue = 1;
                        }
                        //if the button color is white or blue or red, set the color to yellow
                        if (button.getBackground() == Color.white || button.getBackground() == Color.blue || button.getBackground() == Color.red) {
                            button.setBackground(Color.yellow);
                        } //otherwise set the color to the previous color
                        else if (button.getBackground() == Color.yellow) {
                            button.setBackground(c);
                        }
                    }
                });

            buttonPanel.add(tiles[row][col]);
           
        }
        }
          
          Random r = new Random();
//Set the background color of 2 randomly selected buttons to red
        for (int i = 0; i < 2; i++) {
            tiles[r.nextInt(10)][r.nextInt(10)].setBackground(Color.RED);
        }
//Set the background color of 3 randomly selected buttons to blue
        for (int i = 0; i < 3; i++) {
            tiles[r.nextInt(10)][r.nextInt(10)].setBackground(Color.BLUE);
        }
//Set the text of 4 randomly selected buttons to "?" if the background color of the button is white
        for (int i = 0; i < 4; i++) {
            if (tiles[r.nextInt(10)][r.nextInt(10)].getBackground() == Color.white) {
                tiles[r.nextInt(10)][r.nextInt(10)].setText("?");
            }
        }
// Iterate through all the buttons in the "tiles" array 
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                // if the button text is "?"
                if ("?".equals(tiles[row][col].getText())) {
                    //create a new button and set it as the current button in the array
                    final JButton button1 = tiles[row][col];
                    //add an action listener to the button that opens a new JFrame when clicked
                    button1.addActionListener((ActionEvent e) -> {
                        JFrame frame = new JFrame("Επίλεξε γράμμα");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        //set the layout of the frame to flow layout
                        frame.setLayout(new FlowLayout());

                        // Create the new JPanel and JTextField
                        JPanel inputPanel = new JPanel();
                        JTextField inputField = new JTextField(10);
                        inputPanel.add(inputField);
                        // Create the submit button
                        JButton submitButton = new JButton("Οκ");
                        inputPanel.add(submitButton);

                        // Add the inputPanel and inputField to the frame
                        frame.add(inputPanel);
                        frame.pack();
                        frame.setVisible(true);
                        // Make the inputPanel and inputField visible
                        inputPanel.setVisible(true);
                        inputField.setVisible(true);

                        // Add an action listener to the submit button
                        submitButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Get the text from the inputField
                                String inputText = inputField.getText();
                                inputText = inputText.toUpperCase();
                                // Set the text of the button to the inputText
                                button1.setText(FrameManipulation.fill_question_mark(inputText));
                                check_word.add(inputText);

                                // Make the inputPanel and inputField not visible
                                inputPanel.setVisible(false);
                                inputField.setVisible(false);
                                frame.setVisible(false);

                            }
                        });

                    });

                }
            }
        }
       l_row11 = new JPanel();
       JLabel label = new JLabel("                                           Έλεγχος                                           ");
       l_row11.add(label);

        JButton check = new JButton();
        //add the check button to l_row11
        l_row11.add(check);
        //add the l_row11 to button panel
        buttonPanel.add(l_row11);
        //add an action listener to the check button that performs various actions when clicked
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //change the check_word and selected_red list to capital letters
                check_word = FrameManipulation.get_capital_letters(check_word);
                selected_red= FrameManipulation.get_capital_letters(selected_red);
                //join the check_word list and store in result variable
                String result = String.join("", check_word);
                //join the selected_red list and store in str variable
                String str = String.join("", selected_red);
                // check if the word is valid and in words list
                if(FrameManipulation.isValid(result, words)){
                    // if selected_red is not empty
                    if(!selected_red.isEmpty()){
                    // calculate score using the selected_red list
                 score +=  FrameManipulation.calculateWordScore(str);
                 //clear the selected_red list
                 selected_red.clear();
                    }
                    //calculate score using the result variable
             score +=  FrameManipulation.calculateWordScore(result);
             //if blue is 1, double the score
             if(blue==1){
                 score*=2;
             }
             //increment the total score
             total_score+=score;
                    //increment the number of words found
                    number++;
                    //set the text for find_words label
                    find_words.setText(Integer.toString(number));
                    //set the text for con_words label
                    con_words.setText(result);
                    //set the text for word_points label
                    word_points.setText(Integer.toString(score));
                    //set the text for total_points label
                    total_points.setText(Integer.toString(total_score));
                    //clear the check_word list
                    check_word.clear();
                    //set score to 0
                    score=0;
                    //iterate through the buttons and set the enabled property based on the background color
                   for(int row=0; row<10; row++){
             for(int col=0; col<10; col++){
                 if(tiles[row][col].getBackground() == Color.yellow){
                    tiles[row][col].setEnabled(false); 
                 }
                 else{
                 tiles[row][col].setEnabled(true); 
                 }
             }
                   }
                }
                else{
                    
                //if the word is not valid, iterate through the buttons and set the background color to white
                for(int row=0; row<10; row++){
             for(int col=0; col<10; col++){
                 if(tiles[row][col].getBackground() == Color.yellow){
                   tiles[row][col].setBackground(Color.white); 
                 }
                 //set all the buttons enabled
                 tiles[row][col].setEnabled(true); 
                 //clear the check_word list
                 check_word.clear();
            }
            }
                }
                //set blue to 0
                blue=0;
                //if the total score is greater than or equal to 100, then open a new JFrame to show a congratulations message and close button
                  if(total_score>=100){
            JFrame congratulations = new JFrame("Τέλος παιχνιδιού");
            congratulations.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            congratulations.setLayout(new FlowLayout()); 
            JLabel label = new JLabel("Συγχαρητήρια! Κέρδισες με " + total_score + " πόντους!");
            congratulations.add(label);
            JButton close = new JButton("Κλείσιμο");
            close.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //when clicked, close the application
                    System.exit(0);
                }
            });
            congratulations.add(close);
            congratulations.setSize(250, 100);
            congratulations.setVisible(true);
        }

            }
        });
        
        // Add the button panel to the frame
       add(buttonPanel);
       
        // Create another panel for the buttons on the right
        JPanel rightButtonPanel = new JPanel();

        // Set the layout of the panel to FlowLayout
        rightButtonPanel.setLayout(new GridLayout(12,5,1,1));

        // Create the panels and add them to the frame
        row = new JPanel();
        row1 = new JPanel();
        row2 = new JPanel();
        row3 = new JPanel();
        row4 = new JPanel();
        row5 = new JPanel();
        row6 = new JPanel();
        row7 = new JPanel();
        row8 = new JPanel();
        row9 = new JPanel();
        row10 = new JPanel();

        help = new JLabel("Βοήθειες", JLabel.RIGHT);
        row.add(help);
        rightButtonPanel.add(row);

        JButton button1 = new JButton();
        button1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        //adds the button1 to JPanel "row1"
        row1.add(button1);
        //creates 4 JTextFields called swap1, swap2, swap3, and swap4
        swap1 = new JTextField(1);
        row1.add(swap1);
        swap2 = new JTextField(1);
        row1.add(swap2);
        swap3 = new JTextField(1);
        row1.add(swap3);
        swap4 = new JTextField(1);
        row1.add(swap4);
        //create a JLabel called l_exchange with text "0/6" and align it to the right
        l_exchange = new JLabel("0/6", JLabel.RIGHT);
        row1.add(l_exchange);
        //sets the text of the button1 to "Εναλλαγή γραμμάτων"
        button1.setText("Εναλλαγή γραμμάτων");
        //initialize variables enable1 and num1 with values 0 and 1 respectively
        enable1 = 0;
        num1 = 1;
        //add an action listener to the button1 that performs various actions when clicked
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //sets the text of l_exchange label to num1/6
                l_exchange.setText(Integer.toString(num1) + "/6");
                //get the values of the 4 JTextFields and parse them to integers
                int row1 = Integer.parseInt(swap1.getText());
                int col1 = Integer.parseInt(swap2.getText());
                int row2 = Integer.parseInt(swap3.getText());
                int col2 = Integer.parseInt(swap4.getText());
                //create a string variable called swap
                String swap;
                //swap the text of the two buttons at the coordinates specified by the JTextFields
                swap = tiles[row1][col1].getText();
                tiles[row1][col1].setText(tiles[row2][col2].getText());
                tiles[row2][col2].setText(swap);
                //increment num1
                num1++;
                //increment enable1
                enable1++;
                if (enable1 < 6) {
                    button1.setEnabled(true);
                } else {
                    button1.setEnabled(false);
                }
            }
        });

// Add the first row to the right button panel
        rightButtonPanel.add(row1);

        // Create a new JButton and set its font
        JButton button2 = new JButton();
        button2.setFont(new Font("Times New Roman", Font.PLAIN, 20));

// Set the button's text and add it to the second row of the right button panel
        button2.setText("Διαγραφή γραμμής");
        row2.add(button2);

// Create a text field to input the row to be deleted
        del_row = new JTextField(1);
        row2.add(del_row);

// Create a label to display the number of deletions
        delete = new JLabel("0/3", JLabel.RIGHT);
        row2.add(delete);

// Create a gap label
        JLabel gap_d = new JLabel("                         ");
        row2.add(gap_d);

// Initialize variables
        enable2 = 0;
        num2 = 1;

// Add an action listener to the button to handle the deletion
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Get the row to be deleted from the text field
                int row = Integer.parseInt(del_row.getText());

                // Loop through the columns of the selected row and set new random text
                for (int col = 0; col < 10; col++) {
                    tiles[row][col].setText(FrameManipulation.fill_random());
                }

                // Update the label to display the number of deletions
                delete.setText(Integer.toString(num2) + "/3");
                num2++;
                enable2++;
                // Enable/disable the button based on the number of deletions
                if (enable2 < 3) {
                    button2.setEnabled(true);
                } else {
                    button2.setEnabled(false);
                }
            }
        });

// Add the second row to the right button panel
        rightButtonPanel.add(row2);


            // Declare a new JButton named button3
        JButton button3 = new JButton();

// Set the font and text of button3
        button3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button3.setText("Αναδιάταξη ταμπλό");

// Add button3 to row3 
        row3.add(button3);

// Declare a new JLabel named rearrangement and set the text to "0/5" with right alignment
        rearrangement = new JLabel("0/5", JLabel.RIGHT);

// Add rearrangement to row3
        row3.add(rearrangement);

// Declare a new JLabel named gap_r and set the text to a string of spaces
        JLabel gap_r = new JLabel("                              ");

// Add gap_r to row3
        row3.add(gap_r);

// Declare and initialize variables enable3 and num3 to 0 and 1 respectively
        enable3 = 0;
        num3 = 1;

// Add an ActionListener to button3
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Declare and initialize an ArrayList named shuffle
                ArrayList<String> shuffle = new ArrayList<>();

                // Add the text of each tile to shuffle
                for (int row = 0; row < 10; row++) {
                    for (int col = 0; col < 10; col++) {
                        shuffle.add(tiles[row][col].getText());
                    }
                }

                // Create a new random object and shuffle the elements in shuffle
                Random rand = new Random();
                Collections.shuffle(shuffle, rand);

                // Iterate through each tile and set the text to the shuffled value
                int i = 0;
                for (int row = 0; row < 10; row++) {
                    for (int col = 0; col < 10; col++) {
                        tiles[row][col].setText(FrameManipulation.fill(i, shuffle));
                        i++;
                    }
                }
// Iterate through all the buttons in the "tiles" array 
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                // if the button text is "?"
                if ("?".equals(tiles[row][col].getText())) {
                    //create a new button and set it as the current button in the array
                    final JButton button1 = tiles[row][col];
                    //add an action listener to the button that opens a new JFrame when clicked
                    button1.addActionListener((ActionEvent e) -> {
                        JFrame frame = new JFrame("Επίλεξε γράμμα");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        //set the layout of the frame to flow layout
                        frame.setLayout(new FlowLayout());

                        // Create the new JPanel and JTextField
                        JPanel inputPanel = new JPanel();
                        JTextField inputField = new JTextField(10);
                        inputPanel.add(inputField);
                        // Create the submit button
                        JButton submitButton = new JButton("Οκ");
                        inputPanel.add(submitButton);

                        // Add the inputPanel and inputField to the frame
                        frame.add(inputPanel);
                        frame.pack();
                        frame.setVisible(true);
                        // Make the inputPanel and inputField visible
                        inputPanel.setVisible(true);
                        inputField.setVisible(true);

                        // Add an action listener to the submit button
                        submitButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                // Get the text from the inputField
                                String inputText = inputField.getText();
                                inputText = inputText.toUpperCase();
                                // Set the text of the button to the inputText
                                button1.setText(FrameManipulation.fill_question_mark(inputText));
                                check_word.add(inputText);

                                // Make the inputPanel and inputField not visible
                                inputPanel.setVisible(false);
                                inputField.setVisible(false);
                                frame.setVisible(false);

                            }
                        });

                    });

                }
            }
        }
                // Update the text of rearrangement to the current number of shuffles
                rearrangement.setText(Integer.toString(num3) + "/5");
                num3++;

                // Increment the enable3 variable
                enable3++;

                // If the number of shuffles is less than 5, enable the button
                if (enable3 < 5) {
                    button3.setEnabled(true);
                } // Otherwise, disable the button
                else {
                    button3.setEnabled(false);
                }
            }
        });

// Add row3 to the rightButtonPanel
        rightButtonPanel.add(row3);

            // Creating a new JButton object
JButton button4 = new JButton();

// Setting the font and text for the button
button4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
button4.setText("Αναδιάταξη στήλης");

// Adding the button to the 4th row of the right button panel
row4.add(button4);

// Creating a new JTextField object for the rear column
rear_col= new JTextField(1);
row4.add(rear_col);

// Creating a new JLabel object to display the number of times the button has been clicked
r_column = new JLabel("0/3", JLabel.RIGHT);
row4.add(r_column);   

// Creating a new JLabel object to act as a gap filler
JLabel gap_rc = new JLabel("                        ");
row4.add(gap_rc); 

// Initializing variables to keep track of button clicks and enable status
enable4=0;
num4=1;

// Adding an action listener to the button
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // Getting the value of the rear column from the text field
                int col = Integer.parseInt(rear_col.getText());
                // Creating an ArrayList to store the values of the tiles in the specified column
                ArrayList<String> shuffle_col = new ArrayList<>();
                // Adding the values of the tiles in the specified column to the ArrayList
                for (int row = 0; row < 10; row++) {
                    shuffle_col.add(tiles[row][col].getText());
                }
                // Creating a new Random object
                Random rand = new Random();
                // Shuffling the ArrayList
                Collections.shuffle(shuffle_col, rand);
                // Setting the shuffled values back to the tiles in the specified column
                int i = 0;
                for (int row = 0; row < 10; row++) {
                    tiles[row][col].setText(FrameManipulation.fill(i, shuffle_col));
                    i++;
                }

                // Updating the label to display the number of times the button has been clicked
                r_column.setText(Integer.toString(num4) + "/3");
                num4++;
                enable4++;
                // Checking if the button has been clicked less than 3 times
                if (enable4 < 3) {
                    // If yes, enabling the button
                    button4.setEnabled(true);
                } else {
                    // If no, disabling the button
                    button4.setEnabled(false);

                }
            }
        });
// Adding the row4 to the right button panel
        rightButtonPanel.add(row4);


        // Creating a new JButton object
        JButton button5 = new JButton();

// Setting the font and text for the button
        button5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        button5.setText("Αναδιάταξη γραμμής");

// Adding the button to the 5th row of the right button panel
        row5.add(button5);

// Creating a new JTextField object for the rear row
        rear_row = new JTextField(1);
        row5.add(rear_row);

// Creating a new JLabel object to display the number of times the button has been clicked
        r_row = new JLabel("0/3", JLabel.RIGHT);
        row5.add(r_row);

// Creating a new JLabel object to act as a gap filler
        JLabel gap_rr = new JLabel("                    ");
        row5.add(gap_rr);

// Initializing variables to keep track of button clicks and enable status
        enable5 = 0;
        num5 = 1;

// Adding an action listener to the button
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Getting the value of the rear row from the text field
                int row = Integer.parseInt(rear_row.getText());
                // Creating an ArrayList to store the values of the tiles in the specified row
                ArrayList<String> shuffle_row = new ArrayList<>();
                // Adding the values of the tiles in the specified row to the ArrayList
                for (int col = 0; col < 10; col++) {
                    shuffle_row.add(tiles[row][col].getText());
                }
                // Creating a new Random object
                Random rand = new Random();
                // Shuffling the ArrayList
                Collections.shuffle(shuffle_row, rand);
                // Setting the shuffled values back to the tiles in the specified row
                int i = 0;
                for (int col = 0; col < 10; col++) {
                    tiles[row][col].setText(FrameManipulation.fill(i, shuffle_row));
                    i++;
                }

                // Updating the label to display the number of times the button has been clicked
                r_row.setText(Integer.toString(num5) + "/3");
                num5++;
                enable5++;
                // Checking if the button has been clicked less than 3 times
                if (enable5 < 3) {
                    // If yes, enabling the button
                    button5.setEnabled(true);
                } else {
                    // If no, disabling the button
                    button5.setEnabled(false);

                }
            }
        });
// Adding the row5 to the right button panel
        rightButtonPanel.add(row5);

      
            goal = new JLabel("Στόχος:", JLabel.LEFT);
        row6.add(goal);
        JLabel gap1 = new JLabel("                                                                                           ");
        row6.add(gap1);
        g_points = new JLabel("100", JLabel.RIGHT);
        row6.add(g_points);
        rightButtonPanel.add(row6);

        total = new JLabel("Συνολική Βαθμολογία:", JLabel.LEFT);
        row7.add(total);
        JLabel gap2 = new JLabel("                                                                     ");
        row7.add(gap2);
        row7.add(total_points);
        rightButtonPanel.add(row7);

        word = new JLabel("Βαθμολογία Λέξης:", JLabel.LEFT);
        row8.add(word);
        JLabel gap3 = new JLabel("                                                                            ");
        row8.add(gap3);
        row8.add(word_points);
        rightButtonPanel.add(row8);

        find = new JLabel("Λέξεις που βρέθηκαν:", JLabel.LEFT);
        row9.add(find);
        JLabel gap4 = new JLabel("                                                                       ");
        row9.add(gap4);
        row9.add(find_words);
        rightButtonPanel.add(row9);
        congrats = new JLabel("Συγχρητήρια βρήκες τη λέξη:", JLabel.LEFT);
        row10.add(congrats);
        row10.add(con_words);
        rightButtonPanel.add(row10);

        add(rightButtonPanel);
    }

    // Overriding the actionPerformed method
    @Override
    public void actionPerformed(ActionEvent e) {
        // Getting the source of the event (the button that was clicked)
        JButton button = (JButton) e.getSource();
        // Checking if the button's text is empty
        if (button.getText().isEmpty()) {
            // If the button's text is empty, prompt the user to enter a letter
            String letter = JOptionPane.showInputDialog(this, "Enter a letter:");
            // Setting the button's text to the letter entered by the user
            button.setText(letter);
            // Disabling the button so it can't be clicked again
            button.setEnabled(false);
            // Changing the font of the button to Arial and making it bold
            button.setFont(new Font("Arial", Font.BOLD, 24));
        } else {
            // If the button's text is not empty, show a message saying the tile is already used
            JOptionPane.showMessageDialog(this, "Tile already used!");
        }
    }

// Creating an inner class to handle button clicks
    private class ButtonListener implements ActionListener {
        // Variables to store the coordinates of the button that was clicked

        private final int x;
        private final int y;

        public ButtonListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // Looping through all the buttons in the tiles array
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    // Checking if the button is within a 1x1 area around the button that was clicked
                    if ((Math.abs(i - x) <= 1 && Math.abs(j - y) <= 1) && !(i == x && j == y)) {
                        // If the button is within the area, enable it
                        tiles[i][j].setEnabled(true);
                    } else {
                        // If the button is not within the area, disable it
                        tiles[i][j].setEnabled(false);
                    }
                }
            }

        }
    }
}

