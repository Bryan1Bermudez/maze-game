package apcs;

import java.util.Random;
//is responsible for keeping track of the players postion health,level ,starting positon,direction,there rool,health and armor.

public class Player {
   private int[] xy;
   private int health;
   private int[] start;
   private int level;
   private Map map;
   private int direction;
   private int Roll;
   private int Health;
   private int Aromor;
   private Monster d1;
    private Monster d2;
    private Monster d3;

   // 0 = up
    // 90 is right
    // - 90 is left
    //sets up all the proper varibles with health starting at 3 and armor at 10 with total health being health becoming a addition of Health and Aromor.
    //(i did it like this because of requirments of the homework : (  )
   public Player(Map mao, int lvl,Monster a1,Monster a2, Monster a3){
       Health = 3;
       Aromor = 10;
       d1 = a1;
       d2 = a2;
       d3 = a3;
       health = Aromor+ Health;
        map = mao;
        level = lvl % 3;
            direction = 0;
            start = new int[]{15,5};
        xy = start;
        map = new Map();
    }



    //getter functions
    public int GetHealth(){return Health;}
    public int Getarmor(){return Aromor;}
    public int GettrueHealth(){return health;}

    //changing functions
    public void Changearmpr(int ar){Aromor = ar;}
    public void Changehealth(int ar){Health = ar;}
    public void ChangetrueHealth(int ar){health = ar;}

    //for changing the monsters aka new level.
    public void Changemon(Monster a1,Monster a2, Monster a3){ d1 = a1; d2 = a2; d3 = a3;}

    //setters
    public void SetDirection(int dic){direction = dic;}
    //more getters
    public int getDirection(){return  direction;}
    public int getRoll(){return Roll;}
    public int[] Getxy() { return  xy;}
    public void Setxy(int[] dic){xy = dic;}

    // calculates an attack roll then sends it back
    public int Attack(){
        Random rand = new Random();
        int temp = rand.nextInt(20);
        Roll = temp;
        temp ++;
        Roll ++;
        return temp;
    }



    //is in charge of the movement and cauees it to move in whatever direction its facing with whatever roll it calculates
    public void move(){
        Random rand = new Random();
        int temp = rand.nextInt(6);
        Roll = temp;
        Roll ++;
        temp ++;
        if(direction == -180 || direction == 180 && level == 1){
            xy[1] --;
            xy[0] --;
            direction = 0;
        }
        else if(direction == -180 || direction == 180 && level == 2){
            xy[0] --;
            direction = 0;
        }
        else if(direction == -180 || direction == 180 && level == 3){
            xy[0] --;
            direction = 0;
        }{


        }
        for(int i = 0; i < temp; i ++){

            if((xy[0] == d1.GetCords()[0] && xy[1] == d1.GetCords()[1]) || (xy[0] == d2.GetCords()[0] && xy[1] == d2.GetCords()[1]) || (xy[0] == d3.GetCords()[0] && xy[1] == d3.GetCords()[1])){
                i = temp + 1;

            }
            else {

                if (direction == 0) {
                    if (canmove()) {
                        if (!on3()) {
                            xy[0]--;
                        }
                    }

                }
                if (direction == 90) {
                    if (canmove()) {
                        if (!on3()) {
                            xy[1]++;
                        }
                    }

                }
                if (direction == -90) {
                    if (canmove()) {
                        if (!on3()) {
                            xy[1]--;
                        }
                    }

                }
            }



            }
        }


        //checks if its on a 3 way as sometimes weird glicthes can happen at them so they have to be acounted for is in special ways.
    private boolean on3(){
        if(level == 1){
                if(map.getLevel1()[xy[0]][xy[1]] == 3){
                    return true;

                }
                else {
                    return false;
                }

        }
        if(level == 2){
            if(map.getLevel2()[xy[0]][xy[1]] == 3){
                return true;

            }
            else {
                return false;
            }

        }
        if(level == 0){
            if(map.getLevel3()[xy[0]][xy[1]] == 3){
                return true;

            }
            else {
                return false;
            }

        }
        return false;
    }





    private boolean canmove(){
       int tempx = xy[0];
       int tempy = xy[1];
       if(level == 1){
           if(direction == 0){
               tempx --;
               if(map.getLevel1()[tempx][tempy] == 1){
                   return false;

               }
               else {
                   return true;
               }
           }
           if(direction == 90){
               tempy ++;
               if(map.getLevel1()[tempx][tempy] == 1){
                   return false;

               }
               else {
                   return true;
               }
           }
           if(direction == -90){
               tempy --;

               if(map.getLevel1()[tempx][tempy] == 1){
                   return false;

               }
               else {
                   return true;
               }
           }
       }
        if(level == 2){
            if(direction == 0){
                tempx --;
                if(map.getLevel2()[tempx][tempy] == 1){
                    return false;

                }
                else {
                    return true;
                }
            }
            if(direction == 90){
                tempy ++;
                if(map.getLevel2()[tempx][tempy] == 1){
                    return false;

                }
                else {
                    return true;
                }
            }
            if(direction == -90){
                tempy --;

                if(map.getLevel2()[tempx][tempy] == 1){
                    return false;

                }
                else {
                    return true;
                }
            }
        }
        if(level == 0){
            if(direction == 0){
                tempx --;
                if(map.getLevel3()[tempx][tempy] == 1){
                    return false;

                }
                else {
                    return true;
                }
            }
            if(direction == 90){
                tempy ++;
                if(map.getLevel3()[tempx][tempy] == 1){
                    return false;

                }
                else {
                    return true;
                }
            }
            if(direction == -90){
                tempy --;

                if(map.getLevel3()[tempx][tempy] == 1){
                    return false;

                }
                else {
                    return true;
                }
            }
        }



    return false;
    }




}
