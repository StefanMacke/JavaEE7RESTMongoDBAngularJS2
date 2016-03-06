package it.macke.ee7.minimal.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Attendee
{
	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "attendees")
	private Set<Talk> talks;

	protected Attendee()
	{}

	public Attendee(final String firstName, final String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId()
	{
		return id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}
}
