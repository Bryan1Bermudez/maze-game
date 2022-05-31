package apcs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
//is in charge of the game while not in combat
public class Exploration {
    //the level the map and the player varibles are well for well i mean its self explanatory.
    private int level;
    private Map map;
    private  Player player;

    //what gets this displayed on screen such as the board storing everying
    private JFrame Board;
    private JPanel game;
    private JPanel counter;

    //buttons used for player input foward is to move foward, right is for right ,etc ,etc and next level is to go to the next level
    private JButton foward;
    private JButton right;
    private JButton left;
    private JButton NextLevel;

    //what is displayed
    private JLabel display;

    //for easer accses of the pngs
    private ImageIcon level1_foward = new ImageIcon(getClass().getResource("level1_straight.png"));
    private ImageIcon level1_3way = new ImageIcon(getClass().getResource("level1_3way.png"));
    private ImageIcon level1_right = new ImageIcon(getClass().getResource("level1_right.png"));
    private ImageIcon level1_left = new ImageIcon(getClass().getResource("level1_left.png"));
    private ImageIcon End = new ImageIcon(getClass().getResource("Exit.png"));

    private ImageIcon foward_ = new ImageIcon(getClass().getResource("Foward.png"));
    private ImageIcon right_ = new ImageIcon(getClass().getResource("Right.png"));
    private ImageIcon left_ = new ImageIcon(getClass().getResource("Left.png"));
    //the monsters you can encounter while exploring
    private Monster m1;
    private Monster m2;
    private Monster m3;


    //sets the varibles and the display up
    public Exploration(){
        level = 1;
        map = new Map();

        m1 = new Monster(level,1);
        m2 = new Monster(level,2);
        m3 = new Monster(level,3);

        player = new Player(map,level,m1,m2,m3);





        foward = new JButton("Foward");
        foward.add(new JLabel(foward_));

        right = new JButton("Right");
        right.add(new JLabel(right_));

        left = new JButton("left");
        left.add(new JLabel(left_));

        NextLevel = new JButton("Next level");

        Board = new JFrame();
        Board.setBackground(Color.GRAY);
        Board.setLayout(new GridLayout(2,1));
        Board.setSize( 1500, 1000);
        Board.setLocation(0,0);
        Board.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        game = new JPanel(new GridLayout(3,1,1,1));
        counter = new JPanel(new BorderLayout(1,1));
        counter.setSize(100,100);
        counter.setBackground(Color.GRAY);

        game.setSize(100,100);
        game.setBackground(Color.GRAY);
        Board.setResizable(false);
    }
    //the same as the last one expecpt that it dosent create a new player and monsters,levels and dead(used to check if monsters are dead.)
    public Exploration(Player play,Monster d1, Monster d2, Monster d3, int level_,int dead){








        level = level_;
        map = new Map();



        int temp = level;
        if(temp % 3 == 1 ){

            level1_foward = new ImageIcon(getClass().getResource("level1_straight.png"));
            level1_3way = new ImageIcon(getClass().getResource("level1_3way.png"));
            level1_right = new ImageIcon(getClass().getResource("level1_right.png"));
            level1_left = new ImageIcon(getClass().getResource("level1_left.png"));
        }

        temp = level;
        if(temp % 3 == 2 ){
            level1_foward = new ImageIcon(getClass().getResource("level2_straight.png"));
            level1_3way = new ImageIcon(getClass().getResource("level2_3way.png"));
            level1_right = new ImageIcon(getClass().getResource("level2_right.png"));
            level1_left = new ImageIcon(getClass().getResource("level2_left.png"));

        }
        temp = level;
        if(temp % 3 == 0 ){
            level1_foward = new ImageIcon(getClass().getResource("level3_straight.png"));
            level1_3way = new ImageIcon(getClass().getResource("level3_3way.png"));
            level1_right = new ImageIcon(getClass().getResource("level3_right.png"));
            level1_left = new ImageIcon(getClass().getResource("level3_left.png"));

        }





















        m1 = d1;
        m2 = d2;
        m3 = d3;
        if(dead==1){
            m1.ChangeCords(new int[]{ 0,0});
        }
        if(dead==2){
            m2.ChangeCords(new int[]{ 0,0});
        }
        if(dead==3){
            m3.ChangeCords(new int[]{ 0,0});
        }
        player = play;
        player.Changemon(d1,d2,d3);

        foward = new JButton("Foward");
        foward.add(new JLabel(foward_));

        right = new JButton("Right");
        right.add(new JLabel(right_));

        left = new JButton("left");
        left.add(new JLabel(left_));


        NextLevel = new JButton("Next level");
        //sets what the buttons will do needed as the buttons reset when using this decleration
        foward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3){
                    if(player.getDirection() == 0) {
                        player.Setxy(new int[]{player.Getxy()[0] - 1, player.Getxy()[1]});
                    }
                    else if(player.getDirection() == 90) {
                        player.Setxy(new int[]{player.Getxy()[0],player.Getxy()[1] + 1});
                    }
                    else if(player.getDirection() == -90) {
                        player.Setxy(new int[]{player.Getxy()[0] ,player.Getxy()[1] - 1});
                    }




                }
                player.move();
                Redraw();
            }
        });
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3){
                    player.Setxy(new int[]{player.Getxy()[0],player.Getxy()[1] + 1});
                }
                player.SetDirection(player.getDirection() + 90);
                player.move();
                Redraw();

            }
        });
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3 && (level % 3 ==1 || level % 3 ==0)){
                    player.Setxy(new int[]{player.Getxy()[0] ,player.Getxy()[1] - 1});
                }
                else if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3 && level % 3 ==2){
                    player.Setxy(new int[]{player.Getxy()[0] - 1 ,player.Getxy()[1]});
                }
                player.SetDirection(player.getDirection() - 90);
                player.move();
                Redraw();
            }
        });
        NextLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextlevel();
            }
        });












        Board = new JFrame();
        Board.setBackground(Color.GRAY);
        Board.setLayout(new GridLayout(2,1));
        Board.setSize( 1500, 1000);
        Board.setLocation(0,0);
        Board.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        game = new JPanel(new GridLayout(3,1,1,1));
        counter = new JPanel(new BorderLayout(1,1));
        counter.setSize(100,100);
        counter.setBackground(Color.GRAY);

        game.setSize(100,100);
        game.setBackground(Color.GRAY);
        Board.setResizable(false);
    }


    //retrunst the current map
    private int[][] getmap(){
        if(level % 3== 1){
            return map.getLevel1();


        }
        if(level % 3== 2){
            return map.getLevel2();


        }
        if(level % 3== 0){
            return map.getLevel3();


        }
        return null;
    }




    //starts the game and finaly shows the board
    public void start(){
        foward.addActionListener(new ActionListener() {
            @Override
            //sets up what the foward button will do, which will move the player foward
            public void actionPerformed(ActionEvent e) {
                if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3){
                    if(player.getDirection() == 0) {
                        player.Setxy(new int[]{player.Getxy()[0] - 1, player.Getxy()[1]});
                    }
                    else if(player.getDirection() == 90) {
                        player.Setxy(new int[]{player.Getxy()[0],player.Getxy()[1] + 1});
                    }
                    else if(player.getDirection() == -90) {
                        player.Setxy(new int[]{player.Getxy()[0] ,player.Getxy()[1] - 1});
                    }




                }
                player.move();
                    Redraw();
            }
        });
        //right moves right
        right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3){
                    player.Setxy(new int[]{player.Getxy()[0],player.Getxy()[1] + 1});
                }
                player.SetDirection(player.getDirection() + 90);
                player.move();
                Redraw();

            }
        });
        //left moves left
        left.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3 && (level % 3 ==1 || level % 3 ==0)){
                    player.Setxy(new int[]{player.Getxy()[0] ,player.Getxy()[1] - 1});
                }
                else if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3 && level % 3 ==2){
                    player.Setxy(new int[]{player.Getxy()[0] - 1 ,player.Getxy()[1]});
                }
                player.SetDirection(player.getDirection() - 90);
                player.move();
                Redraw();
            }
        });
        //next level is next level
        NextLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextlevel();
            }
        });
        game.add(foward);
        game.repaint();
        if(level == 1){
            display = new JLabel(level1_foward);}

        counter.add(new JLabel("Current level " + level),BorderLayout.PAGE_START);
        counter.add(game,BorderLayout.CENTER);
        Board.add(display).setLocation(1,0);
        Board.add(counter).setLocation(0,0);
        //sets it to true
        Board.setVisible(true);
    }
    //gets everyting ready for the next level such as changing the png increasing level by one.
    public void nextlevel(){
        level ++;
        int temp = level;
        if(temp % 3 == 1 ){

    level1_foward = new ImageIcon(getClass().getResource("level1_straight.png"));
    level1_3way = new ImageIcon(getClass().getResource("level1_3way.png"));
    level1_right = new ImageIcon(getClass().getResource("level1_right.png"));
    level1_left = new ImageIcon(getClass().getResource("level1_left.png"));
        }

         temp = level;
        if(temp % 3 == 2 ){
            level1_foward = new ImageIcon(getClass().getResource("level2_straight.png"));
            level1_3way = new ImageIcon(getClass().getResource("level2_3way.png"));
            level1_right = new ImageIcon(getClass().getResource("level2_right.png"));
            level1_left = new ImageIcon(getClass().getResource("level2_left.png"));

        }
         temp = level;
        if(temp % 3 == 0 ){
            level1_foward = new ImageIcon(getClass().getResource("level3_straight.png"));
            level1_3way = new ImageIcon(getClass().getResource("level3_3way.png"));
            level1_right = new ImageIcon(getClass().getResource("level3_right.png"));
            level1_left = new ImageIcon(getClass().getResource("level3_left.png"));

        }
        m1 = new Monster(level,1);
        m2 = new Monster(level,2);
        m3 = new Monster(level,3);
        player = new Player(map,level,m1,m2,m3);
        Redraw();












    }



    //redraws the game and decides what gets shown and what dosent .
    public void Redraw() {




        if(level % 3 ==1 &&getmap()[player.Getxy()[0]][player.Getxy()[1]] == 5 )
        {
            player.Setxy(new int[]{12,5});
            player.SetDirection(0);
        }
        if(level % 3 ==2 &&getmap()[player.Getxy()[0]][player.Getxy()[1]] == 5 )
        {
            player.Setxy(new int[]{11,5});
            player.SetDirection(90);
        }
        if(level % 3 ==0 &&getmap()[player.Getxy()[0]][player.Getxy()[1]] == 5 )
        {
            player.Setxy(new int[]{10,8});
            player.SetDirection(0);
        }

        Board.removeAll();

        Board.dispose();

        Board = new JFrame();
        Board.setBackground(Color.GRAY);
        Board.setLayout(new GridLayout(2,1));
        Board.setSize( 1500, 1000);
        Board.setLocation(0,0);
        Board.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        game = new JPanel(new GridLayout(3,1,1,1));
        game.setBackground(Color.GRAY);

        counter = new JPanel(new BorderLayout(1,1));
        counter.setBackground(Color.GRAY);
        //what button gets shown is decided base on what square value the player is on.
        if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 0){
            display = new JLabel(level1_foward);
        }
        else if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3){
            display = new JLabel(level1_3way);
        }
        else if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 4){
            display = new JLabel(level1_right);
        }
        else if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 2){
            display = new JLabel(level1_left);
        }
        else if(getmap()[player.Getxy()[0]][player.Getxy()[1]] == 6){
            display = new JLabel(End);
        }




        if((getmap()[player.Getxy()[0]][player.Getxy()[1]] == 0)){
            game.add(foward);
        }
        else if((getmap()[player.Getxy()[0]][player.Getxy()[1]] == 3)){
            game.add(foward);
            game.add(right);
            game.add(left);
        }
        else if((getmap()[player.Getxy()[0]][player.Getxy()[1]] == 4)){
            game.add(right);
        }

        else if((getmap()[player.Getxy()[0]][player.Getxy()[1]] == 2)){
            game.add(left);
        }
        else if((getmap()[player.Getxy()[0]][player.Getxy()[1]] == 6)){
            game.add(NextLevel);
        }
        game.repaint();
        counter.add(new JLabel("Current level " + level + " Movement Roll " + player.getRoll() ),BorderLayout.PAGE_START);
        counter.add(game,BorderLayout.CENTER);
        counter.repaint();
        Board.add(display);
        Board.add(counter);
        Board.setVisible(true);
        //if player gets into combat situation
        if((player.Getxy()[0] == m1.GetCords()[0] && player.Getxy()[1] == m1.GetCords()[1]) || (player.Getxy()[0] == m2.GetCords()[0] && player.Getxy()[1] == m2.GetCords()[1]) || (player.Getxy()[0] == m3.GetCords()[0] && player.Getxy()[1] == m3.GetCords()[1])){
            Board.dispose();
            Combat obj = new Combat(player,level,m1,m2,m3);



        }




    }







}
