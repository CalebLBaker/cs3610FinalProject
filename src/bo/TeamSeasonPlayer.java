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
public class TeamSeasonPlayer implements Serializable {

	@EmbeddedId
	TeamSeasonPlayerId id;

	@Embeddable
	static class TeamSeasonPlayerId implements Serializable {
		@ManyToOne
		@JoinColumn(name = "teamid", referencedColumnName = "teamid", insertable = false, updatable = false)
		Team team;
		@JoinColumn(name = "playerid", referencedColumnName = "playerid", insertable = false, updatable = false)
		Player player;
		@Column(name="year")
		Integer teamPlayerYear;
		@Override
		public boolean equals(Object obj) {
			if(!(obj instanceof TeamSeasonPlayerId)){
				return false;
			}
			TeamSeasonPlayerId other = (TeamSeasonPlayerId)obj;
			// in order for two different object of this type to be equal,
			// they must be for the same year and for the same player
			return (this.team==other.team &&
					this.teamPlayerYear==other.teamPlayerYear &&
					this.player==other.player);
		}
		 
		@Override
		public int hashCode() {
			Integer hash = 0;
			if (this.team != null) hash += this.team.hashCode();
			if (this.teamPlayerYear != null) hash += this.teamPlayerYear.hashCode();
			if (this.player != null) hash += this.player.hashCode();
			return hash;
		}
	}

	
	// Hibernate needs a default constructor
	public TeamSeasonPlayer() {}
	
	public TeamSeasonPlayer(Team t, Integer year, Player p) {
		TeamSeasonPlayerId tsi = new TeamSeasonPlayerId();
		tsi.team = t;
		tsi.teamPlayerYear = year;
		tsi.player = p;
		this.id = tsi;
	}
	
	public void setYear(Integer year) {
		this.id.teamPlayerYear = year;
	}

	public Integer getYear() {
		return this.id.teamPlayerYear;
	}

	public Team getTeam() {
		return this.id.team;
	}

	public void setTeam(Team team) {
		this.id.team = team;
	}

	public TeamSeasonPlayerId getId() {
		return this.id;
	}

	public Player getPlayer() {
		return this.id.player;
	}
	
	public void setPlayer(Player p) {
		this.id.player = p;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof TeamSeasonPlayer)){
			return false;
		}
		TeamSeasonPlayer other = (TeamSeasonPlayer)obj;
		return other.getId().equals(this.getId());
	}
	 
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	public static Comparator<TeamSeasonPlayer> teamSeasonPlayerComparator = new Comparator<TeamSeasonPlayer>() {

		public int compare(TeamSeasonPlayer ts1, TeamSeasonPlayer ts2) {
			Integer year1 = ts1.getYear();
			Integer year2 = ts2.getYear();
			return year1.compareTo(year2);
		}

	};
	
}
