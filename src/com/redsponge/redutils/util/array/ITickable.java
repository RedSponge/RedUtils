package com.redsponge.redutils.util.array;

import java.util.ArrayList;
import java.util.List;

public interface ITickable {

  List<ITickable> pending = new ArrayList<ITickable>();

  default void register() {
    pending.add(this);
  }

  void preTick();

  void postTick();

}
