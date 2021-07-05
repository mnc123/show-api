package ar.com.shows.service;

import ar.com.shows.model.Show;
import ar.com.shows.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ShowService {

    private static final Logger logger = Logger.getLogger(ShowService.class.getName());


    @Autowired
    ShowRepository showRepository;


    ShowService() {
    }

    public List<Show> getShows() {
        return showRepository.findAll();
    }

}
