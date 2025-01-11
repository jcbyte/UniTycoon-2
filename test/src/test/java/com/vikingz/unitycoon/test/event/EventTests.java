package com.vikingz.unitycoon.test.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vikingz.unitycoon.event.Event;
import com.vikingz.unitycoon.test.AbstractHeadlessGdxTest;
import org.junit.jupiter.api.Test;

/**
 * Tests checking {@link Event} and {@link Event.Option}.
 */
public class EventTests extends AbstractHeadlessGdxTest {
  @Test
  public void testEvent() {
    Runnable r = () -> {};

    Event.Option o0 = new Event.Option(r, "Opt0");
    Event e = new Event("Event", o0);
    assertEquals("Event", e.getEvent().getMessage());
    assertEquals(o0, e.getEvent().getOpt1());
    assertNull(e.getEvent().getOpt2());
    assertFalse(e.getEvent().hasChoice());

    Event.Option o1 = new Event.Option(r, "Opt1");
    Event.Option o2 = new Event.Option(r, "Opt2");
    Event e1 = new Event("Event1", o1, o2);
    assertEquals("Event1", e1.getEvent().getMessage());
    assertEquals(o1, e1.getEvent().getOpt1());
    assertEquals(o2, e1.getEvent().getOpt2());
    assertTrue(e1.getEvent().hasChoice());

    Event.Option o3 = new Event.Option(r, "Opt3");
    Event e2 = new Event(() -> new Event("Event2", o3));
    assertEquals("Event2", e2.getEvent().getMessage());
    assertEquals(o3, e2.getEvent().getOpt1());
    assertNull(e2.getEvent().getOpt2());
    assertFalse(e2.getEvent().hasChoice());
  }

  @Test
  public void testEventOption() {
    Runnable r = () -> {};
    Event.Option o = new Event.Option(r, "Hello");
    assertEquals("Hello", o.getText());
    assertEquals(r, o.getAction());
    assertFalse(o.isDisabled());

    Event.Option o1 = new Event.Option(r, "World!", true);
    assertEquals("World", o1.getText());
    assertEquals(r, o1.getAction());
    assertTrue(o1.isDisabled());
  }
}
