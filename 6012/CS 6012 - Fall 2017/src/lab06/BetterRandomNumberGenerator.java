package lab06;

public class BetterRandomNumberGenerator implements RandomNumberGenerator {

 private long thisSeed;
 long thisConst1;
 long thisConst2;

 @Override
 public int nextInt(int max) {
  thisSeed = (thisConst1 * thisSeed + thisConst2) % max;
  return (int) thisSeed;
 }

 @Override
 public void setSeed(long seed) {
  thisSeed = seed;
 }

 @Override
 public void setConstants(long const1, long const2) {
  thisConst1 = const1;
  thisConst2 = const2;
 }

}
