package com.madamgames.ramimada;

import android.app.Activity; import android.os.Bundle; import android.graphics.Color; import android.view.Gravity; import android.widget.*;

public class MainActivity extends Activity {
  private RamiGame game; private TextView view;
  @Override public void onCreate(Bundle b){super.onCreate(b); build(); newGame();}
  private void build(){
    LinearLayout root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER); root.setPadding(22,30,22,22);
    TextView title=new TextView(this); title.setText("RamiMada"); title.setTextSize(28); title.setTextColor(Color.DKGRAY);
    view=new TextView(this); view.setTextSize(17); view.setGravity(Gravity.CENTER); view.setPadding(0,15,0,15);
    Button draw=new Button(this); draw.setText("Piocher une carte"); Button reset=new Button(this); reset.setText("Nouvelle donne");
    root.addView(title); root.addView(view); root.addView(draw); root.addView(reset); setContentView(root);
    draw.setOnClickListener(v->{String c=game.draw(); show(c==null?"Pioche vide":"Carte tirée: "+c);}); reset.setOnClickListener(v->newGame());
  }
  private void newGame(){game=new RamiGame(); show("Nouvelle donne");}
  private void show(String event){view.setText(event+"\nMain ("+game.hand().size()+"): "+game.hand()+"\nPioche: "+game.remaining());}
}
