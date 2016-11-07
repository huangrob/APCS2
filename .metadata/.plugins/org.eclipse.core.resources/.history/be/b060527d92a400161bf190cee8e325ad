// Robert Huang
// APCS Period 1
// 11/6/16
// Arrays Lab 3


import java.util.Arrays;


public class ArraysLab3 {
	
	// sum: adds the values at the same indices in two arrays
	// returns an array
	public static int[] sum(int[] arr1, int[] arr2)
	{
		// assume arr1 and arr2 length is greater than 0
		assert (arr1.length > 0);
		assert (arr2.length > 0);
		// assume the lengths of the two arrays are equal
		assert (arr1.length == arr2.length);
		int[] sum = new int[arr1.length];
		// adds the values at each index to a new array
		for (int i = 0; i < arr1.length; i++){
			sum[i] = arr1[i] + arr2[i];	
		}
		return sum;
	}
		
	// append: adds an integer to the end of an array
	// returns an array
	public static int[] append(int[] arr, int num)
	{
		// assume array length greater than 0
		assert (arr.length > 0);
		// makes an array that is 1 larger in length than arr
		int[] appendArr = new int[arr.length + 1];
		// copy arr into appendArr
		for (int i = 0; i <= arr.length - 1; i++){
			appendArr[i] = arr[i];
		}
		// last element is num
		appendArr[arr.length] = num;
		return appendArr;
	}
	
	// remove: removes an element at an index from the array
	// returns an array
	public static int[] remove(int[] arr, int idx)
	{
		// assume arr length is at least 2
		assert (arr.length >= 2);
		// creates an array with 1 less length than arr
		int[] removeIdx = new int[arr.length - 1];
		//
		for (int i = 0; i < idx; i++){
			removeIdx[i] = arr[i];
		}
		for (int i = idx + 1; i <= arr.length-1; i++){
			removeIdx[i-1] = arr[i];
		}
		return removeIdx;
	}
	
	// sumEven: adds the elements at even indices of an array
	// returns an integer
	public static int sumEven(int[] arr)
	{
		assert (arr.length > 0);
		int sumEven = 0;
		for (int i = 0; i <= arr.length - 1; i += 2){
			sumEven += arr[i];
		}
		return sumEven;
		
	}

	// rotateRight: shifts all elements one index to the right
	// last element moves to index 0
	// returns an array
	public static int[] rotateRight(int[] arr)
	{
		assert (arr.length > 0);
		int last = arr[arr.length - 1];
		for (int i = arr.length-1; i > 0; i--) {
			arr[i] = arr[i-1];
		}
		arr[0] = last;
		return arr;
		
	}
	
	// main method: tests and prints out the previous 5 methods
	public static void main(String[] args) {
		int[] a1 = {5, 10, 15, 20, 25, 30, 35, 40};
		int[] a2 = {7, 14, 21, 28, 35, 42, 49, 56};
		int [] sumArr = sum(a1, a2);
		int appendNum = 200;
		int [] appendArr = append(a1, appendNum);
		int removeIdx = 5;
		int [] removeArr = remove(a2, removeIdx);
		int sumOfEvens = sumEven(appendArr);
		rotateRight(a1);
		System.out.println(Arrays.toString(sumArr));
		System.out.println(Arrays.toString(appendArr));
		System.out.println(Arrays.toString(removeArr));
		System.out.println(sumOfEvens);
		System.out.println(Arrays.toString(a1));
	}
}
	






