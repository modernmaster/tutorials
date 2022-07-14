package net.kinguin.leadership.core.factory;

import net.kinguin.leadership.core.Member;

public abstract class AbstractClusterFactory {

  public static final String MODE_SINGLE = "single";
  public static final String MODE_MULTI = "multi";
  protected String mode;

  abstract public Member build();
}
