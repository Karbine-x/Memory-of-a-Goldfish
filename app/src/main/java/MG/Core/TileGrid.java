package MG.Core;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;

public class TileGrid
{
    public TileGrid(int width, int height)
    {
        this.width = width;
        this.height = height;
        nTiles = width * height;
        if (nTiles % 2 != 0)
        {
            throw new RuntimeException("Number of tiles in TileGrid must be even");
        }

        grid = new ArrayList<>();

        //create the tile grid
        for (int i = 0; i < nTiles; ++i)
        {
            grid.add(new Tile());
        }

        //Create a set of unique IDs. Each ID will be assigned to 2 random tiles
        ArrayList<Integer> IDs = new ArrayList<>();
        for (int i = 0; i < nTiles / 2; ++i)
        {
            IDs.add(i);
        }

        //Create a list of all tile coordinates, used to index in to
        //the tile grid to assign IDs
        ArrayList<Integer> tileIndices = new ArrayList<>();
        for (int i = 0; i < height; ++i)
        {
            tileIndices.add(i);
        }

        //randomise the tile coordinates
        Collections.shuffle(tileIndices);

        while (IDs.size() > 0)
        {
            int currentId = IDs.get(IDs.size() - 1);
            int firstTileIndex = tileIndices.get(tileIndices.size() - 1);
            int secondTileIndex = tileIndices.get(tileIndices.size() - 2);
            Tile firstTile = grid.get(firstTileIndex);
            Tile secondTile = grid.get(secondTileIndex);
            //assign the id
            firstTile.id = currentId;
            secondTile.id = currentId;
            //remove the id, first and second tile coords so they aren't reused
            IDs.remove(IDs.size() - 1);
            tileIndices.remove(tileIndices.size() - 1);
            tileIndices.remove(tileIndices.size() - 1);
        }
    }





    private final ArrayList<Tile> grid;
    private final int width;
    private final int height;
    private final int nTiles;
}
