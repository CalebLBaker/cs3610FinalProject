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

@Entity(name = "team")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer teamId;
	
	
//	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="id.")
//	@Fetch(FetchMode.JOIN)
//	Set<TeamSeasonPlayer> rosters = new HashSet<TeamSeasonPlayer>();

	
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
	
//	public void addRoster(TeamSeasonPlayer p) {
//		rosters.add(p);
//	}
//
//	public Set<TeamSeasonPlayer> getRosters() {
//		return rosters;
//	}
//
//	public void setRosters(Set<TeamSeasonPlayer> positions) {
//		this.rosters = positions;
//	}

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
		return teamId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getLeague() {
		return league;
	}
	
	public void setLeague(String l) {
		league = l;
	}
	
	public Integer getFounded() {
		return yearFounded;
	}
	
	public void setFounded(Integer f) {
		yearFounded = f;
	}
	
	public Integer getLast() {
		return yearLast;
	}
	
	public void setLast(Integer l) {
		yearLast = l;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Player)){
			return false;
		}
		Player other = (Player) obj;
		return (this.getId()==other.getId());
	}
	 
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	
	
}
