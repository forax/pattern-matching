package com.github.forax.patternmatching;

import com.github.forax.patternmatching._5_enhanced_instanceof.VideoGame;

import java.util.List;
import java.util.Objects;

public interface _0_gameshop {
  enum PEGI {
    PEGI12, PEGI16, PEGI18;

    public int year() {
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

  interface Item {}
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
    public boolean equals(Object obj) {
      if (!(obj instanceof VideoGame)) {
        return false;
      }
      VideoGame videoGame = (VideoGame) obj;
      return rating == videoGame.rating && name.equals(videoGame.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(name, rating);
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
  }
  class Box implements Item {
    final List<Item> items;

    public Box(List<Item> items) {
      this.items = List.copyOf(items);
    }

    public List<Item> items() {
      return items;
    }
  }
}
