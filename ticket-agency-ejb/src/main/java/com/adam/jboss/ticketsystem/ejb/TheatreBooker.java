package com.adam.jboss.ticketsystem.ejb;

import java.util.concurrent.Future;

import javax.ejb.Asynchronous;

import com.adam.jboss.ticketsystem.exception.NotEnoughMoneyException;
import com.adam.jboss.ticketsystem.exception.SeatBookedException;

public interface TheatreBooker {
   public String bookSeat(int seatId) throws SeatBookedException,NotEnoughMoneyException;
   @Asynchronous
	public Future<String> bookSeatAsync(int seatId);
}