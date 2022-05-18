package com.github.forax.patternmatching;

import java.util.List;
import java.util.Objects;

public interface _2_poo {
  enum PEGI {
    PEGI12, PEGI16, PEGI18;

    int year() {
      int year;
      switch (this) {
        case PEGI12:
          year = 12;
          break;
        case PEGI16:
          year = 16;
          break;
        case PEGI18:
          System.out.println("DEBUG");
          year = 18;
          break;
        default:
          throw new AssertionError("unknown PEGI " + this);
      }
      return year;
    }
  }

  interface Item {
    int price();
  }
  class VideoGame implements Item {
    final String name;
    final PEGI rating;

    VideoGame(String name, PEGI rating) {
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
  }
  class ActionFigure implements Item {
    final String id;
    final String universe;

    ActionFigure(String id, String universe) {
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

    Box(List<Item> items) {
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
    if (item instanceof VideoGame) {
      var videoGame = (VideoGame) item;
      return videoGame.rating().year() * 50;
    }
    if (item instanceof ActionFigure) {
      var actionFigure = (ActionFigure) item;
      return actionFigure.universe().equals("Marvel")? 30: 20;
    }
    if (item instanceof Box) {
      var box = (Box) item;
      return 5 + box.items().stream().mapToInt(i -> price(i)).sum();
    }
    throw new AssertionError("oops");
  }
}
