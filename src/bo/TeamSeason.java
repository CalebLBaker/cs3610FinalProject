package bo;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@SuppressWarnings("serial")
@Entity(name = "teamseason")
public class TeamSeason implements Serializable {

	@EmbeddedId
	TeamSeasonId id;

	@Embeddable
	static class TeamSeasonId implements Serializable {
		@ManyToOne
		@JoinColumn(name = "teamid", referencedColumnName = "teamid", insertable = false, updatable = false)
		Team team;
		@Column(name="year")
		Integer teamYear;
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof TeamSeasonId)){
				return false;
			}
			TeamSeasonId other = (TeamSeasonId)obj;
			// in order for two different object of this type to be equal,
			// they must be for the same year and for the same player
			return (this.team==other.team &&
					this.teamYear==other.teamYear);
		}
		 
		@Override
		public int hashCode() {
			Integer hash = 0;
			if (this.team != null) hash += this.team.hashCode();
			if (this.teamYear != null) hash += this.teamYear.hashCode();
			return hash;
		}
	}

	@Column
	int gamesPlayed;
	@Column
	int wins;
	@Column
	int losses;
	@Column
	int rank;
	@Column 
	int totalAttendance;
	
	// Hibernate needs a default constructor
	public TeamSeason() {}
	
	public TeamSeason(Team t, Integer year) {
		TeamSeasonId tsi = new TeamSeasonId();
		tsi.team = t;
		tsi.teamYear = year;
		this.id = tsi;
	}
	
	// derived stats
	public Integer getnumTies() {
		Integer numTies = 0;
		numTies = gamesPlayed - wins - losses;
		return numTies;
	}

	public void setYear(Integer year) {
		this.id.teamYear = year;
	}

	public Integer getYear() {
		return this.id.teamYear;
	}

	public Team getTeam() {
		return this.id.team;
	}

	public void setTeam(Team team) {
		this.id.team = team;
	}

	public TeamSeasonId getId() {
		return this.id;
	}

	public Integer getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public int getLosses() {
		return losses;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TeamSeason)){
			return false;
		}
		TeamSeason other = (TeamSeason)obj;
		return other.getId().equals(this.getId());
	}
	 
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	public static Comparator<TeamSeason> teamSeasonsComparator = new Comparator<TeamSeason>() {

		public int compare(TeamSeason ts1, TeamSeason ts2) {
			Integer year1 = ts1.getYear();
			Integer year2 = ts2.getYear();
			return year1.compareTo(year2);
		}

	};
	
}
