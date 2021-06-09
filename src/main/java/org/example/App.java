package org.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * # Making Change
 * # Given a number "x" and a sorted array of coins "coinset", write a function
 * # that returns the amounts for each coin in the coinset that sums up to X or
 * # indicate an error if there is no way to make change for that x with the given
 * # coinset. For example, with x=7 and a coinset of [1,5,10,25], a valid answer
 * # would be {1: 7} or {1: 2, 5: 1}. With x = 3 and a coinset of [2,4] it should
 * # indicate an error. Bonus points for optimality.
 *
 * # Use the following examples to test it out
 *
 * # A. x = 6 coinset = [1,5,10,25]
 * # B. x = 6, coinset = [3,4]
 * # C. x = 6, coinset = [1,3,4]
 * # D. x = 6, coinset = [5,7]
 *
 *
 * def make_change(x, coinset):
 *   return {}
 *
 * print make_change(6, [1,5,10,25])
 * print make_change(6, [3,4])
 * print make_change(6, [1,3,4])
 * print make_change(6, [5,7])
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(4);
        System.out.println(App.makeChange(3, list)); // null
        list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(10);
        list.add(25);
        System.out.println(App.makeChange(7,list));

        list = new ArrayList<>();
        list.add(3);
        list.add(4);
        System.out.println(App.makeChange(6, list));

        list = new ArrayList<>();
        list.add(1);
        list.add(3);
        list.add(4);
        System.out.println(App.makeChange(6, list));

        list = new ArrayList<>();
        list.add(5);
        list.add(7);
        System.out.println(App.makeChange(6, list));

        /*
null
{1=2, 5=1}
{3=2}
{1=2, 4=1}
null

null
{1=2, 5=1}
{3=2}
{3=2}
null
         */

    }


    public static Map<Integer, Integer> makeChange(int x, List<Integer> coinset) {
        if (x < 0) throw new IllegalArgumentException("x can't be negative");
        if (coinset == null || coinset.isEmpty()) throw new IllegalArgumentException("coinset cannot be empty");

        Map<Integer, Integer> change = new HashMap<>();

        Integer coinCountOfSolution = null;
        Map<Integer, Integer> bestSolution = null;

        if (x < coinset.get(0)) return null; // no solution found

        for (int i = coinset.size()-1; i >= 0; i--) {
            Integer currentCoin = coinset.get(i);
            if (x == currentCoin) {
                change.put(currentCoin, 1);
                return change;
            } else if (x > currentCoin) {
                change.put(currentCoin, 1);
                Map<Integer, Integer> recur = makeChange(x - currentCoin, coinset);
                if (recur != null) {
                    Map<Integer, Integer> merged = mergeMaps(change, recur);
                    Integer mergedCount = countCoins(merged);
                    if (coinCountOfSolution == null || coinCountOfSolution > mergedCount) {
                        coinCountOfSolution = mergedCount;
                        bestSolution = new HashMap<>(merged);
                    }
                }
                change.clear();
            } else {
                // x < current coin
            }
        }
        return bestSolution;
    }

    private static Integer countCoins(Map<Integer, Integer> change) {
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : change.entrySet()) {
            count += entry.getValue();
        }
        return count;
    }

    private static Map<Integer, Integer> mergeMaps(Map<Integer, Integer> change, Map<Integer, Integer> recur) {
        for (Map.Entry<Integer, Integer> entry : recur.entrySet()) {
            Integer key = entry.getKey();
            Integer prevValue = change.get(key) == null ? 0 : change.get(key);
            change.put(key, prevValue + entry.getValue());
        }
        return change;
    }
}
