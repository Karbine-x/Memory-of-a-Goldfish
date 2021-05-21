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
        for (int i = 0; i < width; ++i)
        {
            grid.add(new ArrayList<>());
            for (int j = 0; j < height; ++j)
            {
                grid.get(i).add(new Tile());
            }
        }

        //Create a set of unique IDs. Each ID will be assigned to 2 random tiles
        ArrayList<Integer> IDs = new ArrayList<>();
        for (int i = 0; i < nTiles / 2; ++i)
        {
            IDs.add(i);
        }

        //Create a list of all tile coordinates, used to index in to
        //the tile grid to assign IDs
        ArrayList<Pair<Integer, Integer>> tileCoords = new ArrayList<>();
        for (int i = 0; i < width; ++i)
        {
            for (int j = 0; j < height; ++j)
            {
                tileCoords.add(new Pair<>(i, j));
            }
        }

        //randomise the tile coordinates
        Collections.shuffle(tileCoords);

        while (IDs.size() > 0)
        {
            int currentId = IDs.get(IDs.size() - 1);
            Pair<Integer, Integer> firstTileCoords = tileCoords.get(tileCoords.size() - 1);
            Pair<Integer, Integer> secondTileCoords = tileCoords.get(tileCoords.size() - 2);
            Tile firstTile = grid.get(firstTileCoords.first).get(firstTileCoords.second);
            Tile secondTile = grid.get(secondTileCoords.first).get(secondTileCoords.second);
            //assign the id
            firstTile.id = currentId;
            secondTile.id = currentId;
            //remove the id, first and second tile coords so they aren't reused
            IDs.remove(IDs.size() - 1);
            tileCoords.remove(tileCoords.size() - 1);
            tileCoords.remove(tileCoords.size() - 1);
        }
    }



    private ArrayList<ArrayList<Tile>> grid;
    private final int width;
    private final int height;
    private final int nTiles;
}
