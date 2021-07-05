package ar.com.shows.rest;

import ar.com.shows.exception.RequestException;
import ar.com.shows.filter.FuncionFilter;
import ar.com.shows.filter.ShowOrder;
import ar.com.shows.model.Funcion;
import ar.com.shows.model.Show;
import ar.com.shows.model.Ticket;
import ar.com.shows.service.FuncionService;
import ar.com.shows.service.ShowService;
import ar.com.shows.service.TicketService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class ShowBusinessRest {

    private static final Logger logger = Logger.getLogger(ShowBusinessRest.class.getName());

    @Autowired
    ShowService showService;

    @Autowired
    FuncionService funcionService;

    @Autowired
    TicketService ticketService;

    @GetMapping(value = "/funciones" , produces = "application/json")
    public List<Funcion> getFunciones(@ModelAttribute Funcion funcion,
                                      @ModelAttribute FuncionFilter funcionFilter,
                                      @ModelAttribute ShowOrder showOrder,
                                      @RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "size") Integer size) {
        return funcionService.getFunciones(funcion, funcionFilter, showOrder, page, size);
    }

    @GetMapping(value = "/funciones/{id}" , produces = "application/json")
    public ResponseEntity getFuncionesById(@PathVariable(value = "id") Long id) {
        Optional<Funcion> response = funcionService.getFuncionesById(id);
        if(response.isPresent()) {
            return ResponseEntity.ok(response.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/shows" , produces = "application/json")
    public List<Show> getShows() {
        return showService.getShows();
    }

    @PostMapping(value = "/butacaTicket/{id}/ticket")
    public ResponseEntity insertTicket(@PathVariable(value = "id") Long butacaTicketId, @RequestBody Ticket ticket) throws Exception {
        try {
            Ticket response = ticketService.generateTicket(butacaTicketId, ticket);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RequestException e) {
            logger.severe(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ImmutableMap.of("error", e.getMessage()));
        } catch (Exception e) {
            logger.severe(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ImmutableMap.of("error", e.getMessage()));
        }
    }


}
