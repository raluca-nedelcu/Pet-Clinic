package petClinic.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table (name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "petId")
    private Long petId;

    @Column(name = "race")
    private String race;

    @Column(name = "birthDate")
    private String birthDate;

    @Column(name = "isVaccinated")
    private Boolean isVaccinated;

    @Column (name = "ownerName")
    private String ownerName;

    @OneToMany(mappedBy = "consult")
    private List<Consult> consults;

    public List<Consult> getConsults() {
        return consults;
    }

    public void setConsults(List<Consult> consults) {
        this.consults = consults;
    }

    public Pet() { }

    public Pet(Long petId, String race, String birthDate, Boolean isVaccinated, String ownerName) {
        this.petId = petId;
        this.race = race;
        this.birthDate = birthDate;
        this.isVaccinated = isVaccinated;
        this.ownerName = ownerName;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
