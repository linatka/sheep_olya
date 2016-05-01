package by.bsu.fpmi.olya.levels.structure;

import by.bsu.fpmi.olya.engine.Constants;
import by.bsu.fpmi.olya.entity.Direction;
import by.bsu.fpmi.olya.entity.Enemy;
import by.bsu.fpmi.olya.entity.Entity;
import by.bsu.fpmi.olya.entity.Sheep;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.levels.Prize;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 23.04.2016.
 */
public class Level {

    public enum Progress{
        IN_PROGRESS,
        PASSED_FAIL,
        PASSED_SUCCESSFUL;
    }

    public enum Attribute{
        HEALTH_0,
        HEALTH_1,
        HEALTH_2,
        HEALTH_3,
        PRIZES;

    }

    private String levelName;
    private Progress progress;
    private int target;
    public final int LENGTH;
    private final Cell[][] levelMatrix;
    private final int finishPosition;

    private Sheep sheep;
    private Map<Point, Prize> prizes;
    private ArrayList<Enemy> enemies;
    private int enemyDelay;
    private Map<Attribute, Texture> attributes;

    Texture background;

    public Level(LevelInitializer initializer){

        LENGTH = initializer.getLength();
        levelMatrix = new Cell[LENGTH][Constants.SCREEN_HEIGHT];
        prizes = new HashMap<>();
        enemies = new ArrayList<>();

        initializer.initLevelMatrix(levelMatrix, prizes);
        initializer.getEnemies().forEach(this::addEnemy);
        attributes = initializer.getAttributes();

        sheep = initializer.getSheep();
        background = initializer.getBackground();
        enemyDelay = Constants.ENEMY_DELAY;
        progress = Progress.IN_PROGRESS;
        target = initializer.getTarget();

        finishPosition = initializer.getFinishPosition();
    }

    public Cell[] getColumn(int i){
        return levelMatrix[i];
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

//    private void setAt(int x, int y, LevelStructure structure){
//        if (0 <= x && x < LENGTH && 0 <= y && y < Constants.SCREEN_HEIGHT){
//            if (structure instanceof Prize){
//                prizes.put(new Point((int)x, (int)y), (Prize)structure);
//                levelMatrix[x][y] =
//                        new Cell(Cell.CellType.PRIZE,structure.getTexture());
//            } else {
//                levelMatrix[x][y] =
//                        new Cell(Cell.CellType.LANDSCAPE,structure.getTexture());
//            }
//        }
//}

    public boolean addEnemy(Enemy enemy){
        if (enemy.getInitX() >= 0 && enemy.getMotionDistance() + enemy.getInitX() < LENGTH){
            enemies.add(enemy);
            return true;
        }
        return false;
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public int update(int currentLevelPosition, int levelDelta, Direction xDirection, Direction yDirection){
        sheep.setXDirection(xDirection);
        sheep.setYDirection(yDirection);
        sheep.incY(sheep.getYDirection().deltaY());

        if (sheep.getX() + currentLevelPosition + levelDelta > finishPosition){
            //level is passed
            progress = Progress.PASSED_SUCCESSFUL;
            return currentLevelPosition;
        }

        if (enemyDelay == 0){
            enemies.forEach(enemy -> {
                enemy.move();
                if (isIntersection(enemy, 0)){
                    enemy.setXDirection(enemy.getXDirection().oppositeDirection());
                    enemy.move();
                }
            });
            enemyDelay = Constants.ENEMY_DELAY;
        } else {
            enemyDelay--;
        }

        for (Enemy enemy: enemies) {
            if (sheep.isIntersection(enemy.getX() - currentLevelPosition, enemy.getY(), enemy.WIDTH, enemy.HEIGHT )){
                if (sheep.getInjured(enemy.getX() > sheep.getX() ? Direction.RIGHT : Direction.LEFT)) {
                    //game over
                    //fail
                    progress = Progress.PASSED_FAIL;
                }
                levelDelta -= 8 * sheep.getXDirection().deltaX();
                System.out.println("Health " + sheep.getHealth());
            }
        }

        if (isIntersection(sheep, currentLevelPosition + levelDelta)) {
            if (sheep.getYDirection() != Direction.REST){
                sheep.incY(-sheep.getYDirection().deltaY());
                sheep.setYDirection(sheep.getYDirection().nexBy());
            } else {
                levelDelta += sheep.getXDirection().deltaX();
            }
        } else {
            if (sheep.getYDirection() == Direction.REST &&
                    sheep.getX() + currentLevelPosition + sheep.WIDTH < LENGTH &&
                    !isIntersection(sheep.getX() + currentLevelPosition,    ///////////Out of Bounds!
                    sheep.getY() + sheep.HEIGHT + 1, sheep.WIDTH, 1, currentLevelPosition)){
                sheep.setYDirection(Direction.DOWN);
            }

        }

        if (currentLevelPosition + levelDelta >= 0 &&
                currentLevelPosition + levelDelta + Constants.SCREEN_WIDTH < LENGTH) {
            currentLevelPosition += levelDelta;
        }

        return currentLevelPosition;
    }

    public int draw(Graphics2D g, int currentLevelPosition/*, int levelDelta, Sheep sheep*/){

//        if (enemyDelay == 0){
//            enemies.forEach(enemy -> {
//                enemy.move();
//                if (isIntersection(enemy, 0)){
//                    enemy.setXDirection(enemy.getXDirection().oppositeDirection());
//                    enemy.move();
//                }
//            });
//            enemyDelay = Constants.ENEMY_DELAY;
//        } else {
//            enemyDelay--;
//        }

        //int levelPosition = currentLevelPosition;


        background.draw(g, 0, 0);

//        for (Enemy enemy: enemies) {
//            if (sheep.isIntersection(enemy.getX() - currentLevelPosition, enemy.getY(), enemy.WIDTH, enemy.HEIGHT )){
//                sheep.getInjured(enemy.getX() > sheep.getX() ?
//                        Direction.RIGHT : Direction.LEFT);
//                levelDelta -= 10 * sheep.getXDirection().deltaX();
//                System.out.println("Health " + sheep.getHealth());
//            }
//        }
//
//        if (isIntersection(sheep, currentLevelPosition + levelDelta)) {
//            if (sheep.getYDirection() != Direction.REST){
//                sheep.incY(-sheep.getYDirection().deltaY());
//                sheep.setYDirection(sheep.getYDirection().nexBy());
//            } else {
//                levelDelta += sheep.getXDirection().deltaX();
//            }
//        } else {
//            if (sheep.getYDirection() == Direction.REST && !isIntersection(sheep.getX() + currentLevelPosition,
//                    sheep.getY() + sheep.HEIGHT + 1,
//                    sheep.WIDTH, 1, currentLevelPosition)){
//                sheep.setYDirection(Direction.DOWN);
//            }
//
//        }
//
//        if (currentLevelPosition + levelDelta >= 0 &&
//                currentLevelPosition + levelDelta + Constants.SCREEN_WIDTH < LENGTH) {
//            currentLevelPosition += levelDelta;
//        }

        for (int i = 0; i < Constants.SCREEN_WIDTH; i++){
            for (int j = 0; j < Constants.SCREEN_HEIGHT; j++){
                if (levelMatrix[i + currentLevelPosition][j] != null){
                    levelMatrix[i + currentLevelPosition][j].getTexture().draw(g, i, j);
                }
            }
        }

        for (Enemy enemy: enemies) {
            drawEnemy(g, enemy, currentLevelPosition);
        }

//
//        enemies.forEach(enemy -> {
//            drawEnemy(g, enemy, levelPosition);
//        });

        sheep.draw(g);

        drawAttributes(g);

        return currentLevelPosition;
    }

    private boolean drawEnemy(Graphics2D g, Enemy enemy, int currentLevelPosition){
        if (currentLevelPosition < enemy.getX() && currentLevelPosition + Constants.SCREEN_WIDTH > enemy.getX()){
            enemy.draw(g, enemy.getX() - currentLevelPosition, enemy.getY());
            return true;
        }
        return false;
    }

    private void drawAttributes(Graphics2D g){
        Texture healthBar;
        switch (sheep.getHealth()){
            case 0:
                healthBar = attributes.get(Attribute.HEALTH_0);
                break;
            case 1:
                healthBar = attributes.get(Attribute.HEALTH_1);
                break;
            case 2:
                healthBar = attributes.get(Attribute.HEALTH_2);
                break;
            case 3:
                healthBar = attributes.get(Attribute.HEALTH_3);
                break;
            default:
                healthBar = attributes.get(Attribute.HEALTH_0);
        }
        healthBar.draw(g, 0, 0);

        Texture prizesTexture = attributes.get(Attribute.PRIZES);
        prizesTexture.draw(g, 0,
                Constants.SCREEN_HEIGHT - prizesTexture.size().height / Constants.CELL_HEIGHT - 2);
        g.setFont(new Font("Georgia", Font.BOLD, 24));
        g.setColor(Color.WHITE);
        String status = sheep.getPrize() + " / " + target;
        g.drawString(status, (prizesTexture.size().width - status.length() * 11) / 2,
                Constants.SCREEN_HEIGHT * Constants.CELL_HEIGHT - (prizesTexture.size().height)/2);
        //g.dispose();
    }

    public boolean isIntersection(Entity entity, int currentLevelPos){
        boolean intersection = false;
        for (int i = entity.getX() + currentLevelPos; i < entity.getX() + entity.WIDTH + currentLevelPos; i++){
            for (int j = entity.getY(); j < entity.getY() + entity.HEIGHT; j++){
                if (levelMatrix[i][j] != null && levelMatrix[i][j].getType() == Cell.CellType.PRIZE){
                    if (entity instanceof Sheep){
                        prizes.get(new Point(i, j)).applyTo((Sheep)entity);
                        levelMatrix[i][j] = null;
                        System.out.println(((Sheep)entity).getPrize());
                    }
                } else {
                    if (levelMatrix[i][j] != null && levelMatrix[i][j].isRigid()/*levelMatrix[i][j].getType() != Cell.CellType.PRIZE*/){
                        intersection = true;
                    }
                }
            }
        }
        return intersection;
    }

    public boolean isIntersection(int x, int y, int width, int height, int currentLevelPos){
        boolean intersection = false;
        for (int i = x; i < x + width; i++){
            for (int j = y; j < y + height; j++){
                if (levelMatrix[i][j] != null && levelMatrix[i][j].isRigid()/*levelMatrix[i][j].getType() != Cell.CellType.PRIZE*/){
                    intersection = true;
                }
            }
        }
        return intersection;
    }

    public int getSheepY(){
        return sheep.getY();
    }

    public Direction getXDirection(){
        return sheep.getXDirection();
    }

    public Direction getYDirection(){
        return sheep.getYDirection();
    }

    public Progress getProgress(){
        return progress;
    }

    public int getTarget() {
        return target;
    }

    public int getScore(){
        return sheep.getPrize();
    }
}
