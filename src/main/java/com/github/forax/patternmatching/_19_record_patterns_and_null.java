package com.github.forax.patternmatching;

import java.util.List;
import java.util.Objects;

public interface _19_record_patterns_and_null {
  enum PEGI {
    PEGI12, PEGI16, PEGI18;

    public int year() {
      return switch (this) {
        case PEGI12 -> 12;
        case PEGI16 -> 16;
        case PEGI18 -> {
          System.out.println("DEBUG");
          yield 18;
        }
      };

      /* require Java 19
      int year;
      switch (this) {
        case null -> throw null;  // exhaustive
        case PEGI12 -> year = 12;
        case PEGI16 -> year = 16;
        case PEGI18 -> {
            System.out.println("DEBUG");
            year = 18;
          }
      }
      return year;
      */
    }
  }

  sealed interface Item /*permits VideoGame, ActionFigure, Box*/ {
    int price();
  }
  record VideoGame(String name, PEGI rating) implements Item {
    public VideoGame {
      Objects.requireNonNull(name);
      Objects.requireNonNull(rating);
    }

    @Override
    public int price() {
      return rating.year() * 50;
    }
  }
  record ActionFigure(String id, String universe) implements Item {
    public ActionFigure {
      Objects.requireNonNull(id);
      Objects.requireNonNull(universe);
    }

    @Override
    public int price() {
      return universe.equals("Marvel")? 30: 20;
    }
  }
  record Box(List<Item> items) implements Item {
    public Box {
      items = List.copyOf(items);
    }

    @Override
    public int price() {
      return 5 + items.stream().mapToInt(Item::price).sum();
    }
  }

  static int price(Item item) {
    return switch(item) {
      case VideoGame videoGame -> videoGame.rating().year() * 50;
      case ActionFigure actionFigure -> actionFigure.universe().equals("Marvel")? 30: 20;
      case Box box -> 5 + box.items().stream().mapToInt(i -> price(i)).sum();
    };

    /* require Java 19
    return switch(item) {
      case VideoGame(String __, PEGI rating) -> rating.year() * 50;
      case ActionFigure(int __, String universe) -> universe.equals("Marvel")? 30: 20;
      case Box(List<Item> items) -> 5 + items.stream().mapToInt(i -> price(i)).sum();
    };
     */
  }
}
