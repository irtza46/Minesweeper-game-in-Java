import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public final class PlayForm extends JFrame {

    private final int boardSize, numOfMines;
    JButton[][] buttons;
    int time = 0;
    Timer timer;
    ArrayList<Integer> bombsLocation;
    boolean win = true;
    
    /**
     * Creates new form PlayForm.
     * @param boardSize size of the board
     * @param numOfMines mines in the board
     */
    public PlayForm(int boardSize, int numOfMines) {
        this.boardSize = boardSize;
        this.numOfMines = numOfMines;
        initComponents();
        intiailizeBoard();
        setBombs();
        setValues();
        initializeTimer();
        pack();
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        text = new javax.swing.JLabel();
        board = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mine Sweeper");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        text.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        text.setForeground(new java.awt.Color(255, 0, 0));
        text.setText("Time: 0");

        javax.swing.GroupLayout boardLayout = new javax.swing.GroupLayout(board);
        board.setLayout(boardLayout);
        boardLayout.setHorizontalGroup(
            boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        boardLayout.setVerticalGroup(
            boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 258, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 272, Short.MAX_VALUE)
                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(text, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This method starts and keeps track of the timer
     */
    private void initializeTimer() {
        this.timer = new Timer(1000, (ActionEvent ae) -> {
            text.setText("Time: " + ++time);
            // if time is 60 sec or 3 mins or 10 mins
            if(time == 60 || time == 180 || time == 600)
                gameOver();
            checkWin();
        });
        // start timer
        this.timer.start();
    }
    
    /**
     * This method initializes the board.
     */
    private void intiailizeBoard() {
        // setting board layout
        board.setLayout(new GridLayout(boardSize, boardSize, -4, -5));
        
        // intializing buttons
        buttons = new JButton[this.boardSize][boardSize];
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                buttons[i][j] = new JButton("    ");
                buttons[i][j].setIconTextGap(0);
                board.add(buttons[i][j]);

                // adding mouse listener
                buttons[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent me) {
                        buttonClickCheck(me);
                    }
                });
            }
        }
    }  

    /**
     * This method stores the bombs randomly in the board according to difficulty level.
     */
    public void setBombs() {
        // generating distinct random locations for bombs
        bombsLocation = new ArrayList<>();
        int iter = 0;
        while(iter < this.numOfMines) {
            int random = (int) (Math.random() * (this.boardSize * this.boardSize));
            if(!bombsLocation.contains(random)) {
                bombsLocation.add(random);
                iter++;
            }
        }
        // storing bombs
        iter = 0;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if(bombsLocation.contains(iter++))
                    buttons[i][j].setName("B");
            }
        }
    }
    
    /**
     * This method count number of bombs around a button/cell and set values.
     */
    public void setValues() {
        int temp = 0;
        // store the number of adjacent bomb with a paticular button/cell
        for(int i = 0; i < this.boardSize; i++) {
            for(int j = 0; j < this.boardSize; j++) {
                if(!bombsLocation.contains(temp++)) {
                    int count = 0;
                    if(j >= 1 && "B".equals(buttons[i][j - 1].getName()))
                        count++;
                    if(j < this.boardSize - 1 && "B".equals(buttons[i][j + 1].getName()))
                        count++;
                    if(i >= 1 && "B".equals(buttons[i - 1][j].getName()))
                        count++;
                    if(i < this.boardSize - 1 && "B".equals(buttons[i + 1][j].getName()))
                        count++;
                    if(i >= 1 && j >= 1 && "B".equals(buttons[i - 1][j - 1].getName()))
                        count++;
                    if(i < this.boardSize - 1 && j < this.boardSize - 1 && "B".equals(buttons[i + 1][j + 1].getName()))
                        count++;
                    if(i >= 1 && j < this.boardSize - 1 && "B".equals(buttons[i - 1][j + 1].getName()))
                        count++;
                    if(i < this.boardSize - 1 && j >= 1 && "B".equals(buttons[i + 1][j - 1].getName()))
                        count++;
                    buttons[i][j].setName("" + count);
                }
            }
        }
    }
    
    void buttonClickCheck(MouseEvent me) {
        // getting button that was clicked
        JButton temp = (JButton) me.getSource();
        
        // if left button was clicked
        if(me.getButton() == MouseEvent.BUTTON1 && temp.getIconTextGap() == 0) {
            
            // if button is not clicked before
            if("    ".equals(temp.getText())) {
                temp.setEnabled(false);
                temp.setText(temp.getName());
                // finding the index of clicked button
                int i = -1, j = -1;
                for (int k = 0; k < this.boardSize; k++) {
                    for (j = 0; j < this.boardSize; j++) {
                        if(buttons[k][j] == temp) {
                            i = k;
                            break;
                        }
                    }
                    if(i != -1)
                        break;
                }
                // checking for any adjacent empty button/cells
                if("0".equals(temp.getName()))
                    checkAdjacent(i, j);
            }
            
            // if bomb is under this button
            if("B".equals(temp.getName())) {
                temp.setEnabled(false);
                this.timer.stop();
                this.win = false;
                gameOver();
            }            
        }
        
        // if right button is clicked
        else if(me.getButton() == MouseEvent.BUTTON3) {
            switch (temp.getIconTextGap()) {
                case 0:
                    temp.setText("");
                    temp.setIcon(new ImageIcon(PlayForm.class.getResource("flag.png")));
                    temp.setIconTextGap(1);
                    break;
                default:
                    temp.setText("    ");
                    temp.setIcon(null);
                    temp.setIconTextGap(0);
                    break;
            }
        }
    }
    
    /**
     * This method checks the adjacent buttons/cells of a particular button/cell and checks them as visited.
     * @param i row location of button
     * @param j column location of button
     */
    public void checkAdjacent(int i, int j) {
        if(i < this.boardSize && i >= 0) {
            if(j < this.boardSize && j >= 0) {
                if(j >= 1 && "0".equals(buttons[i][j - 1].getName()) && !"0".equals(buttons[i][j - 1].getText())) {
                    buttons[i][j - 1].setEnabled(false);
                    buttons[i][j - 1].setText("0");
                    checkAdjacent(i, j - 1);
                }
                if(j < this.boardSize - 1 && "0".equals(buttons[i][j + 1].getName()) && !"0".equals(buttons[i][j + 1].getText())) {
                    buttons[i][j + 1].setEnabled(false);
                    buttons[i][j + 1].setText("0");
                    checkAdjacent(i, j + 1);
                }
                if(i >= 1 && "0".equals(buttons[i - 1][j].getName()) && !"0".equals(buttons[i - 1][j].getText())) {
                    buttons[i - 1][j].setEnabled(false);
                    buttons[i - 1][j].setText("0");
                    checkAdjacent(i - 1, j);
                }
                if(i < this.boardSize - 1 && "0".equals(buttons[i + 1][j].getName()) && !"0".equals(buttons[i + 1][j].getText())) {
                    buttons[i + 1][j].setEnabled(false);
                    buttons[i + 1][j].setText("0");
                    checkAdjacent(i + 1, j);
                }
            }
        }
    }
    
    /**
     * This method explodes the bombs if you loose.
     */
    private void explodeBombs() {
        // play the explode sound
        AudioClip click = Applet.newAudioClip(PlayForm.class.getResource("bombExplosion.wav"));
        click.play();
        // dislplays and explodes all the bombs
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                // if bomb
                if("B".equals(buttons[i][j].getName()) && buttons[i][j].getIconTextGap() == 0) {
                    buttons[i][j].setText("");
                    buttons[i][j].setEnabled(false);
                    buttons[i][j].setIcon(new ImageIcon(PlayForm.class.getResource("bombExplode.gif")));
                }
                // if mark was wrong (bomb wasn't under right clicked button
                if(buttons[i][j].getIconTextGap() == 1 && !"B".equals(buttons[i][j].getName())) {
                    buttons[i][j].setText("");
                    buttons[i][j].setIcon(new ImageIcon(PlayForm.class.getResource("cross.png")));
                    buttons[i][j].setIconTextGap(1);
                }
            }
        }
    }

    /**
     * This method checks if the time is over or game is lose.
     */
    private void gameOver() {
        if(!win || ((boardSize == 8 && time == 60) || (boardSize == 16 && time == 180) || (boardSize == 24 && time == 600))) {
            explodeBombs();
            JOptionPane.showMessageDialog(this, "Game over! You loose.");
            this.dispose();
        }
    }
    
    /**
     * This method checks if all the non-bomb buttons/cells are clicked.
     */
    private void checkWin() {
        int count = 0;
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if(!"B".equals(buttons[i][j].getName()) && !"    ".equals(buttons[i][j].getText()))
                    count++;
            }
        }
        // if all the buttons that are not bombs are clicked
        if(count == (this.boardSize * this.boardSize) - this.numOfMines) {
            JOptionPane.showMessageDialog(this, "You win.");
            this.dispose();
        }
    }

    /**
     * This method is called when the game play form is closed.
     * @param evt Event that triggered this method
     */
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.dispose();
        this.timer.stop();
        new MenuForm().setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel board;
    private javax.swing.JLabel text;
    // End of variables declaration//GEN-END:variables
}