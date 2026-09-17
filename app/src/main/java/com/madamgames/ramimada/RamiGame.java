package com.madamgames.ramimada;
import java.util.*;
public final class RamiGame {
  private final List<String> deck=new ArrayList<>(),hand=new ArrayList<>();private final Random random;
  public RamiGame(){this(new Random());}public RamiGame(Random r){random=Objects.requireNonNull(r);reset();}
  public void reset(){deck.clear();hand.clear();String[]s={"S","H","D","C"},r={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};for(String a:s)for(String b:r)deck.add(b+a);Collections.shuffle(deck,random);for(int i=0;i<10;i++)hand.add(deck.remove(0));}
  public String draw(){if(deck.isEmpty())return null;String c=deck.remove(0);hand.add(c);return c;}public int remaining(){return deck.size();}public List<String>hand(){return new ArrayList<>(hand);}
  public boolean isSet(List<String>cards){if(cards==null||cards.size()<3||cards.size()>4)return false;Set<String>r=new HashSet<>(),s=new HashSet<>();for(String c:cards){if(!isCard(c))return false;r.add(c.substring(0,c.length()-1));s.add(c.substring(c.length()-1));}return r.size()==1&&s.size()==cards.size();}
  private static boolean isCard(String c){return c!=null&&c.length()>=2&&"SHDC".contains(c.substring(c.length()-1))&&Arrays.asList("A","2","3","4","5","6","7","8","9","10","J","Q","K").contains(c.substring(0,c.length()-1));}
}
