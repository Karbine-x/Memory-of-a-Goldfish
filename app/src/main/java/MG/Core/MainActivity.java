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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = (LinearLayout)findViewById(R.id.game_main);
        for (int i = 0; i < 4; ++i)
        {
            LinearLayout row = new LinearLayout(this);
            row.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.25f);
            row.setLayoutParams(p);
            ll.addView(row);

            for (int j = 0; j < 3; ++j)
            {
                LinearLayout cell = new LinearLayout(this);
                cell.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams q = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 0.33333333f);
                cell.setLayoutParams(q);
                Button b = new Button(this);
                LinearLayout.LayoutParams r = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                b.setLayoutParams(r);
                row.addView(cell);
                cell.addView(b);
            }
        }
    }
}