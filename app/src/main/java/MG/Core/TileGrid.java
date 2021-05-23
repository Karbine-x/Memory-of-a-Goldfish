package MG.Core;

import android.util.Pair;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TileGrid implements Iterable<Tile>
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
        for (int i = 0; i < nTiles; ++i)
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

    public Tile get(int index)
    {
        return grid.get(index);
    }

    public void flip(int index)
    {
        Tile selected = get(index);

        if (!selected.faceUp)
        {
            selected.faceUp = true;
            if (currentFlippedTile == null)
            {
                currentFlippedTile = selected;
            }
            else
            {
                if (selected.id == currentFlippedTile.id)
                {
                    selected.matched = true;
                    currentFlippedTile.matched = true;
                }
                else
                {
                    selected.faceUp = false;
                    currentFlippedTile.faceUp = false;
                }

                currentFlippedTile = null;
            }
        }
    }

    public List<Pair<Tile, Tile>> getIDPairs()
    {
        List<Pair<Tile, Tile>> pairs = new ArrayList<>();
        for (int i = 0; i < grid.size(); ++i)
        {
            Tile first = grid.get(i);
            for(int j = i + 1; j < grid.size(); ++j)
            {
                Tile second = grid.get(j);
                if (second.id == first.id)
                    pairs.add(new Pair<>(first, second));
            }
        }

        return pairs;
    }

    @Override
    public Iterator<Tile> iterator() {
        return grid.iterator();
    }

    private final ArrayList<Tile> grid;
    private final int width;
    private final int height;
    private final int nTiles;
    private Tile currentFlippedTile;
}
