package personalProj;

public class PostfixExpressions {

 public static void main(String[] args) throws Exception {

  char[] testArr = new char[] {'1', '2', '5', '+', '*'};
  int[] heapArr = new int[5];

  int top = -1;
  
  for (int i = 0; i < testArr.length; i++) {
   if (testArr[i] == '+') {
    int valueOne = heapArr[top--];
    int valueTwo = heapArr[top--];
    heapArr[++top] = valueTwo + valueOne;

   } else if (testArr[i] == '*') {
    int valueOne = heapArr[top--];
    int valueTwo = heapArr[top--];
    heapArr[++top] = valueTwo * valueOne;

   } else {
    heapArr[++top] = testArr[i] - '0'; // converts ASCII
   }
  }

  if (top != 0) {
   throw new Exception("Inappropriate Input");
  }
  
  System.out.println(heapArr[0]);
 }

}
