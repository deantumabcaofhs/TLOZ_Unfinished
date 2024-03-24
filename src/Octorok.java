//Ver 5.27.11:13AM (mth.day.hr:min)


public class Octorok {
    int x, y, xSpeed, ySpeed, s, px, py, level;


    public Octorok(int x, int y, int xS, int yS, int px, int py, int level)  {
        this.x = x;
        this.y = y;
        this.xSpeed = xS;
        this.ySpeed = yS;
        this.px = px;
        this.py = py;
        this.level = level;
        move();
    }


    public void move() {
        if(y - py > 0) {
            ySpeed = 2;
        } else {
            ySpeed = -2;
        }
        x += xSpeed;
        y += ySpeed;
    }




}