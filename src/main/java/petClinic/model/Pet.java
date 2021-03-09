package petClinic.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table (name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long petId;

    @Column(name = "race")
    private String race;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "is_vaccinated")
    private Boolean isVaccinated;

    @OneToMany(mappedBy = "pet")
    private List<Consult> consults;

    public List<Consult> getConsults() {
        return consults;
    }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Pet() { }

    public Pet(String race, String birthDate, boolean isVaccinated, Owner owner) {
        this.race = race;
        this.birthDate = birthDate;
        this.isVaccinated = isVaccinated;
        this.owner = owner;

    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        isVaccinated = vaccinated;
    }

   }
