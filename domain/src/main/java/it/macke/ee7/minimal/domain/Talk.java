package it.macke.ee7.minimal.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Talk
{
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "Conference",
			joinColumns = @JoinColumn(name = "talk_id", referencedColumnName = "id") ,
			inverseJoinColumns = @JoinColumn(name = "attendee_id", referencedColumnName = "id") )
	private Set<Attendee> attendees;

	protected Talk()
	{}

	public Talk(final String title, final String desciption)
	{
		this.title = title;
		this.description = desciption;
	}

	public long getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getDescription()
	{
		return description;
	}
}
