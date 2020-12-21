package day21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Food {
  int idx;
  List<String> ingredients;
  List<String> allergens;

  public Food (String line[]) {
    ingredients = Arrays.asList(line[0].split(" "));
    String s = line[1].trim();
    s = s.substring(0, s.length()-1); // remove the ')'
    allergens = Arrays.asList(s.split((", *")));
  }

  public static void main(String[] args) throws IOException {
    BufferedReader read = new BufferedReader(new InputStreamReader(Food.class.getResourceAsStream("data.txt")));
//    var parts = read.lines().mapToLong(Long::parseLong).toArray();
    var foods = read.lines().map(ligne -> ligne.split("\\(contains ")).collect(Collectors.toList());

    ArrayList<Food> foodList = new ArrayList<>();
    Map<String, Set <String>> allergens = new HashMap<>();

    for (String line[] : foods) {
      Food food = new Food(line);
      foodList.add(food);
      for (String al : food.allergens)
        allergens.compute(al, (all, res) -> {
          HashSet<String> set = new HashSet<>();
          set.addAll(food.ingredients);
          if (res == null) {
            return set;
          } else {
            res.retainAll(set);
            return res;
          }
        });
    } // for line in foods

    TreeMap<String,String> known = new TreeMap<>();
    boolean changed = true;
    while (changed) {
      changed = false;
      for (var e : allergens.entrySet()) {
        if (e.getValue().size() == 1) {
          // this allergen is known
          String allergen = e.getValue().iterator().next();
          known.put(allergen, e.getKey());
          for (var e2 : allergens.entrySet()) {
            changed = e2.getValue().remove(allergen) || changed; // remove from other possibilites
          }
        } // if unique
      } // for e in allergens
    } // while changed

    System.out.println(known.toString());

    System.out.println();

    System.out.println(allergens.toString());

    System.out.println();

    System.out.println(foodList.stream().flatMap(f->f.ingredients.stream()).filter(i -> !known.containsKey(i)).count());

    System.out.println(known.entrySet().stream().sorted((e1,e2) -> e1.getValue().compareTo(e2.getValue())).map(Map.Entry::getKey).collect(Collectors.joining(",")));
  } // main
} // Exo
