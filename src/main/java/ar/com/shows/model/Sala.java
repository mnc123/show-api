package ar.com.shows.model;


import javax.persistence.*;

@Entity
@Table(name = "SALAS")
public class Sala {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="teatro_id", nullable=false)
    private Teatro teatro;

    public Sala() {
        super();
    }

    public Teatro getTeatro() {
        return teatro;
    }

    public void setTeatro(Teatro teatro) {
        this.teatro = teatro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
