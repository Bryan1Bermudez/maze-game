package apcs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// is in charge of the combat section
public class Combat {
    //what level and player
    private int level;
    private Player player;
    //the board and what gets displayed

    private JFrame Board;
    private JPanel game;
    private JPanel counter;
    //the button attack
    private JButton attack;
    private JLabel display;
    //ease of accses
    private ImageIcon level1_mon = new ImageIcon(getClass().getResource("level1_monster.png"));
    private ImageIcon level2_mon = new ImageIcon(getClass().getResource("level2_monster.png"));
    private ImageIcon level3_mon = new ImageIcon(getClass().getResource("level3_monster.png"));

    private Monster m1;
    private Monster m2;
    private Monster m3;
    private Monster temp;
    private int vnt;


    public Combat(Player play,int level_,Monster d1,Monster d2, Monster d3){


        //sets up the proper parameters for the code
        player = play;
        level = level_;
        m1 = d1;
        m2 = d2;
        m3 = d3;


        //sets up what monster your fighting(vnt) and then stores them in temp.
        if(player.Getxy()[0] == m1.GetCords()[0] && player.Getxy()[1] == m1.GetCords()[1]){
            temp = m1;
            vnt  = 1;

        }
        else if((player.Getxy()[0] == m2.GetCords()[0] && player.Getxy()[1] == m2.GetCords()[1])){
            temp = m2;
            vnt = 2;
        }
        else {
            temp = m3;
            vnt = 3;
        }
        //sets up the jframe.
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

        attack = new JButton("Attack");




        game.setSize(100,100);
        game.setBackground(Color.GRAY);
        Board.setResizable(false);

        //sets up what the attack button does, in which it calls roll for each number and depending on which rolls higher it lowers the health of the other by 1.
        attack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int temp1 = player.Attack();
                int temp2 = temp.Attack();

                if(temp1 > temp2){
                    if (temp.GetArmor() <= 0) {
                        temp.ChangeHealth(temp.GetHealth() - 1);
                        temp.ChangetrueHealth(temp.GetTruehealth() - 1);
                    } else {
                        temp.ChangeArmor( temp.GetArmor() - 1);
                        temp.ChangetrueHealth(temp.GetTruehealth() - 1);

                    }

                }
                else if(temp1 < temp2) {
                    if (player.Getarmor() <= 0) {
                        player.Changehealth(player.GetHealth() - 1);
                        player.ChangetrueHealth(player.GettrueHealth() - 1);
                    } else {
                        player.Changearmpr( player.Getarmor() - 1);
                        player.ChangetrueHealth(player.GettrueHealth() - 1);

                    }
                }
                // calls fight which is essentialy a loop until death
                Fight();

            }
        });





        //sets up as what png the monster is.
        if(level_ % 3 == 1){
            display = new JLabel(level1_mon);
        }
       else if(level_ % 3 == 1){
            display = new JLabel(level2_mon);
        }
       else if(level_ % 3 == 1){
            display = new JLabel(level3_mon);
        }




       Fight();
    }
    //the loop that makes the game continue in which is just resets everything with upadadted informaton until one perseon dies and then it either resets or sends you back to exploration
    //mode where you started
    public void Fight(){
        Board.removeAll();
        Board.dispose();

        Board = new JFrame();
        Board.setBackground(Color.GRAY);
        Board.setLayout(new GridLayout(2,1));
        Board.setSize( 1500, 1300);
        Board.setLocation(0,0);
        Board.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        game = new JPanel(new GridLayout(3,1,1,1));
        game.setBackground(Color.GRAY);

        counter = new JPanel(new BorderLayout(1,1));
        counter.setBackground(Color.GRAY);

        game.add(attack);
        game.repaint();
        counter.add(new JLabel(" Current Armor  " + player.Getarmor() + "\n" + " attack Roll  " + player.getRoll() + "\n" + " Current health  " + player.GetHealth()),BorderLayout.WEST);
        counter.add(new JLabel(" Monster Armor  " + temp.GetArmor() + "\n" + " attack Roll "  + temp.GetRoll() + "\n" + " Monster health  " + temp.GetHealth()),BorderLayout.EAST);
        counter.add(game,BorderLayout.CENTER);
        counter.repaint();
        if(level % 3 == 1){
            display = new JLabel(level1_mon);
        }
        else if(level % 3 == 2){
            display = new JLabel(level2_mon);
        }
        else if(level % 3 == 0){
            display = new JLabel(level3_mon);
        }
        Board.add(display);
        Board.add(counter);
        Board.setResizable(false);
        Board.setBackground(Color.GRAY);
        Board.setVisible(true);
        if(player.GettrueHealth() == 0){
            Board.dispose();
            Exploration obj = new Exploration();
            obj.start();
        }
        if(temp.GetTruehealth() == 0){
            if(vnt ==1){
                m1 = temp;
            }
            else if( vnt ==2){
                m2 = temp;
            }
            else  if(vnt ==3 ){
                m3 = temp;
            }


            Board.dispose();
            Exploration obj = new Exploration(player, m1, m2, m3, level,vnt);
            obj.Redraw();
        }












    }





}
