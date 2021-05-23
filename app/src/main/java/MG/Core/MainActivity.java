package MG.Core;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Integer> pics = new ArrayList<>();
        pics.add(R.drawable.goldfish1_94);
        pics.add(R.drawable.goldfish2_94);
        pics.add(R.drawable.goldfish3_94);
        pics.add(R.drawable.goldfish4_94);
        pics.add(R.drawable.goldfish5_94);
        pics.add(R.drawable.goldfish6_94);
        GameManager manager = new GameManager(3, 4, this, pics);
        manager.display(findViewById(R.id.mainContainer));
    }
}