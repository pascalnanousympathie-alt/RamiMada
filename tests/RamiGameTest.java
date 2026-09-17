package com.madamgames.ramimada;
public final class RamiGameTest {
  static void ok(boolean x,String m){if(!x)throw new AssertionError(m);}
  public static void main(String[] a){
    RamiGame g=new RamiGame(new java.util.Random(7));
    ok(g.hand().size()==10,"initial hand"); ok(g.remaining()==42,"remaining deck");
    String c=g.draw(); ok(c!=null,"draw card"); ok(g.hand().size()==11,"hand grows");
    ok(g.isSet(java.util.Arrays.asList("7S","7H","7D")),"valid set");
    ok(!g.isSet(java.util.Arrays.asList("7S","8H","9D")),"invalid set");
    System.out.println("RAMI_TEST_PASS");
  }
}
