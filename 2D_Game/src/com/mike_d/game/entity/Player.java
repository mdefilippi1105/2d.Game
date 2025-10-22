package com.mike_d.game.entity;
import com.mike_d.game.main.KeyHandler;
import com.mike_d.game.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    int hasKey = 0;
    boolean hasBoot = false;

    // as opposed to the player coordinates, these are the camera coordinates
    // they do not change; hence final int

    public final int screenX;
    public final int screenY;

    public Player (GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        //pass for angles to the constructor
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height= 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/back.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/back2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/front.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/front2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update() {
        boolean moving = false;

        if (keyH.upPressed) {
            direction = "up";
            moving = true;
             // also means: playerY = playerY - playerSpeed
        } else if (keyH.downPressed) {
            direction = "down";
            moving = true;
             // also means: playerY = playerY + playerSpeed
        } else if (keyH.leftPressed) {
            direction = "left";
            moving = true;
             // also means: playerX = playerX - playerSpeed
        } else if (keyH.rightPressed) {
            direction = "right";
            moving = true;

        }
        // this was added because if moving BOOL not set
        // he will just start moving down immediately with no keystroke
        if (moving) {
            //check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //check object collision
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //if collision is false, player can move
            if (collisionOn == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

            }
        }

//      This gets called 60 times per second
//       as per GamePanel class
//       every 12 frames change the sprite
        spriteCounter++;
        if (spriteCounter > 14) {
            if (spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum ==2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "Key":
                    hasKey++;
                    gp.obj[i] = null;
                    System.out.println("Key:" + hasKey);
                    break;
                case "Door":
                    if (hasKey > 0) {
                        gp.obj[i] = null;
                        hasKey--;
                    }
                    break;
                case "Boots":
                    hasBoot = true;
                    speed += 2;
                    gp.obj[i] = null;
                    break;
            }
        }

    }

    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
       BufferedImage image = null;

       switch (direction) {
           case "up":
               if(spriteNum == 1) {
                   image = up1;
               }
               if(spriteNum == 2) {
                   image = up2; //change to up2 when player walk fixed
               }
//               image = up1;
               break;
           case "down":
               if (spriteNum == 1) {
                   image = down1;
               }
               if (spriteNum == 2) {
                   image = down2; // change to down2 when player walk fixed
               }
//               image = down1;
               break;
           case "left":
               if (spriteNum == 1) {
                   image = left1;
               }
               if (spriteNum == 2) {
                   image = left2; // change to left 2 when player walk fixed
               }
//               image = left1;
               break;
           case "right":
               if (spriteNum == 1) {
                   image = right1;
               }
               if (spriteNum == 2) {
                   image = right2; // change to right2 when player walk fixed
               }
//               image = right1;
               break;
       }
       g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
