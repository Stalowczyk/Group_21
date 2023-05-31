package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.*;
import javax.swing.JLabel;
import main.GamePanel;

public class Shelf {
    //forse bisogna cambiare card con placed card*****************

    public PlacedCard[][] shelfLayout;
    private int maxRow = 6;
    // private int chosenColumn;	
    private int points;
    public static int boardsFilled = 0;
    private int row;
    private int col;
    GamePanel gp;
    PersonalGoalsCards pe;
    private boolean playerTurn;
    ArrayList<PlacedCard> copiedCards;
    String playerName;

    public Shelf(GamePanel gp, String playerName) {
        this.shelfLayout = new PlacedCard[6][5];
        this.playerName = "Current players turn: " + playerName;
        this.points = 0;
        this.gp = gp;
        this.pe = new PersonalGoalsCards(gp.playerCount, this.gp);
        this.playerTurn = false;

    }
    
    public int getPersonalGoalCardPoints(){
        return this.pe.score(this);
    }
    
    public int getPoints() {
        return points;
    }

    public boolean getPlayerTurn() {
        return this.playerTurn;
    }

    public void setPlayerTurn(boolean b) {
        this.playerTurn = b;
    }

    public void addPoints(int amount) {
        points += amount;
    }

    public PlacedCard getCard(int row, int column) {
        if(shelfLayout[row][column]==null)
            return null;
        else
            return shelfLayout[row][column];
    }

    public void placeOnShelf(ArrayList<PlacedCard> chosenCards, int chosenCol) {
        int startPoint = 0;
        int c = 0;
        int cardNumber = chosenCards.size();            //il numero di carte da inserire nella shelf
        while (c <= 5 && shelfLayout[c][chosenCol] != null) {        //trovo il punto da cui si inizia a mettere le card
            startPoint++;
            c++;
        }

        if ((maxRow - startPoint) >= cardNumber) {        //se hai abbastanza spazio nella shelf
            for (int i = 0; i < cardNumber; i++) {
                PlacedCard p = chosenCards.get(i);
                PlacedCard test = new PlacedCard(p.getCardType(), p.getCardRow(), p.getCardCol(), gp);
                test.setRow(chosenCol);
                test.setCol(5-startPoint - i);
                shelfLayout[startPoint + i][chosenCol] = test;

            }

        }
    }

    public boolean isColumnAvailable(ArrayList<PlacedCard> chosenCards, int chosenCol) {		//BISOGNA PASSARGLI ANCHE CHOSENCOLUMN
        int startPoint = 0;
        int c = 0;
        if (chosenCards != null) {
            while (c <= 5 && shelfLayout[c][chosenCol] != null) {
                startPoint++;
                c++;
                //System.out.println(c);
                //System.out.println(chosenCol);
            }
            if ((maxRow - startPoint) >= chosenCards.size()) {		//se hai abbastanza spazio nella shelf
                return true;
            }
        }
        return false;
    }

    /*
    In questi metodi fatti molto meglio utilizzo spesso la funzione .stream quindi la spiego velocemente.
    Esegue di fila tutte le azioni che ho scritto, quindi per ordine:
    -Per ogni elemento dell'arraylist prende il CardType (.map(Card::getCardType)
    -Elimina risultati uguali/doppioni (.distinct)
    -Conta quanti CardType diversi ci sono (.count)

    Quindi alla fine il .stream mi ritorna un Int del numero di CardType.
    Sfrutto questo dato in un valore booleano per fare 3 controlli principali:
    - int == 1 se tutti gli elementi hanno lo stesso cardtype.
    - int == array.size se tutti gli elementi hanno cardtype diversi.
    - int >= 3/4 negli obiettivi dove ci deve essere un numero massimo di cardType.

    In questo modo elimino la maggiorparte del codice che c'era prima
     */
    public boolean checkCorners() {
        List<Card> corner = new ArrayList<Card>();
        for (int i = 0; i <= 5; i = i + 5) {
            for (int j = 0; j <= 4; j = j + 4) {
                corner.add(this.shelfLayout[i][j]);
                System.out.println("prova");
            }
        }
        return !corner.contains(null) && corner.stream().map(Card::getCardType).distinct().count() == 1;
    }

    public boolean checkCardCount() {                                         // Controlla tutti gli elementi della shelf
        Map<CardType, Integer> cardCounts = new HashMap<>();
        for (int i = 0; i < this.shelfLayout.length; i++) {
            for (int j = 0; j < this.shelfLayout[i].length; j++) {
                Card card = this.shelfLayout[i][j];                          // Se trova una card, prende il suo cardtype e lo memorizza nella hashmap
                if (card != null) {
                    CardType type = card.getCardType();
                    int count = cardCounts.getOrDefault(type, 0);
                    cardCounts.put(type, count + 1);
                }
            }
        }
        for (int count : cardCounts.values()) {                              // Controlla se nella hashmap ci sono almeno 8 cardtype uguali
            if (count >= 8) {                                                // Serve per secondo obbiettivo
                return true;
            }
        }
        return false;
    }

    public boolean checkXShape() {
        for (int i = 1; i < this.shelfLayout.length - 1; i++) {
            for (int j = 1; j < this.shelfLayout[i].length - 1; j++) {
                List<Card> X = new ArrayList<Card>();
                X.add(this.shelfLayout[i][j]);
                for (int k = i - 1; k <= i + 1; k = k + 2) {
                    for (int l = j - 1; l <= j + 1; l = l + 2) {
                        X.add(this.shelfLayout[k][l]);
                    }
                }
                if (!X.contains(null) && X.stream().map(Card::getCardType).distinct().count() == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkColumnGoal() {
        int Columns = 0;
        for (int j = 0; j < this.shelfLayout[5].length; j++) {
            List<Card> col = new ArrayList<Card>();
            for (int k = 0; k < 6; k++) {
                col.add(this.shelfLayout[5 - k][j]);
            }
            if (!col.contains(null) && col.stream().map(Card::getCardType).distinct().count() == col.size()) {
                Columns++;
            }
        }
        return Columns >= 2;
    }

    public boolean checkRowGoal() {
        int Rows = 0;
        for (int i = 0; i < this.shelfLayout.length; i++) {
            List<Card> row = new ArrayList<Card>();
            for (int k = 0; k < 5; k++) {
                row.add(this.shelfLayout[i][4 - k]);
            }
            if (!row.contains(null) && row.stream().map(Card::getCardType).distinct().count() == row.size()) {
                Rows++;
            }
        }
        return Rows >= 2;
    }

    public boolean checkColumnSecondGoal() {
        int Columns = 0;
        for (int j = 0; j < this.shelfLayout[5].length; j++) {
            List<Card> col = new ArrayList<Card>();
            for (int k = 0; k < 6; k++) {
                col.add(this.shelfLayout[5 - k][j]);
            }
            if (!col.contains(null) && col.stream().map(Card::getCardType).distinct().count() <= 3) {
                Columns++;
            }
        }
        return Columns >= 3;
    }

    public boolean checkRowSecondGoal() {
        int Rows = 0;
        for (int i = 0; i < this.shelfLayout.length; i++) {
            List<Card> row = new ArrayList<Card>();
            for (int k = 0; k < 5; k++) {
                row.add(this.shelfLayout[i][4 - k]);
            }
            if (!row.contains(null) && row.stream().map(Card::getCardType).distinct().count() <= 3) {
                Rows++;
            }
        }
        return Rows >= 4;
    }

    public boolean checkDiagonal() {
        boolean Diagonal1 = false;
        boolean Diagonal2 = false;
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 4; j = j + 4) {
                if (j == 0) {
                    List<Card> diagonal = new ArrayList<Card>();
                    for (int k = 0; k < 5; k++) {
                        diagonal.add(this.shelfLayout[i + k][j + k]);
                    }
                    if (!diagonal.contains(null) && diagonal.stream().map(Card::getCardType).distinct().count() == 1) {
                        Diagonal1 = true;
                    }
                }
                if (j == 4) {
                    List<Card> diagonal = new ArrayList<Card>();
                    for (int k = 0; k < 5; k++) {
                        diagonal.add(this.shelfLayout[i + k][j - k]);
                    }
                    if (!diagonal.contains(null) && diagonal.stream().map(Card::getCardType).distinct().count() == 1) {
                        Diagonal2 = true;
                    }
                }
            }
        }
        return Diagonal1 || Diagonal2;
    }

    public boolean checkTower() {
        boolean Tower1 = true;
        boolean Tower2 = true;
        for (int row = 5; row >= 1; row--) {
            for (int col = row - 1; col >= 0; col--) {
                Card card = this.shelfLayout[row][col];
                if (card == null) {
                    Tower1 = false;
                    break;
                }
            }
        }
        for (int row = 5; row >= 1; row--) {
            for (int col = 5 - row; col <= 4; col++) {
                Card card = this.shelfLayout[row][col];
                if (card == null) {
                    Tower2 = false;
                    break;
                }
            }
        }
        return Tower1 || Tower2;
    }

    public boolean checkCube() {

        List<Card> blacklistedCards = new ArrayList<Card>();
        int Cubi = 0;
        for (int i = 1; i < this.shelfLayout.length; i++) {
            for (int j = 0; j < this.shelfLayout[i].length - 1; j++) {
                List<Card> cubo = new ArrayList<Card>();
                for (int k = i; k >= i - 1; k--) {
                    for (int l = j; l <= j + 1; l++) {
                        cubo.add(this.shelfLayout[k][l]);
                    }
                }
                if (!cubo.contains(null) && !containsAny(cubo, blacklistedCards)
                        && cubo.stream().map(Card::getCardType).distinct().count() == 1) {
                    blacklistedCards.addAll(cubo);
                    Cubi++;
                }
            }
        }
        return Cubi >= 2;
    }

    public boolean checkCoppie() {
        List<Card> blacklistedCards = new ArrayList<Card>();
        int Coppie = 0;
        for (int i = 0; i < this.shelfLayout.length; i++) {
            for (int j = 0; j < this.shelfLayout[i].length; j++) {
                if (i < 5) {
                    List<Card> coppia = new ArrayList<Card>();
                    for (int k = 0; k <= 1; k++) {
                        coppia.add(this.shelfLayout[i + k][j]);
                    }
                    if (!coppia.contains(null) && !containsAny(coppia, blacklistedCards)
                            && coppia.stream().map(Card::getCardType).distinct().count() == 1) {
                        blacklistedCards.addAll(coppia);
                        Coppie++;
                    }
                }
                if (j < 4) {
                    List<Card> coppia = new ArrayList<Card>();
                    for (int k = 0; k <= 1; k++) {
                        coppia.add(this.shelfLayout[i][j + k]);
                    }
                    if (!coppia.contains(null) && !containsAny(coppia, blacklistedCards)
                            && coppia.stream().map(Card::getCardType).distinct().count() == 1) {
                        blacklistedCards.addAll(coppia);
                        Coppie++;
                    }
                }

            }
        }
        return Coppie >= 6;
    }

    public boolean checkTetris() {                                              // Sfrutta il metodo bfs2 e aggiunge a un arraylist tutti i gruppi da 4 carte
        boolean[][] visited = new boolean[6][5];
        List<Integer> Tetris = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    Card card = this.shelfLayout[i][j];
                    if (card != null) {
                        int groupSize = bfs2(visited, i, j, card);
                        if (groupSize == 4) {
                            Tetris.add(groupSize);
                        }
                    }
                }
            }
        }
        return Tetris.size() >= 4;
    }

    public void findCardGroups() {
        boolean[][] visited = new boolean[6][5];
        List<Integer> groupSizes = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j]) {
                    Card card = this.shelfLayout[i][j];
                    if (card != null) {
                        int groupSize = bfs(visited, i, j, card);
                        if (groupSize > 2) {
                            groupSizes.add(groupSize);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < groupSizes.size(); i++) {
            switch (groupSizes.get(i)) {
                case 3 ->
                    this.addPoints(2);
                case 4 ->
                    this.addPoints(3);
                case 5 ->
                    this.addPoints(5);
                case 6 ->
                    this.addPoints(8);
                default ->
                    this.addPoints(8);
            }
        }
    }

    private int bfs(boolean[][] visited, int row, int col, Card card) {    //controlla i group
        int groupSize = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            if (r < 0 || r >= 6 || c < 0 || c >= 5 || visited[r][c]) {
                continue;
            }
            Card currCard = shelfLayout[r][c];
            if (currCard == null || currCard.getCardType() != card.getCardType()) {
                continue;
            }
            visited[r][c] = true;
            groupSize++;
            queue.offer(new int[]{r - 1, c});
            queue.offer(new int[]{r + 1, c});
            queue.offer(new int[]{r, c - 1});
            queue.offer(new int[]{r, c + 1});
        }
        return groupSize;
    }

    private int bfs2(boolean[][] visited, int row, int col, Card card) {                 // Simile al bfs utilizzato per trovare gruppi
        int groupSize = 0;                                                               // Unica differenza che si ferma quando il gruppo di carte arriva a 4
        Queue<int[]> queue = new LinkedList<>();                                         // Serve per trovare tutti i "Tetris" possibili
        queue.offer(new int[]{row, col});
        while (!queue.isEmpty() && groupSize < 4) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            if (r < 0 || r >= 6 || c < 0 || c >= 5 || visited[r][c]) {
                continue;
            }
            Card currCard = shelfLayout[r][c];
            if (currCard == null || currCard.getCardType() != card.getCardType()) {
                continue;
            }
            visited[r][c] = true;
            groupSize++;
            queue.offer(new int[]{r - 1, c});
            queue.offer(new int[]{r, c - 1});
            queue.offer(new int[]{r, c + 1});
            queue.offer(new int[]{r + 1, c});
        }
        return groupSize;
    }

    public static boolean containsAny(List<Card> list1, List<Card> list2) {             // Metodo che serve a alcuni obiettivi per controllare le carte già utilizzate
        for (Card card : list1) {                                                       // Confronta 2 arraylist e ritorna true se la prima contiene almeno un elemento della seconda
            if (list2.contains(card)) {                                                 // Utilizzato per vedere se una possibile forma di carte contiene delle carte gia usate in altre forme (checkCube,checkCoppie)
                return true;
            }
        }
        return false;
    }

    public boolean isShelfFilled() {
        boolean board = true;
        for (int i = 0; i < this.shelfLayout.length; i++) {
            for (int j = 0; j < this.shelfLayout[i].length; j++) {
                if (this.shelfLayout[i][j] == null) {
                    board = false;
                    break;
                }
            }
        }
        if (boardsFilled == 0 && board) {
            this.addPoints(1);
            boardsFilled++;
            return true;
        } else {
            return false;
        }

    }

    public void draw(Graphics2D g2) {
        if (this.playerTurn == true) {
            for (int row = 0; row < this.shelfLayout.length; row++) {
                for (int col = 0; col < this.shelfLayout[row].length; col++) {
                    PlacedCard p = this.shelfLayout[row][col];
                    if (p != null) {
                        switch (p.getCardType()) {
                            case WHITE -> {
                                g2.setColor(Color.white);
                                g2.fillRect(576 + p.getCardX(), 48+p.getCardY(), 48, 48);
                                g2.setColor(Color.BLACK);
                                g2.drawRect(576 + p.getCardX(), 48+p.getCardY(), 48, 48);
                            }
                            case PINK -> {
                                g2.setColor(Color.PINK);
                                g2.fillRect(576 + p.getCardX(), 48+ p.getCardY(), 48, 48);
                                g2.setColor(Color.BLACK);
                                g2.drawRect(576 + p.getCardX(), 48+p.getCardY(), 48, 48);
                            }
                            case BLUE -> {
                                g2.setColor(Color.BLUE);
                                g2.fillRect(576 + p.getCardX(), 48+ p.getCardY(), 48, 48);
                                g2.setColor(Color.BLACK);
                                g2.drawRect(576 + p.getCardX(), 48+ p.getCardY(), 48, 48);
                            }
                            case CYAN -> {
                                g2.setColor(Color.CYAN);
                                g2.fillRect(576 + p.getCardX(), 48+ p.getCardY(), 48, 48);
                                g2.setColor(Color.BLACK);
                                g2.drawRect(576 + p.getCardX(), 48+ p.getCardY(), 48, 48);
                            }
                            case GREEN -> {
                                g2.setColor(Color.GREEN);
                                g2.fillRect(576 + p.getCardX(), 48+ p.getCardY(), 48, 48);
                                g2.setColor(Color.BLACK);
                                g2.drawRect(576 + p.getCardX(), 48+ p.getCardY(), 48, 48);
                            }
                            case YELLOW -> {
                                g2.setColor(Color.YELLOW);
                                g2.fillRect(576 + p.getCardX(), 48+ p.getCardY(), 48, 48);
                                g2.setColor(Color.BLACK);
                                g2.drawRect(576 + p.getCardX(), 48+ p.getCardY(), 48, 48);
                            }
                        }
                    }
                    g2.setColor(Color.BLACK);
                    g2.drawRect(576 + col * 48, 48 + row * 48, 48, 48);

                }
            }
            Font currentFont = new Font("Times New Roman", Font.BOLD, 28);
            g2.setFont(currentFont);

            g2.drawString(this.playerName, 576, 384);
            pe.draw(g2);
        }
    }
    
    
    public void printOutArray(){
         for (int row = 0; row < this.shelfLayout.length; row++) {
                for (int col = 0; col < this.shelfLayout[row].length; col++) {
                    PlacedCard p = this.shelfLayout[row][col];
                    if(p!=null){
                        
                    System.out.println("SHELF LAYOUT ROW AND COL "+p.getCardRow()+" "+ p.getCardCol());
                    }
                }
         }
         printAllPersonalGoal();
    }
    
    public void printAllPersonalGoal(){
        pe.printOutAll();
    }

}
