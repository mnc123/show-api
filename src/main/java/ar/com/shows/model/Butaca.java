package ar.com.shows.model;


import javax.persistence.*;

@Entity
@Table(name = "BUTACAS")
public class Butaca {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="sala_id", nullable=false)
    private Sala sala;

    public Butaca() {
        super();
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
