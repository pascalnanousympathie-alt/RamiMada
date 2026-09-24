package com.madamgames.ramimada;

import android.app.Activity;import android.os.Bundle;import android.graphics.Color;import android.view.Gravity;import android.widget.*;import android.content.SharedPreferences;

public class MainActivity extends Activity{
 private RamiGame game;private TextView view;private EditText discardInput;private SharedPreferences prefs; private CalmMusic music; private Button musicButton;
 @Override public void onCreate(Bundle b){super.onCreate(b);music=new CalmMusic(this);prefs=getSharedPreferences("rami",0);build();if(!restore())newGame();}
 private TextView label(String text,int size){TextView v=new TextView(this);v.setText(text);v.setTextSize(size);v.setGravity(Gravity.CENTER);v.setPadding(0,10,0,10);return v;}
 private void build(){
  LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setGravity(Gravity.CENTER_HORIZONTAL);root.setPadding(20,22,20,16);
  TextView title=label("RamiMada · 52 cartes",28);title.setTextColor(Color.rgb(35,45,55));view=label("",17);
  Button draw=new Button(this);draw.setText("Piocher une carte");discardInput=new EditText(this);discardInput.setHint("Carte à défausser (ex. 7S)");Button discard=new Button(this);discard.setText("Défausser");Button reset=new Button(this);reset.setText("Nouvelle donne");
  root.addView(title);root.addView(view);root.addView(draw);root.addView(discardInput);root.addView(discard);root.addView(reset);musicButton=new Button(this);musicButton.setText(musicLabel());musicButton.setOnClickListener(v->{music.toggle();musicButton.setText(musicLabel());});root.addView(musicButton);setContentView(root);
  draw.setOnClickListener(v->{String c=game.draw();show(c==null?"Pioche vide":"Carte tirée: "+c);persist();});discard.setOnClickListener(v->{String c=discardInput.getText().toString().trim().toUpperCase(java.util.Locale.ROOT);if(game.discard(c)){discardInput.setText("");show("Carte défaussée: "+c);persist();}else Toast.makeText(this,"Carte absente de la main",Toast.LENGTH_SHORT).show();});reset.setOnClickListener(v->{newGame();persist();});
 }
 private void newGame(){game=new RamiGame();show("Nouvelle donne");}
 private boolean restore(){String s=prefs.getString("state","");if(s.isEmpty())return false;game=new RamiGame();if(game.loadState(s)){show("Partie restaurée");return true;}return false;}
 private void persist(){prefs.edit().putString("state",game.saveState()).apply();}
 private void show(String event){view.setText(event+"\nMain ("+game.hand().size()+"): "+game.hand()+"\nPioche: "+game.remaining()+"\nValeur: "+game.handValue());}
 @Override protected void onStart(){super.onStart();if(music!=null)music.start();}
 @Override protected void onStop(){if(music!=null)music.stop();super.onStop();if(game!=null)persist();}
 private String musicLabel(){return music.enabled()?"🎵 Mozika: MISOKATRA":"🎵 Mozika: NOKATONA";}

}
