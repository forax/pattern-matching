package com.github.forax.patternmatching;

import java.util.List;
import java.util.Objects;

public interface _5_enhanced_instanceof {
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

  interface Item {
    int price();
  }
  class VideoGame implements Item {
    final String name;
    final PEGI rating;

    public VideoGame(String name, PEGI rating) {
      this.name = Objects.requireNonNull(name);
      this.rating = Objects.requireNonNull(rating);
    }

    public String name() {
      return name;
    }
    public PEGI rating() {
      return rating;
    }

    @Override
    public int price() {
      return rating.year() * 50;
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof VideoGame videoGame
          && rating == videoGame.rating
          && name.equals(videoGame.name);
    }
  }
  class ActionFigure implements Item {
    final String id;
    final String universe;

    public ActionFigure(String id, String universe) {
      this.id = Objects.requireNonNull(id);
      this.universe = Objects.requireNonNull(universe);
    }

    public String id() {
      return id;
    }
    public String universe() {
      return universe;
    }

    @Override
    public int price() {
      return universe.equals("Marvel")? 30: 20;
    }
  }
  class Box implements Item {
    final List<Item> items;

    public Box(List<Item> items) {
      this.items = List.copyOf(items);
    }

    public List<Item> items() {
      return items;
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