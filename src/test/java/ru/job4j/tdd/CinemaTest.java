package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class CinemaTest {
    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(List.of(new Session3D())));
    }

    @Ignore
    @Test
    public void whenNotFind() {
        Cinema cinema = new Cinema3D();
        List<Session> sessions = cinema.find(session -> true);
        assertNull(sessions);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTheSameTicketException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 3, 3, date);
        Ticket secondTicket = cinema.buy(account, 3, 3, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 25, 1, 15, 0);
        Ticket ticket = cinema.buy(account, 3, 3, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidSeat() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 05, 20, 15, 0);
        Ticket ticket = cinema.buy(account, 300, 3, date);
    }
}