package MG.Core;

import android.content.Context;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.util.HashMap;
import java.util.List;

public class GameManager
{
    public GameManager(int boardWidth, int boardHeight, Context context, List<Integer> imageSet)
    {
        tiles = new TileGrid(boardWidth, boardHeight);
        view = new GameView(boardWidth, boardHeight, context);
        tileIDToImg = new HashMap<>();
        List<Pair<Tile, Tile>> pairs = tiles.getIDPairs();
        int imgIndex = 0;
        for (Pair<Tile, Tile> p: pairs)
        {
            if (imgIndex > imageSet.size())
            {
                throw new RuntimeException("Not enough images provided for board size");
            }
            int imgId = imageSet.get(imgIndex++);
            tileIDToImg.put(p.first.id, imgId);
        }

        addOnClickListeners();
    }

    public void display(ViewGroup view)
    {
        View game = this.view.asViewGroup();
        if (game.getParent() != null)
            ((ViewGroup)game.getParent()).removeView(game);
        view.addView(game);
    }

    private void addOnClickListeners()
    {
        for (ImageView img: view)
        {
            img.setOnClickListener(v -> {
                int tileNo = v.getId();
                Tile selected = tiles.get(tileNo);
                tiles.flip(tileNo);
                v.setClickable(false);
                ((ImageView)v).setImageResource(tileIDToImg.get(selected.id));
                onMatch();
            });
        }
    }

    private void onMatch()
    {
        for (ImageView img: view)
        {
            Tile curr = tiles.get(img.getId());
            if (!curr.matched && !curr.faceUp)
            {
                img.setClickable(true);
                img.setImageResource(R.drawable.tileback_94);
            }
        }
    }

    private TileGrid tiles;
    private GameView view;
    private HashMap<Integer, Integer> tileIDToImg;
}
