package ar.com.shows.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FUNCIONES")
public class Funcion {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FECHA")
    private Date fecha;

    @ManyToOne
    @JoinColumn(name="sala_id", nullable=false)
    private Sala sala;

    @ManyToOne
    @JoinColumn(name="show_id", nullable=false)
    private Show show;

    @OneToMany(mappedBy = "funcion",
            orphanRemoval = true)
    private Set<ButacaTicket> butacaTickets = new HashSet();

    public Funcion() {
        super();
    }


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ButacaTicket> getButacaTickets() {
        return butacaTickets;
    }

    public void setButacaTickets(Set<ButacaTicket> butacaTickets) {
        this.butacaTickets = butacaTickets;
    }
}
