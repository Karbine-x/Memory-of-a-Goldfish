package MG.Core;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.widget.LinearLayoutCompat;

public class GameView {

    /**
     * Creates a LinearLayout container defining a grid view of tiles.
     * @param width The number of tiles per row.
     * @param height The number of tiles per column.
     * @return The square root of the given number.
     *
     * Notes:
     * the IDs of the tiles are 0 - n -1 where n = width * height is the number of tiles.
     * the IDs are indexed first by column and then row, so a tile in the first row and the
     * first column has ID 0, a tile in the first row and the second column has ID 1, and so on.
     *
     * The ID of the return value itself is n, 1 more than the total number of tiles.
     */
    public static LinearLayout Create(int width, int height, Context context)
    {
        int id = 0; //unique id for each tile
        int matchParent = LinearLayout.LayoutParams.MATCH_PARENT;
        float rowWeight = 1.f/height; //n rows so each row has 1/n weight
        float cellWeight = 1.f/width; //m cells per row so each has 1/m weight
        LinearLayout container = new LinearLayout(context); //the view to return
        //configure the container
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
                matchParent, matchParent
        );
        container.setLayoutParams(containerParams);
        container.setOrientation(LinearLayout.VERTICAL); //want to stack rows vertically
        container.setGravity(Gravity.CENTER);
        container.setId(makeID(width, height));

        for (int i = 0; i < height; ++i)
        {
            //divide the view in to horizontal segments
            LinearLayout row = new LinearLayout(context);
            row.setGravity(Gravity.CENTER); //center the rows
            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(
                    matchParent, matchParent, rowWeight
            );
            row.setLayoutParams(rowParams);
            container.addView(row);
            container.setId(makeID(width, height));

            for (int j = 0; j < width; ++j)
            {
                //create a new LinearLayout for each button
                LinearLayout cell = new LinearLayout(context);
                cell.setGravity(Gravity.CENTER);
                //Scale the button cells so they fit evenly inside the row
                LinearLayout.LayoutParams cellParams = new LinearLayout.LayoutParams(
                        matchParent, matchParent, cellWeight
                );
                cell.setLayoutParams(cellParams);
                Button tile = new Button(context);
                LinearLayout.LayoutParams tileParams = new LinearLayout.LayoutParams(
                        matchParent, matchParent
                );
                tile.setLayoutParams(tileParams);
                tile.setId(id++);
                row.addView(cell);
                cell.addView(tile);
            }
        }

        return container;
    }

    private static int makeID(int width, int height)
    {
        //for now set ID 1 higher than the ID of the last tile to be added.
        //A bit hacky...
        return width * height;
    }

}
