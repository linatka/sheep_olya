package by.bsu.fpmi.olya.levels;

import by.bsu.fpmi.olya.engine.Constants;
import by.bsu.fpmi.olya.entity.Enemy;
import by.bsu.fpmi.olya.entity.Sheep;
import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.levels.structures.ComplexLandscape;
import by.bsu.fpmi.olya.levels.structures.Prize;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 24.04.2016.
 */
public class LevelInitializer {

    private int length;
    private Texture background;
    private Map<Point, LevelStructure> structures;
    private Map<Level.Attribute, Texture> attributes;
    private ArrayList<Enemy> enemies;
    private Sheep sheep;
    private int target;
    private int finishPosition;
    private int blockPosition;

    public LevelInitializer(int length, Texture background, Sheep sheep, Map<Level.Attribute, Texture> attributes) {
        this.sheep = sheep;
        this.length = length;
        this.background = background;
        structures = new HashMap<>();
        enemies = new ArrayList<>();
        target = 0;
        this.attributes = attributes;
        finishPosition = length - Constants.SCREEN_WIDTH + sheep.getX() - sheep.WIDTH;
        blockPosition = finishPosition - sheep.WIDTH;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public boolean addStructure(LevelStructure structure, int x, int y){
        if (structure.getWidth() > length - x|| structure.getWidth() > length ||
                structure.getHeight() > Constants.SCREEN_HEIGHT - y|| structure.getHeight() > Constants.SCREEN_HEIGHT){
            return false;
        }
        if (structure instanceof Prize){
            target += ((Prize)structure).getPrice();
        }
//        if (structures.containsKey(new Point(x, y))){
//            return false;
//        }
        structures.put(new Point(x, y), structure);
        return true;
    }

    public void initLevelMatrix(Cell[][] levelMatrix, Map<Point, Prize> prizes){
        structures.forEach((point, structure) -> {
            if (!(structure instanceof ComplexLandscape)){
                for (int j = 0; j < structure.getHeight(); j++){
                    for (int i = 0; i < structure.getWidth(); i++) {
                        Cell.CellType type = Cell.CellType.valueOf(structure.getClass().getSimpleName().toUpperCase());
                        if (type == Cell.CellType.PRIZE){
                            prizes.put(point, (Prize)structure);
                        }
                        levelMatrix[i + point.x][j + point.y] = new Cell(type, structure.getTexture(),
                                structure.isRigid());
                    }
                }
            } else {
                for (int j = 0; j < structure.getHeight(); j++){
                    for (int i = 0; i < structure.getWidth(); i++) {
                        levelMatrix[i + point.x][j + point.y] = ((ComplexLandscape)structure).getCell(i, j);
                    }
                }
            }

        });

        //this.enemies.forEach(enemies::add);
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public Sheep getSheep() {
        return sheep;
    }

    public Map<Level.Attribute, Texture> getAttributes(){
        return attributes;
    }

    public int getTarget(){
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getFinishPosition() {
        return finishPosition;
    }

    public void setFinishPosition(int finishPosition) {
        this.finishPosition = finishPosition;
    }

    public int getBlockPosition() {
        return blockPosition;
    }

    public void setBlockPosition(int blockPosition) {
        this.blockPosition = blockPosition;
    }
}
