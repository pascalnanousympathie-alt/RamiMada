package com.madamgames.ramimada;
import java.util.*;
public final class RamiGame{
 private final List<String>deck=new ArrayList<>(),hand=new ArrayList<>();private final Random random;
 public RamiGame(){this(new Random());}public RamiGame(Random r){random=Objects.requireNonNull(r);reset();}
 public void reset(){deck.clear();hand.clear();String[]s={"S","H","D","C"},r={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};for(String a:s)for(String b:r)deck.add(b+a);Collections.shuffle(deck,random);for(int i=0;i<10;i++)hand.add(deck.remove(0));}
 public String draw(){if(deck.isEmpty())return null;String c=deck.remove(0);hand.add(c);return c;}public int remaining(){return deck.size();}public List<String>hand(){return new ArrayList<>(hand);}
 public boolean isSet(List<String>cards){if(cards==null||cards.size()<3||cards.size()>4)return false;Set<String>r=new HashSet<>(),s=new HashSet<>();for(String c:cards){if(!isCard(c))return false;r.add(c.substring(0,c.length()-1));s.add(c.substring(c.length()-1));}return r.size()==1&&s.size()==cards.size();}
 private static boolean isCard(String c){return c!=null&&c.length()>=2&&"SHDC".contains(c.substring(c.length()-1))&&Arrays.asList("A","2","3","4","5","6","7","8","9","10","J","Q","K").contains(c.substring(0,c.length()-1));}
 public boolean isRun(List<String>cards){if(cards==null||cards.size()<3)return false;String suit=null;List<Integer>v=new ArrayList<>();for(String c:cards){if(!isCard(c))return false;String s=c.substring(c.length()-1);if(suit==null)suit=s;else if(!suit.equals(s))return false;v.add(rank(c));}Collections.sort(v);for(int i=1;i<v.size();i++)if(v.get(i)!=v.get(i-1)+1)return false;return new HashSet<>(v).size()==v.size();}
 private static int rank(String c){String r=c.substring(0,c.length()-1);return "A".equals(r)?1:"J".equals(r)?11:"Q".equals(r)?12:"K".equals(r)?13:Integer.parseInt(r);}
 public String saveState(){return String.join(",",deck)+";"+String.join(",",hand);}public boolean loadState(String state){try{String[]p=state.split(";",-1);if(p.length!=2)return false;List<String>d=parse(p[0]),h=parse(p[1]);if(d.size()+h.size()!=52)return false;Set<String>all=new HashSet<>(d);all.addAll(h);if(all.size()!=52)return false;for(String c:all)if(!isCard(c))return false;deck.clear();deck.addAll(d);hand.clear();hand.addAll(h);return true;}catch(Exception e){return false;}}
 private static List<String>parse(String s){return s.isEmpty()?new ArrayList<>():new ArrayList<>(Arrays.asList(s.split(",")));}
}
