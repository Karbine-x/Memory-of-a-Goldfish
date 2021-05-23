package MG.Core;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed()
    {
        setContentView(R.layout.activity_main);
    }

    public void onClickPlay(View view)
    {
        setContentView(R.layout.activity_game);
        if (activeGameInstance == null)
        {
            startNewGameInstance();
        }
        activeGameInstance.display(findViewById(R.id.gameContainer));
    }

    public void onClickSettings(View view)
    {
        setContentView(R.layout.activity_preferences);
    }

    public void onSoundEnableDisable(View view) {
        CheckBox box = (CheckBox)view;
    }

    private void startNewGameInstance()
    {
        ArrayList<Integer> pics = new ArrayList<>();
        pics.add(R.drawable.goldfish1_94);
        pics.add(R.drawable.goldfish2_94);
        pics.add(R.drawable.goldfish3_94);
        pics.add(R.drawable.goldfish4_94);
        pics.add(R.drawable.goldfish5_94);
        pics.add(R.drawable.goldfish6_94);
        pics.add(R.drawable.goldfish7_94);
        pics.add(R.drawable.goldfish8_94);
        pics.add(R.drawable.goldfish9_94);
        pics.add(R.drawable.goldfish10_94);
        activeGameInstance = new GameManager(4, 5, this, pics);
    }

    private GameManager activeGameInstance = null;
}