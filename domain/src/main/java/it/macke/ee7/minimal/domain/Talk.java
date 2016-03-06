package it.macke.ee7.minimal.domain;

import java.util.HashSet;
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "Conference",
			joinColumns = @JoinColumn(name = "talk_id", referencedColumnName = "id") ,
			inverseJoinColumns = @JoinColumn(name = "attendee_id", referencedColumnName = "id") )
	private final Set<Attendee> attendees = new HashSet<>();

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

	public void isWatchedBy(final Attendee a)
	{
		if (!attendees.contains(a))
		{
			attendees.add(a);
			a.attend(this);
		}
	}

	public Iterable<Attendee> getAttendees()
	{
		return attendees;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (description == null ? 0 : description.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (title == null ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final Talk other = (Talk) obj;
		if (description == null)
		{
			if (other.description != null)
			{
				return false;
			}
		}
		else
			if (!description.equals(other.description))
			{
				return false;
			}
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		}
		else
			if (!id.equals(other.id))
			{
				return false;
			}
		if (title == null)
		{
			if (other.title != null)
			{
				return false;
			}
		}
		else
			if (!title.equals(other.title))
			{
				return false;
			}
		return true;
	}
}
