package ar.com.shows.service;

import ar.com.shows.exception.RequestException;
import ar.com.shows.model.ButacaTicket;
import ar.com.shows.model.Ticket;
import ar.com.shows.repository.ButacaTicketRepository;
import ar.com.shows.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ButacaTicketRepository butacaTicketRepository;

    public Ticket generateTicket(Long butacaTicketId, Ticket ticket) throws RequestException {
        ButacaTicket butacaTicket = butacaTicketRepository.findById(butacaTicketId)
                .orElseThrow(() -> new RequestException("No existe butacaTicket para el id enviado"));
        ticket.setNroTicket(genarateNroTicket(butacaTicket));
        Ticket ticketDB = ticketRepository.save(ticket);
        butacaTicket.setTicket(ticketDB);
        butacaTicketRepository.save(butacaTicket);
        return ticketDB;
    }

    private String genarateNroTicket(ButacaTicket butacaTicket) {
        StringBuilder sb = new StringBuilder();
        sb.append(butacaTicket.getSeccion()).append("-").append(butacaTicket.getButaca().getId());
        return sb.toString();
    }

}
