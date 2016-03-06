package it.macke.ee7.minimal.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Attendee
{
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private String firstName;
	private String lastName;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "attendees")
	private final Set<Talk> talks = new HashSet<>();

	protected Attendee()
	{}

	public Attendee(final String firstName, final String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId()
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

	public void attend(final Talk t)
	{
		if (!talks.contains(t))
		{
			talks.add(t);
			t.isWatchedBy(this);
		}
	}

	public Iterable<String> getTalkIds()
	{
		return talks.stream().map(Talk::getId).collect(Collectors.toList());
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (firstName == null ? 0 : firstName.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		result = prime * result + (lastName == null ? 0 : lastName.hashCode());
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
		final Attendee other = (Attendee) obj;
		if (firstName == null)
		{
			if (other.firstName != null)
			{
				return false;
			}
		}
		else
			if (!firstName.equals(other.firstName))
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
		if (lastName == null)
		{
			if (other.lastName != null)
			{
				return false;
			}
		}
		else
			if (!lastName.equals(other.lastName))
			{
				return false;
			}
		return true;
	}
}
