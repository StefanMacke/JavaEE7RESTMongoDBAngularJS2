package it.macke.ee7.minimal.persistence;

import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import it.macke.ee7.minimal.domain.Talk;

@RequestScoped
public class TalkRepository
{
	@PersistenceContext
	private EntityManager em;

	public Iterable<Talk> findAll()
	{
		return em
				.createQuery("SELECT t FROM Talk t ORDER BY t.title", Talk.class)
				.getResultList();
	}

	public Optional<Talk> find(final String id)
	{
		Talk t = null;
		try
		{
			t = em.find(Talk.class, id);
		}
		catch (final NoResultException e)
		{}
		return Optional.ofNullable(t);
	}
}
