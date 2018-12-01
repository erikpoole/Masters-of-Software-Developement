package personalProj;

public class ScratchObject {
 
 public boolean originallyFalse;
 
 public ScratchObject() {
  originallyFalse = false;
 }
 
 //pass by reference ?
 public static void changeValueObject(ScratchObject input) {
  input.originallyFalse = true;
 }
 
 //pass by value ?
 public static void changeValuePrimitive(Boolean input) {
  input = true;
 }
 
}
