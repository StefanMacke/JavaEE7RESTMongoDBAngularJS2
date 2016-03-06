package it.macke.ee7.minimal.persistence;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
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
				.createQuery("SELECT a FROM Attendee a ORDER BY a.name", Attendee.class)
				.getResultList();
	}
}
