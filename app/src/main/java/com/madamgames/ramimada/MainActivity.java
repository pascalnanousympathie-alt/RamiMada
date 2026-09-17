package com.madamgames.ramimada;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
  private TextView cardView;
  private final List<String> deck = new ArrayList<>();

  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    LinearLayout root = new LinearLayout(this);
    root.setOrientation(LinearLayout.VERTICAL); root.setGravity(Gravity.CENTER);
    root.setPadding(32, 32, 32, 32);
    TextView title = new TextView(this); title.setText("RamiMada");
    title.setTextSize(30); title.setTextColor(Color.DKGRAY);
    cardView = new TextView(this); cardView.setTextSize(22); cardView.setGravity(Gravity.CENTER);
    Button newGame = new Button(this); newGame.setText("Nouvelle donne");
    Button draw = new Button(this); draw.setText("Piocher une carte");
    root.addView(title); root.addView(cardView); root.addView(newGame); root.addView(draw);
    setContentView(root); resetDeck();
    newGame.setOnClickListener(v -> resetDeck());
    draw.setOnClickListener(v -> drawCard());
  }

  private void resetDeck() {
    deck.clear();
    String[] suits = {"♠", "♥", "♦", "♣"};
    String[] ranks = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    for (String s : suits) for (String r : ranks) deck.add(r + s);
    Collections.shuffle(deck); cardView.setText("52 cartes · Pioche prête");
  }

  private void drawCard() {
    cardView.setText(deck.isEmpty() ? "Pioche vide" : "Carte: " + deck.remove(0) + "\nRestantes: " + deck.size());
  }
}
