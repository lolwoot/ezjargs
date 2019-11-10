package com.lolwoot.ezjargs.processors;

import com.lolwoot.ezjargs.ArgumentLine;

public interface Processor<T> {
  public T process(ArgumentLine line);
}
