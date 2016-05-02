package by.bsu.fpmi.olya.levels.structures;

import by.bsu.fpmi.olya.garphics.Texture;
import by.bsu.fpmi.olya.levels.Cell;
import by.bsu.fpmi.olya.levels.LevelException;
import by.bsu.fpmi.olya.levels.LevelStructure;

/**
 * Created by Lenovo on 02.05.2016.
 */
public class ComplexLandscape extends LevelStructure {

    private final Cell[][] cells;
    private String name;

    public ComplexLandscape(Texture texture, boolean isRigid, String name) throws LevelException{
        super(texture, texture.size().getWidthInCell(), texture.size().getHeightInCell(), isRigid);

        this.name = name;

        cells = new Cell[texture.size().getWidthInCell()][texture.size().getHeightInCell()];
        TexturesProducer producer = new TexturesProducer(texture, name);
        Texture[][] textures = producer.produce();
        for (int i = 0; i < texture.size().getWidthInCell(); i++) {
            for (int j = 0; j < texture.size().getHeightInCell(); j++){
                cells[i][j] = new Cell(Cell.CellType.LANDSCAPE, textures[i][j], isRigid);
            }
        }
    }

    public Cell getCell(int i, int j) {
        if (0 <= i && i < cells.length && 0 <= j && j < cells[0].length){
            return cells[i][j];
        }
        return null;
    }
}
