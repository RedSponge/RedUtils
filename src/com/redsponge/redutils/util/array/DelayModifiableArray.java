package com.redsponge.redutils.util.array;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class DelayModifiableArray<T> implements Iterable<T>, ITickable, List<T> {

  private ArrayList<T> values, afterTick;
  private boolean beforeTick;
  public DelayModifiableArray() {
    values = new ArrayList<>();
    afterTick = new ArrayList<>();
    beforeTick = true;
    register();
  }

  @Override
  public void preTick() {
    afterTick = new ArrayList<>(values);
    beforeTick = false;
  }

  @Override
  public void postTick() {
    values = afterTick;
    beforeTick = true;
  }

  private ArrayList<T> modify() {
    if(beforeTick) return values;
    return afterTick;
  }

  @Override
  public int size() {
    return modify().size();
  }

  @Override
  public boolean isEmpty() {
    return modify().isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return modify().contains(o);
  }

  @Override
  public Iterator<T> iterator() {
    return values.iterator();
  }

  @Override
  public Object[] toArray() {
    return modify().toArray();
  }

  @Override
  public <T1> T1[] toArray(T1[] a) {
    return modify().toArray(a);
  }

  @Override
  public boolean add(T t) {
    return modify().add(t);
  }

  @Override
  public boolean remove(Object o) {
    return modify().remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return modify().containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends T> c) {
    return modify().addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    return modify().addAll(index, c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return modify().removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return modify().retainAll(c);
  }

  @Override
  public void clear() {
    modify().clear();
  }

  @Override
  public T get(int index) {
    return modify().get(index);
  }

  @Override
  public T set(int index, T element) {
    return modify().set(index, element);
  }

  @Override
  public void add(int index, T element) {
    modify().add(index, element);
  }

  @Override
  public T remove(int index) {
    return modify().remove(index);
  }

  @Override
  public int indexOf(Object o) {
    return modify().indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return modify().lastIndexOf(o);
  }

  @Override
  public ListIterator<T> listIterator() {
    return modify().listIterator();
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    return modify().listIterator(index);
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return modify().subList(fromIndex, toIndex);
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    modify().forEach(action);
  }

  @Override
  public Spliterator<T> spliterator() {
    return modify().spliterator();
  }
}
