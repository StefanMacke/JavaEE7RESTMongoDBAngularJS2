package it.macke.ee7.minimal.persistence;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import it.macke.ee7.minimal.domain.Attendee;

@RequestScoped
public class AttendeeRepository
{
	@PersistenceContext
	private EntityManager em;

	public Iterable<Attendee> findAll()
	{
		return em
				.createQuery("SELECT a FROM Attendee a ORDER BY a.lastName", Attendee.class)
				.getResultList();
	}

	public Optional<Attendee> find(final String id)
	{
		Attendee a = null;
		try
		{
			a = em.find(Attendee.class, id);
		}
		catch (final NoResultException e)
		{}
		return Optional.ofNullable(a);
	}
}
