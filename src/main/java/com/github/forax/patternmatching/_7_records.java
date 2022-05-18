package com.github.forax.patternmatching;

import java.util.List;
import java.util.Objects;

public interface _7_records {
  enum PEGI {
    PEGI12, PEGI16, PEGI18;

    int year() {
      return switch (this) {
        case PEGI12 -> 12;
        case PEGI16 -> 16;
        case PEGI18 -> {
          System.out.println("DEBUG");
          yield 18;
        }
      };
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
    if (item instanceof VideoGame videoGame) {
      return videoGame.rating().year() * 50;
    }
    if (item instanceof ActionFigure actionFigure) {
      return actionFigure.universe().equals("Marvel")? 30: 20;
    }
    if (item instanceof Box box) {
      return 5 + box.items().stream().mapToInt(i -> price(i)).sum();
    }
    throw new AssertionError("oops");
  }
}
