package com.madamgames.ramimada;
public final class RamiGameTest{
 static void ok(boolean x,String m){if(!x)throw new AssertionError(m);}
 public static void main(String[]a){
  RamiGame g=new RamiGame(new java.util.Random(7));ok(g.hand().size()==10,"initial hand");ok(g.remaining()==42,"remaining deck");ok(g.canDraw(),"draw available");String c=g.draw();ok(c!=null,"draw card");ok(g.hand().size()==11,"hand grows");int value=g.handValue();ok(value>0,"hand value");ok(g.discard(c),"discard drawn card");ok(g.hand().size()==10,"discard reduces hand");ok(g.discardPile().size()==1,"discard pile tracks card");ok(!g.discard(c),"duplicate discard blocked");
  ok(g.isSet(java.util.Arrays.asList("7S","7H","7D")),"valid set");ok(!g.isSet(java.util.Arrays.asList("7S","8H","9D")),"invalid set");ok(!g.isSet(java.util.Arrays.asList("7S","7S","7D")),"duplicate card blocked");ok(!g.isSet(java.util.Arrays.asList("XS","7H","7D")),"invalid card blocked");
  ok(g.isRun(java.util.Arrays.asList("5S","6S","7S")),"valid run");ok(!g.isRun(java.util.Arrays.asList("5S","6H","7S")),"mixed suit run blocked");
  String saved=g.saveState();RamiGame r=new RamiGame(new java.util.Random(9));ok(r.loadState(saved),"state restores");ok(r.hand().equals(g.hand()),"hand restored");ok(r.remaining()==g.remaining(),"deck restored");System.out.println("RAMI_TEST_PASS");
 }
}
