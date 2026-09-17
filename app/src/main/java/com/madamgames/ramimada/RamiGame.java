package com.madamgames.ramimada;

import java.util.*;

public final class RamiGame {
  private final List<String> deck=new ArrayList<>();
  private final List<String> hand=new ArrayList<>();
  private final Random random;
  public RamiGame(){this(new Random());}
  public RamiGame(Random r){random=r; reset();}
  public void reset(){
    deck.clear(); hand.clear(); String[] suits={"S","H","D","C"}; String[] ranks={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    for(String s:suits) for(String x:ranks) deck.add(x+s); Collections.shuffle(deck,random); for(int i=0;i<10;i++) hand.add(deck.remove(0));
  }
  public String draw(){if(deck.isEmpty()) return null; String c=deck.remove(0); hand.add(c); return c;}
  public int remaining(){return deck.size();} public List<String> hand(){return new ArrayList<>(hand);}
  public boolean isSet(List<String> cards){
    if(cards.size()<3||cards.size()>4)return false; Set<String> ranks=new HashSet<>(); Set<String> suits=new HashSet<>();
    for(String c:cards){if(c.length()<2)return false; String r=c.substring(0,c.length()-1); String s=c.substring(c.length()-1); ranks.add(r); suits.add(s);}
    return ranks.size()==1 && suits.size()==cards.size();
  }
}
