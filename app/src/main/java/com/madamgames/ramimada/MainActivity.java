package com.madamgames.ramimada;
import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.*;
import android.content.SharedPreferences;
public class MainActivity extends Activity{
 private RamiGame game;private TextView view;private EditText discardInput;private SharedPreferences prefs;private CalmMusic music;private Button musicButton;
 @Override public void onCreate(Bundle b){super.onCreate(b);music=new CalmMusic(this);prefs=getSharedPreferences("rami",0);build();if(!restore())newGame();}
 private void build(){
  ScrollView scroll=new ScrollView(this);scroll.setFillViewport(true);LinearLayout root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(BrandUi.dp(this,18),BrandUi.dp(this,16),BrandUi.dp(this,18),BrandUi.dp(this,22));root.setBackgroundColor(Color.rgb(7,19,38));scroll.addView(root);
  BrandUi.header(this,root,"RamiMada","Cartes • stratégie • fluide",Color.rgb(151,36,61));
  root.addView(BrandUi.hero(this,"Rami premium","Composez votre main avec précision.",Color.rgb(224,82,109),Color.rgb(82,28,44)),new LinearLayout.LayoutParams(-1,BrandUi.dp(this,104)));
  Button draw=BrandUi.button(this,"Piocher",Color.rgb(164,36,64),Color.rgb(216,79,99));root.addView(draw,new LinearLayout.LayoutParams(-1,BrandUi.dp(this,56)));
  discardInput=new EditText(this);discardInput.setHint("Carte à défausser");discardInput.setSingleLine(true);discardInput.setTextSize(15);discardInput.setPadding(14,0,14,0);GradientDrawable ig=new GradientDrawable();ig.setColor(Color.WHITE);ig.setCornerRadius(18);ig.setStroke(1,Color.rgb(231,210,217));discardInput.setBackground(ig);LinearLayout.LayoutParams ip=new LinearLayout.LayoutParams(-1,BrandUi.dp(this,52));ip.topMargin=10;root.addView(discardInput,ip);
  Button discard=BrandUi.button(this,"Défausser",Color.rgb(92,42,58),Color.rgb(138,64,84));root.addView(discard,new LinearLayout.LayoutParams(-1,BrandUi.dp(this,52)));
  Button reset=BrandUi.button(this,"Nouvelle donne",Color.rgb(42,45,58),Color.rgb(75,80,96));root.addView(reset,new LinearLayout.LayoutParams(-1,BrandUi.dp(this,52)));
  LinearLayout state=BrandUi.card(this,Color.rgb(244,226,231));state.addView(BrandUi.text(this,"Votre table",14,Color.rgb(190,55,78),true));view=BrandUi.text(this,"",14.5f,Color.rgb(55,65,81),false);view.setPadding(0,8,0,0);state.addView(view);root.addView(state);
  musicButton=BrandUi.button(this,"♫  Son • ON",Color.rgb(69,29,43),Color.rgb(107,48,66));root.addView(musicButton,new LinearLayout.LayoutParams(-1,BrandUi.dp(this,50)));
  draw.setOnClickListener(v->{String c=game.draw();show(c==null?"Pioche vide":"Carte tirée : "+c);persist();});discard.setOnClickListener(v->{String c=discardInput.getText().toString().trim().toUpperCase(java.util.Locale.ROOT);if(game.discard(c)){discardInput.setText("");show("Carte défaussée : "+c);persist();}else Toast.makeText(this,"Carte absente de la main",Toast.LENGTH_SHORT).show();});reset.setOnClickListener(v->{newGame();persist();});musicButton.setOnClickListener(v->{music.toggle();musicButton.setText(musicLabel());});setContentView(scroll);
 }
 private void newGame(){game=new RamiGame();show("Nouvelle donne");}
 private boolean restore(){String s=prefs.getString("state","");if(s.isEmpty())return false;game=new RamiGame();if(game.loadState(s)){show("Partie restaurée");return true;}return false;}
 private void persist(){prefs.edit().putString("state",game.saveState()).apply();}
 private void show(String event){view.setText(event+"\nMain ("+game.hand().size()+") : "+game.hand()+"\nPioche : "+game.remaining()+"\nValeur : "+game.handValue());}
 @Override protected void onStart(){super.onStart();if(music!=null)music.start();}
 @Override protected void onStop(){if(music!=null)music.stop();super.onStop();if(game!=null)persist();}
 private String musicLabel(){return music.enabled()?"Son • ON":"Son • OFF";}
}