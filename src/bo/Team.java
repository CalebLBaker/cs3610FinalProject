package bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "player")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer teamId;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="id.")
	@Fetch(FetchMode.JOIN)
	Set<TeamSeasonPlayer> rosters = new HashSet<TeamSeasonPlayer>();

	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="id.team")
	@Fetch(FetchMode.JOIN)
	Set<TeamSeason> seasons = new HashSet<TeamSeason>();
	
	@Column
	String name;
	@Column
	String league;
	@Column
	Integer yearFounded;
	@Column
	Integer yearLast;

	// utility function
	public TeamSeason getTeamSeason(Integer year) {
		for (TeamSeason ps : seasons) {
			if (ps.getYear().equals(year)) return ps;
		}
		return null;
	}
	
	public void addRoster(TeamSeasonPlayer p) {
		rosters.add(p);
	}

	public Set<TeamSeasonPlayer> getRosters() {
		return rosters;
	}

	public void setRosters(Set<TeamSeasonPlayer> positions) {
		this.rosters = positions;
	}

	public void addSeason(TeamSeason s) {
		seasons.add(s);
	}

	public Set<TeamSeason> getSeasons() {
		return seasons;
	}
	
	public void setSeasons(Set<TeamSeason> seasons) {
		this.seasons = seasons;
	}
	
	public Integer getId() {
		return playerId;
	}
	public void setId(Integer id) {
		this.playerId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String nickName) {
		this.givenName = nickName;
	}

	public String getBattingHand() {
		return battingHand;
	}

	public void setBattingHand(String battingHand) {
		this.battingHand = battingHand;
	}

	public String getThrowingHand() {
		return throwingHand;
	}

	public void setThrowingHand(String throwingHand) {
		this.throwingHand = throwingHand;
	}

	public String getBirthCity() {
		return birthCity;
	}

	public void setBirthCity(String birthCity) {
		this.birthCity = birthCity;
	}

	public String getBirthState() {
		return birthState;
	}

	public void setBirthState(String birthState) {
		this.birthState = birthState;
	}
  
  public String getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public Date getFirstGame() {
		return firstGame;
	}

	public void setFirstGame(Date firstGame) {
		this.firstGame = firstGame;
	}

	public Date getLastGame() {
		return lastGame;
	}

	public void setLastGame(Date lastGame) {
		this.lastGame = lastGame;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Date getDeathDay() {
		return deathDay;
	}

	public void setDeathDay(Date deathDay) {
		this.deathDay = deathDay;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Player)){
			return false;
		}
		Player other = (Player) obj;
		return (this.getName().equalsIgnoreCase(other.getName()) &&
				this.getBirthDay()==other.getBirthDay() &&
				this.getDeathDay()==other.getDeathDay());
	}
	 
	@Override
	public int hashCode() {
		Integer hash = 0;
		if (this.getName()!=null) hash += this.getName().hashCode(); 
		if (this.getBirthDay()!=null) hash += this.getBirthDay().hashCode();
		if (this.getDeathDay()!=null) hash += this.getDeathDay().hashCode();
		return hash;
	}
	
	
}
