//Ver 6.2.7:41PM (mth.day.hr:min)


import java.util.ArrayList;
import processing.core.*;
import processing.core.PImage;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;


public class RunGraphicalGame extends PApplet {
    //Libraries
    PImage appIcon;
    PImage logo, startBG; //Title Screen
    PImage p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, pGt; //Link animations
    PImage pSword;
    PImage o1, o2;
    PImage r1, r2;
    Minim audio;
    AudioPlayer startM, gameM, getItem, swordStrike;




    //Objects
    GameBoard game;
    Display display;
    Octorok o;






    //Variables
    int px = 325, py = 450, ph = 100, pw = 100; //Player position
    int pSize = ph/50;
    int playGame;
    boolean up, down, left, right; //Player movement
    int pAnim = 5, pAnimTimer, pAnimLast, pAnimLastSet, pAnimYes = 1; //Player animation
    int pMove = 1;
    int gotSword;
    int pAttack, swordAttack; // Sword attack status
    int level;
    int levelSet, levelSet2;
    int noContLTrig = 1, noContLTrig2 = 1;
    int pContL, pContR, pContU, pContD;
    int timer;
    int oAX = 355;
    int oAY = 150;
    int lives = 6;
    int u = 375;
    int j = 375;




    //Arrays
    ArrayList<Octorok> octo = new ArrayList<>();
    ArrayList<Integer> oAnim = new ArrayList<>();
    ArrayList<Integer> oAnimTimer = new ArrayList<>();
    ArrayList<OctorokAttack> octoA = new ArrayList<>();












    public void settings() {
        size(750, 750);
    }




    public void setup() {
        surface.setTitle("The Legend Of Zelda"); //App titlebar name
        surface.setResizable(true);
        appIcon = loadImage("assets/Logo.png");
        surface.setIcon(appIcon);
        //IMAGES
        logo = loadImage("assets/Logo.png");
        startBG = loadImage("assets/TitleBG.jpeg");
        p1 = loadImage("assets/link1.png");
        p2 = loadImage("assets/link2.png");
        p3 = loadImage("assets/link3.png");
        p4 = loadImage("assets/link4.png");
        p5 = loadImage("assets/link5.png");
        p6 = loadImage("assets/link6.png");
        p7 = loadImage("assets/link7.png");
        p8 = loadImage("assets/link8.png");
        p9 = loadImage("assets/link9.png");
        p10 = loadImage("assets/link10.png");
        p11 = loadImage("assets/link11.png");
        p12 = loadImage("assets/link12.png");
        pGt = loadImage("assets/getSword.png");
        pSword = loadImage("assets/linkSword.png");
        o1 = loadImage("assets/o1.png");
        o2 = loadImage("assets/o2.png");


        r1 = loadImage("assets/frame_apngframe1.png");
        r2 = loadImage("assets/frame_apngframe2.png");






        //AUDIO
        audio = new Minim(this);
        startM = audio.loadFile("assets/Legend of Zelda, The (NES) Music - Title Theme.mp3");
        gameM = audio.loadFile("assets/Legend of Zelda, The (NES) Music - Overworld Theme.mp3");
        getItem = audio.loadFile("assets/getItem.mp3");
        swordStrike = audio.loadFile("assets/8d82b5_The_Legend_of_Zelda_Sword_Sound_Effect.mp3");




        Octorok o = new Octorok(300,100,100,100,px,py,1);
        octo.add(o);


        for(int i = 0; i < octo.size(); i++){
            oAnim.add(1);
            oAnimTimer.add(0);
        }




        if(playGame == 1) {
            //GRID CREATION
            game = new GameBoard(10, 10);
            display = new Display(this, 0, 0, 750, 750);
            display.setImage(1, "assets/overworldGround.png");
            display.setImage(2, "assets/overworldBushes.png");
            display.setImage(3, "assets/Entrance.png");
            display.initializeWithGame(game);
        }
    }












    @Override
    public void draw() {
        if(playGame == 0) {
            image(startBG, -50, 0,860,753);
            image(logo, 125, 100);
            textSize(30);
            fill(0);
            text("CLICK WINDOW THEN PUSH Z BUTTON", 110, 510);
            if (!startM.isPlaying()) {
                startM.play();
            }
            if(key == 'z' || key == 'Z'){
                startM.close();
                playGame = 1;
                setup();
            }
        }else{
            if (!gameM.isPlaying()) {
                gameM.play();
            }
            display.drawGrid(game.getGrid()); // display the game
            fill(0,255,0);






            playerMove();
            checkLTrig();
            tCollision();
            game.nextLevel(level);


            rok();


            if(level == 0) {
                if (gotSword != 0) {
                    fill(255);
                    textSize(25);
                    text("PUSH Z TO THRUST SWORD", 220, 450);
                }
                if(gotSword == 0){
                    image(pSword, 368, 300, 14, 32);
                }
                if(gotSword == 0) {
                    if (px + 25 >= 343 && px + 25 <= 393 && py + 26 >= 274 && py + 26 <= 325) {
                        gameM.mute();
                        getItem.play();
                        getItem.rewind();
                        gotSword = 1;
                    }
                }
                if(!getItem.isPlaying()) {
                    pMove = 1;
                    gameM.unmute();
                    if(gotSword == 1){
                        gotSword = 2;
                    }
                }else{
                    pMove = 0;
                    image(pGt, px, py, ph, pw);
                }
            }




            if(pAttack == 1){
                if(swordAttack == 0) {
                    swordStrike.play();
                    swordStrike.rewind();
                    swordAttack = 1;
                }
                if(pAnimYes == 1) {
                    pAnimLast = pAnim;
                    pAnimYes = 2;
                }
                if(pAnimLast == 1 || pAnimLast == 2){
                    image(p9, px, py, ph, pw);
                }if(pAnimLast == 3 || pAnimLast == 4){
                    image(p10, px, py, ph, pw);
                }if(pAnimLast == 5 || pAnimLast == 6){
                    image(p11, px, py, ph, pw);
                }if(pAnimLast == 7 || pAnimLast == 8){
                    image(p12, px, py, ph, pw);
                }
            }else{
                swordAttack = 0;
                if(pMove == 1) {
                    if (pAnim == 1) {
                        image(p1, px, py, ph, pw);
                    }
                    if (pAnim == 2) {
                        image(p2, px, py, ph, pw);
                    }
                    if (pAnim == 3) {
                        image(p3, px, py, ph, pw);
                    }
                    if (pAnim == 4) {
                        image(p4, px, py, ph, pw);
                    }
                    if (pAnim == 5) {
                        image(p5, px, py, ph, pw);
                    }
                    if (pAnim == 6) {
                        image(p6, px, py, ph, pw);
                    }
                    if (pAnim == 7) {
                        image(p7, px, py, ph, pw);
                    }
                    if (pAnim == 8) {
                        image(p8, px, py, ph, pw);
                    }




                    if (!up && !down && !left && !right && pAnimYes == 2) {
                        if (pAnimLast == 1) {
                            image(p1, px, py, ph, pw);
                        }
                        if (pAnimLast == 2) {
                            image(p2, px, py, ph, pw);
                        }
                        if (pAnimLast == 3) {
                            image(p3, px, py, ph, pw);
                        }
                        if (pAnimLast == 4) {
                            image(p4, px, py, ph, pw);
                        }
                        if (pAnimLast == 5) {
                            image(p5, px, py, ph, pw);
                        }
                        if (pAnimLast == 6) {
                            image(p6, px, py, ph, pw);
                        }
                        if (pAnimLast == 7) {
                            image(p7, px, py, ph, pw);
                        }
                        if (pAnimLast == 8) {
                            image(p8, px, py, ph, pw);
                        }
                    }
                    if (up || down || left || right) {
                        pAnimYes = 1;
                    }
                }
            }


            if(level == 2) {
                finalFight();
                px = 375;
                py = 600;
            }
            for(int i = 0; i < octo.size(); i++){
                Octorok o = octo.get(i);
                if(level == o.level) {
                    if (oAnimTimer.get(i) < 10) {
                        image(o1, o.x, o.y);
                        oAnimTimer.set(i, oAnimTimer.get(i) + 1);
                    }
                    if (oAnimTimer.get(i) >= 10 && oAnimTimer.get(i) < 20) {
                        image(o2, o.x, o.y);
                        oAnimTimer.set(i, oAnimTimer.get(i) + 1);
                    }
                    if (oAnimTimer.get(i) >= 20) {
                        oAnimTimer.set(i, 0);
                    }
                }
            }
        }
    }








    public void playerMove(){
        if (pAttack == 0 && pMove == 1) {
            if (up && !down && !left && !right) {
                if(pContD == 0) {
                    py -= 5;
                }
                if (pAnimTimer < 5) {
                    pAnim = 5;
                    pAnimTimer++;
                } else {
                    pAnim = 6;
                    if (pAnimTimer < 10) {
                        pAnimTimer++;
                    } else {
                        pAnimTimer = 0;
                    }
                }
            }
            if (down && !up && !left && !right) {
                if(pContU == 0) {
                    py += 5;
                }
                if (pAnimTimer < 5) {
                    pAnim = 7;
                    pAnimTimer++;
                } else {
                    pAnim = 8;
                    if (pAnimTimer < 10) {
                        pAnimTimer++;
                    } else {
                        pAnimTimer = 0;
                    }
                }
            }
            if (left && !right && !down && !up) {
                if(pContR == 0) {
                    px -= 5;
                }
                if (pAnimTimer < 5) {
                    pAnim = 3;
                    pAnimTimer++;
                } else {
                    pAnim = 4;
                    if (pAnimTimer < 10) {
                        pAnimTimer++;
                    } else {
                        pAnimTimer = 0;
                    }
                }
            }
            if (right && !left && !down && !up) {
                if(pContL == 0) {
                    px += 5;
                }
                if (pAnimTimer < 5) {
                    pAnim = 1;
                    pAnimTimer++;
                } else {
                    pAnim = 2;
                    if (pAnimTimer < 10) {
                        pAnimTimer++;
                    } else {
                        pAnimTimer = 0;
                    }
                }
            }
        }
    }








    public void keyPressed(){
        if (keyPressed && keyCode == UP) { //REMOVED VERTICAL MOVEMENT
            up = true;
        }
        if (keyPressed && keyCode == LEFT) {
            left = true;
        }
        if (keyPressed && keyCode == DOWN) { //REMOVED VERTICAL MOVEMENT
            down = true;
        }
        if (keyPressed && keyCode == RIGHT) {
            right = true;
        }




        if((key == 'z' || key == 'Z') && gotSword == 2){
            pAttack = 1;
        }
    }








    public void keyReleased(){
        if (keyCode == UP) { //REMOVED VERTICAL MOVEMENT
            up = false;
        }
        if (keyCode == LEFT) {
            left = false;
        }
        if (keyCode == DOWN) { //REMOVED VERTICAL MOVEMENT
            down = false;
        }
        if (keyCode == RIGHT) {
            right = false;
        }




        if(key == 'z' || key == 'Z'){
            pAttack = 0;
        }
    }








    public void checkLTrig(){
        if (level == 0) {
            if (px >= 265 && px <= 385 && py <= -55 && noContLTrig == 1) {
                levelSet2 = 1;
                level = 1;
            }else{
                noContLTrig = 1;
            }
            if (levelSet == 1) {
                noContLTrig = 0;
                noContLTrig2 = 0;
                py = -55;
                levelSet = 0;
            }
        }if (level == 1) {
            if (levelSet == 1) {
                noContLTrig = 0;
                noContLTrig2 = 0;
                py = -55;
                levelSet = 0;
            }
            if (levelSet2 == 1) {
                py = 705;
                noContLTrig2 = 0;
                levelSet2 = 0;
            }
            if (px >= 265 && px <= 385 && py >= 705 && noContLTrig2 == 1) {
                levelSet = 1;
                level = 0;
            }else{
                noContLTrig2 = 1;
            }
            if (px >= 265 && px <= 385 && py <= -55 && noContLTrig == 1) {
                levelSet2 = 1;
                level = 2;
            }else{
                noContLTrig = 1;
            }
        }if (level == 2) {
            if (levelSet2 == 1) {
                py = 705;
                noContLTrig2 = 0;
                levelSet2 = 0;
            }
            if (px >= 265 && px <= 385 && py >= 705 && noContLTrig2 == 1) {
                levelSet = 1;
                level = 1;
            }else{
                noContLTrig2 = 1;
            }
        }if (level == 3) {
        }if (level == 4) {
        }
    }




    public void tCollision(){
        if(level == 0) {
            if (px + 17 * pSize <= 150) {
                pContR = 1;
            }else if (px + 17 * pSize <= 300 && py + 17 * pSize <= 150) {
                pContR = 1;
            } else {
                pContR = 0;
            }


            if (px + 33 * pSize >= 600) {
                pContL = 1;
            }else if (px + 33 * pSize >= 450 && py + 17 * pSize <= 150) {
                pContL = 1;
            } else {
                pContL = 0;
            }


            if (py + 33 * pSize >= 599) {
                pContU = 1;
            } else {
                pContU = 0;
            }


            if (py + 17 * pSize <= 151 && px + 19 * pSize <= 300) {
                pContD = 1;
            }else if (py + 17 * pSize <= 151 && px + 33 * pSize >= 450) {
                pContD = 1;
            } else {
                pContD = 0;
            }
        }


        if(level == 1){
            if(px + 19 <= 280){
                pContR = 1;
            }else{
                pContR = 0;
            }if(px + 33 >= 415){
                pContL = 1;
            }else {
                pContL = 0;
            }
        }
    }


    public void rok(){
        if(level == 1){
            fill(255,255,0);
            rect(oAX, oAY, 40, 40);
            oAY += 2;
            if(oAY - 40 >= 750){
                oAX = 355;
                oAY = 150;
            }
            if((oAY + 40 >= py + 17 && oAY + 40 <= py + 33 && (oAX >= px + 17 && oAX <= px + 33) || oAX + 40 >= px + 17 && oAX + 40 <= px + 33) || (oAY >= py && oAY <= py + 33 && (oAX >= px + 17 && oAX <= px + 33) || oAX + 40 >= px + 17 && oAX + 40 <= px + 33) || (oAX >= px + 17 && oAX <= px + 33 && (oAY >= py + 17 && oAY <= py + 33) || oAY + 40 >= py + 17 && oAY + 40 <= py + 33)){
                lives--;
                oAX = 355;
                oAY = 150;
            }
        }
    }


    public void finalFight(){


    }






    // main method to launch this Processing sketch from computer
    public static void main(String[] args) {
        PApplet.main(new String[] { "RunGraphicalGame" });
    }
}