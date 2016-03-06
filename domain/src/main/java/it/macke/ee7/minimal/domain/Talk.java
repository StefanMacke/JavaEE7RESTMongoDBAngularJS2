package it.macke.ee7.minimal.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Talk
{
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
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

	public String getId()
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
