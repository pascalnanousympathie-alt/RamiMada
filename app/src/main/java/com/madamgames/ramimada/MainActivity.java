package com.madamgames.ramimada;

import android.app.Activity;import android.os.Bundle;import android.graphics.Color;import android.view.Gravity;import android.widget.*;import android.content.SharedPreferences;

public class MainActivity extends Activity{
 private RamiGame game;private TextView view;private SharedPreferences prefs;
 @Override public void onCreate(Bundle b){super.onCreate(b);prefs=getSharedPreferences("rami",0);build();if(!restore())newGame();}
 private TextView label(String text,int size){TextView v=new TextView(this);v.setText(text);v.setTextSize(size);v.setGravity(Gravity.CENTER);v.setPadding(0,10,0,10);return v;}
 private void build(){
  LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setGravity(Gravity.CENTER_HORIZONTAL);root.setPadding(24,28,24,18);
  TextView title=label("RamiMada · 52 cartes",28);title.setTextColor(Color.DKGRAY);view=label("",17);
  Button draw=new Button(this);draw.setText("Piocher une carte");Button reset=new Button(this);reset.setText("Nouvelle donne");
  root.addView(title);root.addView(view);root.addView(draw);root.addView(reset);setContentView(root);
  draw.setOnClickListener(v->{String c=game.draw();show(c==null?"Pioche vide":"Carte tirée: "+c);persist();});reset.setOnClickListener(v->{newGame();persist();});
 }
 private void newGame(){game=new RamiGame();show("Nouvelle donne");}
 private boolean restore(){String s=prefs.getString("state","");if(s.isEmpty())return false;game=new RamiGame();if(game.loadState(s)){show("Partie restaurée");return true;}return false;}
 private void persist(){prefs.edit().putString("state",game.saveState()).apply();}
 private void show(String event){view.setText(event+"\nMain ("+game.hand().size()+"): "+game.hand()+"\nPioche: "+game.remaining());}
 @Override protected void onStop(){super.onStop();if(game!=null)persist();}
}
