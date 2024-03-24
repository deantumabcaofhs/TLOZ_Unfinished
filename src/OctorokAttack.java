//Ver 5.17.9:11AM (mth.day.hr:min)


public class OctorokAttack {
    int x, y, xSpeed, ySpeed, px, py;
    int size = 20;


    public OctorokAttack(int x, int y, int px, int py) {
        this.x = x;
        this.y = y;
        this.px = px;
        this.py = py;
        move();
    }


    public void move() {
        if(x - px > 0) {
            xSpeed = -2;
        } else {
            xSpeed = 2;
        }if(y - px > 0) {
            ySpeed = -2;
        } else {
            ySpeed = 2;
        }
        x += xSpeed;
        y += ySpeed;
    }
}