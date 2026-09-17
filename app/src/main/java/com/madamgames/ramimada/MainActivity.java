package com.madamgames.ramimada;

import android.app.Activity; import android.os.Bundle; import android.graphics.Color;
import android.view.Gravity; import android.widget.*;

public class MainActivity extends Activity {
  private RamiGame game; private TextView view;
  @Override public void onCreate(Bundle b){super.onCreate(b); build(); newGame();}
  private void build(){
    LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER); root.setPadding(28,28,28,28);
    TextView title=new TextView(this); title.setText("RamiMada"); title.setTextSize(28); title.setTextColor(Color.DKGRAY);
    view=new TextView(this); view.setTextSize(18); view.setGravity(Gravity.CENTER); view.setPadding(0,20,0,20);
    Button reset=new Button(this); reset.setText("Nouvelle donne"); Button draw=new Button(this); draw.setText("Piocher");
    root.addView(title); root.addView(view); root.addView(reset); root.addView(draw); setContentView(root);
    reset.setOnClickListener(v->newGame()); draw.setOnClickListener(v->{game.draw(); show();});
  }
  private void newGame(){game=new RamiGame(); show();}
  private void show(){view.setText("Main ("+game.hand().size()+"): "+game.hand()+"\nPioche: "+game.remaining());}
}
