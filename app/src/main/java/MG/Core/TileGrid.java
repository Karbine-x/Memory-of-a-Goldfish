package MG.Core;

import java.util.ArrayList;

public class TileGrid
{
    public TileGrid(int width, int height)
    {
        grid = new ArrayList<>();

        for (int i = 0; i < width; ++i)
        {
            grid.add(new ArrayList<>());
            for (int j = 0; j < height; ++j)
            {

            }
        }
    }

    private ArrayList<ArrayList<Tile>> grid;
}
