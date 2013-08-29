package com.naiyi.jboss.ticketsystem.ejb;

import java.util.concurrent.Future;

import javax.ejb.Asynchronous;

import com.naiyi.jboss.ticketsystem.exception.NotEnoughMoneyException;
import com.naiyi.jboss.ticketsystem.exception.SeatBookedException;

public interface TheatreBooker {
   public String bookSeat(int seatId) throws SeatBookedException,NotEnoughMoneyException;
   @Asynchronous
	public Future<String> bookSeatAsync(int seatId);
}