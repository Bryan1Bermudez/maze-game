package apcs;

import java.util.Random;
//controls the monster and stores all of there varibles such as health armomr "true health" cords and roll.
public class Monster {
    private int health;
    private int armor;
    private int truehealth;
    private int[] cords;
    private int roll;

    public Monster(int level,int num){
        health = 1;
        armor = 1;
        //every 3 leve
        //ls there health increases
        int temp = level/3;
        health = temp + health;
        armor = armor + temp;
        //had to do it this way cause of requirments.

        truehealth = health + armor;

        //sets up there starter cords depending on what number and level the monster are
        if(level % 3 == 1){
            if(num == 1){
                cords = new int[]{12,5};
            }
            if(num == 2){
                cords = new int[]{10,5};
            }
            if(num == 3){
                cords = new int[]{10,1};
            }

        }
        if(level % 3 == 2){
            if(num == 1){
                cords = new int[]{13,3};
            }
            if(num == 2){
                cords = new int[]{10,1};
            }
            if(num == 3){
                cords = new int[]{5,3};
            }

        }
        if(level % 3 == 0){
            if(num == 1){
                cords = new int[]{11,5};
            }
            if(num == 2){
                cords = new int[]{7,2};
            }
            if(num == 3){
                cords = new int[]{2,5};
            }

        }
    }
    //calculates attack roll and returns it.
    public int Attack(){

        Random rand = new Random();
        int temp = rand.nextInt(20);
        roll = temp;
        temp ++;
        roll ++;
        return temp;
    }
    //getters
    public int[] GetCords(){return cords;}
    public int GetHealth(){return health;}
    public int GetRoll(){return roll;}
    public int GetArmor(){return armor;}
    public int GetTruehealth(){return truehealth;}
    //changers
    public void ChangeHealth(int health_){health = health_;}
    public void ChangetrueHealth(int health_){truehealth = health_;}
    public void ChangeArmor(int health_){armor = health_;}
    public void ChangeCords(int[] cords_){cords = cords_;}
    //returns if dead, not sure why i put this here but am scared to remove it
    public boolean isDead(){
        if(truehealth == 0){
            ChangeCords(new int[]{0,0});
            return true;
        }
        return false;


    }





}
